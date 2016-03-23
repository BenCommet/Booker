package com.example.commet.booker;

import android.annotation.SuppressLint;
import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.Image;
import android.os.Bundle;
import android.os.StrictMode;
import android.provider.ContactsContract;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.parse.FindCallback;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class QueryListAdapter extends ListActivity {

    private List<Book> books;

//    QueryListAdapter(Context context, String[] books){
//        super(context, R.layout.book_list_item, books);
//
//    }


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
        setContentView(R.layout.activity_query_list_adapter);

        ParseUser currentUser = ParseUser.getCurrentUser();

//        if (currentUser == null) {
//            loadLoginView();
//        }

        books = new ArrayList<Book>();
        ArrayAdapter<Book> adapter = new ArrayAdapter<Book>(this,
                R.layout.activity_query_list_adapter);
        setListAdapter(adapter);

        refreshPostList();
    }

    private void refreshPostList() {

        ParseQuery<ParseObject> query = ParseQuery.getQuery("bookData");
        query.whereEqualTo("userEmail", "temp10@mail.gvsu.edu");

        setProgressBarIndeterminateVisibility(true);

        query.findInBackground(new FindCallback<ParseObject>() {

            @SuppressWarnings("unchecked")
            @Override
            public void done(List<ParseObject> bookList, com.parse.ParseException e) {
                setProgressBarIndeterminateVisibility(false);
                Log.d("BookList", bookList.toString());
                if (e == null) {
                    // If there are results, update the list of posts
                    // and notify the adapter
                    books.clear();
                    for (ParseObject book : bookList) {
                        Book note = new Book(book.getString("ISBN"));
                        books.add(note);
                    }
                    ((ArrayAdapter<Book>) getListAdapter()).notifyDataSetChanged();
                } else {

                    Log.d(getClass().getSimpleName(), "Error: " + e.getMessage());
                }

            }

        });

    }
}