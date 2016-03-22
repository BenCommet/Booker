package com.example.commet.booker;

import android.content.Intent;
import android.content.res.Resources;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONObject;

public class SearchForm extends AppCompatActivity {
    private GoogleQuery gq = new GoogleQuery();
    private ListView drawerList;
    private String [] navDrawerArray;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_form);

        Resources res = getResources();

        navDrawerArray = res.getStringArray(R.array.nav_drawer_array);
        drawerList = (ListView) findViewById(R.id.left_drawer);
        drawerList.setAdapter(new ArrayAdapter<String>(SearchForm.this, R.layout.nav_drawer_item, navDrawerArray));


        drawerList.setOnItemClickListener(new DrawerItemClickListener());

        final EditText isbn = (EditText) findViewById(R.id.isbnSearch);

//        isbn.setText("9781118102282");
//        isbn.setText("9781118983843");

        final Button searchBtn = (Button) findViewById(R.id.startSearch);

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

        JSONObject j = gq.doInBackground(isbnData);
        String items = "";

        try {
            items = j.getJSONArray("items").getJSONObject(0).getJSONObject("volumeInfo").getString("title");
            Log.d("Create Items", items);

            TextView result =  (TextView) findViewById(R.id.resultSearch);
            result.setText(items);
        } catch (JSONException e) {
            e.printStackTrace();
        }
//        if (j != null) {
//            Log.d("CREATE", "Json Created");
////            Log.d("Create", j.toString()   );
//
//            TextView result =  (TextView) findViewById(R.id.resultSearch);
//            result.setText(items);
//
//        }
//        else {
//            Log.d("CREATE", "Creation Failed");
//        }

    }
    private class DrawerItemClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView parent, View view, int position, long id) {
            switch(position){
                case 0: Toast.makeText(SearchForm.this, "You are already on the home page.", Toast.LENGTH_LONG);
                    break;
                case 1: Intent signupIntent = new Intent(SearchForm.this, SignupActivity.class);
                    startActivity(signupIntent);
                    break;
                case 2: Intent bookListIntent = new Intent(SearchForm.this, BookList.class);
                    startActivity(bookListIntent);
                    break;
                case 3: Intent userPofileIntent = new Intent(SearchForm.this, UserProfile.class);
                    startActivity(userPofileIntent);
                    break;
                case 4: Intent searchIntent = new Intent(SearchForm.this, SearchForm.class);
                    startActivity(searchIntent);
                    break;
            }
        }
    }



}
