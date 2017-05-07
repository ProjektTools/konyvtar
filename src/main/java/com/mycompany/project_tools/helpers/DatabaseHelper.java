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
import org.json.JSONArray;
import org.json.JSONObject;

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

    public static JSONArray getCategories() throws ClassNotFoundException {
        JSONArray res = new JSONArray();

        Connection postgreConnection = null;
        PreparedStatement postgreStmt = null;
        ResultSet rs = null;
        try {
            postgreConnection = getConnection();
            String postgreSql = "SELECT id, name FROM categories;";
            postgreStmt = postgreConnection.prepareStatement(postgreSql);

            rs = postgreStmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");

                JSONObject sor = new JSONObject();
                sor.put("id", id);
                sor.put("name", name);
                System.out.println(sor);
                res.put(sor);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseHelper.class.getName()).log(Level.SEVERE, null, ex);
            res.put("A szerver nem elérhető.");
        } finally {
            try {
                if (postgreConnection != null) {
                    postgreConnection.close();
                }
                if (postgreStmt != null) {
                    postgreStmt.close();
                }
            } catch (Exception ex) {
                Logger.getLogger(DatabaseHelper.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return res;
    }

    public static JSONArray getBooks() throws ClassNotFoundException {
        JSONArray res = new JSONArray();

        Connection postgreConnection = null;
        PreparedStatement postgreStmt = null;
        ResultSet rs = null;
        try {
            postgreConnection = getConnection();
            String postgreSql = "SELECT id, title, author, description, year, count, publisher, category_id FROM public.books;";
            postgreStmt = postgreConnection.prepareStatement(postgreSql);

            rs = postgreStmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String title = rs.getString("title");
                String author = rs.getString("author");
                String description = rs.getString("description");
                int year = rs.getInt("year");
                int count = rs.getInt("count");
                String publisher = rs.getString("publisher");
                int category_id = rs.getInt("category_id");

                JSONObject sor = new JSONObject();
                sor.put("id", id);
                sor.put("title", title);
                sor.put("author", author);
                sor.put("description", description);
                sor.put("year", year);
                sor.put("count", count);
                sor.put("publisher", publisher);
                sor.put("category_id", category_id);
                System.out.println(sor);
                res.put(sor);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseHelper.class.getName()).log(Level.SEVERE, null, ex);
            res.put("A szerver nem elérhető.");
        } finally {
            try {
                if (postgreConnection != null) {
                    postgreConnection.close();
                }
                if (postgreStmt != null) {
                    postgreStmt.close();
                }
            } catch (Exception ex) {
                Logger.getLogger(DatabaseHelper.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return res;
    }

    public static void main(String[] args) throws SQLException {
        //insertUser("ildi", "ildi@ildi.il", "ildi");
        insertUser("admin", "admin@admin.com", "admin");
    }

    public static JSONArray getBookDetails(String id) throws ClassNotFoundException {
        JSONArray res = new JSONArray();
        System.out.println("keressük a megadott könyvet");
        Connection postgreConnection = null;
        PreparedStatement postgreStmt = null;
        ResultSet rs = null;
        try {
            postgreConnection = getConnection();
            String postgreSql = "SELECT * from public.books where id=" + id + ";";
            postgreStmt = postgreConnection.prepareStatement(postgreSql);

            rs = postgreStmt.executeQuery();
            while (rs.next()) {
                String title = rs.getString("title");
                String author = rs.getString("author");
                String description = rs.getString("description");
                int year = rs.getInt("year");
                int count = rs.getInt("count");
                String publisher = rs.getString("publisher");
                int category_id = rs.getInt("category_id");

                JSONObject sor = new JSONObject();
                sor.put("title", title);
                sor.put("author", author);
                sor.put("description", description);
                sor.put("year", year);
                sor.put("count", count);
                sor.put("publisher", publisher);
                sor.put("category_id", category_id);
                System.out.println(sor);
                res.put(sor);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseHelper.class.getName()).log(Level.SEVERE, null, ex);
            res.put("A szerver nem elérhető.");
        } finally {
            try {
                if (postgreConnection != null) {
                    postgreConnection.close();
                }
                if (postgreStmt != null) {
                    postgreStmt.close();
                }
            } catch (Exception ex) {
                Logger.getLogger(DatabaseHelper.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return res;
    }

    public static boolean olvasta(String username, String id) throws ClassNotFoundException {
        boolean result = false;

        System.out.println("megnézzük hogy olvasta-e");
        Connection postgreConnection = null;
        PreparedStatement postgreStmt = null;
        ResultSet rs = null;
        try {
            postgreConnection = getConnection();
            String postgreSql = "SELECT * from public.read where bookid=" + id + " and username='" + username + "';";
            postgreStmt = postgreConnection.prepareStatement(postgreSql);

            rs = postgreStmt.executeQuery();
            int i = 0;
            while (rs.next()) {
                i++;
            }
            if (i == 0) {
                result = false;
            } else {
                result = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseHelper.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (postgreConnection != null) {
                    postgreConnection.close();
                }
                if (postgreStmt != null) {
                    postgreStmt.close();
                }
            } catch (Exception ex) {
                Logger.getLogger(DatabaseHelper.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return result;
    }

    public static void insertToRead(String id, String username) throws ClassNotFoundException {
        Connection postgreConnection = null;
        PreparedStatement postgreStmt = null;
        ResultSet rs = null;
        try {
            postgreConnection = getConnection();
            String postgreSql = "insert into public.read(username, bookid) values('" + username + "'," + id + ");";
            postgreStmt = postgreConnection.prepareStatement(postgreSql);

            rs = postgreStmt.executeQuery();

            System.out.println("adatok beszúrva");
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseHelper.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (postgreConnection != null) {
                    postgreConnection.close();
                }
                if (postgreStmt != null) {
                    postgreStmt.close();
                }
            } catch (Exception ex) {
                Logger.getLogger(DatabaseHelper.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public static int getMaxRead() throws ClassNotFoundException {
        int id = 0;
        Connection postgreConnection = null;
        PreparedStatement postgreStmt = null;
        ResultSet rs = null;
        try {
            postgreConnection = getConnection();
            String postgreSql = "SELECT bookid FROM public.read group by bookid order by count(bookid) desc limit 1;";
            postgreStmt = postgreConnection.prepareStatement(postgreSql);

            rs = postgreStmt.executeQuery();
            while (rs.next()) {
                id = rs.getInt("bookid");
            }
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseHelper.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (postgreConnection != null) {
                    postgreConnection.close();
                }
                if (postgreStmt != null) {
                    postgreStmt.close();
                }
            } catch (Exception ex) {
                Logger.getLogger(DatabaseHelper.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        System.out.println("legolvasottabb: " + id);
        return id;
    }

    public static String getTitle(int id) throws ClassNotFoundException {
        String title = "";
        Connection postgreConnection = null;
        PreparedStatement postgreStmt = null;
        ResultSet rs = null;
        try {
            postgreConnection = getConnection();
            String postgreSql = "SELECT title from public.books where id=" + id + ";";
            postgreStmt = postgreConnection.prepareStatement(postgreSql);

            rs = postgreStmt.executeQuery();
            while (rs.next()) {
                title = rs.getString("title");
            }
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseHelper.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (postgreConnection != null) {
                    postgreConnection.close();
                }
                if (postgreStmt != null) {
                    postgreStmt.close();
                }
            } catch (Exception ex) {
                Logger.getLogger(DatabaseHelper.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        System.out.println("legolvasottabb könyv címe: " + title);
        return title;
    }

    public static JSONArray search(String title, String author, int category_id) throws ClassNotFoundException {
        System.out.println("Könyveket keresünk feltételek szerint");
        String postgreSql = "";
        //cím szerint
        if (author == "" && category_id == 0 && title != "") {
            postgreSql = "SELECT id, title, author, description, year, count, publisher, category_id FROM public.books where title LIKE '%" + title + "%';";
        }
        //szerző szerint
        if (author != "" && category_id == 0 && title == "") {
            postgreSql = "SELECT id, title, author, description, year, count, publisher, category_id FROM public.books where author LIKE '%" + author + "%';";
        }
        //kategória szerint
        if (author == "" && category_id != 0 && title == "") {
            postgreSql = "SELECT id, title, author, description, year, count, publisher, category_id FROM public.books where category_id=" + category_id + ";";
        }
        //cím és kategória
        if (author == "" && category_id != 0 && title != "") {
            postgreSql = "SELECT id, title, author, description, year, count, publisher, category_id FROM public.books where title LIKE '%" + title + "%' and category_id=" + category_id + ";";
        }
        //cím és szerző
        if (author != "" && category_id == 0 && title != "") {
            postgreSql = "SELECT id, title, author, description, year, count, publisher, category_id FROM public.books where title LIKE '%" + title + "%' and author LIKE '%" + author + "%';";
        }
        //szerző és kategória
        if (author != "" && category_id != 0 && title == "") {
            postgreSql = "SELECT id, title, author, description, year, count, publisher, category_id FROM public.books where author LIKE '%" + author + "%'and category_id=" + category_id + ";";
        }
        //cím szerző és kategória
        if (author != "" && category_id != 0 && title != "") {
            postgreSql = "SELECT id, title, author, description, year, count, publisher, category_id FROM public.books where title LIKE '%" + title + "%' and  author LIKE '%" + author + "%'and category_id=" + category_id + ";";
        }
        System.out.println(postgreSql);
        JSONArray res = new JSONArray();

        Connection postgreConnection = null;
        PreparedStatement postgreStmt = null;
        ResultSet rs = null;
        try {
            postgreConnection = getConnection();
            postgreStmt = postgreConnection.prepareStatement(postgreSql);

            rs = postgreStmt.executeQuery();
            
            while (rs.next()) {
                int id = rs.getInt("id");
                String title2 = rs.getString("title");
                String author2 = rs.getString("author");
                String description = rs.getString("description");
                int year = rs.getInt("year");
                int count = rs.getInt("count");
                String publisher = rs.getString("publisher");
                int category_id2 = rs.getInt("category_id");
                JSONObject sor = new JSONObject();
                sor.put("id", id);
                sor.put("title", title2);
                sor.put("author", author2);
                sor.put("description", description);
                sor.put("year", year);
                sor.put("count", count);
                sor.put("publisher", publisher);
                sor.put("category_id", category_id2);
                res.put(sor);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseHelper.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (postgreConnection != null) {
                    postgreConnection.close();
                }
                if (postgreStmt != null) {
                    postgreStmt.close();
                }
            } catch (Exception ex) {
                Logger.getLogger(DatabaseHelper.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return res;
    }
}
