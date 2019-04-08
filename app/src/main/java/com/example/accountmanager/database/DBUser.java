//package com.example.accountmanager.database;
//
//import android.content.ContentValues;
//import android.content.Context;
//import android.database.sqlite.SQLiteDatabase;
//import com.example.accountmanager.model.User;
//public class DBUser {
//    private DataBaseHelper dbHelper;
//    private SQLiteDatabase db;
//    private Context context;
//
//    public DBUser(Context context) {
//        this.context = context;
//        //dbHelper = DataBaseHelper.getInstance(context);
//    }
//
//    public long InsertUser(User user) {
//        db = dbHelper.getWritableDatabase();
//        ContentValues values = new ContentValues();
//        values.put(DataBaseHelper.COLUMN_USER_NAME, user.getName());
//        values.put(DataBaseHelper.COLUMN_USER_EMAIL, user.getEmail());
//        values.put(DataBaseHelper.COLUMN_USER_PASSWORD, user.getPassword());
//        long result = 0;
//        try {
//            result = db.insert(DataBaseHelper.TABLE_USER, null, values);// insert row;
//            db.close(); // close database
//        } catch (Exception e) {
//
//        }
//        return result;
//
//    }
//
//    // delete User
//    public long deleteUser(User user) {
//        db = dbHelper.getWritableDatabase();
//        long reslut = 0;
//        try {
//            // delete user record by id
//            reslut = db.delete(DataBaseHelper.TABLE_USER, DataBaseHelper.COLUMN_USER_ID + " = ?",
//                    new String[]{String.valueOf(user.getId())});
//            db.close();
//        } catch (Exception e) {
//
//        }
//        return reslut;
//    }
//
//    // update User
//    public long updateUser(User user) {
//        db = dbHelper.getWritableDatabase();
//        ContentValues values = new ContentValues();
//        values.put(DataBaseHelper.COLUMN_USER_NAME, user.getName());
//        values.put(DataBaseHelper.COLUMN_USER_EMAIL, user.getEmail());
//        values.put(DataBaseHelper.COLUMN_USER_PASSWORD, user.getPassword());
//        // updating row
//        long reslut = 0;
//        try {
//            reslut = db.update(DataBaseHelper.TABLE_USER, values, DataBaseHelper.COLUMN_USER_ID + " = ?",
//                    new String[]{String.valueOf(user.getId())});
//            db.close();
//        } catch (Exception e) {
//        }
//        return reslut;
//    }
//
//
//}
