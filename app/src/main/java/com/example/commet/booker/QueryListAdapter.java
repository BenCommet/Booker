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
import android.support.v7.widget.Toolbar;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.parse.FindCallback;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import org.w3c.dom.Text;

import java.lang.reflect.Array;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class QueryListAdapter extends ListActivity {

    private ArrayList<Book> books;
    private ListView drawerList;
    private String [] navDrawerArray;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
        setContentView(R.layout.activity_query_list_adapter);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        final TextView title = (TextView) findViewById(R.id.listTitle);
//        ParseUser currentUser = ParseUser.getCurrentUser();

//        if (currentUser == null) {
//            loadLoginView();
//        }

        books = new ArrayList<Book>();
        ArrayAdapter adapter = new ArrayAdapter<Book>(this,
                R.layout.activity_query_list_adapter, books);
        setListAdapter(adapter);

        refreshPostList();
    }

    private void refreshPostList() {

        ParseQuery<ParseObject> query = ParseQuery.getQuery("TestData");
//        query.whereEqualTo("userEmail", "temp10@mail.gvsu.edu");

        setProgressBarIndeterminateVisibility(true);

        query.findInBackground(new FindCallback<ParseObject>() {

            @SuppressWarnings("unchecked")
            @Override
            public void done(List<ParseObject> bookList, com.parse.ParseException e) {
                setProgressBarIndeterminateVisibility(false);
//                Log.d("BookList", bookList.toString());
                if (e == null) {
                    // If there are results, update the list of posts
                    // and notify the adapter
                    books.clear();
                    for (ParseObject book : bookList) {
                        Book b = new Book(book.getString("Isbn"));
                        books.add(b);
                    }
                    ((ArrayAdapter<Book>) getListAdapter()).notifyDataSetChanged();
                } else {

                    Log.d(getClass().getSimpleName(), "Error: " + e.getMessage());
                }

            }

        });

    }
}