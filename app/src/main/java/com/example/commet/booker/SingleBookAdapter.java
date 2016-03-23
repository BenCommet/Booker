package com.example.commet.booker;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class SingleBookAdapter extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_book_adapter);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Bundle bundle = getIntent().getExtras();
        String strISBN = bundle.getString("data");

        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        Book b =  new Book(strISBN);
//        Intent i = getIntent();
//        Book b = (Book) i.getSerializableExtra("data");

        final TextView title = (TextView) findViewById(R.id.bookTitle);
        final TextView isbn = (TextView) findViewById(R.id.bookIsbn);
        final ImageView image = (ImageView) findViewById(R.id.bookImage);
        final TextView description = (TextView) findViewById(R.id.bookDescription);
        final TextView bookAuthor = (TextView) findViewById(R.id.bookAuthor);
        final TextView bookPubDate = (TextView) findViewById(R.id.bookPubDate);
        final TextView bookPub = (TextView) findViewById(R.id.bookPub);
        final Button bookSearch = (Button) findViewById(R.id.bookSubmit);

        title.setText(b.title);
        isbn.setText(b.isbn);
        description.setText(b.description);
        bookAuthor.setText(b.author);
        bookPub.setText(b.publisher);
        bookPubDate.setText(b.dataPub);
        image.setImageDrawable(b.largeImg);
    }
}
