package karikuncheva.dominosapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.widget.EditText;
import android.widget.Button;
import android.view.View;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;

import karikuncheva.dominosapp.model.User;
import karikuncheva.dominosapp.model.Shop;


public class MainActivity extends AppCompatActivity {

    private EditText username_login;
    private EditText password_login;
    private Button loginButton;
    private Button registerButton;
    private String username, password;
    public static User loggedUser;
    SharedPreferences sharedPreference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        username_login = (EditText) this.findViewById(R.id.username_login);
        password_login = (EditText) this.findViewById(R.id.password_login);
        loginButton = (Button) this.findViewById(R.id.login_button);
        registerButton = (Button) this.findViewById(R.id.registration_button);
        loginButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (validate()) {
                    Toast.makeText(MainActivity.this, "User data is valid", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(MainActivity.this, CatalogActivity.class);
                    MainActivity.this.startActivity(intent);
                } else {
                    Toast.makeText(MainActivity.this, "User data not valid", Toast.LENGTH_SHORT).show();
                }

            }
        });

        registerButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent_reg = new Intent(MainActivity.this, RegistrationActivity.class);
                MainActivity.this.startActivity(intent_reg);
            }
        });
    }

    public boolean validate(){
        initialize();
        if (username.isEmpty()){
            username_login.setError("Please, enter a valid username!");
            username_login.requestFocus();
            return false;
        }
        if (password.isEmpty()){
            password_login.setError("Please, enter a valid password");
            password_login.requestFocus();
           return false;
        }

        sharedPreference = this.getSharedPreferences(RegistrationActivity.PREFS_NAME, Context.MODE_PRIVATE);
        String currentUser = sharedPreference.getString("user", null);
        if(currentUser != null){
            Gson gson = new Gson();
            loggedUser = gson.fromJson(currentUser, User.class);
            return true;

        }

        return false;
     }

    public void initialize(){
        username = username_login.getText().toString().trim();
        password = password_login.getText().toString().trim();
    }

}

