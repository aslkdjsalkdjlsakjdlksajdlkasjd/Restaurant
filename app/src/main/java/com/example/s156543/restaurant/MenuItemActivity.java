package com.example.s156543.restaurant;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

// Activity for selected menu Item
public class MenuItemActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_item);

        // Retrieve menuItem Object from the intent
        Intent intent = getIntent();
        Bundle menuclick = intent.getBundleExtra("BUNDLE");
        MenuItem menuItem = (MenuItem) menuclick.getSerializable("MenuItem");
        System.out.println(menuItem);

        // Display menuItem
        TextView descriptionView = (TextView) findViewById(R.id.description);
        TextView priceView = (TextView) findViewById(R.id.price);
        ImageView imageView = (ImageView) findViewById(R.id.image);
        TextView nameView = (TextView) findViewById(R.id.name);

        descriptionView.setText(menuItem.getDescription());
        priceView.setText(menuItem.getPrice());
        nameView.setText(menuItem.getName());
        Picasso.get().load(menuItem.getImageUrl()).into(imageView);
    }
}
