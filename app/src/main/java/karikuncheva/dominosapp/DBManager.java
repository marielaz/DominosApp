package karikuncheva.dominosapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.AsyncTask;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

import karikuncheva.dominosapp.model.User;

/**
 * Created by Mariela Zviskova on 11.4.2017 г..
 */

public class DBManager extends SQLiteOpenHelper {
    private static DBManager ourInstance;

    private static Context context;
    private static HashMap<String, User>  registeredUsers;
    private static final String SQL_CREATE_USERS = "CREATE TABLE users(\n" +
            "\n" +
            " id INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
            " username text NOT NULL,\n" +
            " password text NOT NULL,\n" +
            " email text NOT NULL,\n" +
            " name text,\n" +
            " phone text,\n" +
            ");";

    public static DBManager getInstance(Context context) {
        if(ourInstance == null){
            ourInstance = new DBManager(context);
            DBManager.context = context;
            registeredUsers = new HashMap<>();
            loadUsers();
        }
        return ourInstance;
    }

    private DBManager(Context context) {
        super(context, "mydb", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_USERS);
        Toast.makeText(context, "DB created", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE users;");
        onCreate(db);
    }

    public SQLiteDatabase create() {
        return getWritableDatabase();
    }

    public void addUser(User u){
        if(existsUser(u.getUsername())){
            Toast.makeText(context, "User already exists", Toast.LENGTH_SHORT).show();
            return;
        }
        ContentValues contentValues = new ContentValues();
        contentValues.put("username", u.getUsername());
        contentValues.put("password", u.getPassword());
        contentValues.put("email", u.getEmail());
        contentValues.put("name", u.getName());
        contentValues.put("phone", u.getPhoneNumber());
        long id = getWritableDatabase().insert("users", null, contentValues );
        u.setId((int)id);
        registeredUsers.put(u.getUsername(), u);
        Toast.makeText(context, "User added successfully", Toast.LENGTH_SHORT).show();
    }

    public void updateUser(String username){

        new AsyncTask<String, Void, Void>(){
            @Override
            protected Void doInBackground(String... strings) {
                String username = strings[0];
                User u = registeredUsers.get(username);

                u.setUsername("");
                u.setPassword("");
                u.setEmail("");
                u.setName("");
                u.setPhoneNumber("");
                ContentValues values = new ContentValues();
                values.put("username",u.getUsername());
                values.put("password", u.getPassword());
                values.put("email", u.getEmail());
                values.put("name", u.getName());
                values.put("phone", u.getPhoneNumber());
                registeredUsers.remove(username);
                registeredUsers.put(u.getUsername(), u);
                getWritableDatabase().update("users", values ,"username = ?", new String[]{username});
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                Toast.makeText(context, " user updated successfully", Toast.LENGTH_SHORT).show();
            }
        }.execute(username);
    }

    private static void loadUsers(){
        Cursor cursor = ourInstance.getWritableDatabase().rawQuery("SELECT id, username, password, email FROM users;", null);
        while(cursor.moveToNext()){
            int id = cursor.getInt(cursor.getColumnIndex("id"));
            String username = cursor.getString(cursor.getColumnIndex("username"));
            String password = cursor.getString(cursor.getColumnIndex("password"));
            String email = cursor.getString(cursor.getColumnIndex("email"));
            User u = new User(username, password, email);
            u.setId(id);
            registeredUsers.put(username, u);
        }
    }

    public boolean existsUser(String username){
        return registeredUsers.containsKey(username);
    }

}
