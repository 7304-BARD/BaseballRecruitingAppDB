package com.org.bard.RecruitingAppDB.dao;

import com.org.bard.RecruitingAppDB.connection.dbConnection;
import com.org.bard.RecruitingAppDB.entities.Account;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AccountDAO {

    private static final Logger log = Logger.getLogger( dbConnection.class.getName() );

    private dbConnection dbConn;

    Connection conn = null;
    PreparedStatement stmt = null;

    public AccountDAO() {
        dbConn = new dbConnection();
        conn = null;
    }

    public String createTable() {
        try {
            conn = dbConn.getConnection();

            String sql = "CREATE TABLE Accounts " +
                    "(uid BIGINT not NULL, " +
                    " username VARCHAR(255), " +
                    " password VARCHAR(255), " +
                    " organization VARCHAR(255), " +
                    " PRIMARY KEY ( uid ))";
            stmt = conn.prepareStatement(sql);
            stmt.execute();
        } catch (SQLException se) {
            String msg = "An error occurred creating the account table";
            log.log(Level.SEVERE, msg, se);
            dbConnection.closeQuietly(conn);
            return msg;
        } finally {
            dbConnection.closeQuietly(conn);
            return "Successfully created account table";
        }
    }

    public String addAccount(Account account) {
        conn = dbConn.getConnection();
        String sql = "INSERT INTO Accounts (uid, username, password, organization)"
                + " values (?, ?, ?, ?)";
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setLong(1, account.uid);
            stmt.setString(2, account.username);
            stmt.setString(3, account.password);
            stmt.setString(4, account.organization);
            stmt.executeUpdate();
        } catch (SQLException se) {
            String msg = "An error occurred registering the account";
            log.log(Level.SEVERE, msg, se);
            dbConnection.closeQuietly(conn);
            return msg;
        }

        try {
            sql = "CREATE TABLE Watchlist" + account.uid +
                    " (pgid BIGINT not NULL, " +
                    " PRIMARY KEY ( pgid ))";
            stmt = conn.prepareStatement(sql);
            stmt.execute();
        } catch (SQLException se) {
            String msg = "An error occurred creating the watchlist table";
            log.log(Level.SEVERE, msg, se);
            dbConnection.closeQuietly(conn);
            return msg;
        } finally {
            dbConnection.closeQuietly(conn);
            return "Successfully registered";
        }
    }

//    public Account getAccount() {
//
//    }

}
