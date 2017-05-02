package karikuncheva.dominosapp.catalog;

/**
 * Created by Mariela Zviskova on 10.3.2017 г..
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import karikuncheva.dominosapp.cart.CartActivity;
import karikuncheva.dominosapp.LoginActivity;
import karikuncheva.dominosapp.navigation.AddressActivity;
import karikuncheva.dominosapp.navigation.ContactsActivity;
import karikuncheva.dominosapp.navigation.NavigDrawerActivity;
import karikuncheva.dominosapp.navigation.ProfileActivity;
import karikuncheva.dominosapp.R;

public class CatalogActivity extends NavigDrawerActivity implements PizzaFragment.ProductsCommunicator {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private TextView name;
    private TextView email;
    private TextView products;
    private ImageButton cart_bnt;
    public static int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navig_drawer);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        View header = navigationView.getHeaderView(0);
        name = (TextView) header.findViewById(R.id.header_name);
        email = (TextView) header.findViewById(R.id.header_email);
        products = (TextView) findViewById(R.id.count_products);
        cart_bnt = (ImageButton) findViewById(R.id.cart_bnt);

        if(LoginActivity.loggedUser.getName() != null  && !LoginActivity.loggedUser.getName().isEmpty()) {
            name.setText(LoginActivity.loggedUser.getName());
        }
        email.setText(LoginActivity.loggedUser.getEmail());

        resetCount();
        cart_bnt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CatalogActivity.this, CartActivity.class);
                startActivity(intent);
            }
        });
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);

        tabLayout.addTab(tabLayout.newTab().setText("Pizzas"));
        tabLayout.addTab(tabLayout.newTab().setText("Desserts"));
        tabLayout.addTab(tabLayout.newTab().setText("Drinks"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        viewPager = (ViewPager) findViewById(R.id.pager);

        PagerAdapter adapter = new PagerAdapter(getSupportFragmentManager(), tabLayout.getTabCount());

        viewPager.setAdapter(adapter);

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

    }

    @Override
    protected void onResume() {
        super.onResume();
        resetCount();
    }

    private void resetCount() {
        if (count != 0) {
            products.setVisibility(View.VISIBLE);
            products.setText(String.valueOf(count));
        } else {
            products.setVisibility(View.GONE);
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.navig_drawer, menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_log_out) {
            Intent i = new Intent(this, LoginActivity.class);
            this.startActivity(i);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_profile) {
            Intent i = new Intent(CatalogActivity.this, ProfileActivity.class);
            startActivity(i);

        } else if (id == R.id.nav_contacts) {
            Intent i = new Intent(CatalogActivity.this, ContactsActivity.class);
            startActivity(i);

        } else if (id == R.id.nav_cart) {
            Intent i = new Intent(CatalogActivity.this, CartActivity.class);
            startActivity(i);
        }

        if (id == R.id.nav_address) {
            Intent i = new Intent(CatalogActivity.this, AddressActivity.class);
            startActivity(i);

        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void increment() {
        products.setVisibility(View.VISIBLE);
        count++;
        products.setText(String.valueOf(count));
    }

    @Override
    public void decrement() {
        count--;
        products.setText(String.valueOf(count));
    }

    @Override
    public void clear() {
        count = 0;
    }

}
