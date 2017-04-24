package karikuncheva.dominosapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.Button;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import java.util.Arrays;

import karikuncheva.dominosapp.model.User;


public class MainActivity extends AppCompatActivity {

    private EditText username_login;
    private EditText password_login;
    private Button loginButton;
    private Button registerButton;
    private String username, password;
    public static User loggedUser;
    private LoginButton loginFbButton;
    private CallbackManager callbackManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FacebookSdk.sdkInitialize(getApplicationContext());

        setContentView(R.layout.activity_main);

        username_login = (EditText) this.findViewById(R.id.username_login);
        password_login = (EditText) this.findViewById(R.id.password_login);
        loginButton = (Button) this.findViewById(R.id.login_button);
        registerButton = (Button) this.findViewById(R.id.registration_button);
        loginFbButton = (LoginButton) this.findViewById(R.id.login_fb_button);
        callbackManager = CallbackManager.Factory.create();
        loginButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (validate()) {

                    if (DBManager.getInstance(MainActivity.this).existsUser(username_login.getText().toString())) {
                        loggedUser = DBManager.getInstance(MainActivity.this).getUser(username_login.getText().toString());

                        Toast.makeText(MainActivity.this, "User data is valid", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(MainActivity.this, CatalogActivity.class);
                        MainActivity.this.startActivity(intent);
                    } else {
                        Toast.makeText(MainActivity.this, "User data not valid", Toast.LENGTH_SHORT).show();

                    }

                } else {
                    Toast.makeText(MainActivity.this, "User data not valid", Toast.LENGTH_SHORT).show();
                }
            }
        });

        loginFbButton.setReadPermissions(Arrays.asList(
                "public_profile", "email"));

        loginFbButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
//                info.setText(
//                        "User ID: "
//                                + loginResult.getAccessToken().getUserId()
//                                + "\n" +
//                                "Auth Token: "
//                                + loginResult.getAccessToken().getToken()
//                );
            Intent intent = new Intent(MainActivity.this, CatalogActivity.class);
                MainActivity.this.startActivity(intent);
            }

            @Override
            public void onCancel() {
               // info.setText("Login attempt canceled.");
                Toast.makeText(MainActivity.this, "Login attempt canceled.", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(FacebookException e) {
               // info.setText("Login attempt failed.");
                Toast.makeText(MainActivity.this, "Login attempt failed.", Toast.LENGTH_SHORT).show();

            }
        });

        registerButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent_reg = new Intent(MainActivity.this, RegistrationActivity.class);
                MainActivity.this.startActivity(intent_reg);
            }
        });
    }

    public boolean validate() {
        initialize();
        if (username.isEmpty()) {
            username_login.setError("Please, enter a valid username!");
            username_login.requestFocus();
            return false;
        }
        if (password.isEmpty()) {
            password_login.setError("Please, enter a valid password");
            password_login.requestFocus();
            return false;
        }
        return true;
    }

    public void initialize() {
        username = username_login.getText().toString().trim();
        password = password_login.getText().toString().trim();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }
}

