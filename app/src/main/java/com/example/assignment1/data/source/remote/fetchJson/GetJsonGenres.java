package com.example.assignment1.data.source.remote.fetchJson;

import android.os.AsyncTask;

import com.example.assignment1.data.model.Genres;
import com.example.assignment1.data.source.remote.OnFetchDataJsonListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class GetJsonGenres extends AsyncTask<String, Void, String> {

    private final OnFetchDataJsonListener<ArrayList<Genres>> listener;
    private final String keyEntity;

    public GetJsonGenres(OnFetchDataJsonListener<ArrayList<Genres>> listener, String keyEntity) {
        this.listener = listener;
        this.keyEntity = keyEntity;
    }

    @Override
    protected String doInBackground(String... strings) {
        String data = "";
        try {
            ParseDataWithJson parseDataWithJson = new ParseDataWithJson();
            data = parseDataWithJson.getJsonFromUrl(strings[0]);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);

        if (s != null && !s.isEmpty()) {
            try {
                JSONObject jsonObject = new JSONObject(s);
                listener.onSuccess((new ParseDataWithJson().parseJsonToDataGenres(jsonObject, keyEntity)));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}
