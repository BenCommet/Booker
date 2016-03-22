package com.example.commet.booker;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import android.accounts.NetworkErrorException;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created by jesse on 3/19/2016.
 */
public class GoogleQuery extends AsyncTask<String, Void, JSONObject> {

    @Override
    protected JSONObject doInBackground(String... bookURLs) {
        URL url;
        JSONObject book = null;
        HttpURLConnection urlConnection = null;
        Log.d("MainUrl", bookURLs[0]);
        String searchUrl = "https://www.googleapis.com/books/v1/volumes?"+
                "q=isbn:" + bookURLs[0] + "&key=AIzaSyAvugTGEcCdKhLAeWnpUgAMcOoV5HuuwUU";

//        String searchUrl = "https://www.googleapis.com/books/v1/volumes?q=isbn:9781118102282&key=AIzaSyAvugTGEcCdKhLAeWnpUgAMcOoV5HuuwUU";
        try {
            url = new URL(searchUrl);
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.setReadTimeout(5000);
            urlConnection.setConnectTimeout(5000);

            int responseCode =  urlConnection.getResponseCode();
            String responseMessage = urlConnection.getResponseMessage();

            if(responseCode == HttpURLConnection.HTTP_OK) {
                String responseStr = readStream(urlConnection.getInputStream());
                book = new JSONObject(responseStr);
                Log.d("Create", book.toString());
                return book;
            }
            else {
                throw new NetworkErrorException();
            }
        }
        catch ( Exception e) {
            Log.e("Error", "Error incorrect HTTP request", e);
            e.printStackTrace();

        }finally {
            if(urlConnection != null)
                urlConnection.disconnect();
        }
        return book;
    }

    private String readStream(InputStream in) {
        BufferedReader reader = null;
        StringBuffer response = new StringBuffer();
        try {
            reader = new BufferedReader(new InputStreamReader(in));
            String line = "";
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return response.toString();
    }
}
