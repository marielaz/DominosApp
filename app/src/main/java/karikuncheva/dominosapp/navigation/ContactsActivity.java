package karikuncheva.dominosapp.navigation;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import karikuncheva.dominosapp.catalog.CatalogActivity;
import karikuncheva.dominosapp.R;

public class ContactsActivity extends AppCompatActivity {

    Button call;
    Button tweet;
    Button fb;
    Button insta;
    Button back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_contacts);

        call = (Button) findViewById(R.id.call);
        tweet = (Button) findViewById(R.id.tweeter);
        fb = (Button) findViewById(R.id.fb);
        back = (Button) findViewById(R.id.back_button_contact);
        insta = (Button) findViewById(R.id.instagram);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ContactsActivity.this, CatalogActivity.class));
            }
        });

        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onCall();
            }
        });

        fb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/DominosBulgaria")));
            }
        });

        tweet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://twitter.com/DominosBulgaria")));
            }
        });

        insta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.instagram.com/dominos_bg/")));
            }
        });

    }

    public void onCall() {
        Uri call = Uri.parse("tel:070012525");
        Intent surf = new Intent(Intent.ACTION_DIAL, call);
        int permissionCheck = ContextCompat.checkSelfPermission(ContactsActivity.this,
                Manifest.permission.CALL_PHONE);
        if (ActivityCompat.checkSelfPermission(ContactsActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(ContactsActivity.this, new String[]{Manifest.permission.CALL_PHONE}, 1);

                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return;
            }
        }
        ContactsActivity.this.startActivity(surf);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case 1:
                if ((grantResults.length > 0) && (grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                    onCall();
                } else {
                    Log.e("mari", "Call permission not granted!");
                }
                break;

            default:
                break;
        }
    }
}


