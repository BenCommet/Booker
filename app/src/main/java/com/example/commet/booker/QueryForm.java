package com.example.commet.booker;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.os.StrictMode;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class QueryForm extends AppCompatActivity implements Serializable{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_query_form);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        final EditText isbn = (EditText) findViewById(R.id.queryIsbn);
        final Button searchBtn = (Button) findViewById(R.id.querySearchBtn);

        isbn.setText("9781118102282");
//        isbn.setText("9781118983843");

        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String isbnData = isbn.getText().toString();
                search(isbnData);

            }
        });
    }
    private void search(String isbnData) {
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
//        Book data = new Book(isbnData);
        Intent searchIntent = new Intent(QueryForm.this, SingleBookAdapter.class);
        searchIntent.putExtra("data", isbnData);
        startActivity(searchIntent);
    }
}


