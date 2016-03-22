package com.example.commet.booker;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

/**
* MainActivity: Login Screen and text fields. Includes helper functions to error check text input.
* */
public class MainActivity extends AppCompatActivity {

    private EditText username_text;
    private EditText pass_text;
    private ListView drawerList;
    private String [] navDrawerArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Resources res = getResources();

        navDrawerArray = res.getStringArray(R.array.nav_drawer_array);
        drawerList = (ListView) findViewById(R.id.left_drawer);
        drawerList.setAdapter(new ArrayAdapter<String>(MainActivity.this, R.layout.nav_drawer_item, navDrawerArray));

        drawerList.setOnItemClickListener(new DrawerItemClickListener());

        final Button login_button = (Button) findViewById(R.id.login_button);
        final Button signup_button = (Button) findViewById(R.id.signup_button);

        username_text = (EditText)findViewById(R.id.username);
        pass_text = (EditText)findViewById(R.id.password);
        username_text.setText("jessedroe@gmail.com");
        pass_text.setText("123456789");

        login_button.setOnClickListener( new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                final String username = username_text.getText().toString();
                final String password = String.valueOf(pass_text.getText());
                login(v, username, password);
            }
        });
        signup_button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                signup(v);
            }
        });
    }

    private class DrawerItemClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView parent, View view, int position, long id) {
            switch(position){
                case 0: Toast.makeText(MainActivity.this, "You are already on the home page.", Toast.LENGTH_LONG);
                    break;
                case 1: Intent signupIntent = new Intent(MainActivity.this, SignupActivity.class);
                    startActivity(signupIntent);
                    break;
                case 2: Intent bookListIntent = new Intent(MainActivity.this, BookList.class);
                    startActivity(bookListIntent);
                    break;
                case 3: Intent userPofileIntent = new Intent(MainActivity.this, UserProfile.class);
                    startActivity(userPofileIntent);
                    break;
            }
        }
    }

    public void login(View view, String user, String pass){
        if (checkUsername(user) && checkPassword(pass)) {
            Intent intent = new Intent(this, UserProfile.class);
            startActivity(intent);
        }
        else {
            Toast.makeText(getApplicationContext(), "Invalid login credentials! Valid email/password" +
                            " required.",
                    Toast.LENGTH_SHORT).show();
        }
    }

//    public void login(View view, String user, String pass){
//        if (checkUsername(user) && checkPassword(pass)) {
//            Intent intent = new Intent(this, BookList.class);
//            startActivity(intent);
//        }
//        else {
//            Toast.makeText(getApplicationContext(), "Invalid login credentials! Valid email/password" +
//                            " required.",
//                    Toast.LENGTH_SHORT).show();
//        }
//    }

    public void signup(View view){
            Intent intent = new Intent(this, SignupActivity.class);
            startActivity(intent);
    }

    public boolean checkUsername(String s) {
        return s.contains(String.valueOf('@')) != false;
    }
    public boolean checkPassword(String s) {
        return s.length() >= 8;
    }
}
