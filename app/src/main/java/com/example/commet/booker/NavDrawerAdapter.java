package com.example.commet.booker;
import android.app.Activity;
import android.content.res.TypedArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
/**
 * Created by bcom3_000 on 4/1/2016.
 */
public class NavDrawerAdapter extends ArrayAdapter<String>{
    private final Activity context;
    private final String[] itemname;
    private final TypedArray imgid;

        public NavDrawerAdapter(Activity context, String[] itemname, TypedArray images) {
        super(context, R.layout.nav_drawer_row, itemname);
        // TODO Auto-generated constructor stub

        this.context=context;
        this.itemname=itemname;
        this.imgid=images;
    }

    public View getView(int position,View view,ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.nav_drawer_row, null,true);

        TextView txtTitle = (TextView) rowView.findViewById(R.id.item);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.icon);

        txtTitle.setText(itemname[position]);
        imageView.setImageResource(imgid.getResourceId(position, 0));
        return rowView;

    };
}
