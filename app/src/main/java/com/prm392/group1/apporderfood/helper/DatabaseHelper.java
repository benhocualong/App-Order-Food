package com.prm392.group1.apporderfood.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.prm392.group1.apporderfood.database.CartDatabase;
import com.prm392.group1.apporderfood.database.CartItemDatabase;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "app_order_food.db";
    private static final int DATABASE_VERSION = 1;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Tạo bảng cart
        String CREATE_TABLE_CART = "CREATE TABLE IF NOT EXISTS " + CartDatabase.TB_NAME +
                "(" +
                CartDatabase.COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                CartDatabase.COLUMN_TOTAL_PRICE + " REAL," +
                CartDatabase.COLUMN_USER_NAME + " TEXT," +
                CartDatabase.COLUMN_TOTAL_SHIP + " REAL," +
                CartDatabase.COLUMN_TOTAL_DISCOUNT + " REAL," +
                CartDatabase.COLUMN_TOTAL_AMOUNT + " INTEGER" +
                ")";
        // Tạo bảng cart_item
        String CREATE_TABLE_CART_ITEM = "CREATE TABLE IF NOT EXISTS " + CartItemDatabase.TB_NAME +
                "(" +
                CartItemDatabase.COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                CartItemDatabase.COLUMN_PRODUCT_NAME + " TEXT," +
                CartItemDatabase.COLUMN_PRODUCT_IMAGE + " TEXT," +
                CartItemDatabase.COLUMN_PRODUCT_QUANTITY + " INTEGER," +
                CartItemDatabase.COLUMN_ORDER_PRICE + " REAL," +
                CartItemDatabase.COLUMN_PRODUCT_PRICE + " REAL," +
                CartItemDatabase.COLUMN_CART_ID + " INTEGER," +
                "FOREIGN KEY (" + CartItemDatabase.COLUMN_CART_ID + ") REFERENCES " + CartDatabase.TB_NAME + "(" + CartDatabase.COLUMN_ID + ")" +
                ")";
        db.execSQL(CREATE_TABLE_CART);
        db.execSQL(CREATE_TABLE_CART_ITEM);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}

