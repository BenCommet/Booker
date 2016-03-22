package com.example.commet.booker;

import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class QueryForm extends AppCompatActivity {
    private ListView drawerList;
    private String [] navDrawerArray;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_query_form);

        Resources res = getResources();

        navDrawerArray = res.getStringArray(R.array.nav_drawer_array);
        drawerList = (ListView) findViewById(R.id.left_drawer);
        drawerList.setAdapter(new ArrayAdapter<String>(QueryForm.this, R.layout.nav_drawer_item, navDrawerArray));

        drawerList.setOnItemClickListener(new DrawerItemClickListener());
    }







    private class DrawerItemClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView parent, View view, int position, long id) {
            switch(position){
                case 0: Toast.makeText(QueryForm.this, "You are already on the home page.", Toast.LENGTH_LONG);
                    break;
                case 1: Intent signupIntent = new Intent(QueryForm.this, SignupActivity.class);
                    startActivity(signupIntent);
                    break;
                case 2: Intent bookListIntent = new Intent(QueryForm.this, BookList.class);
                    startActivity(bookListIntent);
                    break;
                case 3: Intent userPofileIntent = new Intent(QueryForm.this, UserProfile.class);
                    startActivity(userPofileIntent);
                    break;
                case 4: Intent searchIntent = new Intent(QueryForm.this, SearchForm.class);
                    startActivity(searchIntent);
                    break;
            }
        }
    }
}
