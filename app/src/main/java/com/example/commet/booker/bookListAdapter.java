package com.example.commet.booker;

import android.content.Context;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * This is the class that actually calls our parse querying methods and then puts the data
 * we get into the a textview, that is then added to the listview.
 */
public class bookListAdapter extends ArrayAdapter<bookData> {
    private Context currentContext;
    //This list holds the items in the listview
    private List<bookData> listViewData;

    public bookListAdapter(Context context, List<bookData> objects){
        super(context, R.layout.book_list_item, objects);
        this.currentContext = context;
        this.listViewData = objects;
    }

    public View getView(int position, View convertView, ViewGroup bookGroup){
        //if layout is not yet instantiated, this will inflate it
        if(convertView == null){
            LayoutInflater layoutInflater = LayoutInflater.from(currentContext);
            convertView = layoutInflater.inflate(R.layout.book_list_item, null);
        }


        bookData currentBookData = listViewData.get(position);
        TextView currentTextView = (TextView) convertView.findViewById(R.id.book_description);
        currentTextView.setText(currentBookData.getISBN());
        if(currentBookData.isCompleted()){
            currentTextView.setPaintFlags(currentTextView.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        }else{
            currentTextView.setPaintFlags(currentTextView.getPaintFlags() & (~Paint.STRIKE_THRU_TEXT_FLAG));
        }

        return convertView;
    }
}
