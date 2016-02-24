package com.example.commet.booker;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
/**
* Sign up activity. Displays text fields for user info and signup button.
* Currently does not implement full functionality. Toasts are used to display this
* activity is still in development.
*
*/
public class SignupActivity extends AppCompatActivity {

    private EditText username_text;
    private EditText pass_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final Button login_button = (Button) findViewById(R.id.login_button);
        final Button signup_button = (Button) findViewById(R.id.signup_button);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        username_text = (EditText)findViewById(R.id.username);
        pass_text = (EditText)findViewById(R.id.password);


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Will link to Email app on next release", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        signup_button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                signup(v);
            }
        });

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public void signup(View view){
//      Intent intent = new Intent(this, SignupActivity.class);
//      startActivity(intent);
        Toast.makeText(getApplicationContext(), "This feature has not been implemented yet. " +
                            "Thank you for your patience.",
                Toast.LENGTH_SHORT).show();
    }
}
