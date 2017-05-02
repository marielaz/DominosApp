package karikuncheva.dominosapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import karikuncheva.dominosapp.catalog.CatalogActivity;


public class MakeOrderActivity extends AppCompatActivity {

    private Button carry_out_bnt;
    private Button delivery_bnt;
    private Button carry_out_checked;
    private Button delivery_checked;
    public static int makeOrderMethod;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_order);

        carry_out_bnt = (Button) findViewById(R.id.carry_out_bnt);
        delivery_bnt = (Button) findViewById(R.id.delivery_bnt);
        carry_out_checked = (Button) findViewById(R.id.carry_out_checked);
        delivery_checked = (Button) findViewById(R.id.delivery_checked);

        carry_out_bnt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                carry_out_checked.setVisibility(View.VISIBLE);
                makeOrderMethod = 2;
                Intent intent = new Intent(MakeOrderActivity.this, MapsMarkerActivity.class);
                startActivity(intent);
                carry_out_checked.setVisibility(View.GONE);
            }
        });

        delivery_bnt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                delivery_checked.setVisibility(View.VISIBLE);
                makeOrderMethod = 1;
                Intent intent = new Intent(MakeOrderActivity.this, CatalogActivity.class);
                MakeOrderActivity.this.startActivity(intent);
                delivery_checked.setVisibility(View.GONE);
            }
        });
    }
}
