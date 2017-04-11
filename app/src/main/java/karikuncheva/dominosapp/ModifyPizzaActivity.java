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
import android.widget.RadioButton;
import java.util.ArrayList;
import java.util.List;

import karikuncheva.dominosapp.model.CustomLinearLayoutManager;
import karikuncheva.dominosapp.model.products.Pizza;

public class ModifyPizzaActivity extends AppCompatActivity {

    List<RadioButton> radioButtons = new ArrayList<RadioButton>();
    Button small, med, large;
    RadioButton trad_bnt, ital_bnt, thin_bnt;
    private Pizza p;
    private Pizza.Size size;
    private Pizza.Type type;
    Button addToCart;
    Button cancelModify;
    RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_pizza);

        if(getIntent().getExtras() != null) {
            Bundle b = getIntent().getExtras();
                if(b.getSerializable("pizza") != null){
                    p = (Pizza) b.getSerializable("pizza");
                    p.size = p.getSize();
                    p.type = p.getType();
                }
        }
        recyclerView = (RecyclerView) findViewById(R.id.ingredients_recycler_view);
        ModifyAdapter adapter = new ModifyAdapter(this, p);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        recyclerView.setNestedScrollingEnabled(false);
        trad_bnt = (RadioButton) findViewById(R.id.trad_bnt);
        ital_bnt = (RadioButton) findViewById(R.id.ital_bnt);
        thin_bnt = (RadioButton) findViewById(R.id.thin_bnt);

        radioButtons.add(trad_bnt);
        radioButtons.add(ital_bnt);
        radioButtons.add(thin_bnt);

        small = (Button) findViewById(R.id.small);
        med = (Button) findViewById(R.id.med);
        large = (Button) findViewById(R.id.large);

        trad_bnt.setChecked(true);
        large.setPressed(true);
        size = Pizza.Size.LARGE;

        small.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                small.setPressed(true);
                size = Pizza.Size.SMALL;
                med.setPressed(false);
                large.setPressed(false);
                return true;
            }
        });
        med.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                med.setPressed(true);
                size = Pizza.Size.MEDIUM;
                small.setPressed(false);
                large.setPressed(false);
                return true;
            }
        });
        large.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                large.setPressed(true);
                size = Pizza.Size.LARGE;
                small.setPressed(false);
                med.setPressed(false);
                return true;
            }
        });

        for (RadioButton button : radioButtons) {
            button.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked) processRadioButtonClick(buttonView);
                }

            });
        }

        addToCart = (Button) findViewById(R.id.add_to_cart_modify);
        cancelModify = (Button) findViewById(R.id.cancel_modify);

        cancelModify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ModifyPizzaActivity.this, CatalogActivity.class);
                ModifyPizzaActivity.this.startActivity(intent);
            }
        });
        addToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (trad_bnt.isChecked()){
                    type = Pizza.Type.TRADITIONAL;
                    if(small.isSelected()){
                        size = Pizza.Size.SMALL;
                    }
                    if(med.isSelected()){
                        size = Pizza.Size.MEDIUM;
                    }
                    if(large.isSelected()){
                        size = Pizza.Size.LARGE;
                    }
                }
                else if(ital_bnt.isChecked()){
                    type = Pizza.Type.ITALIAN_STYLE;
                }
                else{
                    type = Pizza.Type.THIN_AND_CRISPY;
                }
                p = p.modifyPizza(p, size , type);

                MainActivity.loggedUser.getCart().addProduct(p);
                Intent intent = new Intent(ModifyPizzaActivity.this, CartActivity.class);
                ModifyPizzaActivity.this.startActivity(intent);
            }
        });
    }
    private void processRadioButtonClick(CompoundButton buttonView) {
        for (RadioButton button : radioButtons) {
            if (button != buttonView) button.setChecked(false);
        }
    }

}