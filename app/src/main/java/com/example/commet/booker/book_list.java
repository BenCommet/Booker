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
public class book_list extends AppCompatActivity {
    private bookListAdapter bList;
    private ListView listView;
    private ListView drawerList;
    private String [] navDrawerArray;

    public bookListAdapter getList() {
        return bList;
    }

    //This method, like its counterpart in main, creates
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_list);

        Resources res = getResources();

        navDrawerArray = res.getStringArray(R.array.nav_drawer_array);
        drawerList = (ListView) findViewById(R.id.left_drawer);
        drawerList.setAdapter(new ArrayAdapter<String>(book_list.this, R.layout.nav_drawer_item, navDrawerArray));

        drawerList.setOnItemClickListener(new DrawerItemClickListener());


        //This connects us to parse. Without the error checking the app will force close
        //If this activity is opened again.
        try {
            Parse.enableLocalDatastore(this);
            Parse.initialize(this);
        } catch (Exception e){
            e.printStackTrace();
        }
        ParseAnalytics.trackAppOpenedInBackground(getIntent());
        ParseObject.registerSubclass(bookData.class);

        bList = new bookListAdapter(this, new ArrayList<bookData>());
        listView = (ListView) findViewById(R.id.book_list);
        listView.setAdapter(bList);
        updateData();
    }

    private class DrawerItemClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView parent, View view, int position, long id) {
            switch(position){
                case 0: Intent mainIntent = new Intent(book_list.this, MainActivity.class);
                    startActivity(mainIntent);
                    break;
                case 1: Intent signupIntent = new Intent(book_list.this, SignupActivity.class);
                    startActivity(signupIntent);
                    break;
                case 2: Toast.makeText(book_list.this, "You are looking at your books.", Toast.LENGTH_LONG);
                    break;
            }
        }
    }

    //This is the method that actually fills in our data
    public void updateData(){
        ParseQuery<bookData> query = ParseQuery.getQuery(bookData.class);
        query.findInBackground(new FindCallback<bookData>() {
            @Override
            public void done(List<bookData> objects, ParseException e) {
                if(objects != null) {
                    bList.clear();
                    bList.addAll(objects);
                }
            }
        });

    }
}
