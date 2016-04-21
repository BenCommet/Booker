package com.example.commet.booker;

import android.content.Intent;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


/*
* Main Activity of application, connects all services together
*
* */
public class UserProfile extends AppCompatActivity {
    private ListView drawerList;
    private String [] navDrawerArray;
    private TypedArray img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        Resources res = getResources();
        UserData app = (UserData) getApplicationContext();

        navDrawerArray = res.getStringArray(R.array.nav_drawer_array);
        drawerList = (ListView) findViewById(R.id.left_drawer);
        img = res.obtainTypedArray(R.array.nav_images);
        drawerList.setAdapter(new NavDrawerAdapter(UserProfile.this, navDrawerArray, img));
        drawerList.setOnItemClickListener(new DrawerItemClickListener());

        TextView name = (TextView) findViewById(R.id.userName);
        final Button postBook = (Button) findViewById(R.id.userPost);
        final Button searchBook = (Button) findViewById(R.id.userSearch);
        final Button myBooks = (Button) findViewById(R.id.myBooks);
        final Button viewAll = (Button) findViewById(R.id.viewAllUser);
        final Button signOut = (Button) findViewById(R.id.signOut);

//      Need to pull in local name once authentication is implemented
        name.setText(app.getEmail());

        setSupportActionBar(toolbar);

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
                case 0:
                    break;
                case 1: Intent signupIntent = new Intent(UserProfile.this, SignupActivity.class);
                    startActivity(signupIntent);
                    break;
                case 2: Intent bookListIntent = new Intent(UserProfile.this, BookList.class);
                    bookListIntent.putExtra("data", "user");
                    startActivity(bookListIntent);
                    break;
                case 3: Intent userPofileIntent = new Intent(UserProfile.this, BookList.class);
                    userPofileIntent.putExtra("data", "user");
                    startActivity(userPofileIntent);
                    break;
                case 4: Intent searchIntent = new Intent(UserProfile.this, SearchForm.class);
                    startActivity(searchIntent);
                    break;
                case 5:
                    Intent profileIntent = new Intent(UserProfile.this, UserProfile.class);
                    startActivity(profileIntent);
                    break;
                case 6:
                    Intent postIntent = new Intent(UserProfile.this, QueryForm.class);
                    startActivity(postIntent);
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
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        Intent intent = new Intent(UserProfile.this, BookList.class);
        intent.putExtra("data", "user");
        startActivity(intent);
    }

    private void displayAll() {
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
        Intent intent = new Intent(UserProfile.this, BookList.class);
        intent.putExtra("data", "all");
        startActivity(intent);
    }
    private void userLogout() {
        Intent intent = new Intent(UserProfile.this, MainActivity.class);
        UserData app = (UserData) getApplicationContext();
        app.setEmail("");
        app.setPassword("");
        startActivity(intent);
    }

}
