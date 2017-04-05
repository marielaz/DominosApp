package karikuncheva.dominosapp;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.AsyncTask;

import com.google.gson.Gson;
import com.google.gson.JsonArray;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import karikuncheva.dominosapp.model.products.Product;

/**
 * Created by Mariela Zviskova on 4.4.2017 г..
 */

public class SharedPreferenceCart {

    public static final String PREFS_NAME = "PRODUCT_APP";
    public static final String PRODUCTS = "Product_In_Cart";
    JSONArray jsonProducts = null;

    public SharedPreferenceCart() {
        super();
    }

    public String writeJSON(List<Product> products) {
        jsonProducts = new JSONArray();
        for (int i = 0; i < products.size(); i++) {
            Product p = products.get(i);
            JSONObject product = new JSONObject();
            try {
                product.put("name", p.getName());
                product.put("description", p.getDescription());
                product.put("quantity", p.getQuantity());
                product.put("price", p.getPrice());
                if (p.pType == Product.ProductType.PIZZA) {
                    product.put("price", p.getDiscPrice());
                    product.put("size", p.getSize());
                    product.put("type", p.getType());
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
            jsonProducts.put(p);
        }
        return jsonProducts.toString();
    }

    // This four methods are used for maintaining products.
    public void saveProducts(final Activity activity, final List<Product> products) {

        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {
                SharedPreferences settings;
                SharedPreferences.Editor editor;
                Activity act = activity;
                List<Product> pr = products;

                settings = act.getSharedPreferences(PREFS_NAME,
                        Activity.MODE_PRIVATE);
                editor = settings.edit();
                String tempJson = writeJSON(products);
                editor.putString(PRODUCTS, tempJson);
                editor.commit();
                return null;
            }
        }.execute();
    }

    public void addProduct(final Activity activity, final Product product) {
        new AsyncTask<Void, Void, Void>() {
            Activity act = activity;
            Product prdct = product;

            @Override
            protected Void doInBackground(Void... params) {
                List<Product> products = getProducts(act);
                if (products == null) {
                    products = new ArrayList<Product>();
                } else if (products.contains(prdct)) {
                    prdct.setQuantity(prdct.getQuantity() + 1);
                } else {
                    products.add(prdct);
                }
                saveProducts(act, products);
                return null;
            }

        }.execute();
    }


    public void removeProduct(Activity activity, Product product) {
        ArrayList<Product> products = getProducts(activity);
        if (products != null) {
            if (product.getQuantity() > 1) {
                product.setQuantity(product.getQuantity() - 1);
            } else {
                products.remove(product);
            }
            saveProducts(activity, products);
        }
    }

    public ArrayList<Product> getProducts(Activity activity) {
        SharedPreferences settings;
        ArrayList<Product> products;

        settings = activity.getSharedPreferences(PREFS_NAME,
                Activity.MODE_PRIVATE);

        ArrayList<String> temp = new ArrayList<>();
        products = new ArrayList<Product>();
        JSONArray jArray = jsonProducts;
        if (jArray != null) {
            for (int i = 0; i < jsonProducts.length(); i++) {
                try {
                    temp.add(jsonProducts.get(i).toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                Product p = null;
                //   p.setQuantity(jsonProducts.getString("quantity"));
            }
        }
//            if (settings.contains(PRODUCTS)) {
//            String jsonProducts = settings.getString(PRODUCTS, null);
//            Gson gson = new Gson();
//            Product[] cartItems = gson.fromJson(jsonProducts,
//                    Product[].class);
//
//            products = Arrays.asList(cartItems);
//            products = new ArrayList<Product>(products);
//        } else
//            return null;

        return (ArrayList<Product>) products;
    }
}

