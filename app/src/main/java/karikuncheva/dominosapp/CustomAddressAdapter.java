package karikuncheva.dominosapp;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import karikuncheva.dominosapp.model.Address;
import karikuncheva.dominosapp.model.products.Pizza;

/**
 * Created by Mariela Zviskova on 9.4.2017 г..
 */

public class CustomAddressAdapter extends RecyclerView.Adapter<CustomAddressAdapter.AddressViewHolder> {

    private Activity activity;
    private List<Address> addresses;


    public CustomAddressAdapter(Activity activity, List<Address> addresses) {
        this.activity = activity;
        this.addresses = addresses;
    }

    @Override
    public CustomAddressAdapter.AddressViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater li = LayoutInflater.from(activity);
        View row = li.inflate(R.layout.single_row_address, parent, false);
        CustomAddressAdapter.AddressViewHolder vh = new CustomAddressAdapter.AddressViewHolder(row);
        return vh;
    }

    @Override
    public void onBindViewHolder(final CustomAddressAdapter.AddressViewHolder vh, final int position) {
        Address address = addresses.get(position);
        vh.town.setText(address.getTown());
        vh.neighborhood.setText(address.getNaighborhood());
        vh.block.setText(address.getBlock());
        vh.phone.setText(address.getPhoneNumber());
    }

    @Override
    public int getItemCount() {
        return addresses.size();
    }

    class AddressViewHolder extends RecyclerView.ViewHolder {
        TextView town;
        TextView neighborhood;
        TextView block;
        TextView phone;

        AddressViewHolder(View row) {
            super(row);
            town = (TextView) row.findViewById(R.id.town);
            neighborhood = (TextView) row.findViewById(R.id.neighborhood);
            block = (TextView) row.findViewById(R.id.block);
            phone = (TextView) row.findViewById(R.id.phone);
        }
    }
}
