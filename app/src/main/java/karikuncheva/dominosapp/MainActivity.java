package karikuncheva.dominosapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.widget.EditText;
import android.widget.Button;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

import karikuncheva.dominosapp.model.User;
import karikuncheva.dominosapp.model.Shop;


public class MainActivity extends AppCompatActivity {

    private User user;
    private EditText username_login;
    private EditText password_login;
    private Button loginButton;
    private Button registerButton;
    private String username, password;
    private Shop shop;
    public static ArrayList<User> users = new ArrayList<User>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        User mari = new User("Mariela", "Mari123");
        MainActivity.users.add(mari);

        username_login = (EditText) this.findViewById(R.id.username_login);
        password_login = (EditText) this.findViewById(R.id.password_login);
        loginButton = (Button) this.findViewById(R.id.login_button);
        registerButton = (Button) this.findViewById(R.id.registration_button);
        loginButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (validate()) {
                    Toast.makeText(MainActivity.this, "User data is valid", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(MainActivity.this, CatalogActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("user", user);
                    intent.putExtras(bundle);
                    MainActivity.this.startActivity(intent);
                } else {
                    Toast.makeText(MainActivity.this, "User data not valid", Toast.LENGTH_SHORT).show();
                }

            }
        });

        registerButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent_reg = new Intent(MainActivity.this, RegistrationActivity.class);
                startActivityForResult(intent_reg, 1);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RegistrationActivity.RESULT_CODE_SUCCESS){
            if (data != null){
                username_login.setText(data.getStringExtra("user"));
                password_login.setText(data.getStringExtra("pass"));
            }
        }
        else{
            Toast.makeText(this, "Try again", Toast.LENGTH_SHORT).show();
        }
    }

    public boolean validate(){
        initialize();
        boolean valid =true;
        if (username.isEmpty()){
            username_login.setError("Please, enter a valid username!");
            valid= false;
        }
        if (password.isEmpty()){
            password_login.setError("Please, enter a valid password");
            valid = false;
        }
        if (!valid){
            return valid;
        }
        for(int i =0; i< users.size(); i++){
           if (users.get(i).getUsername().equals(username) && users.get(i).getPassword().equals(password)){
               user = new User(username, password);
               valid = true;
               return valid;
           }

        }

        return false;
     }

    public void initialize(){
        username = username_login.getText().toString();
        password = password_login.getText().toString();
    }

}

