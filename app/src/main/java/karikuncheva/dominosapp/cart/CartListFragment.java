package karikuncheva.dominosapp.cart;


import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import karikuncheva.dominosapp.CartBroadcastReceiver;
import karikuncheva.dominosapp.R;
import karikuncheva.dominosapp.model.products.Product;

/**
 * A simple {@link Fragment} subclass.
 */
public class CartListFragment extends Fragment {


    private RecyclerView recyclerView;
    private CartBroadcastReceiver cartBroadcastReceiver = new CartBroadcastReceiver(new CartBroadcastReceiver.CartCheckout() {
        @Override
        public void clearCart() {
            recyclerView.setAdapter(new CartAdapter(getActivity()));
            LocalBroadcastManager.getInstance(getActivity()).unregisterReceiver(cartBroadcastReceiver);
        }
    });

    interface CartComunicator {
        public void sumTotalPrice(double sum);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.activity_cart_fragment, container, false);
        recyclerView = (RecyclerView) root.findViewById(R.id.products_recycle_view);
        recyclerView.setAdapter(new CartAdapter(getActivity()));
        return root;

    }

    @Override
    public void onStart() {
        super.onStart();
        LocalBroadcastManager.getInstance(getActivity()).registerReceiver(cartBroadcastReceiver, new IntentFilter(CartBroadcastReceiver.ACTION_CLEAR_CART));
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    public void addBonusProduct(Product p) {
        recyclerView.setAdapter(new CartAdapter(getActivity(), p));
    }
}
