package karikuncheva.dominosapp;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Mariela Zviskova on 26.4.2017 г..
 */

public class Session {
    SharedPreferences prefs;
    SharedPreferences.Editor editor;
    Activity activity;

    public Session(Activity activity) {
        this.activity = activity;
        prefs = activity.getSharedPreferences("myapp", Context.MODE_PRIVATE);
        editor = prefs.edit();
    }

    public void setLoggedin(boolean logggedin) {
        editor.putBoolean("loggedInmode", logggedin);
        editor.commit();
    }

    public boolean loggedin() {
        return prefs.getBoolean("loggedInmode", false);
    }
}
