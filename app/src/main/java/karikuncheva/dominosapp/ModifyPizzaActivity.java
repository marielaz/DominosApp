package karikuncheva.dominosapp;

import android.content.Intent;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.List;

import karikuncheva.dominosapp.model.products.Pizza;

public class ModifyPizzaActivity extends AppCompatActivity {

    List<RadioButton> radioButtons = new ArrayList<RadioButton>();
    Button small, med, large;
    RadioButton radioButton1, radioButton2, radioButton3;
    private Pizza p;
    private Pizza.Size size;
    private Pizza.Type type;

    Button addToCart;
    Button cancelModify;
    SharedPreferenceCart sharedPreferenceCart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_pizza);


        sharedPreferenceCart = new SharedPreferenceCart();
        if(getIntent().getExtras() != null) {
            Bundle b = getIntent().getExtras();
                if(b.getSerializable("pizza") != null){
                    p = (Pizza) b.getSerializable("pizza");
                    p.size = p.getSize();
                    p.type = p.getType();
                }
        }

        radioButton1 = (RadioButton) findViewById(R.id.radioButton);
        radioButton2 = (RadioButton) findViewById(R.id.radioButton2);
        radioButton3 = (RadioButton) findViewById(R.id.radioButton3);

        radioButtons.add(radioButton1);
        radioButtons.add(radioButton2);
        radioButtons.add(radioButton3);

        small = (Button) findViewById(R.id.small);
        med = (Button) findViewById(R.id.med);
        large = (Button) findViewById(R.id.large);

//        int width = getResources().getDisplayMetrics().widthPixels/3;
//        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(width, RelativeLayout.LayoutParams.WRAP_CONTENT);
//        small.setLayoutParams(params);
//        params.addRule(RelativeLayout.RIGHT_OF, small.getId());
//        med.setLayoutParams(params);
//        params.addRule(RelativeLayout.RIGHT_OF, R.id.med);
//        large.setLayoutParams(params);

        radioButton1.setChecked(true);
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
                if (radioButton1.isChecked()){
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
                else if(radioButton2.isChecked()){
                    type = Pizza.Type.ITALIAN_STYLE;
                }
                else{
                    type = Pizza.Type.THIN_AND_CRISPY;
                }
                p = p.modifyPizza(p, size , type);
               // p.setDiscPrice(p.getPrice() - p.getPrice()*0.05);
                sharedPreferenceCart.addProduct(ModifyPizzaActivity.this, p);
                Intent intent = new Intent(ModifyPizzaActivity.this, CartActivity.class);
                ModifyPizzaActivity.this.startActivity(intent);
//                intent.putExtra("pizza", p);
//                setResult(5, intent);
//                finish();
            }
        });

    }
    private void processRadioButtonClick(CompoundButton buttonView) {
        for (RadioButton button : radioButtons) {
            if (button != buttonView) button.setChecked(false);
        }
    }

}