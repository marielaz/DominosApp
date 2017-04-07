package karikuncheva.dominosapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MakeOrderActivity extends AppCompatActivity {

    private Button carry_out_bnt;
    private Button delivery_bnt;
    private Button carry_out_checked;
    private Button delivery_checked;

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
                // intent to the google maps
               // Intent inten = new Integer();
            }
        });

        delivery_bnt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                delivery_checked.setVisibility(View.VISIBLE);
                // intent to the list with the address
//                Intent intent = new Intent(MakeOrderActivity.this, CatalogActivity.class);
//                MakeOrderActivity.this.startActivity(intent);
            }
        });
    }
}
