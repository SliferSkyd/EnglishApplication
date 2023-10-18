package com.example.englishapplication.base;

import okhttp3.*;
import java.io.BufferedReader;
import java.io.OutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

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
        if (fromLang == toLang) return text;
        // TODO: Should have used a 3rd party library to make a JSON string from an object
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        MediaType mediaType = MediaType.parse("application/json");
        //RequestBody body = RequestBody.create(mediaType, "{\n    \"Text\": \"hello world\"\n}");
        System.out.println(text);
        RequestBody body = RequestBody.create(mediaType, "{\n    \"Text\": \" + " + text + "\"\n}");
        Request request = new Request.Builder()
                .url("https://hoctap.coccoc.com/composer/proxyapi/translate?from=auto&to=vi")
                .method("POST", body)
                .addHeader("Content-Type", "application/json")
                .addHeader("Cookie", "coccoc_office=1")
                .build();
        Response response = client.newCall(request).execute();
        String word = "";

        if (response.isSuccessful()) {
            String responseBody = response.body().string();
            System.out.println("Response Body:");
            System.out.println(responseBody);

            for (int i = 0; i < responseBody.length(); ++i)
                if (responseBody.substring(i, i + 4).equals("text")) {
                    int l = i + 7; int r = l;
                    while (responseBody.charAt(r) != '\"') ++r;

                    word = responseBody.substring(l, r);
                    break;
                }
            response.close();
        } else {
            System.out.println("Request was not successful. Response code: " + response.code());
        }

        return word;
    }

}