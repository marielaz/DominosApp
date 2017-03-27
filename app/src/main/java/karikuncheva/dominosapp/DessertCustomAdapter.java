package karikuncheva.dominosapp;
import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import karikuncheva.dominosapp.model.Shop;
import karikuncheva.dominosapp.model.User;
import karikuncheva.dominosapp.model.products.Dessert;

import static karikuncheva.dominosapp.R.id.cart_bnt;

/**
 * Created by Mariela Zviskova on 14.3.2017 г..
 */

public class DessertCustomAdapter extends ArrayAdapter<String> {

    private Activity activity;
    private List<Dessert> desserts = new ArrayList<>();
    private User user;


    class DessertViewHolder {
        View row;
        ImageButton cart_bnt;
        ImageView dessertImage;
        TextView dessertName;
        TextView dessertDescr;
        TextView dessertPrice;

        DessertViewHolder(View row){

            cart_bnt = (ImageButton) row.findViewById(R.id.cart_bnt);
            dessertImage = (ImageView) row.findViewById(R.id.image);
            dessertName = (TextView) row.findViewById(R.id.name);
            dessertDescr = (TextView) row.findViewById(R.id.description);
            dessertPrice = (TextView) row.findViewById(R.id.price);
        }

    }


    public DessertCustomAdapter(Activity activity, List<Dessert> desserts, User user){
        super(activity, R.layout.single_row_des_dr);
        this.activity = activity;
        this.desserts = desserts;
        this.user = user;
    }

    @Override
    public int getCount() {
        return desserts.size();
    }


    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row;
        DessertViewHolder vh;

        if (convertView == null) {
            row = inflater.inflate(R.layout.single_row_des_dr, parent, false);
            vh = new DessertViewHolder(row);
            row.setTag(vh);
        }else{
            row = convertView;
            vh = (DessertViewHolder) row.getTag();
        }

        ImageButton cart_bnt = vh.cart_bnt;
        ImageView dessertImage = vh.dessertImage;
        final TextView dessertName = vh.dessertName;
        final TextView dessertDescr = vh.dessertDescr;
        TextView dessertPrice = vh.dessertPrice;
        dessertImage.setImageResource(desserts.get(position).getImageId());
        dessertName.setText(desserts.get(position).getName());
        dessertDescr.setText(desserts.get(position).getDescription());
        double p = desserts.get(position).getPrice();
        dessertPrice.setText(String.format("%.2f", p));

        cart_bnt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO add to cart
                user.getCart().addProduct(desserts.get(position));
                String chosenDessert = dessertName.getText().toString() + " is added to your cart!" ;
                Toast.makeText(v.getContext(),  chosenDessert, Toast.LENGTH_SHORT).show();
            }
        });

        return row;
    }
}
