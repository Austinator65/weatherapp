package com.austin.weatherapp.controller;

import java.io.IOException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

@Service
public class WeatherService {
    private OkHttpClient client;
    private Response response;

    public JSONObject getWeather(String name) {
        client = new OkHttpClient();

        Request request = new Request.Builder()
                .url("https://api.openweathermap.org/data/2.5/weather?q=" + name
                        + "&units=metric&appid=6bed260650ab58e2e87160501c177ba8")
                .build();

        try {
            response = client.newCall(request).execute();
            if (response.isSuccessful()) {
                return new JSONObject(response.body().string());
            }
        } catch (IOException | JSONException e) {
            // handle exceptions
            e.printStackTrace();
        }

        return null;

    }

    private JSONArray weatherArray(String name) throws JSONException {

        JSONArray weatherJSONArray = getWeather(name).getJSONArray("weather");

        return weatherJSONArray;
    }
}
