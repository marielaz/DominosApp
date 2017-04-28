package karikuncheva.dominosapp;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import karikuncheva.dominosapp.model.Cart;

public class AddressActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private TextView choose;
    private Button finalize;
    private Button back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view_address);
        choose = (TextView) findViewById(R.id.choose_address);
        finalize = (Button) findViewById(R.id.finalize_order);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            if (bundle.getString("fromCart") != null && bundle.getString("fromCart").equals("cart")) {
                choose.setVisibility(View.VISIBLE);
                finalize.setVisibility(View.VISIBLE);
            }
        }
        if (CartActivity.temp == 1) {
            choose.setVisibility(View.VISIBLE);
            finalize.setVisibility(View.VISIBLE);
        } else {
            choose.setVisibility(View.GONE);
            finalize.setVisibility(View.GONE);
        }


        AddressAdapter adapter = new AddressAdapter(this, MainActivity.loggedUser.getAddresses());
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        back = (Button) findViewById(R.id.back_button_address);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CartActivity.temp = 0;
                Intent intent = new Intent(AddressActivity.this, CatalogActivity.class);
                AddressActivity.this.startActivity(intent);
            }
        });


        finalize.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (MainActivity.loggedUser.getAddresses().size() == 0) {
                    finalize.setClickable(false);

                    final Dialog dialog = new Dialog(AddressActivity.this);
                    dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
                    dialog.setContentView(R.layout.fragment_dialog);
                    dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                    // set the custom dialog components - text and button
                    TextView txt = (TextView) dialog.findViewById(R.id.text_dialog);
                    txt.setText("Please, add address first!");
                    TextView ok = (TextView) dialog.findViewById(R.id.ok_tv);
                    ok.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                    // if button is clicked, close the custom dialog
                    ok.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            dialog.dismiss();
                        }
                    });
                    dialog.show();
                    finalize.setClickable(true);
                } else {
                    Intent i = new Intent(AddressActivity.this, TrackerActivity.class);
                    AddressActivity.this.startActivity(i);
                    MainActivity.loggedUser.setCart(new Cart());
                    finish();
                }
            }
        });
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(AddressActivity.this, AddAddressActivity.class);
                i.putExtra("fromActivity", 2);
                startActivity(i);
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        CartActivity.temp = 0;
    }
}
