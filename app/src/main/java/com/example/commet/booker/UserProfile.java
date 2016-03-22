package com.example.commet.booker;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class UserProfile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        TextView name = (TextView) findViewById(R.id.userName);
        final Button postBook = (Button) findViewById(R.id.userPost);
        final Button searchBook = (Button) findViewById(R.id.userSearch);
        final Button myBooks = (Button) findViewById(R.id.myBooks);
        final Button viewAll = (Button) findViewById(R.id.viewAllUser);
        final Button signOut = (Button) findViewById(R.id.signOut);

//      Need to pull in local name
        name.setText("Jesse");

        setSupportActionBar(toolbar);

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        postBook.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                post();
            }
        });
        searchBook.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                lookup();
            }
        });
        myBooks.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                userBooks();
            }
        });
        signOut.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                userLogout();
            }
        });
        viewAll.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                displayAll();
            }
        });
    }

    private class DrawerItemClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView parent, View view, int position, long id) {
            switch(position){
                case 0: Toast.makeText(UserProfile.this, "You are already on the home page.", Toast.LENGTH_LONG);
                    break;
                case 1: Intent signupIntent = new Intent(UserProfile.this, SignupActivity.class);
                    startActivity(signupIntent);
                    break;
                case 2: Intent bookListIntent = new Intent(UserProfile.this, BookList.class);
                    startActivity(bookListIntent);
                    break;
                case 3: Intent userPofileIntent = new Intent(UserProfile.this, UserProfile.class);
                    startActivity(userPofileIntent);
                    break;
            }
        }
    }


    private void post() {
        Intent postIntent = new Intent(UserProfile.this, QueryForm.class);
        startActivity(postIntent);
    }

    private void lookup() {
        Intent searchIntent = new Intent(UserProfile.this, SearchForm.class);
        startActivity(searchIntent);
    }

    private void userBooks() {

    }

    private void displayAll() {
        Intent intent = new Intent(this, BookList.class);
        startActivity(intent);
    }
    private void userLogout() {

    }

}
