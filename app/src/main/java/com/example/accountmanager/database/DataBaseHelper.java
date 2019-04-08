package com.example.accountmanager.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.accountmanager.model.Category;
import com.example.accountmanager.model.User;

import java.util.ArrayList;
import java.util.jar.Attributes;

public class DataBaseHelper extends SQLiteOpenHelper {
    Context context;
    private static final String DATABASE_NAME = "UserManager.db";
    private static final int DATABASE_VERSION = 1;
    //  table user
    public static final String TABLE_USER = "user";
    public static final String TAG1 = "user";
    // table category
    public static final String TABLE_CATEGORY = "category";
    public static final String TAG2 = "category";
    //create table account
    public static final String TABLE_ACCOUNT = "account";
    public static final String TAG3 = "account";

    // Columns user
    public static final String COLUMN_USER_ID = "user_id";
    public static final String COLUMN_USER_NAME = "user_name";
    public static final String COLUMN_USER_EMAIL = "user_email";
    public static final String COLUMN_USER_PASSWORD = "user_password";

    //  columns category
    public static final String COLUMN_CATEGORY_ID = "category_id";
    public static final String COLUMN_CATEGORY_NAME = "category_name";

    // columns account
    public static final String COLUMN_ACCOUNT_ID = "account_id";
    public static final String COLUMN_ACCOUNT_TITLE = "account_title";
    public static final String COLUMN_ACCOUNT_USER = "account_user";
    public static final String COLUMN_ACCOUNT_PASSWORD = "account_password";
    public static final String COLUMN_ACCOUNT_EMAIL = "account_email";
    public static final String COLUMN_ACCOUNT_WEB = "account_web";
    public static final String COLUMN_ACCOUNT_DESP = "account_desp";
    public static final String COLUMN_CATEGORY = "account_category";


    // create table user
    private String CREATE_USER_TABLE = "CREATE TABLE " + TABLE_USER + "("
            + COLUMN_USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_USER_NAME + " TEXT,"
            + COLUMN_USER_EMAIL + " TEXT," + COLUMN_USER_PASSWORD + " TEXT" + ")";

    // create table category
    private String CREATE_CATEGORY_TABLE = "CREATE TABLE " + TABLE_CATEGORY + "("
            + COLUMN_CATEGORY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_CATEGORY_NAME + " TEXT" + ")";

    // create table account
    private String CREATE_ACCOUNT_TABLE = "CREATE TABLE " + TABLE_ACCOUNT + "("
            + COLUMN_ACCOUNT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_ACCOUNT_TITLE + " TEXT," +
            COLUMN_ACCOUNT_USER + " TEXT," + COLUMN_ACCOUNT_PASSWORD + " TEXT," + COLUMN_ACCOUNT_EMAIL + " TEXT,"
            + COLUMN_ACCOUNT_WEB + " TEXT," + COLUMN_ACCOUNT_DESP + " TEXT," + COLUMN_CATEGORY + " TEXT" + ")";


    // drop table
    private String DROP_USER_TABLE = "DROP TABLE IF EXISTS " + TABLE_USER;

    // drop table category
    private String DROP_CATEGORY_TABLE = "DROP TABLE IF EXISTS " + TABLE_CATEGORY;

    // drop table account
    private String DROP_ACCOUNT_TABLE = "DROP TABLE IF EXISTS " + TABLE_CATEGORY;

