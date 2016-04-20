package com.example.commet.booker;

import android.accounts.NetworkErrorException;
import android.util.Log;

import org.json.JSONObject;

import java.net.HttpURLConnection;
import java.net.URL;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Jesse on 4/20/2016.
 */
public class MySQLController {

    JSONObject data = null;

    public JSONObject getByISBN(String isbn) {
        URL url;
        HttpURLConnection urlConnection = null;
        String searchUrl = "";

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
                data = new JSONObject(responseStr);
                return data;
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
        return data;

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
