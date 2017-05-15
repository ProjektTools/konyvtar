package com.mycompany.project_tools.helpers;

import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Calendar;
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
            StringBuilder sb = new StringBuilder();
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
                } catch (SQLException e) {
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

            } catch (SQLException | ClassNotFoundException ex) {
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
                } catch (SQLException e) {
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
            String postgreSql = "SELECT id, name FROM categories ORDER BY id;";
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
            } catch (SQLException ex) {
                Logger.getLogger(DatabaseHelper.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return res;
    }

    public static void main(String[] args) throws SQLException {
        //insertUser("ildi", "ildi@ildi.il", "ildi");
        //insertUser("admin", "admin@admin.com", "admin");
        //System.out.println(LocalDateTime.now().getYear()+"-"+LocalDateTime.now().getMonthValue()+"-"+LocalDateTime.now().getDayOfMonth());
        //System.out.println(LocalDateTime.now().getYear()+"-"+(LocalDateTime.now().getMonthValue()+1)+"-"+LocalDateTime.now().getDayOfMonth());
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
            } catch (SQLException ex) {
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
            } catch (SQLException ex) {
                Logger.getLogger(DatabaseHelper.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return result;
    }

    public static boolean ertekelte(String username, String id) throws ClassNotFoundException {
        boolean result = false;

        System.out.println("megnézzük hogy értékelte-e");
        Connection postgreConnection = null;
        PreparedStatement postgreStmt = null;
        ResultSet rs = null;
        try {
            postgreConnection = getConnection();
            String postgreSql = "SELECT * from public.rating where bookid=" + id + " and username='" + username + "';";
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
            } catch (SQLException ex) {
                Logger.getLogger(DatabaseHelper.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return result;
    }

    public static boolean kolcsonzi(String username, String id) throws ClassNotFoundException {
        boolean result = false;

        System.out.println("megnézzük hogy kölcsönzi-e");
        Connection postgreConnection = null;
        PreparedStatement postgreStmt = null;
        ResultSet rs = null;
        try {
            postgreConnection = getConnection();
            String postgreSql = "SELECT * from public.borrows where bookid=" + id + " and username='" + username + "';";
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

    public static void insertToRate(String id, String username, int point) throws ClassNotFoundException {
        Connection postgreConnection = null;
        PreparedStatement postgreStmt = null;
        ResultSet rs = null;
        try {
            postgreConnection = getConnection();
            String postgreSql = "insert into public.rating(username, bookid, point) values('" + username + "'," + id + "," + point + ");";
            postgreStmt = postgreConnection.prepareStatement(postgreSql);

            rs = postgreStmt.executeQuery();
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
            } catch (SQLException ex) {
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

    public static int getMaxPoint() {
        int id = 0;
        Connection postgreConnection = null;
        PreparedStatement postgreStmt = null;
        ResultSet rs = null;
        try {
            postgreConnection = getConnection();
            String postgreSql = "SELECT avg(point), bookid FROM public.rating group by bookid order by avg(point) desc limit 1;";
            postgreStmt = postgreConnection.prepareStatement(postgreSql);

            rs = postgreStmt.executeQuery();
            while (rs.next()) {
                id = rs.getInt("bookid");
            }
        } catch (Exception ex) {
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
        return id;
    }

    public static int getMinPoint() {
        int id = 0;
        Connection postgreConnection = null;
        PreparedStatement postgreStmt = null;
        ResultSet rs = null;
        try {
            postgreConnection = getConnection();
            String postgreSql = "SELECT avg(point), bookid FROM public.rating group by bookid order by avg(point) limit 1;";
            postgreStmt = postgreConnection.prepareStatement(postgreSql);

            rs = postgreStmt.executeQuery();
            while (rs.next()) {
                id = rs.getInt("bookid");
            }
        } catch (Exception ex) {
            Logger.getLogger(DatabaseHelper.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (postgreConnection != null) {
                    postgreConnection.close();
                }
                if (postgreStmt != null) {
                    postgreStmt.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(DatabaseHelper.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
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

    public static int idkereses() throws ClassNotFoundException {
        int id = 0;
        Connection postgreConnection = null;
        PreparedStatement postgreStmt = null;
        ResultSet rs = null;
        try {
            postgreConnection = getConnection();
            String postgreSql = "SELECT count(id) FROM public.books;";
            postgreStmt = postgreConnection.prepareStatement(postgreSql);

            rs = postgreStmt.executeQuery();
            while (rs.next()) {
                id = rs.getInt("count");
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
        return id + 1;
    }

    public static String konvert(String input) {
        String result = "";
        try {
            byte ptext[] = input.getBytes("ISO_8859_1");
            result = new String(ptext, "UTF-8");
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(DatabaseHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    public static void insertBook(String title, String author, String publisher, String desc, String category, String year) throws SQLException {
        int id;
        System.out.println("Könyvet szúrunk be..");
        if (title != null && author != null && publisher != null && desc != null && category != null && year != null) {

            Connection conn = null;
            PreparedStatement stmt = null;
            int evszam = Integer.parseInt(year);
            int kategoria = Integer.parseInt(category);
            try {
                id = idkereses();
                title = konvert(title);
                author = konvert(author);
                desc = konvert(desc);
                publisher = konvert(publisher);

                conn = getConnection();
                String sql = "INSERT INTO public.books(\n"
                        + "	id, title, author, description, year, count, publisher, category_id)\n"
                        + "	VALUES (" + id + ",'" + title + "','" + author + "','" + desc + "'," + evszam + ", 5, '" + publisher + "'," + kategoria + ");";

                stmt = conn.prepareStatement(sql);
                stmt.execute();
            } catch (Exception ex) {
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

    public static void insertToBorrow(String id, String username) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int id2 = Integer.parseInt(id);

        String kezdo = LocalDateTime.now().getYear() + "-" + LocalDateTime.now().getMonthValue() + "-" + LocalDateTime.now().getDayOfMonth();
        Date d_kezdo = Date.valueOf(kezdo);
        String zaro = LocalDateTime.now().getYear() + "-" + (LocalDateTime.now().getMonthValue() + 1) + "-" + LocalDateTime.now().getDayOfMonth();
        Date d_zaro = Date.valueOf(zaro);
        try {
            conn = getConnection();
            username = konvert(username);
            int kolcs_id = getMaxiKolcs();
            String sql = "INSERT INTO public.borrows(\n"
                    + "	username, bookid, borrowdate, expiredate, renew, status, id)\n"
                    + "	VALUES (?, ?, ?, ?, ?, ?, ?);";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, username);
            stmt.setInt(2, id2);
            stmt.setDate(3, d_kezdo);
            stmt.setDate(4, d_zaro);
            stmt.setInt(5, 0);
            stmt.setString(6, "uj kolcsonzes");
            stmt.setInt(7, kolcs_id);
            stmt.execute();
        } catch (Exception ex) {
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

    public static JSONArray getBorrows(String username) {
        String postgreSql = "";
        //admin -- minden kölcsönzés
        if (username.equals("admin")) {
            postgreSql = "SELECT username, bookid, borrowdate, expiredate, renew, status, id FROM public.borrows order by id;";
        } //nem admin -- saját kölcsönzések
        else {
            postgreSql = "SELECT username, bookid, borrowdate, expiredate, renew, status, id FROM public.borrows where username='" + username + "' order by id;";
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
                int bookid = rs.getInt("bookid");
                String user = rs.getString("username");
                Date borrowdate = rs.getDate("borrowdate");
                Date expiredate = rs.getDate("expiredate");
                int renew = rs.getInt("renew");
                String status = rs.getString("status");
                String title = getTitle(bookid);
                int id = rs.getInt("id");
                JSONObject sor = new JSONObject();
                sor.put("bookid", bookid);
                sor.put("title", title);
                sor.put("user", user);
                sor.put("borrowdate", borrowdate);
                sor.put("expiredate", expiredate);
                sor.put("renew", renew);
                sor.put("status", status);
                sor.put("kolcs_id", id);
                res.put(sor);
            }
        } catch (Exception ex) {
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

    public static JSONArray search(String category_id) {
        System.out.println("Könyveket keresünk feltételek szerint");
        String postgreSql = "SELECT id, title, author, description, year, count, publisher, category_id FROM public.books where category_id=" + category_id + ";";

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
                String category_name = getCategoryName(category_id2);
                JSONObject sor = new JSONObject();
                sor.put("category_name", category_name);
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
        } catch (Exception ex) {
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

    private static String getCategoryName(int id) {
        String title = "";
        Connection postgreConnection = null;
        PreparedStatement postgreStmt = null;
        ResultSet rs = null;
        try {
            postgreConnection = getConnection();
            String postgreSql = "SELECT name from public.categories where id=" + id + ";";
            postgreStmt = postgreConnection.prepareStatement(postgreSql);

            rs = postgreStmt.executeQuery();
            while (rs.next()) {
                title = rs.getString("name");
            }
        } catch (Exception ex) {
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
        return title;
    }

    private static int getMaxiKolcs() {
        int id = 0;
        Connection postgreConnection = null;
        PreparedStatement postgreStmt = null;
        ResultSet rs = null;
        try {
            postgreConnection = getConnection();
            String postgreSql = "SELECT count(id) FROM public.borrows;";
            postgreStmt = postgreConnection.prepareStatement(postgreSql);

            rs = postgreStmt.executeQuery();
            while (rs.next()) {
                id = rs.getInt("count");
            }
        } catch (Exception ex) {
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
        return id + 1;
    }

    public static void deleteBorrow(String id) {
        Connection postgreConnection = null;
        PreparedStatement postgreStmt = null;
        ResultSet rs = null;
        try {
            postgreConnection = getConnection();
            String postgreSql = "Delete FROM public.borrows where id=" + id + ";";
            postgreStmt = postgreConnection.prepareStatement(postgreSql);

            rs = postgreStmt.executeQuery();
        } catch (Exception ex) {
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

    public static void setStatus(int kolcs_id, String input) {
        Connection postgreConnection = null;
        PreparedStatement postgreStmt = null;
        ResultSet rs = null;
        try {
            postgreConnection = getConnection();
            String postgreSql = "update public.borrows set status=\'" + input + "\' where id=" + kolcs_id + ";";
            postgreStmt = postgreConnection.prepareStatement(postgreSql);

            rs = postgreStmt.executeQuery();

        } catch (Exception ex) {
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

    public static void renew(int kolcs_id) {
        String postgreSql = "SELECT username, bookid, borrowdate, expiredate, renew, status, id FROM public.borrows where id =" + kolcs_id + ";";

        Connection postgreConnection = null;
        PreparedStatement postgreStmt = null;
        ResultSet rs = null;
        try {
            postgreConnection = getConnection();
            postgreStmt = postgreConnection.prepareStatement(postgreSql);

            rs = postgreStmt.executeQuery();

            while (rs.next()) {
                Date expiredate = rs.getDate("expiredate");
                int renew = rs.getInt("renew");
                String status = rs.getString("status");

                //3 x 1 hónapos kölcsönzés hosszabbítás engedélyezett
                if (renew < 3) {
                    System.out.println("Régi: "+ expiredate);
                    Calendar myCal = Calendar.getInstance();
                    myCal.setTime(expiredate);
                    myCal.add(Calendar.MONTH, +1);
                    expiredate=  (java.sql.Date) new Date(myCal.getTimeInMillis());
                    System.out.println(expiredate);
                    System.out.println(expiredate.getClass());
                    postgreSql = "update public.borrows set renew="+(renew+1)+", status='hosszabbitott', expiredate='"+(Date) expiredate+"' where id=" + kolcs_id + ";";
                    postgreStmt = postgreConnection.prepareStatement(postgreSql);

                    rs = postgreStmt.executeQuery();
                }
                else{
                    postgreSql = "update public.borrows set status='hosszabbitas elutasitva' where id=" + kolcs_id + ";";
                    postgreStmt = postgreConnection.prepareStatement(postgreSql);

                    rs = postgreStmt.executeQuery();
                }

            }
        } catch (Exception ex) {
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

}
