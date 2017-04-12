package karikuncheva.dominosapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import karikuncheva.dominosapp.model.products.Pizza;

public class ModifyPizzaActivity extends AppCompatActivity implements ModifyPizzaFragment.ModifyCommunicator {

    TextView price, total;
    Button add_to_cart;
    Button cancel_modify;
    private Pizza p;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_pizza);

        if (getIntent().getExtras() != null) {
            Bundle b = getIntent().getExtras();
            if (b.getSerializable("pizza") != null) {
                p = (Pizza) b.getSerializable("pizza");
                p.size = p.getSize();
                p.type = p.getType();
            }
        }
        total = (TextView) findViewById(R.id.total_tv);
        price = (TextView) findViewById(R.id.price_modif_tv);
        add_to_cart = (Button) findViewById(R.id.add_to_cart_modify);
        cancel_modify = (Button) findViewById(R.id.cancel_modify);

        cancel_modify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ModifyPizzaActivity.this, CatalogActivity.class);
                ModifyPizzaActivity.this.startActivity(intent);
            }
        });

        add_to_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ModifyPizzaFragment.pizza.setPrice(Double.parseDouble(price.getText().toString()));
                MainActivity.loggedUser.getCart().addProduct(ModifyPizzaFragment.pizza);
                Intent intent = new Intent(ModifyPizzaActivity.this, CartActivity.class);
                ModifyPizzaActivity.this.startActivity(intent);
            }
        });

        price.setText(String.valueOf(p.getPrice()));
    }

    @Override
    public void modifyPrice(double sum) {
        price.setText(String.format("%.2f", sum));
    }
}