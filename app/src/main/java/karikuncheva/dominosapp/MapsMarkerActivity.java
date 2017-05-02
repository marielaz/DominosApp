package karikuncheva.dominosapp;

import android.Manifest;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import static com.google.android.gms.location.LocationServices.FusedLocationApi;

/**
 * Created by Patarinski on 4/28/2017.
 */

public class MapsMarkerActivity extends AppCompatActivity
        implements OnMapReadyCallback, GoogleMap.OnMarkerClickListener,
        GoogleApiClient.OnConnectionFailedListener, GoogleApiClient.ConnectionCallbacks, LocationListener {

    private GoogleMap mMap;
    private GoogleApiClient mGoogleApiClient;
    private Location mLastLocation;
    private Marker mCurrLocationMarker;
    private LatLng location;

    /**
     * An activity that displays a Google map with a marker (pin) to indicate a particular location.
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Retrieve the content view that renders the map.
        setContentView(R.layout.activity_map);
        // Get the SupportMapFragment and request notification
        // when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        buildGoogleApiClient();
    }

    /**
     * Manipulates the map when it's available.
     * The API invokes this callback when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user receives a prompt to install
     * Play services inside the SupportMapFragment. The API invokes this method after the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);


        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        mMap.setMyLocationEnabled(true);

        if (location != null) {
            mMap.addMarker(new MarkerOptions().position(location).title("You are here"));
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location, 15));
        }
        LatLng place1 = new LatLng(42.6730175, 23.2855542);
        LatLng place2 = new LatLng(42.6971674, 23.3168656);
        LatLng place3 = new LatLng(42.6800025, 23.3564037);
        LatLng place4 = new LatLng(42.6363762, 23.3679974);
        LatLng place5 = new LatLng(42.6615331, 23.2649124);
        LatLng place6 = new LatLng(42.6745826, 23.3092887);
        LatLng sofia = new LatLng(42.6953468,23.1838616);


        mMap.addMarker(new MarkerOptions()
                .position(place1)
                .icon(BitmapDescriptorFactory.fromResource(R.mipmap.map_logo)));
        mMap.addMarker(new MarkerOptions()
                .position(place2)
                .icon(BitmapDescriptorFactory.fromResource(R.mipmap.map_logo)));
        mMap.addMarker(new MarkerOptions()
                .position(place3)
                .icon(BitmapDescriptorFactory.fromResource(R.mipmap.map_logo)));
        mMap.addMarker(new MarkerOptions()
                .position(place4)
                .icon(BitmapDescriptorFactory.fromResource(R.mipmap.map_logo)));
        mMap.addMarker(new MarkerOptions()
                .position(place5)
                .icon(BitmapDescriptorFactory.fromResource(R.mipmap.map_logo)));
        mMap.addMarker(new MarkerOptions()
                .position(place6)
                .icon(BitmapDescriptorFactory.fromResource(R.mipmap.map_logo)));

        mMap.moveCamera(CameraUpdateFactory.newLatLng(sofia));
        mMap.animateCamera(CameraUpdateFactory.zoomTo(11));
        mMap.setOnMarkerClickListener(this);
    }


    @Override
    public boolean onMarkerClick(Marker marker) {

        final Dialog dialog = new Dialog(MapsMarkerActivity.this);
        dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.alert_dialog_map);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        TextView txt = (TextView) dialog.findViewById(R.id.text_dialog);
        txt.setText("Are you sure you are select the right store?");
        TextView yes = (TextView) dialog.findViewById(R.id.yes_bnt);
        yes.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        TextView no = (TextView) dialog.findViewById(R.id.no_bnt);
        no.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MapsMarkerActivity.this, CatalogActivity.class);
                startActivity(intent);
            }
        });
        dialog.show();
        return true;
    }

    @Override
    protected void onStart() {
        super.onStart();
        mGoogleApiClient.connect();
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        mLastLocation = FusedLocationApi.getLastLocation(mGoogleApiClient);

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public void onLocationChanged(Location location) {
        mLastLocation = location;
        if (mCurrLocationMarker != null) {
            mCurrLocationMarker.remove();
        }

        //Place current location marker
        LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(latLng);
        markerOptions.title("Current Position");
        mCurrLocationMarker = mMap.addMarker(markerOptions);

        //move map camera
        mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
        mMap.animateCamera(CameraUpdateFactory.zoomTo(11));

        //stop location updates
        if (mGoogleApiClient != null) {
            FusedLocationApi.removeLocationUpdates(mGoogleApiClient, this);
        }

    }

    protected synchronized void buildGoogleApiClient() {
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
    }

}
