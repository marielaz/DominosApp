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

/**
 * Created by Mariela Zviskova on 14.3.2017 г..
 */

public class DessertCustomAdapter extends ArrayAdapter<String> {

    Context fragment2;
    List<Dessert> desserts;
    Shop shop = Shop.getInstance();

    public DessertCustomAdapter(Context fragment2, List<Dessert> desserts){
        super(fragment2, R.layout.single_row_des_dr);
        this.fragment2 = fragment2;
        this.desserts = desserts;
    }

    @Override
    public int getCount() {
        return desserts.size();
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) fragment2.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row = inflater.inflate(R.layout.single_row_des_dr, parent, false);

        ImageView pizzaImage = (ImageView) row.findViewById(R.id.image);
        TextView pizzaName = (TextView) row.findViewById(R.id.name);
        TextView pizzaDescr = (TextView) row.findViewById(R.id.description);
        TextView pizzaPrice = (TextView) row.findViewById(R.id.price);
        pizzaImage.setImageResource(desserts.get(position).getImageId());
        pizzaName.setText(desserts.get(position).getName());
        pizzaDescr.setText(desserts.get(position).getDescription());
        String price = Double.toString(desserts.get(position).getPrice());
        pizzaPrice.setText(price);
        return row;
    }
}
