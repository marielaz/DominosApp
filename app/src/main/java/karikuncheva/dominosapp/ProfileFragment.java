package karikuncheva.dominosapp;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import karikuncheva.dominosapp.model.User;


/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment {

    private EditText phone;
    private EditText name;
    private EditText password;
    private EditText confirm;
    private TextView welcome;
    private Button save;
    private Button cancel;

    private User loggedUser;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_profile, container, false);

        welcome = (TextView) v.findViewById(R.id.welcome);
        phone = (EditText) v.findViewById(R.id.phone_edit_et);
        name = (EditText) v.findViewById(R.id.name_edit_et);
        password = (EditText) v.findViewById(R.id.password_edit_et);
        confirm = (EditText) v.findViewById(R.id.password2_edit_et);
        save = (Button) v.findViewById(R.id.save);
        cancel = (Button) v.findViewById(R.id.cancel);


        welcome.setText("Welcome, " + MainActivity.loggedUser.getUsername());

        if (MainActivity.loggedUser.getName() != null) {
            name.setText(MainActivity.loggedUser.getName());
        }
        if (MainActivity.loggedUser.getPhoneNumber() != null){
            phone.setText(MainActivity.loggedUser.getPhoneNumber());
        }

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!name.getText().toString().isEmpty()){
                    MainActivity.loggedUser.setName(name.getText().toString());
                }
                if (!password.getText().toString().isEmpty() &&
                        password.getText().toString().equals(confirm.getText().toString())) {
                    MainActivity.loggedUser.setPassword(password.getText().toString());
                }
                if (!phone.getText().toString().isEmpty() && phone.getText().toString() != null) {
                    if (validateMobileNumber(phone.getText().toString())) {
                        MainActivity.loggedUser.setPhoneNumber(phone.getText().toString());
                    }
                }
                DBManager.getInstance(getActivity()).updateUser(MainActivity.loggedUser.getUsername());

                Toast.makeText(getActivity(), "Saved changes", Toast.LENGTH_SHORT).show();
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), CatalogActivity.class);
                startActivity(i);
            }
        });

        return v;
    }
    public boolean validateMobileNumber(String mobileNumber) {
        Pattern regexPattern = Pattern.compile("^((088)|(089)|(087))[0-9]{7}$");
        Matcher regMatcher = regexPattern.matcher(mobileNumber);
        if (regMatcher.matches()) {
            return true;
        }
        Toast.makeText(getActivity(), "Invalid Mobile Number", Toast.LENGTH_SHORT).show();
        return false;
    }
}
