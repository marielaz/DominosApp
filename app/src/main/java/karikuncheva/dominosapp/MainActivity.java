package karikuncheva.dominosapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.widget.EditText;
import android.widget.Button;
import android.view.View;
import android.widget.Toast;
import android.content.Intent;
import karikuncheva.dominosapp.model.Client;
import karikuncheva.dominosapp.model.Shop;


public class MainActivity extends AppCompatActivity {
    private Client user;
    private EditText username_login;
    private EditText password_login;
    private Button loginButton;
    private String username, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username_login = (EditText) this.findViewById(R.id.username_login);
        password_login = (EditText) this.findViewById(R.id.password_login);
        loginButton = (Button) this.findViewById(R.id.login_button);

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
        }
    public boolean validate(){
        initialize();
        boolean valid = true;
        user = new Client(username, password);
        if (username.isEmpty()){
            username_login.setError("Please, enter a valid username!");
            valid = false;
        }
        if (!user.validatePassword(password)){
            password_login.setError("Please, enter a valid password");
            valid = false;
        }
        return valid;
     }

    public void initialize(){
        username = username_login.getText().toString();
        password = password_login.getText().toString();
    }

}

