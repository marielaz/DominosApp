package karikuncheva.dominosapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInstaller;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.Button;
import android.view.View;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.Profile;
import com.facebook.ProfileTracker;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.fitness.data.Session;

import karikuncheva.dominosapp.model.User;


public class MainActivity extends AppCompatActivity {

    private EditText username_login;
    private EditText password_login;
    private Button loginButton;
    private Button registerButton;
    private String username, password;
    public static User loggedUser;
    public static User loggedFbUser;
    private LoginButton loginFbButton;
    private CallbackManager callbackManager;
    private AccessTokenTracker accessTokenTracker;
    private ProfileTracker profileTracker;
    private karikuncheva.dominosapp.Session session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FacebookSdk.sdkInitialize(getApplicationContext());

        session = new karikuncheva.dominosapp.Session(this);

        if (session.loggedin()) {
            session.setLoggedin(false);
            finish();
            startActivity(new Intent(MainActivity.this, CatalogActivity.class));
        }

        setContentView(R.layout.activity_main);

        username_login = (EditText) this.findViewById(R.id.username_login);
        password_login = (EditText) this.findViewById(R.id.password_login);
        loginButton = (Button) this.findViewById(R.id.login_button);
        registerButton = (Button) this.findViewById(R.id.registration_button);
        loginFbButton = (LoginButton) this.findViewById(R.id.login_fb_button);
        callbackManager = CallbackManager.Factory.create();
        loginButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (validate()) {

                    if (DBManager.getInstance(MainActivity.this).existsUser(username_login.getText().toString())) {
                        loggedUser = DBManager.getInstance(MainActivity.this).getUser(username_login.getText().toString());
                        if (loggedUser.getPassword().equals(password_login.getText().toString())) {
                            Intent intent = new Intent(MainActivity.this, CatalogActivity.class);
                            MainActivity.this.startActivity(intent);
                        }
                        else{
                            password_login.setError("Wrong password! Please, try again!");
                            password_login.setText("");
                            password_login.requestFocus();
                        }
                    } else {
                        Toast.makeText(MainActivity.this, "User data not valid", Toast.LENGTH_SHORT).show();
                    }

                } else {
                    Toast.makeText(MainActivity.this, "User data not valid", Toast.LENGTH_SHORT).show();
                }
            }
        });

        profileTracker = new ProfileTracker() {
            @Override
            protected void onCurrentProfileChanged(Profile oldProfile, Profile currentProfile) {
                if (Profile.getCurrentProfile() != null) {
                    Intent i = new Intent(MainActivity.this, CatalogActivity.class);
                    startActivity(i);
                }
            }
        };

        accessTokenTracker = new AccessTokenTracker() {
            @Override
            protected void onCurrentAccessTokenChanged(AccessToken oldAccessToken, AccessToken currentAccessToken) {
                if (AccessToken.getCurrentAccessToken() != null) {
                    Intent i = new Intent(MainActivity.this, CatalogActivity.class);
                    startActivity(i);
                }
            }
        };

        accessTokenTracker.startTracking();
        profileTracker.startTracking();

        loginFbButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                AccessToken accessToken = loginResult.getAccessToken();
                Profile profile = Profile.getCurrentProfile();

                if (profile != null) {
                    Intent i = new Intent(MainActivity.this, CatalogActivity.class);
                    loggedFbUser = new User(profile.getFirstName().toString(), profile.getId().toString());
                    DBManager.getInstance(MainActivity.this).addUser(loggedFbUser);
                    if (DBManager.getInstance(MainActivity.this).existsUser(loggedFbUser.getUsername().toString())) {
                        loggedUser = DBManager.getInstance(MainActivity.this).getUser(loggedFbUser.getUsername().toString());
                        startActivity(i);
                    }
                }
            }

            @Override
            public void onCancel() {
                Toast.makeText(MainActivity.this, "Login attempt canceled.", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(FacebookException e) {
                Toast.makeText(MainActivity.this, "Login attempt failed.", Toast.LENGTH_SHORT).show();

            }
        });

        registerButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent_reg = new Intent(MainActivity.this, RegistrationActivity.class);
                MainActivity.this.startActivity(intent_reg);
            }
        });
    }

    public boolean validate() {
        initialize();
        if (username.isEmpty()) {
            username_login.setError("Please, enter a valid username!");
            username_login.requestFocus();
            return false;
        }
        if (password.isEmpty()) {
            password_login.setError("Please, enter a valid password");
            password_login.requestFocus();
            return false;
        }
        return true;
    }

    public void initialize() {
        username = username_login.getText().toString().trim();
        password = password_login.getText().toString().trim();
    }

    @Override
    protected void onStop() {
        super.onStop();
        accessTokenTracker.stopTracking();
        profileTracker.stopTracking();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

}

