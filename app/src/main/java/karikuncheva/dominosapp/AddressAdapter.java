package karikuncheva.dominosapp;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import karikuncheva.dominosapp.model.Address;

/**
 * Created by Mariela Zviskova on 9.4.2017 г..
 */

public class AddressAdapter extends RecyclerView.Adapter<AddressAdapter.AddressViewHolder> {

    private Activity activity;
    private List<Address> addresses;

    public AddressAdapter(Activity activity, List<Address> addresses) {
        this.activity = activity;
        this.addresses = MainActivity.loggedUser.getAddresses();
    }

    @Override
    public AddressAdapter.AddressViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater li = LayoutInflater.from(activity);
        View row = li.inflate(R.layout.single_row_address, parent, false);
        AddressAdapter.AddressViewHolder vh = new AddressAdapter.AddressViewHolder(row);
        return vh;
    }

    @Override
    public void onBindViewHolder(final AddressAdapter.AddressViewHolder vh, final int position) {
        Address address = addresses.get(position);
        vh.town.setText(address.getTown());
        vh.neighborhood.setText(address.getNeighbourhood());
        vh.block.setText(address.getBlock());

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
