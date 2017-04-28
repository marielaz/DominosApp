package karikuncheva.dominosapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

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
        CartActivity.temp = 0;
        MainActivity.loggedUser.setCart(new Cart());
    }
}
