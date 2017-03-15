package karikuncheva.dominosapp;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.List;
import karikuncheva.dominosapp.model.Shop;
import karikuncheva.dominosapp.model.products.Dessert;
import karikuncheva.dominosapp.model.products.Drink;

/**
 * Created by Mariela Zviskova on 14.3.2017 г..
 */

public class DrinkCustomAdapter extends ArrayAdapter<String> {

    Context fragment3;
    List<Drink> drinks;
    Shop shop = Shop.getInstance();

    public DrinkCustomAdapter(Context fragment3, List<Drink> drinks){
        super(fragment3, R.layout.single_row_des_dr);
        this.fragment3 = fragment3;
        this.drinks = drinks;
    }

    @Override
    public int getCount() {
        return drinks.size();
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) fragment3.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row = inflater.inflate(R.layout.single_row_des_dr, parent, false);

        ImageView drinkImage = (ImageView) row.findViewById(R.id.image);
        TextView drinkName = (TextView) row.findViewById(R.id.name);
        TextView drinkDescr = (TextView) row.findViewById(R.id.description);
        TextView drinkPrice = (TextView) row.findViewById(R.id.price);
        drinkImage.setImageResource(drinks.get(position).getImageId());
        drinkName.setText(drinks.get(position).getName());
        drinkDescr.setText(drinks.get(position).getDescription());
        String price = Double.toString(drinks.get(position).getPrice());
        drinkPrice.setText(price);
        return row;
    }
}
