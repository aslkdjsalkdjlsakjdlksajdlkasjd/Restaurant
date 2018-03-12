package com.example.s156543.restaurant;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;

// Activity for the menu of the selected category
public class MenuActivity extends AppCompatActivity implements MenuRequest.Callback {
    String categoryName;
    ArrayList<MenuItem> filteredMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        // Retrieve clicked category
        Intent intent = getIntent();
        categoryName = (String) intent.getSerializableExtra("categoryName");

        // Request menu content
        MenuRequest menuRequest = new MenuRequest(this);
        menuRequest.getMenu(this);
    }

    // Handle retrieved menu
    @Override
    public void gotMenu(ArrayList<MenuItem> menu) {
        filteredMenu = new ArrayList<MenuItem>();

        // Filter the full menu for menu items of the selected category
        for (MenuItem mi : menu) {
            if (mi.getCategory().equals(categoryName))
                filteredMenu.add(mi);
        }

        // Display menu and listen for clicks
        ArrayAdapter<MenuItem> menuAdapter =
                new MenuAdapter(this, R.layout.menu_item, filteredMenu);
        ListView menuList = findViewById(R.id.menuList);
        menuList.setAdapter(menuAdapter);
        menuList.setOnItemClickListener(new ListViewClickListener());
    }


    @Override
    public void gotMenuError(String message) {
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(this, message, duration);
        toast.show();
    }

    // Listener for clikcs
    private class ListViewClickListener implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            MenuItem intentItem = (MenuItem) adapterView.getItemAtPosition(i);

            // Pass selected MenuItem object to the MenuItemActivity.
            Intent intent = new Intent(MenuActivity.this, MenuItemActivity.class);
            Bundle menuClick = new Bundle();
            menuClick.putSerializable("MenuItem",(Serializable)intentItem);
            intent.putExtra("BUNDLE", menuClick);
            startActivity(intent);

        }
    }
}
