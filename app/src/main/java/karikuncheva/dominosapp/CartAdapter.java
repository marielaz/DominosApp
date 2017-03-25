package karikuncheva.dominosapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
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
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import karikuncheva.dominosapp.model.User;
import karikuncheva.dominosapp.model.products.Pizza;
import karikuncheva.dominosapp.model.products.Product;

/**
 * Created by Patarinski on 3/25/2017.
 */

public class CartAdapter extends ArrayAdapter<String>{

    private Activity activity;
    private User user;


    public CartAdapter(Activity activity, User user){
        super(activity, R.layout.single_row_cart);
        this.activity = activity;
        this.user = user;
    }

    @Override
    public int getCount() {
       // hashmap !!!
        //return user.getCart().size();
        return 0;
    }


    @NonNull
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        //convert xml to java with inflater
        LayoutInflater inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row = inflater.inflate(R.layout.single_row_pizza, parent, false);

//        if (convertView == null) {
//            convertView = LayoutInflater.from(getContext()).inflate(R.layout.single_row_pizza, parent, false);
//        }


        return row;
    }
}
