package com.example.englishapplication.core;

import java.util.ArrayList;
import java.util.List;
import java.sql.*;

public class Database {
    private static Connection c = null;

    public static void startDatabase() {
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:src/main/resources/com/example/englishapplication/data/database.db");

            String sql = "CREATE TABLE IF NOT EXISTS " + "favoriteWords" + " (\n"
                    +   "id integer PRIMARY KEY,\n"
                    +   "englishWord varchar UNIQUE NOT NULL,\n"
                    +   "vietnameseWord varchar NOT NULL\n"
                    +   ");";
            System.out.println(sql);
            Statement stmt = c.createStatement();
            stmt.execute(sql);

        } catch (Exception e) {
            System.err.println("Can't start database");
            System.exit(0);
        }
    }

    public static boolean addWord(Word word) {
        Statement stmt = null;
        try {
            stmt = c.createStatement();
            String sql = "INSERT INTO favoriteWords(englishWord, vietnameseWord) " +
                    "VALUES ('" + word.getTarget() + "', '" + word.getExplain() + "');" ;
            System.out.println(sql);
            stmt.executeUpdate(sql);

        } catch (SQLException e) {
            System.out.println("Something went wrong!");
            return false;
        }

        return true;
    }

    public static List<Word> getAllWords() {
        List<Word> favoriteWords = new ArrayList<>();

        Statement stmt = null;
        ResultSet rs;

        try {
            stmt = c.createStatement();
            rs = stmt.executeQuery("SELECT * FROM favoriteWords;");

            while (rs.next()) {
                int id = rs.getInt("id");
                String wordTarget = rs.getString("englishWord");
                String wordExplain = rs.getString("vietnameseWord");

                favoriteWords.add(new Word(wordTarget, wordExplain));
            }
        } catch (SQLException e) {
            return favoriteWords;
        }

        return favoriteWords;
    }


    public static boolean existWord(String s) throws ClassNotFoundException {
        Statement stmt = null;
        ResultSet rs;

        try {
            stmt = c.createStatement();
            rs = stmt.executeQuery("SELECT * FROM favoriteWords WHERE englishWord = '" + s + "';");
            if (rs.next()) {
                return true;
            }
        } catch (SQLException e) {
            return false;
        }

        return false;
    }

    public static boolean removeWord(String s) throws ClassNotFoundException {
        Statement stmt = null;
        try {
            stmt = c.createStatement();
            String sql = "DELETE FROM favoriteWords " +
                    "WHERE englishWord = '" + s + "';" ;
            System.out.println(sql);
            stmt.executeUpdate(sql);

        } catch (SQLException e) {
            System.out.println("Something went wrong!");
            return false;
        }
        return true;
    }

    public static void stopDatabase() {
        try {
            c.close();
        } catch (SQLException e) {
            System.err.println("Can't stop database");
            System.exit(0);
        }
    }

    public static void clearData() {
        Statement stmt = null;
        try {
            stmt = c.createStatement();
            String sql = "DELETE FROM favoriteWords;" ;
            System.out.println(sql);
            stmt.executeUpdate(sql);

        } catch (SQLException e) {
            System.out.println("Something went wrong!");
        }
    }
    public static void main(String[] args) {
        startDatabase();
//        addWord(new Word("helloaa", "xin chào"));
//        clearData();

        List<Word> wordList = getAllWords();
        for (Word word : wordList) {
            System.out.println(word.getTarget() + " " + word.getExplain());
        }

        stopDatabase();
    }
}
