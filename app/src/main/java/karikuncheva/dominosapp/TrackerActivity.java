package karikuncheva.dominosapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import karikuncheva.dominosapp.cart.CartActivity;
import karikuncheva.dominosapp.model.Cart;

public class TrackerActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tracker);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        CartActivity.addressVisibility = 0;
        LoginActivity.loggedUser.setCart(new Cart());
    }
}
