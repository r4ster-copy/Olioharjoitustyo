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

    // Hakee Lappeenrannan sään ja palauttaa true, jos sää suosii treetattavaa Lutemonia.
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
                String color = lutemon.getColor().toLowerCase();

                boolean isFavorable = isWeatherFavorable(weather, temp, color);

                callback.onWeatherData(isFavorable, temp, weather);

            } catch (Exception e) {
                e.printStackTrace();
                callback.onWeatherData(false, 0, "-");
            }
        });
    }

    // Ehdot eri lutemonien treenauksen sääbonuksille
    private static boolean isWeatherFavorable(String weather, double tempC, String color) {
        if (tempC > 20 && color.equals("orange")) {
            return true;
        }
        else if (weather.contains("rain") && color.equals("green")) {
            return true;
        }
        else if (tempC < 0 && color.equals("white")) {
            return true;
        }
        else if (weather.contains("cloud") && color.equals("black")) {
            return true;
        }
        else if (weather.contains("clear") && color.equals("pink")) {
            return true;
        }
        else {
            return false;
        }
    }
}