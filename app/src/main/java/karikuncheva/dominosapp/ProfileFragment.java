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

import karikuncheva.dominosapp.model.User;


/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment {

    private TextView username;
    private EditText phone;
    private EditText name;
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
        username = (TextView) v.findViewById(R.id.username_edit_et);
        phone = (EditText) v.findViewById(R.id.phone_edit_et);
        name = (EditText) v.findViewById(R.id.name_edit_et);
        password = (EditText) v.findViewById(R.id.password_edit_et);
        confirm = (EditText) v.findViewById(R.id.password2_edit_et);
        save = (Button) v.findViewById(R.id.save);
        cancel = (Button) v.findViewById(R.id.cancel);

        // TODO edit user also in shared prefs!!
        // nqma smisul ot towa kato imame static loggedUser
        sharedPreference = getActivity().getSharedPreferences(RegistrationActivity.PREFS_NAME, Context.MODE_PRIVATE);
//        String currentUser = sharedPreference.getString("user", MainActivity.loggedUser.getUsername());
//        if(currentUser != null){
//            Gson gson = new Gson();
//            loggedUser = gson.fromJson(currentUser, User.class);
//    }

        loggedUser = MainActivity.loggedUser;

        welcome.setText("Welcome, " + loggedUser.getUsername());
        username.setText(loggedUser.getUsername());

        if (loggedUser.getName() != null) {
            name.setText(loggedUser.getName());
        }
        if (loggedUser.getPhoneNumber() != null){
            phone.setText(loggedUser.getPhoneNumber());
        }

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!name.getText().toString().isEmpty()){
                    loggedUser.setName(name.getText().toString());
                }
                if (!password.getText().toString().isEmpty() &&
                        password.getText().toString().equals(confirm.getText().toString())) {
                    loggedUser.setPassword(password.getText().toString());
                }
                if (!phone.getText().toString().isEmpty() && phone.getText().toString() != null){
                    loggedUser.setPhoneNumber(phone.getText().toString());
                }
                User user = loggedUser;
               // user.setCart(loggedUser.getCart());
                SharedPreferences.Editor editor = sharedPreference.edit();
                Gson gson = new Gson();
                editor.putString("user", gson.toJson(user));
                editor.apply();
                MainActivity.loggedUser = user;
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

}
