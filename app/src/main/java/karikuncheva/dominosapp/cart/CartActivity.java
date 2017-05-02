package karikuncheva.dominosapp.cart;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Map;

import karikuncheva.dominosapp.CartBroadcastReceiver;
import karikuncheva.dominosapp.catalog.CatalogActivity;
import karikuncheva.dominosapp.LoginActivity;
import karikuncheva.dominosapp.MakeOrderActivity;
import karikuncheva.dominosapp.navigation.AddressActivity;
import karikuncheva.dominosapp.R;
import karikuncheva.dominosapp.TrackerActivity;
import karikuncheva.dominosapp.model.Cart;
import karikuncheva.dominosapp.model.products.Product;


public class CartActivity extends AppCompatActivity implements CartListFragment.CartComunicator, CouponFragment.CouponCommunicator {

    private RecyclerView recyclerView;
    private Button checkOut;
    private Button back;
    private TextView total;
    public static int addressVisibility = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        recyclerView = (RecyclerView) findViewById(R.id.products_recycle_view);
        back = (Button) findViewById(R.id.back_button);
        total = (TextView) findViewById(R.id.total_price_tv);

        CartAdapter adapter = new CartAdapter(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        checkOut = (Button) findViewById(R.id.check_out_button);
        checkOut.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onClick(View v) {

                if (MakeOrderActivity.makeOrderMethod == 2) {
                    Intent intent = new Intent(CartActivity.this, TrackerActivity.class);
                    startActivity(intent);
                    LoginActivity.loggedUser.setCart(new Cart());
                    LocalBroadcastManager.getInstance(CartActivity.this).sendBroadcast(new Intent(CartBroadcastReceiver.ACTION_CLEAR_CART));
                    CatalogActivity.count = 0;
                }

                ArrayList<Product> productsInCart = new ArrayList<Product>();
                for (Map.Entry<String, HashSet<Product>> products : LoginActivity.loggedUser.getCart().getProducts().entrySet()) {
                    for (Product p : products.getValue()) {
                        productsInCart.add(p);
                    }
                }
                if (productsInCart.size() == 0 ||
                        (productsInCart.size() == 1 &&
                                productsInCart.get(0).getPrice() == 0)) {
                    checkOut.setClickable(false);

                    final Dialog dialog = new Dialog(CartActivity.this);
                    dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
                    dialog.setContentView(R.layout.fragment_dialog);
                    dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                    // set the custom dialog components - text and button
                    TextView txt = (TextView) dialog.findViewById(R.id.text_dialog);
                    txt.setText("Your cart is empty. Please, add products!");
                    TextView ok = (TextView) dialog.findViewById(R.id.ok_tv);
                    ok.setBackground(new ColorDrawable(Color.TRANSPARENT));
                    // if button is clicked, close the custom dialog
                    ok.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            dialog.dismiss();
                        }
                    });
                    dialog.show();
                    checkOut.setClickable(true);
                } else {
                    Intent intent = new Intent(CartActivity.this, AddressActivity.class);
                    intent.putExtra("click", 1);
                    intent.putExtra("fromCart", "cart");
                    addressVisibility = 1;
                    CartActivity.this.startActivity(intent);
                }
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CartActivity.this, CatalogActivity.class);
                CartActivity.this.startActivity(intent);
            }
        });

        total.setText("Total: " + String.format("%.2f", CartAdapter.total));

    }

    @Override
    public void sumTotalPrice(double sum) {
        total.setText("Total: " + String.format("%.2f", sum));
    }

    @Override
    public void addProduct(Product p) {
        FragmentManager fm = getSupportFragmentManager();
        CartListFragment fragment = (CartListFragment) fm.findFragmentById(R.id.cart_list_products);
        fragment.addBonusProduct(p);
    }
}