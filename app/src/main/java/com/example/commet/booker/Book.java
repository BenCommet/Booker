package com.example.commet.booker;

import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.io.Serializable;
import java.lang.reflect.Array;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by jesse on 3/22/2016.
 */

/*Object to hold all book related data and contain Google Api queries*/
public class Book implements Serializable{

    GoogleQuery g = new GoogleQuery();
    JSONObject j= null;

    String isbn = "";
    String title = "";
    String author = "";
    String dataPub = "";
    String publisher = "";
    String description = "";
    String smallImgUrl = "";
    String largeImgUrl = "";
    Drawable smallImg = null;
    Drawable largeImg = null;

    public Book(String isbn) {
        Log.d("Creating Book", "Book: ");
        this.isbn = isbn;
        if (isbn == "") {
            j = new JSONObject();
        }
        else {
            j = g.doInBackground(isbn);
        }
        try {
            this.title = j.getJSONArray("items").getJSONObject(0).getJSONObject("volumeInfo").getString("title");
            this.author = j.getJSONArray("items").getJSONObject(0).getJSONObject("volumeInfo").getJSONArray("authors").toString();
            this.dataPub = j.getJSONArray("items").getJSONObject(0).getJSONObject("volumeInfo").getString("publishedDate");
            this.publisher = j.getJSONArray("items").getJSONObject(0).getJSONObject("volumeInfo").getString("publisher");
            this.description = j.getJSONArray("items").getJSONObject(0).getJSONObject("volumeInfo").getString("description");
            this.smallImgUrl = j.getJSONArray("items").getJSONObject(0).getJSONObject("volumeInfo").getJSONObject("imageLinks").getString("smallThumbnail");
            this.largeImgUrl = j.getJSONArray("items").getJSONObject(0).getJSONObject("volumeInfo").getJSONObject("imageLinks").getString("thumbnail");
            this.largeImg = LoadImageFromWebOperations(this.largeImgUrl);
            this.smallImg = LoadImageFromWebOperations(this.smallImgUrl);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public static Drawable LoadImageFromWebOperations(String url) {
        try {
            InputStream is = (InputStream) new URL(url).getContent();
            Drawable d = Drawable.createFromStream(is, "src name");
            return d;
        } catch (Exception e) {
            return null;
        }
    }

    public String toString() {
       return title;
    }


}
