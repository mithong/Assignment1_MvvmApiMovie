package com.example.assignment1.data.source.remote.fetchJson;

import com.example.assignment1.data.model.Genres;
import com.example.assignment1.data.model.Movie;

import org.json.JSONException;
import org.json.JSONObject;

public class ParseJson {

    public Movie MovieParseJson(JSONObject jsonObject) {
        Movie movie = null;
        try {
            movie = new Movie(jsonObject.getInt("id"),
                    jsonObject.getString("title"),
                    jsonObject.getString("overview"),
                    jsonObject.getString("poster_path"),
                    jsonObject.getString("release_date"),
                    jsonObject.getDouble("vote_average"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return movie;
    }

    public Genres GenresParseJson(JSONObject jsonObject) {
        Genres genres = null;
        try {
            genres = new Genres(jsonObject.getString("name"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return genres;
    }
}
