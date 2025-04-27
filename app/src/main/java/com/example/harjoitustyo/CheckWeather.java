package com.example.harjoitustyo;

import com.example.harjoitustyo.lutemons.Lutemon;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CheckWeather {

    private static final String API_KEY = "4ca999846d0453c2ccecc37e69cb0b4e";

    public interface WeatherDataCallback {
        void onWeatherData(boolean isFavorable, double temp, String weather);
    }

    // Fetches the weather in Lappeenranta and returns true if the weather favors the Lutemon being trained
    public static void getWeatherBonus(Lutemon lutemon, WeatherDataCallback callback) {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.execute(() -> {
            try {
                String url = "https://api.openweathermap.org/data/2.5/weather?q=Lappeenranta&appid=" +
                        API_KEY + "&units=metric";

                HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
                conn.setRequestMethod("GET");

                BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                ObjectMapper mapper = new ObjectMapper();
                JsonNode root = mapper.readTree(reader);
                reader.close();

                String weather = root.get("weather").get(0).get("main").asText().toLowerCase();
                double temp = root.get("main").get("temp").asDouble();
                String type = lutemon.getType().toLowerCase();

                boolean isFavorable = isWeatherFavorable(weather, temp, type);

                callback.onWeatherData(isFavorable, temp, weather);

            } catch (Exception e) {
                e.printStackTrace();
                callback.onWeatherData(false, 0, "-");
            }
        });
    }

    // Conditions for training weather bonuses based on Lutemon type
    private static boolean isWeatherFavorable(String weather, double temp, String type) {
        if (temp > 20 && type.equals("fire")) {
            return true;
        }
        else if (weather.contains("rain") && type.equals("grass")) {
            return true;
        }
        else if (temp > 0 && temp <= 10 && type.equals("normal")) {
            return true;
        }
        else if (weather.contains("cloud") && type.equals("shadow")) {
            return true;
        }
        else if (weather.contains("clear") && type.equals("fairy")) {
            return true;
        }
        else {
            return false;
        }
    }
}