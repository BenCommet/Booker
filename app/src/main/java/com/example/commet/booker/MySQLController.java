package com.example.commet.booker;

import android.accounts.NetworkErrorException;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.DataOutputStream;
import java.lang.reflect.Array;
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

    public JSONObject getByISBN (String isbn) {
        String url = "http://www.cis.gvsu.edu/~roeje/Booker/getByIsbn.php?isbn=" + isbn;
        return requestCtrlGet(url);
    }

    public JSONObject getByEmail (String email) {
        String url = "http://www.cis.gvsu.edu/~roeje/Booker/getByEmail.php?email=" + email;
        return requestCtrlGet(url);
    }

    public JSONObject getAll () {
        String url = "http://www.cis.gvsu.edu/~roeje/Booker/getAll.php";
        return requestCtrlGet(url);
    }

    public void postData (String email, String isbn) {
        String url = "http://www.cis.gvsu.edu/~roeje/Booker/post.php";
        requestCtrlPost(url, email, isbn);
    }

    public String[] getAllArray() {
        String[] temp = new String[100];

        JSONObject books = getAll();

        Log.d("Books", books.toString());

        return temp;
    }

    private JSONObject requestCtrlGet(String searchUrl) {
        URL url;
        HttpURLConnection urlConnection = null;
//        String searchUrl = queryUrl;

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
