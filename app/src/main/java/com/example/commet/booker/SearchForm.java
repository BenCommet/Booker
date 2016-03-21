package com.example.commet.booker;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

public class SearchForm extends AppCompatActivity {
    private GoogleQuery gq = new GoogleQuery();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_form);

        final EditText isbn = (EditText) findViewById(R.id.isbnSearch);
        isbn.setText("9781118102282");
        final Button searchBtn = (Button) findViewById(R.id.startSearch);

        searchBtn.setOnClickListener(new View.OnClickListener() {
            final String isbnData = isbn.getText().toString();
            @Override
            public void onClick(View v) {
                search(isbnData);
            }
        });

    }


    private void search(String isbnData) {

        JSONObject j = gq.doInBackground(isbnData);
        if (j != null) {
            Log.d("CREATE", "Json Created");
            try {
                Log.d("Create", j.getString("title"));

                TextView result =  (TextView) findViewById(R.id.resultSearch);
                result.setText(j.getString("title"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        else {
            Log.d("CREATE", "Creation Failed");
        }

    }


}
