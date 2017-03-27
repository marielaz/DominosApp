package karikuncheva.dominosapp;

/**
 * Created by Mariela Zviskova on 10.3.2017 г..
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IntRange;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import karikuncheva.dominosapp.model.User;
import karikuncheva.dominosapp.model.products.Pizza;


public class CatalogActivity extends AppCompatActivity {

    private User user;
    private ImageButton go_to_cart;

    //This is our tablayout
    private TabLayout tabLayout;

    //This is our viewPager
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catalog);

        go_to_cart = (ImageButton) findViewById(R.id.go_to_cart_bnt);
        //Initializing the tablayout
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);

        //Adding the tabs using addTab() method
        tabLayout.addTab(tabLayout.newTab().setText("Pizzas"));
        tabLayout.addTab(tabLayout.newTab().setText("Desserts"));
        tabLayout.addTab(tabLayout.newTab().setText("Drinks"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        //Initializing viewPager
        viewPager = (ViewPager) findViewById(R.id.pager);

        //get the user
         user = (User)getIntent().getExtras().getSerializable("user");

        //Creating our pager adapter
        PagerAdapter adapter = new PagerAdapter(getSupportFragmentManager(), tabLayout.getTabCount(), user);

        //Adding adapter to pager
        viewPager.setAdapter(adapter);

        //Adding onTabSelectedListener to swipe views
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


        go_to_cart.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                // TODO
                // startActivityForResult - bring back the user if he is changing the products in the cart !
                Intent intent = new Intent(CatalogActivity.this, CartActivity.class);
                intent.putExtra("user", user);
                CatalogActivity.this.startActivity(intent);
            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == 5) {
            if (data != null) {
                Pizza p = (Pizza) data.getSerializableExtra("pizza");
                user.getCart().addProduct(p);

            }
        }
    }

}
