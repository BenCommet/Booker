package com.example.commet.booker;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

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

    public bookListAdapter getList() {
        return bList;
    }

    //This method, like its counterpart in main, creates
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_list);

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
