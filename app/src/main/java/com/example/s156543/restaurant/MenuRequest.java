package com.example.s156543.restaurant;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

// Request class to request the menu content
public class MenuRequest implements Response.Listener<JSONObject>,
        Response.ErrorListener {

    Context context;
    Callback callback;

    public MenuRequest(Context c) {

        context = c;
    }
    @Override
    public void onErrorResponse(VolleyError error) {
        callback.gotMenuError(error.getMessage());
    }

    // Called when request gets responded
    @Override
    public void onResponse(JSONObject response) {

        // Retrieve JSONarray from the response
        JSONArray a = null;
        try {
            a = response.getJSONArray("items");
        } catch (JSONException e) {
            callback.gotMenuError("Error in JSON");
            return;
        }
        ArrayList<MenuItem> menu = new ArrayList<>();

        // Add items from JSONarray in a menu arraylist
        for( int i = 0; i < a.length(); i++){
            try {
                menu.add(new MenuItem(a.getJSONObject(i)));
            } catch (JSONException e) {
                callback.gotMenuError("Error in MenuItem JSON");
                return;
            }
        }
        callback.gotMenu(menu);
    }

    public interface Callback {
        void gotMenu(ArrayList<MenuItem> menu);
        void gotMenuError(String message);
    }

    // Requests menu from the internet
    public void getMenu(Callback activity){
        callback = activity;
        RequestQueue queue = Volley.newRequestQueue(context);
        String url = "https://resto.mprog.nl/menu";

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(url, null, this, this);
        queue.add(jsonObjectRequest);

    }

}
