package karikuncheva.dominosapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by Patarinski on 5/2/2017.
 */

public class CartBroadcastReceiver extends BroadcastReceiver{

    private CartCheckout cartCheckout;
    public static final String ACTION_CLEAR_CART = "BroadcastAction_ClearCart";

    public CartBroadcastReceiver(CartCheckout cartCheckout) {
        this.cartCheckout = cartCheckout;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals(ACTION_CLEAR_CART)){
            if (cartCheckout != null){
                cartCheckout.clearCart();
            }
        }
    }

    public interface CartCheckout{
        void clearCart();
    }
}
