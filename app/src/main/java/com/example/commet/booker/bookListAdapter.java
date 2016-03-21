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
public class BookListAdapter extends ArrayAdapter<BookData> {
    private Context currentContext;
    //This list holds the items in the listview
    private List<BookData> listViewData;

    public BookListAdapter(Context context, List<BookData> objects){
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

        BookData currentBookData = listViewData.get(position);
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
