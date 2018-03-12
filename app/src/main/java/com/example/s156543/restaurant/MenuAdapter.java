package com.example.s156543.restaurant;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import java.util.ArrayList;

// Adapter form menu items
public class MenuAdapter extends ArrayAdapter<MenuItem> {

    ArrayList<MenuItem> menu;
    @NonNull
    @Override

    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        convertView = LayoutInflater.from(getContext()).inflate(R.layout.menu_item, parent, false);
        TextView descriptionView = (TextView) convertView.findViewById(R.id.name);
        TextView priceView = (TextView) convertView.findViewById(R.id.price);
        ImageView imageView = (ImageView) convertView.findViewById(R.id.image);

        // Display the menu with relevant entries
        MenuItem item = menu.get(position);
        descriptionView.setText(item.getName());
        priceView.setText(item.getPrice());

        // To display the image im using Picasso, which is referenced here:
        // http://square.github.io/picasso/
        Picasso.get().load(item.getImageUrl()).into(imageView);

        if (convertView == null){
        }

        return convertView;
    }

    public MenuAdapter(@NonNull Context context, int resource,
                           @NonNull ArrayList<MenuItem> menu) {
        super(context, resource, menu);
        this.menu = menu;
    }
}
