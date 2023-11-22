package com.example.englishapplication.helper;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class FetchData  {
    private JSONObject dict = new JSONObject();
    private JSONObject word = new JSONObject();
    private JSONArray type = new JSONArray();
    private JSONArray explain = new JSONArray();
    private JSONArray example = new JSONArray();
    private String lastExplain = "";
    private String lastType = "";
    private String lastWord = "";
    private String lastPronoun = "";

    private void reset(boolean _example, boolean _explain, boolean _type, boolean _word) {
        if (_example) example = new JSONArray();
        if (_explain) explain = new JSONArray();
        if (_type) type = new JSONArray();
        if (_word) word = new JSONObject();
    }

    private void resetLast(boolean _explain, boolean _type, boolean _word, boolean _pronoun) {
        if (_explain) lastExplain = "";
        if (_type) lastType = "";
        if (_word) lastWord = "";
        if (_pronoun) lastPronoun = "";
    }

    private void addExplain() {
        if (!lastExplain.equals("")) {
            JSONObject explainElement = new JSONObject();
            explainElement.put(lastExplain, example);
            explain.put(explainElement);
        }
    }

    private void addType() {
        if (!lastType.equals("")) {
            JSONObject typeElement = new JSONObject();
            typeElement.put(lastType, explain);
            type.put(typeElement);
        }
    }

    private void addWord() {
        if (!lastWord.equals("")) {
            if (dict.isNull(lastWord)) {
                word.put("pronoun", lastPronoun);
                word.put("type", type);
                //Document doc = new Document().append("word", lastWord).append("pronoun", lastPronoun).append("type", type.toString());
                //if (ev) db.getEnglish_Vietnamese().insertOne(doc);
                //else db.getVietnamese_English().insertOne(doc);
                //System.out.println(doc.toJson());
                dict.put(lastWord, word);
            } else {
                for (int i = 0; i < type.length(); i++) {
                    JSONObject newType = type.getJSONObject(i);
                    dict.getJSONObject(lastWord).getJSONArray("type").put(newType);
                }
            }
        }
    }

    public void fetchData(String inFile, String outFile) throws IOException {
        dict = new JSONObject();
        FileInputStream fileInputStream = new FileInputStream(inFile);
        Scanner sc = new Scanner(fileInputStream);
        while (sc.hasNextLine()) {
            String WordInput = sc.nextLine();
            if (WordInput.equals("")) {
                continue;
            }
            if (WordInput.charAt(0) == '\uFEFF' || WordInput.charAt(0) == '@') {
                addExplain();
                addType();
                addWord();
                reset(true, true, true, true);
                resetLast(true, true, true, true);
                String[] data = WordInput.split("/");
                if (data[0].charAt(0) == '\uFEFF') {
                    lastWord = data[0].substring(2).trim().toLowerCase();
                } else {
                    lastWord = data[0].substring(1).trim().toLowerCase();
                }
                if (data.length > 1) {
                    lastPronoun = data[1].trim();
                }
            }
            if (WordInput.charAt(0) == '*') {
                addExplain();
                addType();
                reset(true, true, false, false);
                resetLast(true, true, false, false);
                lastType = WordInput.substring(1).trim();
            }
            if (WordInput.charAt(0) == '-') {
                addExplain();
                reset(true, false, false, false);
                resetLast(true, false, false, false);
                lastExplain = WordInput.substring(1).trim();
            }
            if (WordInput.charAt(0) == '=') {
                String[] data = WordInput.split("\\+");
                JSONObject exampleElement = new JSONObject();
                if (data.length > 1) {
                    exampleElement.put(data[0].substring(1).trim(), data[1].trim());
                } else {
                    exampleElement.put(data[0].substring(1).trim(), "");
                }
                example.put(exampleElement);
            }
        }
        addExplain();
        addType();
        addWord();
        reset(true, true, true, true);
        resetLast(true, true, true, true);
        FileWriter file = new FileWriter(outFile);
        file.write(String.valueOf(dict));
        file.flush();
    }

    public static void main(String[] args) throws IOException {
        FetchData fetchData = new FetchData();
        //fetchData.fetchData("../resources/WordDictionary/EngToVie.txt", "../resources/WordDictionary/EngToVie.json");
        fetchData.fetchData("/home/flash/EnglishApplication/src/main/resources/WordDictionary/EngToVie.txt", "/home/flash/EnglishApplication/src/main/resources/WordDictionary/EngToVie.json");
    }
}