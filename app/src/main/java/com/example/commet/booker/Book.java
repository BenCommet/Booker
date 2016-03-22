package com.example.commet.booker;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by jesse on 3/22/2016.
 */
public class Book implements Serializable{

    GoogleQuery g = new GoogleQuery();
    JSONObject j= null;

    String isbn = "";
    String title = "";
    String author = "";
    String dataPub = "";
    String publisher = "";
    String description = "";
    String imageUrl = "";

    public Book(String isbn) {
        this.isbn = isbn;
        j = g.doInBackground(isbn);
        try {
            this.title = j.getJSONArray("items").getJSONObject(0).getJSONObject("volumeInfo").getString("title");
            this.author = j.getJSONArray("items").getJSONObject(0).getJSONObject("volumeInfo").getJSONArray("authors").toString();
            this.dataPub = j.getJSONArray("items").getJSONObject(0).getJSONObject("volumeInfo").getString("publishedDate");
            this.publisher = j.getJSONArray("items").getJSONObject(0).getJSONObject("volumeInfo").getString("publisher");
            this.description = j.getJSONArray("items").getJSONObject(0).getJSONObject("volumeInfo").getString("description");
            this.imageUrl = j.getJSONArray("items").getJSONObject(0).getJSONObject("volumeInfo").getString("description");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}
