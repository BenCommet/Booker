package com.example.commet.booker;

import android.util.Log;
import android.widget.ArrayAdapter;

import com.parse.FindCallback;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.FindCallback;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jesse on 3/21/2016.
 */
public class ParseControl {

    public void saveToDb(String isbn, String email) {
        ParseObject gameScore = new ParseObject("Temp");
        gameScore.put("ISBN", isbn);
        gameScore.put("userEmail", "email");
        gameScore.saveInBackground();
        Log.d("ParsePost", "Post Complete");
    }

    public void isInDB(String isbn, String email) {

        final ArrayList books = new ArrayList<Book>();
        ParseQuery<ParseObject> query = ParseQuery.getQuery("bookData");
        query.whereEqualTo("ISBN", "isbn");
        final List<ParseObject> tempList = null;
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> bookList, com.parse.ParseException e) {

                if (e == null) {
                    // If there are results, update the list of posts
                    // and notify the adapter
                    books.clear();
                    for (ParseObject book : bookList) {
                        Book note = new Book(book.getString("ISBN"));
                        books.add(note);
                        Log.d("score", "Retrieved " + bookList.size() + " scores");
                    }
                } else {

                    Log.d(getClass().getSimpleName(), "Error: " + e.getMessage());
                }//
            }
        });

    }

}
