package karikuncheva.dominosapp;

import android.app.Activity;
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

import karikuncheva.dominosapp.model.User;
import karikuncheva.dominosapp.model.products.Pizza;
import karikuncheva.dominosapp.model.products.Product;

/**
 * Created by Karina Kuncheva on 3/25/2017.
 */

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {

    private Activity activity;
    private User user;
    private ArrayList<Product> productsInCart;
    static double total;
    private Pizza.Type type;
    SharedPreferenceCart sharedPreferenceCart;

    public CartAdapter(Activity activity, User user) {
        this.activity = activity;
        this.user = user;
       // this.productsInCart = new ArrayList<Product>();
        sharedPreferenceCart = new SharedPreferenceCart();
        this.productsInCart = sharedPreferenceCart.getProducts(activity);
        total = 0;

        for (Map.Entry<Product.ProductType, HashSet<Product>> products : user.getCart().getProducts().entrySet()) {
            for (Product p1 : products.getValue()) {
                this.productsInCart.add(p1);
                if (p1.pType == Product.ProductType.PIZZA) {
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

    static double getTotal() {
        return total;
    }


    @Override
    public void onBindViewHolder(final CartViewHolder vh, final int position) {
        // problem when single_row_cart is reused
        final Product product = productsInCart.get(position);
        if (product.pType != Product.ProductType.PIZZA){
            vh.price_in_cart.setPaintFlags( vh.price_in_cart.getPaintFlags() & (~ Paint.STRIKE_THRU_TEXT_FLAG));
            vh.dicsount_cart_tv.setText("");
            vh.description_cart_tv.setText("");
            vh.disc_price_in_cart.setText("");
        }
        vh.quantity.setText(String.valueOf(product.getQuantity()));
        vh.p_name_in_cart.setText(product.getName());


        if (product.pType == Product.ProductType.PIZZA) {
            vh.price_in_cart.setText(String.format("%.2f", product.getQuantity() * product.getPrice()));
            vh.price_in_cart.setPaintFlags(vh.price_in_cart.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            vh.disc_price_in_cart.setText(String.format("%.2f", product.getQuantity() * product.getDiscPrice()));

            vh.description_cart_tv.setText(product.getSize().toString());
            vh.descr_type.setText(product.getType().toString());
            vh.dicsount_cart_tv.setText("5% Discount");
        } else {
            vh.price_in_cart.setText(String.format("%.2f", product.getQuantity() * product.getPrice()));
        }

        vh.plus_product.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user.getCart().addProduct(product);
                vh.quantity.setText(String.valueOf(product.getQuantity()));

                if (product.pType == Product.ProductType.PIZZA) {
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
                if (product.getQuantity() == 1) {
                    if (product.pType == Product.ProductType.PIZZA) {
                        total = total - product.getDiscPrice();
                        ((CartListFragment.CartComunicator) activity).sumTotalPrice(total);
                    } else {
                        total = total - product.getPrice();
                        ((CartListFragment.CartComunicator) activity).sumTotalPrice(total);
                    }
                    user.getCart().removeProduct(product);
                    productsInCart.remove(product);
                    // notify the adapter to remove the product from the recyclerview
                    notifyDataSetChanged();
                } else {
                    if (product.pType == Product.ProductType.PIZZA) {
                        total = total - product.getDiscPrice();
                        ((CartListFragment.CartComunicator) activity).sumTotalPrice(total);
                    } else {
                        total = total - product.getPrice();
                        ((CartListFragment.CartComunicator) activity).sumTotalPrice(total);
                    }
                    user.getCart().removeProduct(product);
                    vh.quantity.setText(String.valueOf(product.getQuantity()));
                    if (product.pType == Product.ProductType.PIZZA) {
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
        View row;
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
