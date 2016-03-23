package com.example.commet.booker;

        import android.content.Context;
        import android.os.StrictMode;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.ArrayAdapter;
        import android.widget.TextView;

        import org.json.JSONException;

/**
 * Created by Commet on 3/23/2016.
 */
public class LocalAdapter extends ArrayAdapter<String> {
    LocalAdapter(Context context, String[] books){
        super(context, R.layout.book_list_item, books);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        LayoutInflater bookInflater = LayoutInflater.from(getContext());
        View customView = bookInflater.inflate(R.layout.book_list_item, parent, false);

        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        String currentISBN = getItem(position);
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
        Book currentBook = new Book(currentISBN);
        TextView titleText = (TextView) customView.findViewById(R.id.book_description);
        titleText.setText(currentBook.title);
        return customView;
    }
}