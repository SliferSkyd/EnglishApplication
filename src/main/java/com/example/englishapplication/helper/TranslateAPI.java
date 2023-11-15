package com.example.englishapplication.helper;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class TranslateAPI extends API {
    public static String translate(String text, String fromLang, String toLang) throws Exception {
        String urlStr = "https://script.google.com/macros/s/AKfycbwWzt9xp75xQE9ODdMScAGVhqTnQvDmNSkOrVrTj4HdSIEeij1X_ZGyuqeBHLEHpsPv/exec" +
                "?q=" + URLEncoder.encode(text, "UTF-8") +
                "&target=" + toLang +
                "&source=" + fromLang;
        HttpURLConnection request = connectTo(urlStr);
        request.setRequestProperty("User-Agent", "Mozilla/5.0");
        return readData(request);
    }
}