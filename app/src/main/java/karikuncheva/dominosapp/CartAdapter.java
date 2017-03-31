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

    public CartAdapter(Activity activity, User user) {
        this.activity = activity;
        this.user = user;
        this.productsInCart = new ArrayList<Product>();
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
        vh.dicsount_cart_tv.setText("");
        vh.description_cart_tv.setText(""); //Large Traditional
        vh.disc_price_in_cart.setText("");
        vh.quantity.setText(String.valueOf(productsInCart.get(position).getQuantity()));
        vh.p_name_in_cart.setText(productsInCart.get(position).getName());


        if (productsInCart.get(position).pType == Product.ProductType.PIZZA) {
            vh.price_in_cart.setText(String.format("%.2f", productsInCart.get(position).getQuantity() * productsInCart.get(position).getPrice()));
            vh.price_in_cart.setPaintFlags(vh.price_in_cart.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            vh.disc_price_in_cart.setText(String.format("%.2f", productsInCart.get(position).getQuantity() * productsInCart.get(position).getDiscPrice()));

            vh.description_cart_tv.setText(productsInCart.get(position).getSize().toString());
            vh.descr_type.setText(productsInCart.get(position).getType().toString());
            vh.dicsount_cart_tv.setText("5% Discount");
        } else {
            vh.price_in_cart.setText(String.format("%.2f", productsInCart.get(position).getQuantity() * productsInCart.get(position).getPrice()));
        }

        View.OnClickListener plusListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user.getCart().addProduct(productsInCart.get(position));
                vh.quantity.setText(String.valueOf(productsInCart.get(position).getQuantity()));

                if (productsInCart.get(position).pType == Product.ProductType.PIZZA) {
                    vh.price_in_cart.setText(String.format("%.2f", productsInCart.get(position).getQuantity() * productsInCart.get(position).getPrice()));
                    vh.disc_price_in_cart.setText(String.format("%.2f", productsInCart.get(position).getQuantity() * productsInCart.get(position).getDiscPrice()));
                } else {
                    vh.price_in_cart.setText(String.format("%.2f", productsInCart.get(position).getQuantity() * productsInCart.get(position).getPrice()));

                }
            }
        };

        vh.plus_product.setOnClickListener(plusListener);

        vh.minus_product.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (productsInCart.get(position).getQuantity() == 1) {
                    user.getCart().removeProduct(productsInCart.get(position));
                    productsInCart.remove(productsInCart.get(position));
                    // notify the adapter to remove the product from the listview
                    notifyDataSetChanged();
                } else {
                    user.getCart().removeProduct(productsInCart.get(position));
                    vh.quantity.setText(String.valueOf(productsInCart.get(position).getQuantity()));
                    if (productsInCart.get(position).pType == Product.ProductType.PIZZA) {
                        double tempSum = Double.parseDouble(vh.price_in_cart.getText().toString()) - productsInCart.get(position).getPrice();
                        double tempDics = Double.parseDouble(vh.disc_price_in_cart.getText().toString()) - productsInCart.get(position).getDiscPrice();
                        vh.price_in_cart.setText(String.format("%.2f", tempSum));
                        vh.disc_price_in_cart.setText(String.format("%.2f", tempDics));
                        total += productsInCart.get(position).getQuantity() * productsInCart.get(position).getDiscPrice();
                    } else {
                        double tempSum = Double.parseDouble(vh.price_in_cart.getText().toString()) - productsInCart.get(position).getPrice();
                        vh.price_in_cart.setText(String.format("%.2f", tempSum));
                        total += productsInCart.get(position).getQuantity() * productsInCart.get(position).getPrice();
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
