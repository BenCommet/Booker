package com.example.commet.booker;

import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
    private BookListAdapter bList;
    private ListView listView;
    private ListView drawerList;
    private String [] navDrawerArray;


    //This method, like its counterpart in main, creates
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_list);


        Resources res = getResources();
        //instantiating drawer items
        navDrawerArray = res.getStringArray(R.array.nav_drawer_array);
        drawerList = (ListView) findViewById(R.id.left_drawer);
        drawerList.setAdapter(new ArrayAdapter<String>(BookList.this, R.layout.nav_drawer_item, navDrawerArray));

        drawerList.setOnItemClickListener(new DrawerItemClickListener());

        //This connects us to parse. Without the error checking the app will force close
        //If this activity is opened again.
        ParseAnalytics.trackAppOpenedInBackground(getIntent());
        ParseObject.registerSubclass(BookData.class);

        bList = new BookListAdapter(this, new ArrayList<BookData>());
        listView = (ListView) findViewById(R.id.book_list);
        listView.setAdapter(bList);
        updateData();
    }

    public void updateData(){
        ParseQuery<BookData> query = ParseQuery.getQuery(BookData.class);
        query.findInBackground(new FindCallback<BookData>() {
            @Override
            public void done(List<BookData> objects, ParseException e) {
                if(objects != null) {
                    bList.clear();
                    bList.addAll(objects);
                }
            }
        });

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

    //This is the method that actually fills in our data

}
