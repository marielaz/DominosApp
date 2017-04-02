package karikuncheva.dominosapp;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class CartTotalFragment extends Fragment {

    private TextView total;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_cart_total, container, false);
        total = (TextView) root.findViewById(R.id.activity_total_fragment);
        total.setText("Total: " + String.format("%.2f", CartAdapter.getTotal()));
        return root ;
    }

    public void sumTotalPrice(double sum){

        total.setText("Total: " + String.format("%.2f",sum));
    }

}
