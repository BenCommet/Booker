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
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class SingleBookAdapter extends AppCompatActivity {

    private ListView drawerList;
    private String [] navDrawerArray;

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
        isbn.setText("ISBN: " + b.isbn + "\n");
        description.setText(b.description + "\n");
        bookAuthor.setText("Author: " + b.author);
        bookPub.setText("Published By: " + b.publisher);
        bookPubDate.setText("Publishing Date: " + b.dataPub);
        image.setImageDrawable(b.largeImg);
    }

    private class DrawerItemClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView parent, View view, int position, long id) {
            switch(position){
                case 0:
                    break;
                case 1: Intent signupIntent = new Intent(SingleBookAdapter.this, SignupActivity.class);
                    startActivity(signupIntent);
                    break;
                case 2: Intent bookListIntent = new Intent(SingleBookAdapter.this, BookList.class);
                    startActivity(bookListIntent);
                    break;
                case 3: Intent userPofileIntent = new Intent(SingleBookAdapter.this, BookList.class);
                    startActivity(userPofileIntent);
                    break;
                case 4: Intent searchIntent = new Intent(SingleBookAdapter.this, SearchForm.class);
                    startActivity(searchIntent);
                    break;
                case 5:
                    Intent profileIntent = new Intent(SingleBookAdapter.this, UserProfile.class);
                    startActivity(profileIntent);
                    break;
                case 6:
                    Intent postIntent = new Intent(SingleBookAdapter.this, QueryForm.class);
                    startActivity(postIntent);
                    break;
            }
        }
    }
}
