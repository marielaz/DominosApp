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

import java.util.ArrayList;
import java.util.List;

import karikuncheva.dominosapp.model.products.Pizza;

public class ModifyPizzaActivity extends AppCompatActivity {

    List<RadioButton> radioButtons = new ArrayList<RadioButton>();
    Button b1, b2, b3;
    RadioButton radioButton1, radioButton2, radioButton3;
    private Pizza p;
    private Pizza.Size size;
    private Pizza.Type type;

    Button addToCart;

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

        radioButton1 = (RadioButton) findViewById(R.id.radioButton);
        radioButton2 = (RadioButton) findViewById(R.id.radioButton2);
        radioButton3 = (RadioButton) findViewById(R.id.radioButton3);

        radioButtons.add(radioButton1);
        radioButtons.add(radioButton2);
        radioButtons.add(radioButton3);

        b1 = (Button) findViewById(R.id.small);
        b2 = (Button) findViewById(R.id.med);
        b3 = (Button) findViewById(R.id.large);

        radioButton1.setChecked(true);
        b3.setPressed(true);
        size = Pizza.Size.LARGE;

        b1.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                b1.setPressed(true);
                size = Pizza.Size.SMALL;
                b2.setPressed(false);
                b3.setPressed(false);
                return true;
            }
        });
        b2.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                b2.setPressed(true);
                size = Pizza.Size.MEDIUM;
                b1.setPressed(false);
                b3.setPressed(false);
                return true;
            }
        });
        b3.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                b3.setPressed(true);
                size = Pizza.Size.LARGE;
                b1.setPressed(false);
                b2.setPressed(false);
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
        addToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                p = p.modifyPizza(p, size , Pizza.Type.THIN_AND_CRISPY);
                p.setDiscPrice(p.getPrice() - p.getPrice()*0.05);

                Intent intent = new Intent();
                intent.putExtra("pizza", p);
                setResult(5, intent);
                finish();
            }
        });

    }
    private void processRadioButtonClick(CompoundButton buttonView) {
        for (RadioButton button : radioButtons) {
            if (button != buttonView) button.setChecked(false);
        }
    }

}
