package com.example.englishapplication.base;

import okhttp3.*;
import java.io.BufferedReader;
import java.io.OutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class TranslateAPI {
    /**
     * Entry Point
     */
    public static void main(String[] args) throws Exception {
        // TODO: Specify your translation requirements here:
        String fromLang = "en";
        String toLang = "vi";
        String text = "Parallel";

        System.out.println(TranslateAPI.translate(text, fromLang, toLang));
    }

    /**
     * Sends out a WhatsApp message via WhatsMate WA Gateway.
     */
    public static String translate(String text, String fromLang, String toLang) throws Exception {
        String urlStr = "https://script.google.com/macros/s/AKfycbwWzt9xp75xQE9ODdMScAGVhqTnQvDmNSkOrVrTj4HdSIEeij1X_ZGyuqeBHLEHpsPv/exec" +
                "?q=" + URLEncoder.encode(text, "UTF-8") +
                "&target=" + toLang +
                "&source=" + fromLang;

        URL url = new URL(urlStr);
        StringBuilder response = new StringBuilder();
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestProperty("User-Agent", "Mozilla/5.0");
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        return response.toString();
    }

}