package com.org.bard.RecruitingAppDB.dao;

import com.org.bard.RecruitingAppDB.connection.dbConnection;
import com.org.bard.RecruitingAppDB.entities.Player;
import com.org.bard.RecruitingAppDB.utilities.ResultSetToPlayerArrayList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class WatchlistDAO {

    private static final Logger log = Logger.getLogger( dbConnection.class.getName() );

    private dbConnection dbConn;

    Connection conn;
    PreparedStatement stmt = null;

    public WatchlistDAO() {
        dbConn = new dbConnection();
        conn = null;
    }

    public ArrayList<Long> getWatchlistPGids(long uid) {
        ArrayList<Long> pgids = new ArrayList<>();
        try {
            conn = dbConn.getConnection();
            String sql = "SELECT * FROM " + WatchlistTable(uid);
            stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                pgids.add(rs.getLong("pgid"));
            }
        } catch (SQLException se) {
            se.printStackTrace();
        }
        return pgids;
    }

    public ArrayList<Player> getWatchlist(long uid) {
        ArrayList<Player> players = new ArrayList<>();
        try {
            conn = dbConn.getConnection();
            String sql = "SELECT * FROM Players INNER JOIN " + WatchlistTable(uid)
                    + "ON Players.pgid=" + WatchlistTable(uid) + ".pgid";
            stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            ResultSetToPlayerArrayList.convert(rs, players);
        } catch (SQLException se) {
            log.log(Level.SEVERE, "Error retriving user's watchlist", se);
            dbConnection.closeQuietly(conn);
            return null;
        }
        return players;
    }

    public String addToWatchlist(long uid, Long... pgids) {
        try {
            conn = dbConn.getConnection();
            String sql = "INSERT INTO " + WatchlistTable(uid) + " (pgid)"
                    + " values (?)";
            stmt = conn.prepareStatement(sql);
            for (long pgid: pgids) {
                stmt.setLong(1, pgid);
                stmt.executeUpdate();
            }
        } catch (SQLException se) {
            String msg = "An error occured adding the player to the watchlist";
            log.log(Level.SEVERE, msg, se);
            return msg;
        } finally {
            dbConnection.closeQuietly(conn);
            return "Successfully added the player to the watchlist";
        }
    }

    public String removeFromWatchlist(long uid, long... pgids) {
        try {
            conn = dbConn.getConnection();
            String sql = "DELETE FROM " + WatchlistTable(uid) + " WHERE pgid = ?";
            stmt = conn.prepareStatement(sql);
            for (long pgid: pgids) {
                stmt.setLong(1, pgid);
                stmt.executeUpdate();
            }
        } catch (SQLException se) {
            String msg = "An error occured removing the player from the watchlist";
            log.log(Level.SEVERE, msg, se);
            return msg;
        } finally {
            dbConnection.closeQuietly(conn);
            return "Successfully removed the player from the watchlist";
        }
    }

    public String clearWatchlist(long uid) {
        try {
            conn = dbConn.getConnection();
            String sql = "DELETE FROM " + WatchlistTable(uid);
            stmt = conn.prepareStatement(sql);
            stmt.executeUpdate();
        } catch (SQLException se) {
            String msg = "An error occured clearing";
            log.log(Level.SEVERE, msg, se);
            return msg;
        } finally {
            dbConnection.closeQuietly(conn);
            return "Successfully removed the player from the watchlist";
        }
    }

    private String WatchlistTable(long uid) {
        String tablename = "Watchlist" + uid;
        return tablename;
    }
}
