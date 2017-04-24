package karikuncheva.dominosapp;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;

import org.json.JSONObject;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Mariela Zviskova on 24.4.2017 г..
 */

public class PrefFb {

    private static Activity activity;

    // Constructor
    public PrefFb(Activity activity) {
        this.activity = activity;
    }

    public static void saveAccessToken(String token) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(activity);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("fb_access_token", token);
        editor.apply();
    }


    public String getToken() {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(activity);
        return prefs.getString("fb_access_token", null);
    }

    public void clearToken() {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(activity);
        SharedPreferences.Editor editor = prefs.edit();
        editor.clear();
        editor.apply();
    }

    public static void saveFacebookUserInfo(String first_name, String email){
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(activity);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("fb_first_name", first_name);
        editor.putString("fb_email", email);
        editor.apply();
    }

    public void getFacebookUserInfo(){
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(activity);
        Log.d("MyApp", "Name : "+prefs.getString("fb_name",null)+"\nEmail : "+prefs.getString("fb_email",null));
    }

    public Bundle getFacebookData(JSONObject object) {
        Bundle bundle = new Bundle();

        try {
            String id = object.getString("id");
            URL profile_pic;
            try {
                profile_pic = new URL("https://graph.facebook.com/" + id + "/picture?type=large");
                Log.i("profile_pic", profile_pic + "");
                bundle.putString("profile_pic", profile_pic.toString());
            } catch (MalformedURLException e) {
                e.printStackTrace();
                return null;
            }

            bundle.putString("idFacebook", id);
            if (object.has("first_name"))
                bundle.putString("first_name", object.getString("first_name"));
            if (object.has("email"))
                bundle.putString("email", object.getString("email"));


            PrefFb.saveFacebookUserInfo(object.getString("first_name")
                    ,object.getString("email"));

        } catch (Exception e) {
        }

        return bundle;
    }
}