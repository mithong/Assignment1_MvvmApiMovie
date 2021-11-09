package com.example.assignment1.data.source.remote.fetchJson;

import com.example.assignment1.data.model.Genres;
import com.example.assignment1.data.model.Movie;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;

public class ParseDataWithJson {

    public String getJsonFromUrl(String urlString) {
        URL url;
        StringBuilder stringBuilder = null;
        try {
            stringBuilder = new StringBuilder();
            url = new URL(urlString);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setConnectTimeout(15000);
            httpURLConnection.setReadTimeout(15000);
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.connect();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(url.openStream()));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line);
            }

            bufferedReader.close();
            httpURLConnection.disconnect();

        } catch (ProtocolException protocolException) {
            protocolException.printStackTrace();

        } catch (MalformedURLException malformedURLException) {
            malformedURLException.printStackTrace();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        return stringBuilder.toString();
    }

    public ArrayList<Movie> parseJsonToData(JSONObject jsonObject, String keyEntity) {
        ArrayList<Movie> data = new ArrayList<>();
        try {
            JSONArray jsonArray = jsonObject.getJSONArray(keyEntity);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObjects = jsonArray.getJSONObject(i);
                Movie item = new ParseDataWithJson().parseJsonToObject(jsonObjects, keyEntity);
                data.add(item);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return data;
    }

    public ArrayList<Genres> parseJsonToDataGenres(JSONObject jsonObject, String keyEntity) {
        ArrayList<Genres> data = new ArrayList<>();
        try {
            JSONArray jsonArray = jsonObject.getJSONArray(keyEntity);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObjects = jsonArray.getJSONObject(i);
                Genres item = new ParseDataWithJson().parseJsonToObjectGenres(jsonObjects, keyEntity);
                data.add(item);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return data;
    }

    private Movie parseJsonToObject(JSONObject jsonObject, String keyEntity) {
        if (keyEntity.equals("results")) {
            ParseJson parseJson = new ParseJson();
            return parseJson.MovieParseJson(jsonObject);
        }
        return null;
    }

    private Genres parseJsonToObjectGenres(JSONObject jsonObject, String keyEntity) {
        if (keyEntity.equals("genres")) {
            ParseJson parseJson = new ParseJson();
            return parseJson.GenresParseJson(jsonObject);
        }
        return null;
    }
}
