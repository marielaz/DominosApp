package karikuncheva.dominosapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import karikuncheva.dominosapp.model.Client;
import karikuncheva.dominosapp.model.Shop;


public class MainActivity extends AppCompatActivity {
    private Shop shop = Shop.getInstance();
    private EditText username_login, pass_login;
    private String username, pass;
    private Button login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

}}
