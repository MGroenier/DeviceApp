package com.lastdown.deviceapp.deviceapp;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Martijn on 18/09/2016.
 */
public class ViewHolder {

    private ImageView image;
    private TextView title;

    public ViewHolder (View view){
        image = (ImageView) view.findViewById(R.id.imageView);
        title = (TextView) view.findViewById(R.id.textView);
    }

    public void populateRow(GridItem item) {
        //Set the icon for this row
        image.setImageResource(item.getImage());
        //Set the title for this row
        title.setText(item.getTitle());
    }


}
