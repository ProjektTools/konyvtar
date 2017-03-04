/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.project_tools.helpers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ildi
 */
public class DatabaseHelper {

    private static final String postgreDbHost = "localhost";
    private static final Integer postgreDbPort = 5432;
    private static final String postgreDbName = "project_tools";
    private static final String postgreDbUser = "postgres";
    private static final String postgreDbPass = "1234";

    private static Connection getConnection() throws SQLException, ClassNotFoundException {
        Class.forName("org.postgresql.Driver");
        Connection c = DriverManager.getConnection(
                "jdbc:postgresql://" + postgreDbHost + ":" + postgreDbPort + "/" + postgreDbName,
                postgreDbUser, postgreDbPass
        );
        return c;
    }

    public static String toMd5(String input) {
        String salt = "TSM";
        input += salt;
        try {
            java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
            byte[] array = md.digest(input.getBytes());
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < array.length; ++i) {
                sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1, 3));
            }
            return sb.toString();
        } catch (java.security.NoSuchAlgorithmException e) {
        }
        return null;
    }

    public static void insertUser(String name, String email, String passw) throws SQLException {
        if (name != null && email != null && passw != null) {
            Connection conn = null;
            PreparedStatement stmt = null;

            try {
                conn = getConnection();
                String sql = "INSERT INTO users(\n"
                        + "   username, passw, email)\n"
                        + "    VALUES (?, ?, ?);";
                stmt = conn.prepareStatement(sql);
                stmt.setString(1, name);
                stmt.setString(2, toMd5(passw));
                stmt.setString(3, email);

                stmt.execute();
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(DatabaseHelper.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                try {
                    if (conn != null) {
                        conn.close();
                    }
                    if (stmt != null) {
                        stmt.close();
                    }
                } catch (Exception e) {
                    Logger.getLogger(DatabaseHelper.class.getName()).log(Level.SEVERE, null, e);
                }
            }
        }
    }

    public static boolean validateUserLogin(String username, String password) {
        boolean ret = false;

        if (username == null || password == null) {
            return ret;
        } else {
            Connection conn = null;
            PreparedStatement stmt = null;
            ResultSet rs = null;
            try {
                conn = getConnection();
                String sql = "SELECT passw\n"
                        + "  FROM users WHERE username=?;";
                stmt = conn.prepareStatement(sql);
                stmt.setString(1, username);
                rs = stmt.executeQuery();
                if (rs.isBeforeFirst()) {
                    while (rs.next()) {
                        String dbPassword = rs.getString(1);
                        if (toMd5(password).equals(dbPassword)) {
                            ret = true;
                        }
                    }
                }

            } catch (SQLException ex) {
                Logger.getLogger(DatabaseHelper.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(DatabaseHelper.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                try {
                    if (conn != null) {
                        conn.close();
                    }
                    if (stmt != null) {
                        stmt.close();
                    }
                    if (rs != null) {
                        rs.close();
                    }
                } catch (Exception e) {
                    Logger.getLogger(DatabaseHelper.class.getName()).log(Level.SEVERE, null, e);
                }
            }
        }
        return ret;
    }

    public static void main(String[] args) throws SQLException {
        insertUser("ildi", "ildi@ildi.il", "ildi");
    }
    
}
