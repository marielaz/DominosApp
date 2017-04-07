package karikuncheva.dominosapp;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;


/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment {

    private EditText username;
    private EditText phone;
    private EditText address;
    private EditText password;
    private EditText confirm;

    public ProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_profile, container, false);

        username = (EditText) v.findViewById(R.id.username_edit_et);
        phone = (EditText) v.findViewById(R.id.phone_edit_et);
        address = (EditText) v.findViewById(R.id.address_edit_et);
        password = (EditText) v.findViewById(R.id.password_edit_et);
        confirm = (EditText) v.findViewById(R.id.password2_edit_et);


        return v;
    }

}
