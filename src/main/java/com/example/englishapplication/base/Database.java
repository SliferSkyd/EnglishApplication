package com.example.englishapplication.base;

import java.util.ArrayList;
import java.util.List;
import java.sql.*;

public class Database {
    private static Connection c = null;

    public Database() throws ClassNotFoundException {
        startDatabase();
    }

    public void startDatabase() {
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:src/main/resources/WordDictionary/favoriteWords");

            String sql = "CREATE TABLE IF NOT EXISTS favorite_word (\n"
                    +   "id integer PRIMARY KEY,\n"
                    +   "english_word text UNIQUE NOT NULL\n"
                    +   ");";

            Statement stmt = c.createStatement();
            stmt.execute(sql);

            stmt.close();
            c.close();
        } catch (Exception e) {
            System.err.println("Can't start database");
            System.exit(0);
        }
    }

    public boolean addWord(String s) throws ClassNotFoundException {
        Class.forName("org.sqlite.JDBC");
        try {
            c = DriverManager.getConnection("jdbc:sqlite:src/main/resources/WordDictionary/favoriteWords");
        } catch (SQLException e) {
            System.out.println("Can't connect to database");
            throw new RuntimeException(e);
        }

        System.out.println(existWord(s));
        Statement stmt = null;
        try {
            stmt = c.createStatement();
            String sql = "INSERT INTO favorite_word(english_word) " +
                    "VALUES ('" + s + "');" ;
            System.out.println(sql);
            stmt.executeUpdate(sql);

        } catch (SQLException e) {
            System.out.println("Duplicate words!!!");
            return false;
        }

        return true;
    }


    public List<String> getAllWords() throws ClassNotFoundException {
        Class.forName("org.sqlite.JDBC");
        try {
            c = DriverManager.getConnection("jdbc:sqlite:src/main/resources/WordDictionary/favoriteWords");
        } catch (SQLException e) {
            System.out.println("Can't connect to database");
            throw new RuntimeException(e);
        }

        List<String> favoriteWords = new ArrayList<>();

        Statement stmt = null;
        ResultSet rs;

        try {
            stmt = c.createStatement();
            rs = stmt.executeQuery("SELECT * FROM favorite_word;");
            while (rs.next()) {
                int id = rs.getInt("id");
                String word = rs.getString("english_word");

                favoriteWords.add(word);
            }
        } catch (SQLException e) {
            return favoriteWords;
        }

        return favoriteWords;
    }

    public boolean existWord(String s) throws ClassNotFoundException {
        Class.forName("org.sqlite.JDBC");
        try {
            c = DriverManager.getConnection("jdbc:sqlite:src/main/resources/WordDictionary/favoriteWords");
        } catch (SQLException e) {
            System.out.println("Can't connect to database");
            throw new RuntimeException(e);
        }

        Statement stmt = null;
        ResultSet rs;

        try {
            stmt = c.createStatement();
            rs = stmt.executeQuery("SELECT * FROM favorite_word WHERE english_word = '" + s + "';");
            if (rs.next()) {
                return true;
            }
        } catch (SQLException e) {
            return false;
        }

        return false;
    }

    public boolean removeWord(String s) throws ClassNotFoundException {
        Class.forName("org.sqlite.JDBC");
        try {
            c = DriverManager.getConnection("jdbc:sqlite:src/main/resources/WordDictionary/favoriteWords");
        } catch (SQLException e) {
            System.out.println("Can't connect to database");
            throw new RuntimeException(e);
        }

        Statement stmt = null;
        try {
            stmt = c.createStatement();
            String sql = "DELETE FROM favorite_word " +
                    "WHERE english_word = '" + s + "';" ;
            System.out.println(sql);
            stmt.executeUpdate(sql);

        } catch (SQLException e) {
            System.out.println("Duplicate words!!!");
            return false;
        }

        return true;
    }
}
