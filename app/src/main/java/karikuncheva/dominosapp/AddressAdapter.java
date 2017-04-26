package karikuncheva.dominosapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import karikuncheva.dominosapp.model.Address;

/**
 * Created by Mariela Zviskova on 9.4.2017 г..
 */

public class AddressAdapter extends RecyclerView.Adapter<AddressAdapter.AddressViewHolder> {

    private Activity activity;
    private List<Address> addresses;
    private int click;
    Bundle bundle;
    private int index =1;

    public AddressAdapter(Activity activity, List<Address> addresses) {
        this.activity = activity;
        this.addresses = MainActivity.loggedUser.getAddresses();
    }

//    public AddressAdapter(Activity activity, List<Address> addresses, int click) {
//        this.activity = activity;
//        this.addresses = MainActivity.loggedUser.getAddresses();
//        this.click = click;
//    }

    @Override
    public AddressAdapter.AddressViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater li = LayoutInflater.from(activity);
        View row = li.inflate(R.layout.single_row_address, parent, false);
        AddressAdapter.AddressViewHolder vh = new AddressAdapter.AddressViewHolder(row);
        return vh;
    }

    @Override
    public void onBindViewHolder(final AddressAdapter.AddressViewHolder vh, final int position) {
        final Address address = addresses.get(position);
        vh.town.setText(address.getTown());
        vh.infoAddress.setText(address.toString());

        vh.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addresses.remove(address);
                notifyDataSetChanged();
                DBManager.getInstance(activity).deleteAddress(address);
            }
        });

        bundle = activity.getIntent().getExtras();
        if(bundle != null){
            vh.layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    vh.layout.setBackgroundDrawable(activity.getResources().getDrawable(R.drawable.rounded_button));

                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return addresses.size();
    }



    class AddressViewHolder extends RecyclerView.ViewHolder {
        TextView town;
        TextView infoAddress;
        ImageButton delete;
        RelativeLayout layout;


        AddressViewHolder(View row) {
            super(row);
            town = (TextView) row.findViewById(R.id.town);
            infoAddress = (TextView) row.findViewById(R.id.info_address);
            delete = (ImageButton) row.findViewById(R.id.delete_address);
            layout = (RelativeLayout) row.findViewById(R.id.single_row_address);
        }
    }
}
