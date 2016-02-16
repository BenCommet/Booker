package com.example.commet.booker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button login_button = (Button) findViewById(R.id.login_button);
        final Button signup_button = (Button) findViewById(R.id.signup_button);

        login_button.setOnClickListener( new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                login(v);
            }
        });

        signup_button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Toast.makeText(getApplicationContext(), "You do not need to signup in this version.",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void login(View view){
        Intent intent = new Intent(this, book_list.class);
        startActivity(intent);
    }
}
