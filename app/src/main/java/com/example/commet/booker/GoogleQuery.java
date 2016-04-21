package com.example.commet.booker;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.net.HttpURLConnection;
import java.net.URL;

import android.accounts.NetworkErrorException;
import android.os.AsyncTask;
import android.os.StrictMode;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Creates a Http query to the Google Books API and returns JSON representation of data
 *
 * Created by jesse on 3/19/2016.
 */
public class GoogleQuery extends AsyncTask<String, Void, JSONObject> implements Serializable{

    JSONObject book = null;

    @Override
    protected JSONObject doInBackground(String... bookURLs) {

        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        URL url;
        HttpURLConnection urlConnection = null;
        String searchUrl = "https://www.googleapis.com/books/v1/volumes?"+
                "q=isbn:" + bookURLs[0] + "&key=AIzaSyAvugTGEcCdKhLAeWnpUgAMcOoV5HuuwUU";
        Log.d("Generating Request", "Req");
//                + "&key=AIzaSyAvugTGEcCdKhLAeWnpUgAMcOoV5HuuwUU";

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
