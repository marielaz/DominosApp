package karikuncheva.dominosapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import karikuncheva.dominosapp.model.Shop;
import karikuncheva.dominosapp.model.Client;

public class RegistrationActivity extends AppCompatActivity {
    private Client client;
    private  Shop shop = Shop.getInstance();
    private EditText username_text, email_text, address_text, pass_text, confirm_pass_text;
    private String username, email, address, pass, confirmPass;
    Button regButton ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        username_text = (EditText) findViewById(R.id.username);
        email_text = (EditText) findViewById(R.id.email);
        address_text = (EditText) findViewById(R.id.address);
        pass_text = (EditText) findViewById(R.id.password);
        confirm_pass_text = (EditText) findViewById(R.id.confirm_pass);
        regButton = (Button) findViewById(R.id.registration_button);

        regButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                register(); //call when the button is clicked to validate the input
            }
        });
    }
    public void register(){
        initialize();
        if (validate()){
            shop.addClient(client);
            Toast.makeText(this, "Registration complete", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(RegistrationActivity.this, CatalogActivity.class);
            RegistrationActivity.this.startActivity(intent);
            // Intent to go to menu page!

        }
        else{
            Toast.makeText(this, "Registration has failed", Toast.LENGTH_SHORT).show();
        }
    }


    public boolean validate(){
        boolean valid = true;
        this.client = new Client(username, address, pass, email);
        if (username.isEmpty()){
            username_text.setError("Username must not be empty!");
        }
        if (!client.validateEmailAddress(email)){
            email_text.setError("Please, enter a valid email!");
        }
        if (address.isEmpty()){
            address_text.setError("Please, enter a valid address!");
        }
        if (!client.validatePassword(pass)){
            pass_text.setError("Please, enter a valid password");
        }
        if (!pass.equals(confirmPass)){
            confirm_pass_text.setError("Passwords do not match");
        }
        return valid;
    }

    public void initialize(){
            username = username_text.getText().toString();
            email = email_text.getText().toString();
            address = address_text.getText().toString();
            pass = pass_text.getText().toString();
            confirmPass = confirm_pass_text.getText().toString();
    }
}
