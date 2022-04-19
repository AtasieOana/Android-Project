package com.example.projectandroid.repository;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.projectandroid.database.AppDatabase;
import com.example.projectandroid.models.User;

import java.util.ArrayList;
import java.util.List;

public class UserRepository {

    private final AppDatabase appDatabase;
    private final SQLiteDatabase db;

    public UserRepository(Context context) {
        appDatabase = new AppDatabase(context);
        db = appDatabase.getReadableDatabase();

    }

    public int insertUser(User user) {
        ContentValues values = new ContentValues();
        values.put("name", user.getName());
        values.put("email", user.getEmail());
        values.put("password", user.getPassword());

        return (int) db.insert("USER_TABLE", null, values);
    }

    public User getUserById(int id) {

        Cursor cursor = db.query("USER_TABLE", new String[] { "id",
                        "email", "password", "name" }, "id = ?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        return new User(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1), cursor.getString(2), cursor.getString(3));
    }

    public User getUserByEmail(String email) {
        Cursor cursor = db.query("USER_TABLE", new String[] { "id",
                        "email", "password", "name" }, "email = ?",
                new String[] { String.valueOf(email) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        return new User(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1), cursor.getString(2), cursor.getString(3));
    }


    public User getUserByName(String name) {
        Cursor cursor = db.query("USER_TABLE", new String[] { "id",
                        "email", "password", "name" }, "name = ?",
                new String[] { String.valueOf(name) }, null, null, null, null);

        if(cursor.getCount()<=0){
            return null;
        }

        cursor.moveToFirst();

        return new User(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1), cursor.getString(2), cursor.getString(3));
    }
    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<User>();
        String selectQuery = "SELECT  * FROM USER_TABLE";

        Cursor cursor = db.rawQuery(selectQuery, null);
        
        if (cursor.moveToFirst()) {
            do {
                User user = new User();
                user.setId(Integer.parseInt(cursor.getString(0)));
                user.setEmail(cursor.getString(1));
                user.setPassword(cursor.getString(2));
                user.setName(cursor.getString(3));
                
                userList.add(user);
            } while (cursor.moveToNext());
        }
        return userList;
    }

    public int updateUser(User user) {
        ContentValues values = new ContentValues();
        values.put("name", user.getName());
        values.put("email", user.getEmail());
        values.put("password", user.getPassword());
        
        return db.update("USER_TABLE", values, "id = ?",
                new String[] { String.valueOf(user.getId()) });
    }

    public void deleteUser(User user) {
        db.delete("USER_TABLE", "id = ?",
                new String[] { String.valueOf(user.getId()) });
        db.close();
    }


}