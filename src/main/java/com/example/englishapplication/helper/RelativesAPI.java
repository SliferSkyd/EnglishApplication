package com.example.englishapplication.helper;

import org.apache.commons.text.*;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class RelativesAPI extends API {
    public static JSONObject getList(String wordForm) {
        try {
            HttpURLConnection request = connectTo("https://languagetools.p.rapidapi.com/all/" + URLEncoder.encode(wordForm, StandardCharsets.UTF_8).replace("+", "%20"));
            request.setRequestProperty("x-rapidapi-host", "languagetools.p.rapidapi.com");
            request.setRequestProperty("x-rapidapi-key", "aca2c0c9a3mshdae9b0fd091fb0dp1923ffjsn84863605816e");
            request.setRequestMethod("GET");
            return new JSONObject(StringEscapeUtils.unescapeHtml4(readData(request).toString()));
        } catch (IOException e) {
            return new JSONObject("{\"hypernyms\":[],\"synonyms\":[],\"antonyms\":[],\"hyponyms\":[]}");
        }
    }
}