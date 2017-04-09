package karikuncheva.dominosapp;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;

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

//        recyclerView = (RecyclerView) v.findViewById(R.id.recycler_view_address);
//
//
//        CustomAddressAdapter adapter = new CustomAddressAdapter(getActivity(), MainActivity.loggedUser.getAddresses());
//        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
//        recyclerView.setAdapter(adapter);
        return v;

    }

}
