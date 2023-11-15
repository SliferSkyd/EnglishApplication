package com.example.englishapplication.helper;

import com.example.englishapplication.core.Utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public abstract class API extends Utils {
    protected static HttpURLConnection connectTo(String url) throws IOException {
        return (HttpURLConnection) new URL(url).openConnection();
    }

    protected static String readData(HttpURLConnection con) throws IOException {
        StringBuilder response = new StringBuilder();
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        return response.toString();
    }
}
