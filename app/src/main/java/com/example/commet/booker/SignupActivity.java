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
* Sign up activity. Displays text fields for user info and signup button.
* Currently does not implement full functionality. Toasts are used to display this
* activity is still in development.
*
*/
public class SignupActivity extends AppCompatActivity {

    private EditText username_text;
    private EditText pass_text;
    private ListView drawerList;
    private String [] navDrawerArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        Resources res = getResources();

        navDrawerArray = res.getStringArray(R.array.nav_drawer_array);
        drawerList = (ListView) findViewById(R.id.left_drawer);
        drawerList.setAdapter(new ArrayAdapter<String>(SignupActivity.this, R.layout.nav_drawer_item, navDrawerArray));

        drawerList.setOnItemClickListener(new DrawerItemClickListener());

        final Button login_button = (Button) findViewById(R.id.login_button);
        final Button signup_button = (Button) findViewById(R.id.signup_button);
        username_text = (EditText)findViewById(R.id.username);
        pass_text = (EditText)findViewById(R.id.password);

        signup_button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                signup(v);
            }
        });

    }

    public void signup(View view){
//      Intent intent = new Intent(this, SignupActivity.class);
//      startActivity(intent);
        Toast.makeText(getApplicationContext(), "This feature has not been implemented yet. " +
                            "Thank you for your patience.",
                Toast.LENGTH_SHORT).show();
    }

    private class DrawerItemClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView parent, View view, int position, long id) {
            switch(position){
                case 0: Intent mainIntent = new Intent(SignupActivity.this, MainActivity.class);
                    startActivity(mainIntent);
                    break;
                case 1: Toast.makeText(SignupActivity.this, "You are already on the signiup page", Toast.LENGTH_LONG);
                    break;
                case 2: Intent bookListIntent = new Intent(SignupActivity.this, BookList.class);
                    startActivity(bookListIntent);
                    break;
                case 3: Intent userPofileIntent = new Intent(SignupActivity.this, UserProfile.class);
                    startActivity(userPofileIntent);
                    break;
                case 4: Intent searchIntent = new Intent(SignupActivity.this, SearchForm.class);
                    startActivity(searchIntent);
                    break;
            }
        }
    }
}