    public DataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_USER_TABLE);
        db.execSQL(CREATE_CATEGORY_TABLE);
        db.execSQL(CREATE_ACCOUNT_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //Drop User Table if exist
        db.execSQL(DROP_USER_TABLE);
        // drop table category
        db.execSQL(DROP_CATEGORY_TABLE);
        db.execSQL(DROP_ACCOUNT_TABLE);
        // Create tables again
        onCreate(db);

    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.setVersion(oldVersion);
    }

    // Add User
    public void addUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_USER_NAME, user.getName());
        cv.put(COLUMN_USER_EMAIL, user.getEmail());
        cv.put(COLUMN_USER_PASSWORD, user.getPassword());

        long result = db.insert(TABLE_USER, null, cv);
        if (result == -1) {
            Log.d(TAG1, "add failed");
        } else {
            Log.d(TAG1, "add succ");
        }
        db.close();
    }

    // checkUser
    public boolean checkEmail(String email) {
        String[] columns = {
                COLUMN_USER_ID
        };
        SQLiteDatabase db = this.getWritableDatabase();
        String selection = COLUMN_USER_EMAIL + " =? ";
        String[] selectionArgs = {email};
        Cursor cursor = db.query(TABLE_USER, columns, selection, selectionArgs, null, null, null);
        int count = cursor.getCount();
        cursor.close();
        db.close();
        if (count > 0) {
            return true;
        }
        return false;
    }

    public boolean checkUser(String email, String password) {
        String[] columns = {
                COLUMN_USER_ID
        };
        SQLiteDatabase db = this.getWritableDatabase();
        String selection = COLUMN_USER_EMAIL + " =? " + " AND " + COLUMN_USER_PASSWORD + " =? ";
        String[] selectionArgs = {email, password};
        Cursor cursor = db.query(TABLE_USER, columns, selection, selectionArgs, null, null, null);
        int count = cursor.getCount();
        cursor.close();
        db.close();
        if (count > 0) {
            return true;
        }
        return false;
    }

    // check user if exist
    public User queryUser(String email, String password) {
//        String[] columns = {DataBaseHelper.COLUMN_USER_ID};
        User user = null;
        SQLiteDatabase db = this.getReadableDatabase();
        String selection = COLUMN_USER_EMAIL + " = ? " + " AND " + COLUMN_USER_PASSWORD + " = ? ";
        Cursor cursor = db.query(TABLE_USER, new String[]{DataBaseHelper.COLUMN_USER_ID, DataBaseHelper.COLUMN_USER_NAME, DataBaseHelper.COLUMN_USER_EMAIL, DataBaseHelper.COLUMN_USER_PASSWORD}, selection, new String[]{email, password}, null, null, null, "1");
        if (cursor != null)
            cursor.moveToFirst();
        if (cursor != null && cursor.getCount() > 0) {
            user = new User(cursor.getString(1), cursor.getString(2), cursor.getString(3));
        }
        // return user
        return user;
    }

    //insert category
    public long insertCategory(Category category) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_CATEGORY_NAME, category.getCategoryname());
        long result = 0;
        try {
            result = db.insert(TABLE_CATEGORY, null, values);
            db.close();

        } catch (Exception e) {

        }
        return result;
    }

    // update Category
    public void updateCategory(Category category) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_CATEGORY_NAME, category.getCategoryname());
        db.update(TABLE_CATEGORY, values, COLUMN_CATEGORY_ID + " =? ", new String[]{String.valueOf(category.getId())});
        db.close();
    }

    // delete Category
    public void deleteCategory(Category category) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_CATEGORY, COLUMN_CATEGORY_ID + " =? ",
                new String[]{String.valueOf(category.getId())});
        db.close();
    }

//    public Cursor getAllCategory() {
//        SQLiteDatabase db = this.getReadableDatabase();
//        Cursor cursor= null;
//        try {
//            cursor = db.rawQuery("SELECT* FROM category ORDER BY category_name ASC;", null);
//
//        }
//        catch (Exception e) {
//        }
//        return cursor;
//    }
//    public Category getNameCategory(String name){
//        Category category= new Category();
//        SQLiteDatabase db= this.getReadableDatabase();
//        Cursor cursor= db.query(TABLE_CATEGORY,new String[]{COLUMN_CATEGORY_ID,COLUMN_CATEGORY_NAME},COLUMN_CATEGORY_ID +" =? ",new String[]{String.valueOf(category.getId())},null,null,null,null);
//        if (cursor!=null){
//            cursor.moveToFirst();
//        }
//        category=new Category(cursor.getString(1));
//        return category;
//
//    }
    public ArrayList<Category> getAllCategoryName(){
        ArrayList<Category> listNameCategory= new ArrayList<>();
        String query= "SELECT* FROM "+ TABLE_CATEGORY;
        SQLiteDatabase db= this.getWritableDatabase();
        Cursor cursor= db.rawQuery(query,null);
        if (cursor.moveToFirst()){
            do {
                Category category= new Category();
                //category.setId(Integer.parseInt(cursor.getString(0)));
                category.setCategoryname(cursor.getString(1));
                listNameCategory.add(category);
            }
            while (cursor.moveToNext());
        }
        return listNameCategory;
    }
}
