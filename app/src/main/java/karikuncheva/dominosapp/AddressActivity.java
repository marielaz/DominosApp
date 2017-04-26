package karikuncheva.dominosapp;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

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
        if(bundle != null) {
            if (bundle.getString("fromCart") != null && bundle.getString("fromCart").equals("cart")) {
                choose.setVisibility(View.VISIBLE);
                finalize.setVisibility(View.VISIBLE);
            }
        }

        AddressAdapter adapter = new AddressAdapter(this, MainActivity.loggedUser.getAddresses());
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        back = (Button) findViewById(R.id.back_button_address);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddressActivity.this, CatalogActivity.class);
                AddressActivity.this.startActivity(intent);
            }
        });

        finalize.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(AddressActivity.this, TrackerActivity.class);
                startActivity(i);
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
}
