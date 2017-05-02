package karikuncheva.dominosapp.cart;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Map;

import karikuncheva.dominosapp.catalog.CatalogActivity;
import karikuncheva.dominosapp.LoginActivity;
import karikuncheva.dominosapp.R;
import karikuncheva.dominosapp.model.products.Pizza;
import karikuncheva.dominosapp.model.products.Product;

/**
 * Created by Karina Kuncheva on 3/25/2017.
 */

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {

    private Activity activity;
    private ArrayList<Product> productsInCart;
    static double total;


    public CartAdapter(Activity activity) {
        this.activity = activity;
        productsInCart = new ArrayList<Product>();
        total = 0;

        for (Map.Entry<String, HashSet<Product>> products : LoginActivity.loggedUser.getCart().getProducts().entrySet()) {
            for (Product p : products.getValue()) {
                productsInCart.add(p);
                if (p.getpType().equals("pizza")) {
                    total += p.getQuantity() * p.getDiscPrice();
                } else {
                    total += p.getQuantity() * p.getPrice();
                }
            }
        }
    }

    public CartAdapter(Activity activity, Product p) {
        this.activity = activity;
        productsInCart = new ArrayList<Product>();
        total = 0;

        LoginActivity.loggedUser.getCart().addProduct(p);
        for (Map.Entry<String, HashSet<Product>> products : LoginActivity.loggedUser.getCart().getProducts().entrySet()) {
            for (Product p1 : products.getValue()) {
                productsInCart.add(p1);
                if (p1.getpType().equals("pizza")) {
                    total += p1.getQuantity() * p1.getDiscPrice();
                } else {
                    total += p1.getQuantity() * p1.getPrice();
                }
            }
        }

    }


    @Override
    public CartViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater li = LayoutInflater.from(activity);
        View row = li.inflate(R.layout.single_row_cart, parent, false);
        CartAdapter.CartViewHolder vh = new CartViewHolder(row);
        return vh;
    }


    @Override
    public void onBindViewHolder(final CartViewHolder vh, final int position) {


        final Product product = productsInCart.get(position);
        vh.plus_product.setVisibility(View.VISIBLE);
        vh.minus_product.setVisibility(View.VISIBLE);
        vh.dicsount_cart_tv.setVisibility(View.VISIBLE);
        vh.quantity.setText(String.valueOf(product.getQuantity()));
        vh.p_name_in_cart.setText(product.getName());
        vh.price_in_cart.setText(String.format("%.2f", product.getQuantity() * product.getPrice()));

        if (!product.getpType().equals("pizza")) {
            vh.dicsount_cart_tv.setText("");
            vh.description_cart_tv.setText("");
            vh.descr_type.setText("");
            vh.disc_price_in_cart.setText("");
            vh.price_in_cart.setPaintFlags(vh.price_in_cart.getPaintFlags() & (~Paint.STRIKE_THRU_TEXT_FLAG));
        }
        if (product.getPrice() == 0.00) {
            vh.plus_product.setVisibility(View.INVISIBLE);
            vh.minus_product.setVisibility(View.INVISIBLE);
            vh.description_cart_tv.setText("BONUS");
            vh.dicsount_cart_tv.setVisibility(View.GONE);
            vh.price_in_cart.setText("");
            vh.quantity.setText("");
            vh.description_cart_tv.setTextColor(Color.argb(255, 212, 8, 59));
        }

        if (product.getpType().equals("pizza")) {
            vh.price_in_cart.setText(String.format("%.2f", product.getQuantity() * product.getPrice()));
            vh.price_in_cart.setPaintFlags(vh.price_in_cart.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            vh.disc_price_in_cart.setText(String.format("%.2f", product.getQuantity() * product.getDiscPrice()));
            //TODO to find way to get pizza size and type
            Pizza pizza = (Pizza) product;
            vh.description_cart_tv.setText(pizza.getSize());
            vh.description_cart_tv.setTextColor(Color.argb(255, 130, 130, 130));
            vh.descr_type.setText(pizza.getType());
            vh.dicsount_cart_tv.setText("5% Discount");
        }

        vh.plus_product.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CatalogActivity.count++;
                LoginActivity.loggedUser.getCart().addProduct(product);

                vh.quantity.setText(String.valueOf(product.getQuantity()));
                if (product.getpType().equals("pizza")) {
                    vh.price_in_cart.setText(String.format("%.2f", product.getQuantity() * product.getPrice()));
                    vh.disc_price_in_cart.setText(String.format("%.2f", product.getQuantity() * product.getDiscPrice()));
                    total = total + product.getDiscPrice();
                    ((CartListFragment.CartComunicator) activity).sumTotalPrice(total);
                } else {
                    vh.price_in_cart.setText(String.format("%.2f", product.getQuantity() * product.getPrice()));
                    total = total + product.getPrice();
                    ((CartListFragment.CartComunicator) activity).sumTotalPrice(total);
                }
            }
        });

        vh.minus_product.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
               CatalogActivity.count--;
                if (product.getQuantity() == 1) {
                    if (product.getpType().equals("pizza")) {
                        total = total - product.getDiscPrice();
                        ((CartListFragment.CartComunicator) activity).sumTotalPrice(total);
                    } else {
                        total = total - product.getPrice();
                        ((CartListFragment.CartComunicator) activity).sumTotalPrice(total);
                    }
                    productsInCart.remove(product);
                    LoginActivity.loggedUser.getCart().removeProduct(product);
                    // notify the adapter to remove the product from the recyclerview
                    notifyDataSetChanged();

                } else {
                    LoginActivity.loggedUser.getCart().removeProduct(product);

                    if (product.getpType().equals("pizza")) {
                        total = total - product.getDiscPrice();
                        ((CartListFragment.CartComunicator) activity).sumTotalPrice(total);
                    } else {
                        total = total - product.getPrice();
                        ((CartListFragment.CartComunicator) activity).sumTotalPrice(total);
                    }

                    vh.quantity.setText(String.valueOf(product.getQuantity()));
                    if (product.getpType().equals("pizza")) {
                        double tempSum = Double.parseDouble(vh.price_in_cart.getText().toString()) - product.getPrice();
                        double tempDics = Double.parseDouble(vh.disc_price_in_cart.getText().toString()) - product.getDiscPrice();
                        vh.price_in_cart.setText(String.format("%.2f", tempSum));
                        vh.disc_price_in_cart.setText(String.format("%.2f", tempDics));

                    } else {
                        double tempSum = Double.parseDouble(vh.price_in_cart.getText().toString()) - product.getPrice();
                        vh.price_in_cart.setText(String.format("%.2f", tempSum));
                    }
                }
            }
        });

    }


    @Override
    public int getItemCount() {

        return productsInCart.size();
    }

    class CartViewHolder extends RecyclerView.ViewHolder {
        TextView p_name_in_cart;
        ImageButton plus_product;
        ImageButton minus_product;
        TextView dicsount_cart_tv;
        TextView description_cart_tv;
        TextView price_in_cart;
        TextView disc_price_in_cart;
        TextView quantity;
        TextView descr_type;

        CartViewHolder(View row) {
            super(row);
            p_name_in_cart = (TextView) row.findViewById(R.id.p_name_in_cart);
            plus_product = (ImageButton) row.findViewById(R.id.cart_plus_img);
            minus_product = (ImageButton) row.findViewById(R.id.cart_minus_img);
            dicsount_cart_tv = (TextView) row.findViewById(R.id.dicsount_cart_tv);
            description_cart_tv = (TextView) row.findViewById(R.id.description_cart_tv);
            price_in_cart = (TextView) row.findViewById(R.id.price_in_cart);
            disc_price_in_cart = (TextView) row.findViewById(R.id.dics_price_in_cart);
            quantity = (TextView) row.findViewById(R.id.cart_product_quantity_tv);
            descr_type = (TextView) row.findViewById(R.id.descr_type);
        }
    }
}
