package karikuncheva.dominosapp;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import karikuncheva.dominosapp.model.products.Dessert;


/**
 * Created by Mariela Zviskova on 14.3.2017 г..
 */

public class DessertCustomAdapter extends RecyclerView.Adapter<DessertCustomAdapter.DessertViewHolder> {

    private Activity activity;
    private List<Dessert> desserts = new ArrayList<>();

    public DessertCustomAdapter(Activity activity, List<Dessert> desserts) {
        this.activity = activity;
        this.desserts = desserts;
    }

    @Override
    public DessertViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater li = LayoutInflater.from(activity);
        View row = li.inflate(R.layout.single_row_des_dr, parent, false);
        DessertViewHolder vh = new DessertViewHolder(row);
        return vh;
    }

    @Override
    public void onBindViewHolder(final DessertViewHolder vh, final int position) {
        vh.dessertImage.setImageResource(desserts.get(position).getImageId());
        vh.dessertName.setText(desserts.get(position).getName());
        vh.dessertDescr.setText(desserts.get(position).getDescription());
        double p = desserts.get(position).getPrice();
        vh.dessertPrice.setText(String.format("%.2f", p));

        vh.cart_bnt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginActivity.loggedUser.getCart().addProduct(desserts.get(position));
                ((PizzaFragment.ProductsCommunicator) activity).increment();
            }
        });

    }

    @Override
    public int getItemCount() {
        return desserts.size();
    }

    class DessertViewHolder extends RecyclerView.ViewHolder {
        ImageButton cart_bnt;
        ImageView dessertImage;
        TextView dessertName;
        TextView dessertDescr;
        TextView dessertPrice;

        DessertViewHolder(View row) {
            super(row);
            cart_bnt = (ImageButton) row.findViewById(R.id.cart_bnt);
            dessertImage = (ImageView) row.findViewById(R.id.image);
            dessertName = (TextView) row.findViewById(R.id.name);
            dessertDescr = (TextView) row.findViewById(R.id.description);
            dessertPrice = (TextView) row.findViewById(R.id.price);
        }

    }
}
