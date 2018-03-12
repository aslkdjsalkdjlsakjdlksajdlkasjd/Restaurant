package com.example.s156543.restaurant;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.Objects;

// MenuItem Class
public class MenuItem implements Serializable{

    String name;
    String description;
    String imageUrl;
    String price;
    String category;

    public MenuItem(String name, String description, String imageUrl,
    String price, String category) {
        setCategory(category);
        setDescription(description);
        setImageUrl(imageUrl);
        setPrice(price);
        setName(name);

    }

    // Constructs this MenuItem with the values found in the JSONObjects
    public MenuItem(JSONObject o) throws JSONException {
            this(o.getString("name"), o.getString("description"),
                    o.getString("image_url"), Objects.toString(o.getDouble("price")),
                    o.getString("category"));
    }

    public String getName(){
        return name;
    }

    public String getDescription(){
        return description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getPrice() {
        return price;
    }

    public String getCategory() {

        return category;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setName(String name) {
        this.name = name;
    }
}
