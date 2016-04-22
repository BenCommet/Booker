package com.example.commet.booker;

import android.content.Intent;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.os.Parcelable;
import android.os.StrictMode;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class QueryForm extends AppCompatActivity implements Serializable{

    private ListView drawerList;
    private String [] navDrawerArray;
    private TypedArray img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_query_form);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Resources res = getResources();

        navDrawerArray = res.getStringArray(R.array.nav_drawer_array);
        drawerList = (ListView) findViewById(R.id.left_drawer);
        img = res.obtainTypedArray(R.array.nav_images);
        drawerList.setAdapter(new NavDrawerAdapter(QueryForm.this, navDrawerArray, img));
        drawerList.setOnItemClickListener(new DrawerItemClickListener());

        final EditText isbn = (EditText) findViewById(R.id.queryIsbn);
//        final EditText email = (EditText) findViewById(R.id.emailText);
        final Button searchBtn = (Button) findViewById(R.id.querySearchBtn);
        final Button scanBtn = (Button) findViewById(R.id.scanBtn);

        isbn.setText("9780073229720");
//        isbn.setText("9781118983843");

        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserData app = (UserData) getApplicationContext();
                String isbnData = isbn.getText().toString();
                String emailData = app.getEmail();
                post(isbnData, emailData);
            }
        });

        scanBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scan();
            }
        });

    }

    private void scan () {
        new IntentIntegrator(this).initiateScan(); // `this` is the current Activity
    }

    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        IntentResult scanningResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);

        if (scanningResult != null) {
            String isbnData = scanningResult.getContents();
//            Log.e("Error", isbnData);
            Intent searchIntent = new Intent(QueryForm.this, SingleBookAdapter.class);
            searchIntent.putExtra("data", isbnData);
            startActivity(searchIntent);
        }else{
            Toast toast = Toast.makeText(getApplicationContext(),
                    "No scan data received!", Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    private void post(String isbnData, String email) {
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
//        Book data = new Book(isbnData);
        Intent searchIntent = new Intent(QueryForm.this, SingleBookAdapter.class);
        searchIntent.putExtra("data", isbnData);
        searchIntent.putExtra("email", email);
        startActivity(searchIntent);
    }

    private class DrawerItemClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView parent, View view, int position, long id) {
            switch(position){
                case 0:
                    break;
                case 1: Intent signupIntent = new Intent(QueryForm.this, SignupActivity.class);
                    startActivity(signupIntent);
                    break;
                case 2: Intent bookListIntent = new Intent(QueryForm.this, BookList.class);
                    startActivity(bookListIntent);
                    break;
                case 3: Intent userPofileIntent = new Intent(QueryForm.this, BookList.class);
                    startActivity(userPofileIntent);
                    break;
                case 4: Intent searchIntent = new Intent(QueryForm.this, SearchForm.class);
                    startActivity(searchIntent);
                    break;
                case 5:
                    Intent profileIntent = new Intent(QueryForm.this, UserProfile.class);
                    startActivity(profileIntent);
                    break;
                case 6:
                    Intent postIntent = new Intent(QueryForm.this, QueryForm.class);
                    startActivity(postIntent);
                    break;
            }
        }
    }
}


