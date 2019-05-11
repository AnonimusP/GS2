package com.example.komputer.crudmvvm.model;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class DBHelperMySQL{

    public void AddGame(String title, String release, String genre, String platform, String price) {

        try {
        new URL("http://192.168.1.107/CRUDAPI/create.php?" +
                "Title="+title+
                "&Place="+release+
                "&Genre="+genre+
                "&Platform="+platform+
                "&Price="+price).openStream();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    public JsonArray ShowGame(String id) throws IOException {
        URL s_url = new URL("http://192.168.1.107/CRUDAPI/read.php?PID="+id);
        HttpURLConnection urlConnection = (HttpURLConnection) s_url.openConnection();
        JsonParser jp = new JsonParser();
        JsonElement root = jp.parse(new InputStreamReader((InputStream) s_url.getContent()));
        return root.getAsJsonArray();
    }

    public JsonArray ShowAllGames() throws IOException {

        URL s_url = new URL("http://192.168.1.107/CRUDAPI/readall.php");
        HttpURLConnection urlConnection = (HttpURLConnection) s_url.openConnection();
        JsonParser jp = new JsonParser();
        JsonElement root = jp.parse(new InputStreamReader((InputStream) s_url.getContent()));
        return root.getAsJsonArray();

    }

    public void UpdateGame(String id, String title, String release, String genre, String platform, String price){

        try {
            new URL("http://192.168.1.107/CRUDAPI/update.php?" +
                    "PID="+id+
                    "&Title="+title+
                    "&Place="+release+
                    "&Genre="+genre+
                    "&Platform="+platform+
                    "&Price="+price).openStream();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
        public void DeleteGame (String id){
            try {
                new URL("http://192.168.1.107/CRUDAPI/delete.php?PID="+id).openStream();
            }
            catch (IOException e){
                e.printStackTrace();
            }
        }
}
