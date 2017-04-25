package karikuncheva.dominosapp;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import karikuncheva.dominosapp.model.products.Dessert;
import karikuncheva.dominosapp.model.products.Drink;
import karikuncheva.dominosapp.model.products.Product;


/**
 * A simple {@link Fragment} subclass.
 */
public class CouponFragment extends Fragment {

    public interface CouponCommunicator {
        public void addProduct(Product p);
    }

    private Button redeem_coupon;
    private EditText coupon_code;
    private static int counter = 0;


    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_coupon, container, false);

        redeem_coupon = (Button) v.findViewById(R.id.redeem_coupon);
        coupon_code = (EditText) v.findViewById(R.id.coupon_code);

        redeem_coupon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (counter == 1) {
                    Toast.makeText(getActivity(), "You can use only one coupon code!", Toast.LENGTH_SHORT).show();

                } else {

                    if (coupon_code.getText().toString().equals("KARIF")) {
                        addBonus(new Drink("Fanta", 0.00, R.drawable.fanta, "1,25l"));

                    } else if (coupon_code.getText().toString().equals("KARIS")) {
                        addBonus(new Dessert("Souflle", 0.00, R.drawable.souffle, "Chocolate lava cake filled with melted warm chocolate"));

                    } else if (coupon_code.getText().toString().equals("MARIC")) {
                        addBonus(new Drink("Coca-Cola", 0.00, R.drawable.cola, "1,25l"));

                    } else if (coupon_code.getText().toString().equals("MARIN")) {
                        addBonus(new Dessert("Nirvana", 0.00, R.drawable.nirvana, "Nirvana Pralines & Cream"));

                    } else {
                        Toast.makeText(getActivity(), "Invalid coupon code!", Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });

        return v;
    }

    private void addBonus(Product p) {
        ((CouponCommunicator) getActivity()).addProduct(p);
        counter++;
        coupon_code.setText("");
        Toast.makeText(getActivity(), "The products is added to your cart!", Toast.LENGTH_SHORT).show();
    }
}
