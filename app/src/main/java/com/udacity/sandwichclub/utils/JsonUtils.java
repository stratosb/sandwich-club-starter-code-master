package com.udacity.sandwichclub.utils;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    public static Sandwich parseSandwichJson(String json) {
        Sandwich sandwich = new Sandwich();

        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONObject nameObject = jsonObject.getJSONObject("name");
            sandwich.setMainName(nameObject.getString("mainName"));
            sandwich.setImage(jsonObject.getString("image"));
            sandwich.setPlaceOfOrigin(jsonObject.getString("placeOfOrigin"));
            sandwich.setDescription(jsonObject.getString("description"));

            JSONArray akaArray = nameObject.getJSONArray("alsoKnownAs");
            if (akaArray != null && akaArray.length() > 0) {
                List<String> alsoKnownAs = new ArrayList<String>();
                for (int i = 0; i < akaArray.length(); i++) {
                    alsoKnownAs.add(akaArray.getString(i));
                }
                sandwich.setAlsoKnownAs(alsoKnownAs);
            }

            JSONArray ingredientsArray = jsonObject.getJSONArray("ingredients");
            if (ingredientsArray != null && ingredientsArray.length() > 0) {
                List<String> ingredients = new ArrayList<String>();
                for (int i=0; i < ingredientsArray.length(); i++) {
                    ingredients.add(ingredientsArray.getString(i));
                }
                sandwich.setIngredients(ingredients);
            }
        } catch (JSONException ex) {
            ex.printStackTrace();
            return null;
        }

        return sandwich;
    }
}
