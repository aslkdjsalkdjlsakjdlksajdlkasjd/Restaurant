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

/**
 * Created by s156543 on 8-3-2018.
 */

    public class CategoriesRequest implements Response.Listener<JSONObject>,
        Response.ErrorListener {

    Context context;
    Callback callback;

    public CategoriesRequest(Context c) {
        context = c;
    }
    @Override
    public void onErrorResponse(VolleyError error) {
        callback.gotCategoriesError(error.getMessage());
    }

    @Override
    public void onResponse(JSONObject response) {

        JSONArray a = null;
        try {
            a = response.getJSONArray("categories");
        } catch (JSONException e) {
            e.printStackTrace();
            return;
        }
        ArrayList<String> categories = new ArrayList<>();

        for( int i = 0; i < a.length(); i++){
            try {
                categories.add(a.getString(i));
            } catch (JSONException e) {

            }
        }
        callback.gotCategories(categories);
    }




    public interface Callback {
        void gotCategories(ArrayList<String> categories);
        void gotCategoriesError(String message);
    }



    public void getCategories(Callback activity){
        callback = activity;
        RequestQueue queue = Volley.newRequestQueue(context);
        String url = "https://resto.mprog.nl/categories";


        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(url, null, this, this);
                queue.add(jsonObjectRequest);

    }

}
