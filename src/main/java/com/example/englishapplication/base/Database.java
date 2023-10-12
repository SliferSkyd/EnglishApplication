package com.example.englishapplication.base;

import java.util.ArrayList;
import java.util.List;
import java.sql.*;

public class Database {
    private static Connection c = null;

    public static void startDatabase() {
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

    /*private static void endDatabase() {
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:src/main/resources/WordDictionary/favoriteWords");

            String sql = "DROP DATABASE IF EXITS";
            Statement stmt = c.createStatement();
            stmt.execute(sql);

            stmt.close();
            c.close();
        } catch (Exception e) {
            System.err.println("Can't drop database");
            throw new RuntimeException(e);
        }
    }*/

    public static boolean addWord(String s) throws ClassNotFoundException {
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
            String sql = "INSERT INTO favorite_word(english_word) " +
                    "VALUES ('" + s + "');" ;
            stmt.executeUpdate(sql);

        } catch (SQLException e) {
            System.out.println("Duplicate words!!!");
            return false;
        }

        return true;
    }

    public static boolean deleteWord(String s) throws ClassNotFoundException {
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
                    "WHERE english_word = ('" + s + "');" ;
            stmt.executeUpdate(sql);

        } catch (SQLException e) {
            System.out.println("Duplicate words!!!");
            return false;
        }

        return true;
    }

    public static List<String> getAllWords() throws ClassNotFoundException {
        List<String> favoriteWords = new ArrayList<>();

        Class.forName("org.sqlite.JDBC");
        try {
            c = DriverManager.getConnection("jdbc:sqlite:src/main/resources/WordDictionary/favoriteWords");
        } catch (SQLException e) {
            System.out.println("Can't start database");
            throw new RuntimeException(e);
        }

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

    public static void main(String[] args) throws ClassNotFoundException {
        startDatabase();
        //endDatabase();

        addWord("House");
        addWord("Home");
        List<String> favoriteWords = getAllWords();

        for (int i = 0; i < favoriteWords.size(); ++i) System.out.println(favoriteWords.get(i));
    }
}
