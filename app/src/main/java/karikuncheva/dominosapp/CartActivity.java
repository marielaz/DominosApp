package karikuncheva.dominosapp;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import karikuncheva.dominosapp.model.User;

public class CartActivity extends AppCompatActivity implements CartListFragment.CartComunicator {

    private RecyclerView recyclerView;
    private Button checkOut;
    private Button back;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        recyclerView = (RecyclerView) findViewById(R.id.products_recycle_view);
        back = (Button) findViewById(R.id.back_button);

        CartAdapter adapter = new CartAdapter(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        checkOut = (Button) findViewById(R.id.check_out_button);
        checkOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CartActivity.this, TrackerActivity.class);
                Toast.makeText(CartActivity.this, "You paid successful!", Toast.LENGTH_SHORT).show();
                CartActivity.this.startActivity(intent);
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CartActivity.this, CatalogActivity.class);
                CartActivity.this.startActivity(intent);

            }
        });
    }
    @Override
    public void sumTotalPrice(double sum) {
        FragmentManager fm = getSupportFragmentManager();
        CartTotalFragment fragment = (CartTotalFragment) fm.findFragmentById(R.id.fragment_total);
        fragment.sumTotalPrice(sum);
    }
}