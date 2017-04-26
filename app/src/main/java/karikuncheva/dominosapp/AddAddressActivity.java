package karikuncheva.dominosapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import karikuncheva.dominosapp.model.Address;

public class AddAddressActivity extends AppCompatActivity {

    private Button addAddress;
    private EditText neighborhood;
    private EditText town;
    private EditText number;
    private EditText street;
    private EditText apartament;
    private EditText floor;
    private EditText postCode;
    private EditText block;
    private Button back;
    private String townTxt;
    private String neighborhoodTxt;
    private String streetTxt;
    private String numberTxt;
    private String blockTxt;
    private String postCodeTxt;
    private String apartamentTxt;
    private String floorTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_address);

        neighborhood = (EditText) findViewById(R.id.neighborhood_et);
        town = (EditText) findViewById(R.id.city_et);
        number = (EditText) findViewById(R.id.number_et);
        street = (EditText) findViewById(R.id.street_et);
        apartament = (EditText) findViewById(R.id.apartament_et);
        floor = (EditText) findViewById(R.id.floor_et);
        block = (EditText) findViewById(R.id.block_et);
        postCode = (EditText) findViewById(R.id.post_code_et);


        addAddress = (Button) findViewById(R.id.add_new_address);
        addAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initialize();
                if (validateFields()) {
                    Address address = new Address(townTxt, neighborhoodTxt, streetTxt, numberTxt, blockTxt, postCodeTxt, apartamentTxt, floorTxt);
                    DBManager.getInstance(AddAddressActivity.this).addAddress(address);
                    Bundle bundle = getIntent().getExtras();
                    int index = bundle.getInt("fromActivity");
                    if (index == 1) {
                        Intent i = new Intent(AddAddressActivity.this, MakeOrderActivity.class);
                        startActivity(i);
                    } else {
                        Intent i = new Intent(AddAddressActivity.this, AddressActivity.class);
                        i.putExtra("item", "click");
                        startActivity(i);
                    }
                }
            }
        });

        back = (Button) findViewById(R.id.back_button_add_address);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AddAddressActivity.this, ProfileActivity.class));
            }
        });
    }

    public void initialize() {

        townTxt = town.getText().toString();
        neighborhoodTxt = neighborhood.getText().toString();
        streetTxt = street.getText().toString();
        numberTxt = number.getText().toString();
        blockTxt = block.getText().toString();
        postCodeTxt = postCode.getText().toString();
        apartamentTxt = apartament.getText().toString();
        floorTxt = floor.getText().toString();
    }

    private boolean validateFields() {
        if (townTxt.isEmpty()) {
            town.setError("Please, enter town!");
            town.requestFocus();
            return false;
        }
        if (neighborhoodTxt.isEmpty()) {
            neighborhood.setError("Please, enter neighborhood!");
            neighborhood.requestFocus();
            return false;
        }
        if (postCodeTxt.isEmpty()) {
            postCode.setError("Please, enter post code!");
            postCode.requestFocus();
            return false;
        }
        if (streetTxt.isEmpty()) {
            street.setError("Please, enter street!");
            street.requestFocus();
            return false;
        }
        if (numberTxt.isEmpty()) {
            number.setError("Please, enter number of the street!");
            number.requestFocus();
            return false;
        }
        if (blockTxt.isEmpty()) {
            block.setError("Please, enter number of the block!");
            block.requestFocus();
            return false;
        }
        if (floorTxt.isEmpty()) {
            floor.setError("Please, enter floor!");
            floor.requestFocus();
            return false;
        }
        if (apartamentTxt.isEmpty()) {
            apartament.setError("Please, enter apartment!");
            apartament.requestFocus();
            return false;
        }
        return true;
    }
}
