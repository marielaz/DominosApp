package karikuncheva.dominosapp;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import karikuncheva.dominosapp.model.products.Dessert;
import karikuncheva.dominosapp.model.products.Drink;
import karikuncheva.dominosapp.model.products.Pizza;
import karikuncheva.dominosapp.model.products.Product;

/**
 * Created by Mariela Zviskova on 4.4.2017 г..
 */

public class SharedPreferenceCart {

    public static final String PREFS_NAME = "PRODUCT_APP";
    public static final String PRODUCTS = "Product_In_Cart";
    static JSONArray jsonProducts = null;
    JSONObject product = null;

    public SharedPreferenceCart() {
        super();
    }

    public JSONArray writeJSON(List<Product> products) {
        jsonProducts = new JSONArray();
        for (int i = 0; i < products.size(); i++) {
            Product p = products.get(i);
            product = new JSONObject();
            try {
                product.put("name", p.getName());
                product.put("description", p.getDescription());
                product.put("quantity", p.getQuantity());
                product.put("price", p.getPrice());
                product.put("pType", p.pType.toString());
                if (p.pType == Product.ProductType.PIZZA) {
                    product.put("size", p.getSize().toString());
                    product.put("type", p.getType().toString());
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
            jsonProducts.put(product);
        }
        return jsonProducts;

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
                //call the method
                String tempJson = writeJSON(products).toString();
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
                List<Product> products = getProducts(); // act
                if (products == null) {
                    products = new ArrayList<Product>();
                } else if (products.contains(prdct)) {
                    for (Product p : products) {
                        if (p.equals(prdct)) {
                            p.setQuantity(p.getQuantity() + 1);
                            break;
                        }
                    }
                } else {
                    products.add(prdct);
                }
                saveProducts(act, products);
                return null;
            }

        }.execute();
    }


    public void removeProduct(Activity activity, Product product) {
        ArrayList<Product> products = getProducts();
        if (products != null) {
            if (product.getQuantity() > 1) {
                for (Product p : products){
                    if (p.equals(product)){
                        p.setQuantity(p.getQuantity() -1);
                        break;
                    }
                }
            } else {
                products.remove(product);
            }
            saveProducts(activity, products);
        }
    }

    public ArrayList<Product> getProducts() {
        ArrayList<Product> products = new ArrayList<Product>();
        String name, desc, pType, size, type = null;
        int quantity = 0;
        double price = 0;
        JSONObject obj = null;

        if (jsonProducts != null) {
            for (int i = 0; i < jsonProducts.length(); i++) {
                try {
                    obj = jsonProducts.getJSONObject(i);
                    name = obj.getString("name");
                    desc = obj.getString("description");
                    quantity = obj.getInt("quantity");
                    price = obj.getDouble("price");
                    pType = obj.getString("pType");
                    if (pType.equals("PIZZA")) {
                        size = obj.getString("size");
                        type = obj.getString("type");
                        // make new products constructurs without imageId
                        Pizza pizza = new Pizza(name, price, desc);
                        pizza.setQuantity(quantity);
                        //TODO
                        // if we modify pizza, we must initialize size and type AGAIN!

                        pizza.setSize(Product.Size.valueOf(size.toUpperCase()));
                        pizza.setType(Product.Type.valueOf(type.toUpperCase()));
                        products.add(pizza);

                    } else if (pType.equals("DESSERT")) {
                        Dessert dessert = new Dessert(name, price, desc);
                        dessert.setQuantity(quantity);
                        products.add(dessert);
                    } else if (pType.equals("DRINK")) {
                        Drink drink = new Drink(name, price, desc);
                        drink.setQuantity(quantity);
                        products.add(drink);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }
        return products;
    }
}

