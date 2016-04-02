package com.example.commet.booker;

import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.Parse;
import com.parse.ParseAnalytics;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;
/**
* This class creates a listview that holds the ISBN"s of our database's books.
*/
public class BookList extends AppCompatActivity {
    private LocalAdapter bookList;
    private ListView listView;
    private ListView drawerList;
    private String [] navDrawerArray;
    private String [] localBooks;


    //This method, like its counterpart in main, creates
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_list);


        Resources res = getResources();
        //instantiating drawer items
        navDrawerArray = res.getStringArray(R.array.nav_drawer_array);
        localBooks = res.getStringArray(R.array.local_isbn_array);
        drawerList = (ListView) findViewById(R.id.left_drawer);
        drawerList.setAdapter(new ArrayAdapter<String>(BookList.this, R.layout.nav_drawer_item, navDrawerArray));

        drawerList.setOnItemClickListener(new DrawerItemClickListener());

        //This connects us to parse. Without the error checking the app will force close
        //If this activity is opened again.

        bookList = new LocalAdapter(this, localBooks);
        listView = (ListView) findViewById(R.id.book_list);
        listView.setAdapter(bookList);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Object listItem = listView.getItemAtPosition(position);
                String isbn = listItem.toString();
                Intent detailedView = new Intent(BookList.this, SingleBookAdapter.class);
                detailedView.putExtra("data", isbn);
                startActivity(detailedView);
            }
        } );
    }

    private class DrawerItemClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView parent, View view, int position, long id) {
            switch(position){
                case 0: Intent mainIntent = new Intent(BookList.this, MainActivity.class);
                    startActivity(mainIntent);
                    break;
                case 1: Intent signupIntent = new Intent(BookList.this, SignupActivity.class);
                    startActivity(signupIntent);
                    break;
                case 2: Toast.makeText(BookList.this, "You are looking at your books.", Toast.LENGTH_LONG);
                    break;
                case 3: Intent userPofileIntent = new Intent(BookList.this, UserProfile.class);
                    startActivity(userPofileIntent);
                    break;
                case 4: Intent searchIntent = new Intent(BookList.this, SearchForm.class);
                    startActivity(searchIntent);
                    break;
            }
        }
    }


}
