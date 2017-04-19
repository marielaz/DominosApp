package karikuncheva.dominosapp;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import karikuncheva.dominosapp.model.User;
import karikuncheva.dominosapp.model.products.Product;

/**
 * A simple {@link Fragment} subclass.
 */
public class CartListFragment extends Fragment {


    private RecyclerView recyclerView;
    static Product product;

    interface CartComunicator{
        public void sumTotalPrice(double sum);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.activity_cart_fragment, container, false);
        recyclerView = (RecyclerView) root.findViewById(R.id.products_recycle_view);
        recyclerView.setAdapter(new CartAdapter(getActivity()));
        if (product != null){
            MainActivity.loggedUser.getCart().addProduct(product);
        }
        return root;

    }
    public void  addProduct(Product p){
        product = p;
    }
}
