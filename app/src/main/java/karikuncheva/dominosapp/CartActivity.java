package karikuncheva.dominosapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import karikuncheva.dominosapp.model.User;

public class CartActivity extends AppCompatActivity {

    private User user;
    private ListView list;
    private Button checkOut;
    private TextView total;
    private double sumtotal = 0;
    private Button back;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        total = (TextView) findViewById(R.id.total_cart);
        list = (ListView) findViewById(R.id.products_listview);
        user = (User) getIntent().getSerializableExtra("user");

        back = (Button) findViewById(R.id.back_button);

        CartAdapter adapter = new CartAdapter(this, user);
        list.setAdapter(adapter);
        String sum = String.format("%.2f", CartAdapter.getTotal());
        total.setText("Total: " + sum);
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
                Intent i = new Intent();
                i.putExtra("user", user);
                setResult(6, i);
                finish();
            }
        });
    }
}