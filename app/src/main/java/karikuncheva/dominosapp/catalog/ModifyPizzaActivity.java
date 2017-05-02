package karikuncheva.dominosapp.catalog;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import karikuncheva.dominosapp.cart.CartActivity;
import karikuncheva.dominosapp.LoginActivity;
import karikuncheva.dominosapp.R;
import karikuncheva.dominosapp.model.products.Pizza;

public class ModifyPizzaActivity extends AppCompatActivity implements ModifyPizzaFragment.ModifyCommunicator {

    TextView price;
    Button add_to_cart;
    Button cancel_modify;
    private Pizza p;
    TextView pizza_name;
    private Button back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_pizza);

        if (getIntent().getExtras() != null) {
            Bundle b = getIntent().getExtras();
            if (b.getSerializable("pizza") != null) {
                p = (Pizza) b.getSerializable("pizza");
                p.setSize(p.getSize());
                p.setType(p.getType());
            }
        }

        pizza_name = (TextView) findViewById(R.id.name_modify_pizza);
        price = (TextView) findViewById(R.id.price_modif_tv);
        add_to_cart = (Button) findViewById(R.id.add_to_cart_modify);
        cancel_modify = (Button) findViewById(R.id.cancel_modify);
        back = (Button) findViewById(R.id.back_button_modify);
        pizza_name.setText(p.getName());

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ModifyPizzaActivity.this, CatalogActivity.class));
            }
        });
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
                //TODO if phone language is bulgarian !!!
                if (price.getText().toString().contains(",")) {
                    ModifyPizzaFragment.pizza.setPrice(Double.parseDouble(price.getText().toString().replace(',', '.')));
                } else {
                    ModifyPizzaFragment.pizza.setPrice(Double.parseDouble(price.getText().toString()));
                }
                CatalogActivity.count++;
                LoginActivity.loggedUser.getCart().addProduct(ModifyPizzaFragment.pizza);
                Intent intent = new Intent(ModifyPizzaActivity.this, CatalogActivity.class);
                ModifyPizzaActivity.this.startActivity(intent);
            }
        });

        price.setText(String.valueOf(String.format("%.2f", p.getPrice())));
        pizza_name.setText(p.getName());
    }

    @Override
    public void modifyPrice(double sum) {
        price.setText(String.format("%.2f", sum));
    }


}