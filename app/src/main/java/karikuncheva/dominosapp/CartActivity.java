package karikuncheva.dominosapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import karikuncheva.dominosapp.model.Shop;
import karikuncheva.dominosapp.model.User;

public class CartActivity extends AppCompatActivity {

    private User user;
    private ListView list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        list = (ListView) findViewById(R.id.products_listview);
        user = (User) getIntent().getSerializableExtra("user");

        CartAdapter adapter = new CartAdapter(this, user);
        list.setAdapter(adapter);


    }
}
