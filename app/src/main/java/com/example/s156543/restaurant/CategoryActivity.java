// Minor Programmeren Unit 5 - Restaurant
// An app that will help users look at a restaurant’s menu
// and view the details of the items in the menu.\

// Nina Boelsums 10742670

package com.example.s156543.restaurant;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Objects;

public class CategoryActivity extends AppCompatActivity implements CategoriesRequest.Callback{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        System.out.println("l1all");

        CategoriesRequest request = new CategoriesRequest(this);
        request.getCategories(this);

        ListView catListView = findViewById(R.id.categoriesList);
        catListView.setOnItemClickListener(new ListViewClickListener());
    }

    // Request the menu categories from the internet
    @Override
    public void gotCategories(ArrayList<String> categories) {
        ArrayAdapter<String> categoryAdapter =
                new CategoryAdapter(this, R.layout.grid_item, categories);
        ListView lv = findViewById(R.id.categoriesList);

        lv.setAdapter(categoryAdapter);
    }

    // Error handling, in case the request goes wrong
    @Override
    public void gotCategoriesError(String message) {
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(this, message, duration);
        toast.show();
    }

    // Listener on the Lisview which checks whether a user clicks a category
    private class ListViewClickListener implements AdapterView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            String categoryName = Objects.toString(adapterView.getItemAtPosition(i));

            // Clicked category is passed to the MenuActivity
            Intent intent = new Intent(CategoryActivity.this, MenuActivity.class);
            intent.putExtra("categoryName", categoryName);
            startActivity(intent);
        }
    }
}
