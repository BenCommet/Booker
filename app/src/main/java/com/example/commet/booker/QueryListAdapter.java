package com.example.commet.booker;

import android.app.ListActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ArrayAdapter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class QueryListAdapter extends ListActivity {

    Bundle bundle = getIntent().getExtras();
    Book data = (Book) bundle.getSerializable("data");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_query_list_adapter);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);



//        ArrayAdapter adapter = new ArrayAdapter<GoogleQuery>(this, R.layout.activity_query_list_adapter, data);
//        setListAdapter(adapter);
    }

}
