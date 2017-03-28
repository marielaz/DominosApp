package karikuncheva.dominosapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.os.DropBoxManager;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import karikuncheva.dominosapp.model.User;
import karikuncheva.dominosapp.model.products.Pizza;
import karikuncheva.dominosapp.model.products.Product;

import static karikuncheva.dominosapp.model.products.Pizza.Type.TRADITIONAL;

/**
 * Created by Karina Kuncheva on 3/25/2017.
 */

public class CartAdapter extends ArrayAdapter<String> {

    private Activity activity;
    private User user;
    private  ArrayList<Product> productsInCart;
    static double total;
    private Pizza.Type type;

    class CartViewHolder {
        View row;
        TextView p_name_in_cart;
        ImageButton plus_product;
        ImageButton minus_product;
        TextView dicsount_cart_tv;
        TextView description_cart_tv;
        TextView price_in_cart;
        TextView disc_price_in_cart;
        TextView quantity;

        CartViewHolder(View row){

            p_name_in_cart = (TextView) row.findViewById(R.id.p_name_in_cart);
            plus_product = (ImageButton) row.findViewById(R.id.cart_plus_img);
            minus_product = (ImageButton) row.findViewById(R.id.cart_minus_img);
            dicsount_cart_tv = (TextView) row.findViewById(R.id.dicsount_cart_tv);
            description_cart_tv = (TextView) row.findViewById(R.id.description_cart_tv);
            price_in_cart = (TextView) row.findViewById(R.id.price_in_cart);
            disc_price_in_cart = (TextView) row.findViewById(R.id.dics_price_in_cart);
            quantity = (TextView) row.findViewById(R.id.cart_product_quantity_tv);
        }

    }


    public CartAdapter(Activity activity, User user) {
        super(activity, R.layout.single_row_cart);
        this.activity = activity;
        this.user = user;
        this.productsInCart = new ArrayList<Product>();
        total = 0;

        for (Map.Entry<Product.ProductType, HashSet<Product>> products : user.getCart().getProducts().entrySet()) {
            for (Product p1 : products.getValue()) {
                this.productsInCart.add(p1);
                if (p1.pType == Product.ProductType.PIZZA){
                    total += p1.getQuantity()*p1.getDiscPrice();
                }
                else{
                    total += p1.getQuantity()*p1.getPrice();
                }
            }
        }
    }

    @Override
    public int getCount() {
        return productsInCart.size();
    }

    static double getTotal(){
        return  total;
    }
    @NonNull
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        //convert xml to java with inflater
        LayoutInflater inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row;
        CartViewHolder vh;

        if (convertView == null) {
            row = inflater.inflate(R.layout.single_row_cart, parent, false);
            vh = new CartViewHolder(row);
            row.setTag(vh);
        }else{
            row = convertView;
            vh = (CartViewHolder) row.getTag();
            vh.price_in_cart.setPaintFlags( vh.price_in_cart.getPaintFlags() & (~ Paint.STRIKE_THRU_TEXT_FLAG));
        }

        TextView p_name_in_cart = vh.p_name_in_cart;
        ImageButton plus_product = vh.plus_product;
        ImageButton minus_product = vh.minus_product;
        TextView description_cart_tv = vh.description_cart_tv;
        TextView dicsount_cart_tv = vh.dicsount_cart_tv;
        final TextView price_in_cart = vh.price_in_cart;
        final TextView disc_price_in_cart = vh.disc_price_in_cart;
        final TextView quantity = vh.quantity;

        dicsount_cart_tv.setText("");
        description_cart_tv.setText(""); //Large Traditional
        disc_price_in_cart.setText("");
        quantity.setText(String.valueOf(productsInCart.get(position).getQuantity()));
        p_name_in_cart.setText(productsInCart.get(position).getName());


        if (productsInCart.get(position).pType == Product.ProductType.PIZZA){
            price_in_cart.setText(String.format("%.2f",productsInCart.get(position).getQuantity() * productsInCart.get(position).getPrice()));
            price_in_cart.setPaintFlags(price_in_cart.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            disc_price_in_cart.setText(String.format("%.2f",productsInCart.get(position).getQuantity() * productsInCart.get(position).getDiscPrice()));
            description_cart_tv.setText("Large Traditional");//  size i type
            dicsount_cart_tv.setText("5% Discount");
        }
        else{
            price_in_cart.setText(String.format("%.2f",productsInCart.get(position).getQuantity() * productsInCart.get(position).getPrice()));
        }

        View.OnClickListener plusListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user.getCart().addProduct(productsInCart.get(position));
                quantity.setText(String.valueOf(productsInCart.get(position).getQuantity()));

                if (productsInCart.get(position).pType == Product.ProductType.PIZZA){
                    price_in_cart.setText(String.format("%.2f",productsInCart.get(position).getQuantity() * productsInCart.get(position).getPrice()));
                    disc_price_in_cart.setText(String.format("%.2f",productsInCart.get(position).getQuantity() * productsInCart.get(position).getDiscPrice()));
                }
                else {
                    price_in_cart.setText(String.format("%.2f",productsInCart.get(position).getQuantity() * productsInCart.get(position).getPrice()));
                }
            }
        };

        plus_product.setOnClickListener(plusListener);

        minus_product.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                if (productsInCart.get(position).getQuantity() == 1){
                    user.getCart().removeProduct(productsInCart.get(position));
                    productsInCart.remove(productsInCart.get(position));
                    // notify the adapter to remove the product from the listview
                    notifyDataSetChanged();
                }
                else {
                    user.getCart().removeProduct(productsInCart.get(position));
                    quantity.setText(String.valueOf(productsInCart.get(position).getQuantity()));
                    if (productsInCart.get(position).pType == Product.ProductType.PIZZA){
                        double tempSum = Double.parseDouble(price_in_cart.getText().toString())- productsInCart.get(position).getPrice();
                        double tempDics = Double.parseDouble(disc_price_in_cart.getText().toString())- productsInCart.get(position).getDiscPrice();
                        price_in_cart.setText(String.format("%.2f", tempSum));
                        disc_price_in_cart.setText(String.format("%.2f", tempDics));
                    }
                    else{
                        double tempSum = Double.parseDouble(price_in_cart.getText().toString())- productsInCart.get(position).getPrice();
                        price_in_cart.setText(String.format("%.2f", tempSum));
                    }
                }
            }
        });

        return row;
    }
}