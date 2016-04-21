package com.example.commet.booker;

import android.accounts.NetworkErrorException;
import android.os.AsyncTask;
import android.os.StrictMode;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Jesse on 4/20/2016.
 */
public class GetAsyncTask extends AsyncTask<String, Void, JSONArray>{

    @Override
    protected JSONArray doInBackground (String... searchUrl) {
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        JSONArray data = null;
        URL url;
        HttpURLConnection urlConnection = null;
//        String searchUrl = queryUrl;

        try {
            url = new URL(searchUrl[0]);
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.setReadTimeout(5000);
            urlConnection.setConnectTimeout(5000);

            int responseCode =  urlConnection.getResponseCode();
            String responseMessage = urlConnection.getResponseMessage();

            if(responseCode == HttpURLConnection.HTTP_OK) {
                String responseStr = readStream(urlConnection.getInputStream());
                data = new JSONArray(responseStr);
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
