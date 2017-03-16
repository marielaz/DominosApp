package karikuncheva.dominosapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import karikuncheva.dominosapp.model.Shop;
import karikuncheva.dominosapp.model.User;

public class RegistrationActivity extends AppCompatActivity {
    public static final int RESULT_CODE_CANCELED = 5;
    public static final int RESULT_CODE_SUCCESS = 3;
    private User user;
    private  Shop shop = Shop.getInstance();
    private EditText username_reg, email_reg, address_reg, pass_reg, confirm_pass_reg;
    private String username, email, address, pass, confirmPass;
    Button regButton ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        username_reg = (EditText) findViewById(R.id.username_reg);
        email_reg = (EditText) findViewById(R.id.email_reg);
        address_reg = (EditText) findViewById(R.id.address_reg);
        pass_reg = (EditText) findViewById(R.id.password_reg);
        confirm_pass_reg = (EditText) findViewById(R.id.confirm_pass_reg);
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
           user = new User(username, pass);
            MainActivity.users.add(user);
            Toast.makeText(this, "Registration complete", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(RegistrationActivity.this, MainActivity.class);
            intent.putExtra("user", username);
            intent.putExtra("pass", pass);
            setResult(RESULT_CODE_SUCCESS, intent);
            finish();//ok
            // Intent to go to menu p
        }
        else{
            Toast.makeText(this, "Registration has failed", Toast.LENGTH_SHORT).show();
        }
    }


    public boolean validate(){
        boolean valid = true;
        this.user = new User(username, address, pass, email);
        if (username.isEmpty()){
            username_reg.setError("Username must not be empty!");
            valid = false;
        }
        if (!user.validateEmailAddress(email)){
            email_reg.setError("Please, enter a valid email!");
            valid = false;
        }
        if (address.isEmpty()){
            address_reg.setError("Please, enter a valid address!");
            valid = false;
        }
        if (!user.validatePassword(pass)){
            pass_reg.setError("Please, enter a valid password");
            valid = false;
        }
        if (!pass.equals(confirmPass)){
            confirm_pass_reg.setError("Passwords do not match");
            valid = false;
        }
        return valid;
    }

    public void initialize(){
            username = username_reg.getText().toString();
            email = email_reg.getText().toString();
            address = address_reg.getText().toString();
            pass = pass_reg.getText().toString();
            confirmPass = confirm_pass_reg.getText().toString();
    }
}
