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
import karikuncheva.dominosapp.model.Address;
import karikuncheva.dominosapp.model.User;

/**
 * Created by Mariela Zviskova on 11.4.2017 г..
 */

public class DBManager extends SQLiteOpenHelper {
    private static DBManager ourInstance;
    private static Context context;
    private static HashMap<String, User> registeredUsers;
    //private static ArrayList<Address> addresses;
    private static final String SQL_CREATE_USERS = "CREATE TABLE users(\n" +
            "\n" +
            " id INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
            " username text NOT NULL,\n" +
            " password text NOT NULL,\n" +
            " email text NOT NULL,\n" +
            " name text,\n" +
            " phone text\n" +
            ");";

    private static final String SQL_CREATE_ADDRESS = "CREATE TABLE addresses(\n" +
            "\n" +
            " id INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
            " town text NOT NULL,\n" +
            " neighbourhood text NOT NULL,\n" +
            " street text NOT NULL,\n" +
            " number text NOT NULL,\n" +
            " block text NOT NULL,\n" +
            " postCode text NOT NULL,\n" +
            " apartment text NOT NULL,\n" +
            " floor text NOT NULL,\n" +
            " idUser INTEGER NOT NULL,\n" +
            " FOREIGN KEY(idUser) REFERENCES users(id)\n" +
            ");";

    public static DBManager getInstance(Context context) {
        if (ourInstance == null) {
            ourInstance = new DBManager(context);
            DBManager.context = context;
            registeredUsers = new HashMap<>();
           // addresses = new ArrayList<>();
            loadUsers();
        }
        return ourInstance;
    }

    private DBManager(Context context) {
        super(context, "mydb", null, 2);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_USERS);
        db.execSQL(SQL_CREATE_ADDRESS);
        Toast.makeText(context, "DB created", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE users;");
        onCreate(db);
    }

    // public SQLiteDatabase create() {
//        return getWritableDatabase();
//    }

    public void addUser(User u) {
        if (existsUser(u.getUsername())) {
            Toast.makeText(context, "User already exists", Toast.LENGTH_SHORT).show();
            return;
        }
        ContentValues contentValues = new ContentValues();
        contentValues.put("username", u.getUsername());
        contentValues.put("password", u.getPassword());
        contentValues.put("email", u.getEmail());
        contentValues.put("name", u.getName());
        contentValues.put("phone", u.getPhoneNumber());
        long id = getWritableDatabase().insert("users", null, contentValues);
        u.setId((int) id);
        registeredUsers.put(u.getUsername(), u);
        Toast.makeText(context, "User added successfully", Toast.LENGTH_SHORT).show();
    }

    public void addAddress(Address a) {

        ContentValues contentValues = new ContentValues();
        contentValues.put("town", a.getTown());
        contentValues.put("neighbourhood", a.getNeighbourhood());
        contentValues.put("street", a.getStreet());
        contentValues.put("number", a.getNumber());
        contentValues.put("postCode", a.getPostCode());
        contentValues.put("block", a.getBlock());
        contentValues.put("apartment", a.getApartment());
        contentValues.put("floor", a.getFloor());
        contentValues.put("idUser", MainActivity.loggedUser.getId());
        long id = getWritableDatabase().insert("addresses", null, contentValues);
        a.setId((int) id);
        a.setIdUser(MainActivity.loggedUser.getId());
        //addresses.add(a);
        MainActivity.loggedUser.getAddresses().add(a);
        Toast.makeText(context, "Address added successfully", Toast.LENGTH_SHORT).show();
    }

    public void updateUser(String username) {

        new AsyncTask<String, Void, Void>() {
            @Override
            protected Void doInBackground(String... strings) {
                String username = strings[0];

                ContentValues values = new ContentValues();
                values.put("username", MainActivity.loggedUser.getUsername());
                values.put("password", MainActivity.loggedUser.getPassword());
                values.put("email", MainActivity.loggedUser.getEmail());
                values.put("name", MainActivity.loggedUser.getName());
                values.put("phone", MainActivity.loggedUser.getPhoneNumber());
                registeredUsers.remove(username);
                registeredUsers.put(username, MainActivity.loggedUser);
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

        Cursor cursor = ourInstance.getWritableDatabase().rawQuery("SELECT id, username, password, email, name, phone FROM users;", null);
        while(cursor.moveToNext()){
            int id = cursor.getInt(cursor.getColumnIndex("id"));
            String username = cursor.getString(cursor.getColumnIndex("username"));
            String password = cursor.getString(cursor.getColumnIndex("password"));
            String email = cursor.getString(cursor.getColumnIndex("email"));
            String name = cursor.getString(cursor.getColumnIndex("name"));
            String phone = cursor.getString(cursor.getColumnIndex("phone"));
            User u = new User(username, password, email);
            u.setName(name);
            u.setPhoneNumber(phone);
            u.setId(id);
            u.setAddresses(loadAddresses(u.getId()));
            registeredUsers.put(username, u);


        }
    }

    public static ArrayList<Address> loadAddresses(int userId ) {
        ArrayList<Address> addresses = new ArrayList<Address>();
        Cursor cursor = ourInstance.getWritableDatabase().rawQuery("SELECT id, town, neighbourhood, street, number, block, postCode, apartment, floor, idUser FROM addresses WHERE idUser = '"+userId+"';",null);
        while (cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndex("id"));
            String town = cursor.getString(cursor.getColumnIndex("town"));
            String neighbourhood = cursor.getString(cursor.getColumnIndex("neighbourhood"));
            String street = cursor.getString(cursor.getColumnIndex("street"));
            String number = cursor.getString(cursor.getColumnIndex("number"));
            String block = cursor.getString(cursor.getColumnIndex("block"));
            String postCode = cursor.getString(cursor.getColumnIndex("postCode"));
            String apartment = cursor.getString(cursor.getColumnIndex("apartment"));
            String floor = cursor.getString(cursor.getColumnIndex("floor"));
            int idUser = cursor.getInt(cursor.getColumnIndex("idUser"));

            Address a = new Address(town, neighbourhood, street, number, block, postCode, apartment, floor);
            a.setId(id);
            a.setIdUser(idUser);
            addresses.add(a);
        }
        return  addresses;
    }


    public void deleteAddress(Address a){
            getWritableDatabase().delete("addresses", "id = ?", new String[]{Integer.toString(a.getId())});
            Toast.makeText(context, a.getId() + " deleted successfully", Toast.LENGTH_SHORT).show();
        // TODO how to remove address from DB
           // addresses.remove(a);
        }

    public boolean existsUser(String username) {
        return registeredUsers.containsKey(username);
    }

    public User getUser(String username) {
        return registeredUsers.get(username);
    }
}