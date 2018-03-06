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

public class PlayerDAO {

    private static final Logger log = Logger.getLogger( dbConnection.class.getName() );

    private dbConnection dbConn;

    Connection conn;
    PreparedStatement stmt = null;

    public PlayerDAO() {
        dbConn = new dbConnection();
        conn = null;
    }

    public String createTable() {
        try {
            conn = dbConn.getConnection();

            String sql = "CREATE TABLE Players " +
                    "(pgid BIGINT not NULL, " +
                    " name VARCHAR(255), " +
                    " year SMALLINT, " +
                    " pos1 VARCHAR(255), " +
                    " pos2 VARCHAR(255), " +
                    " age SMALLINT, " +
                    " height FLOAT, " +
                    " weight FLOAT, " +
                    " bt VARCHAR(255), " +
                    " hs VARCHAR(255), " +
                    " town VARCHAR(255), " +
                    " team_summer VARCHAR(255), " +
                    " team_fall VARCHAR(255), " +
                    " PRIMARY KEY ( pgid ))";
            stmt = conn.prepareStatement(sql);
            stmt.execute();
        } catch (SQLException se) {
            String msg = "An error occurred creating the player table";
            log.log(Level.SEVERE, msg, se);
            dbConnection.closeQuietly(conn);
            return msg;
        } finally {
            dbConnection.closeQuietly(conn);
            return "Successfully created player table";
        }
    }

    public String addPlayer(Player... players) {
        try {
            conn = dbConn.getConnection();
            String sql = "INSERT INTO Players (pgid, name, year, pos1, pos2, " +
                    "age, height, weight, bt, hs, town, team_summer, team_fall)"
                    + " values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            stmt = conn.prepareStatement(sql);
        } catch (SQLException se) {
            String msg = "An error occurred preparing the statement";
            log.log(Level.SEVERE, msg, se);
            dbConnection.closeQuietly(conn);
            return msg;
        }
        for(Player player: players) {
            try {
                stmt.setLong(1, player.pgid);
                stmt.setString(2, player.name);
                stmt.setShort(3, player.year);
                stmt.setString(4, player.pos1);
                stmt.setString(5, player.pos2);
                stmt.setShort(6, player.age);
                stmt.setFloat(7, player.height);
                stmt.setFloat(8, player.weight);
                stmt.setString(9, player.bt);
                stmt.setString(10, player.hs);
                stmt.setString(11, player.town);
                stmt.setString(12, player.team_summer);
                stmt.setString(13, player.team_fall);
                stmt.executeUpdate();
            } catch (SQLException se) {
                String msg = "An error occurred adding " + player.name;
                log.log(Level.SEVERE, msg, se);
                dbConnection.closeQuietly(conn);
                return msg;
            }
        }
        dbConnection.closeQuietly(conn);
        return "Successfully added all players";
    }

    public ArrayList<Player> getPlayerByPGID(long... pgids) {
        ArrayList<Player> players = new ArrayList<>();
        try {
            conn = dbConn.getConnection();
            String sql = "SELECT * FROM Players WHERE pgid = ?";
            stmt = conn.prepareStatement(sql);
            for (long pgid : pgids) {
                stmt.setLong(1, pgid);
                ResultSet rs = stmt.executeQuery();
                ResultSetToPlayerArrayList.convert(rs, players);
            }
        } catch (SQLException se) {
            log.log(Level.SEVERE, "Error retriving player", se);
            dbConnection.closeQuietly(conn);
            return null;
        }
        return players;
    }

    public String removePlayer(Player... players) {
        try {
            conn = dbConn.getConnection();
            String sql = "DELETE FROM Players WHERE pgid = ?";
            stmt = conn.prepareStatement(sql);
        } catch (SQLException se) {
            String msg = "An error occurred preparing the statement";
            log.log(Level.SEVERE, msg, se);
            dbConnection.closeQuietly(conn);
            return msg;
        }
        for(Player player: players) {
            try {
                stmt.setLong(1, player.pgid);
                stmt.executeUpdate();
            } catch (SQLException se) {
                String msg = "An error occurred removing " + player.name;
                log.log(Level.SEVERE, msg, se);
                dbConnection.closeQuietly(conn);
                return msg;
            }
        }
        dbConnection.closeQuietly(conn);
        return "Successfully removed all specified players";
    }
}
