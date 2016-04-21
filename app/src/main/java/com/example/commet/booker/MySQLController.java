package com.example.commet.booker;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.DataOutputStream;
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

    GetAsyncTask req = new GetAsyncTask();

    public JSONArray getByISBN (String isbn) {
        String url = "http://www.cis.gvsu.edu/~roeje/Booker/getByIsbn.php?isbn=" + isbn;
        return req.doInBackground(url);
    }

    public JSONArray getByEmail (String email) {
        String url = "http://www.cis.gvsu.edu/~roeje/Booker/getByEmail.php?email=" + email;
        return req.doInBackground(url);
    }

    public JSONArray getAll () {
        String url = "http://www.cis.gvsu.edu/~roeje/Booker/getAll.php";
        return req.doInBackground(url);
    }

    public void postData (String email, String isbn) {
        String url = "http://www.cis.gvsu.edu/~roeje/Booker/post.php";
        requestCtrlPost(url, email, isbn);
    }

    public String[] getAllArray() throws JSONException {
        String[] temp = new String[10];

        JSONArray books = getAll();

        for (int i = 0; i < books.length(); i++) {
           temp[i] = books.getJSONObject(i).getString("isbn");
        }
        Log.d("Books", temp.toString());

        return temp;
    }

    public String[] getAllUserArray(String email) throws JSONException {
        String[] temp = new String[10];

        JSONArray books = getByEmail(email);

        for (int i = 0; i < books.length(); i++) {
            temp[i] = books.getJSONObject(i).getString("isbn");
        }
        Log.d("Books", temp.toString());

        return temp;
    }

    private void requestCtrlPost(String searchUrl, String email, String isbn) {
        URL url;
        HttpURLConnection urlConnection = null;
//        String searchUrl = queryUrl;
        String urlParameters = "email=" + email + "&isbn=" + isbn;

        try {
            url = new URL(searchUrl);
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("POST");
            urlConnection.setReadTimeout(5000);
            urlConnection.setConnectTimeout(5000);

            urlConnection.setRequestProperty("Content-Type",
                    "application/x-www-form-urlencoded");

            urlConnection.setRequestProperty("Content-Length", "" +
                    Integer.toString(urlParameters.getBytes().length));
            urlConnection.setRequestProperty("Content-Language", "en-US");

            urlConnection.setUseCaches (false);
            urlConnection.setDoInput(true);
            urlConnection.setDoOutput(true);

            //Send request
            DataOutputStream wr = new DataOutputStream (
                    urlConnection.getOutputStream ());
            wr.writeBytes (urlParameters);
            wr.flush ();
            wr.close ();

            int responseCode =  urlConnection.getResponseCode();
            String responseMessage = urlConnection.getResponseMessage();

//            if(responseCode == HttpURLConnection.HTTP_OK) {
//                String responseStr = readStream(urlConnection.getInputStream());
//                data = new JSONObject(responseStr);
//                return data;
//            }
//            else {
//                throw new NetworkErrorException();
//            }
        }
        catch ( Exception e) {
            Log.e("Error", "Error incorrect HTTP request", e);
            e.printStackTrace();

        }finally {
            if(urlConnection != null)
                urlConnection.disconnect();
        }
//        return data;

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
