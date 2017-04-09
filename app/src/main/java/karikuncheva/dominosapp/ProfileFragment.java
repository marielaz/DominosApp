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

import com.google.gson.Gson;

import karikuncheva.dominosapp.model.User;


/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment {

    private EditText username;
    private EditText phone;
    private EditText address;
    private EditText password;
    private EditText confirm;
    private TextView welcome;
    private Button save;
    private Button cancel;

    User loggedUser;
    SharedPreferences sharedPreference;

    public ProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_profile, container, false);

        welcome = (TextView) v.findViewById(R.id.welcome);
        username = (EditText) v.findViewById(R.id.username_edit_et);
        phone = (EditText) v.findViewById(R.id.phone_edit_et);
        address = (EditText) v.findViewById(R.id.address_edit_et);
        password = (EditText) v.findViewById(R.id.password_edit_et);
        confirm = (EditText) v.findViewById(R.id.password2_edit_et);
        save = (Button) v.findViewById(R.id.save);
        cancel = (Button) v.findViewById(R.id.cancel);

        sharedPreference = getActivity().getSharedPreferences(RegistrationActivity.PREFS_NAME, Context.MODE_PRIVATE);
        String currentUser = sharedPreference.getString("user", null);
        if(currentUser != null){
            Gson gson = new Gson();
            loggedUser = gson.fromJson(currentUser, User.class);
    }

    save.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            loggedUser.setPhoneNumber(phone.getText().toString());
            loggedUser.setPassword(password.getText().toString());
            loggedUser.setUsername(username.getText().toString());
        }
    });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), CatalogActivity.class);
                startActivity(i);
            }
        });
    username.setText(loggedUser.getUsername().toString());
    welcome.setText("Welcome, " + loggedUser.getUsername());
        return v;
    }

}
