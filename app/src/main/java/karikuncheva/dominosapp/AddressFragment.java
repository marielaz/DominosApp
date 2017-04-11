package karikuncheva.dominosapp;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;

import karikuncheva.dominosapp.model.Address;
import karikuncheva.dominosapp.model.Shop;
import karikuncheva.dominosapp.model.User;


/**
 * A simple {@link Fragment} subclass.
 */
public class AddressFragment extends Fragment {

    RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_address, container, false);

        recyclerView = (RecyclerView) v.findViewById(R.id.recycler_view_address);

       // MainActivity.loggedUser.addAddress(new Address("sf","ssd", "314", "assd"));

    //    MainActivity.loggedUser.getAddresses().add(new Address("sofiq", "feh", "fhje", "hfu"));

        CustomAddressAdapter adapter = new CustomAddressAdapter(getActivity(), MainActivity.loggedUser.getAddresses());
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);

        FloatingActionButton fab = (FloatingActionButton) v.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
                Intent i = new Intent(getActivity(), EditAddressActivity.class);
                startActivity(i);
            }
        });
        return v;

    }

}
