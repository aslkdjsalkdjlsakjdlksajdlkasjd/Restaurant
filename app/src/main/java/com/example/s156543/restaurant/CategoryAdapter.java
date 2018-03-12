package com.example.s156543.restaurant;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class CategoryAdapter extends ArrayAdapter<String> {

    ArrayList<String> categories;
    @NonNull
    @Override


    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView = LayoutInflater.from(getContext()).inflate(R.layout.grid_item, parent, false);

        TextView categoryView = (TextView) convertView.findViewById(R.id.categoryTitle);
        String category = categories.get(position);
        categoryView.setText(category);

        if (convertView == null){

        }
    return convertView;
    }

    public CategoryAdapter(@NonNull Context context, int resource,
                           @NonNull ArrayList<String> categories) {

        super(context, resource, categories);


        this.categories = categories;


    }
}
