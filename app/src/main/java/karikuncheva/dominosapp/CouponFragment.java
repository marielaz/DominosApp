package karikuncheva.dominosapp;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import karikuncheva.dominosapp.model.products.Dessert;
import karikuncheva.dominosapp.model.products.Product;


/**
 * A simple {@link Fragment} subclass.
 */
public class CouponFragment extends Fragment {

    public interface CouponCommunicator{
        public void addProduct(Product p);
    }
    private Button redeem_coupon;
    private EditText coupon_code;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_catalog, container, false);

        redeem_coupon = (Button) v.findViewById(R.id.redeem_coupon);
        coupon_code = (EditText) v.findViewById(R.id.coupon_code);

        redeem_coupon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validateCouponCode(coupon_code.getText().toString())) {
                    Product p = new Dessert("chocko", 5.4, 0, "");
                    ((CouponCommunicator)getActivity()).addProduct(p);

                } else {
                    coupon_code.setError("Please, enter valid code");
                }
            }
        });


        return v;
    }

    public boolean validateCouponCode(String code) {
        if (code != null && code.length() == 5 && !code.isEmpty()) {
            String n = ".*[0-9]*.";
            String a = ".*[A-Z]*.";
            if (code.matches(n) && code.matches(a)) {
                return true;
            }
        }
        return false;
    }
}
