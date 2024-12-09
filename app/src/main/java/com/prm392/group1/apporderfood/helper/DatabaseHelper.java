package com.prm392.group1.apporderfood.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.prm392.group1.apporderfood.database.CartItemDatabase;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "app_order_food.db";
    private static final int DATABASE_VERSION = 1;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CART_ITEM_TABLE = "CREATE TABLE IF NOT EXISTS " + CartItemDatabase.TB_NAME + "("
                + CartItemDatabase.COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + CartItemDatabase.COLUMN_PRODUCT_IMAGE + " TEXT,"
                + CartItemDatabase.COLUMN_PRODUCT_NAME + " TEXT,"
                + CartItemDatabase.COLUMN_PRODUCT_QUANTITY + " INTEGER,"
                + CartItemDatabase.COLUMN_PRODUCT_PRICE + " REAL,"
                + CartItemDatabase.COLUMN_ORDER_PRICE + " REAL,"
                + CartItemDatabase.COLUMN_CART_ID + " INTEGER)";
        db.execSQL(CREATE_CART_ITEM_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}

