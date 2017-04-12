package karikuncheva.dominosapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import karikuncheva.dominosapp.model.Address;

public class EditAddressActivity extends AppCompatActivity {

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
                inicialize();
                Address address = new Address(townTxt, neighborhoodTxt, streetTxt, numberTxt, blockTxt, postCodeTxt, apartamentTxt, floorTxt);
                MainActivity.loggedUser.getAddresses().add(address);
                Intent i = new Intent(EditAddressActivity.this, CatalogActivity.class);
                startActivity(i);
            }
        });

        back = (Button) findViewById(R.id.back_button_add_address);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(EditAddressActivity.this, ProfileActivity.class));
            }
        });
    }

    public void inicialize() {

        townTxt = town.getText().toString();
        neighborhoodTxt = neighborhood.getText().toString();
        streetTxt = street.getText().toString();
        numberTxt = number.getText().toString();
        blockTxt = block.getText().toString();
        postCodeTxt = postCode.getText().toString();
        apartamentTxt = apartament.getText().toString();
        floorTxt = floor.getText().toString();
    }
}
