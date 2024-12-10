package com.prm392.group1.apporderfood.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;

import com.prm392.group1.apporderfood.model.Cart;
import com.prm392.group1.apporderfood.model.CartItem;
import com.prm392.group1.apporderfood.provider.CartItemProvider;
import com.prm392.group1.apporderfood.provider.CartProvider;

import java.util.ArrayList;
import java.util.List;

public class CartDatabase {

    public static final String TB_NAME = "cart";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_TOTAL_PRICE = "total_price";
    public static final String COLUMN_USER_NAME = "username";
    public static final String COLUMN_TOTAL_SHIP = "total_ship";
    public static final String COLUMN_TOTAL_DISCOUNT = "total_discount";
    public static final String COLUMN_TOTAL_AMOUNT = "total_amount";



    private Context context;

    public CartDatabase(Context context) {
        this.context = context;
    }

    public Cart getCart() {
        Uri uri = Uri.parse(CartProvider.CONTENT_URI.toString());
        Cursor cursor = context.getContentResolver().query(uri, null, null, null, null);
        Cart cart = null;
        if (cursor != null && cursor.moveToFirst()) {
                int idIndex = cursor.getColumnIndex(COLUMN_ID);
                int totalPriceIndex = cursor.getColumnIndex(COLUMN_TOTAL_PRICE);
                int usernameIndex = cursor.getColumnIndex(COLUMN_USER_NAME);

                int totalShipIndex = cursor.getColumnIndex(COLUMN_TOTAL_SHIP);
                int totalDiscountIndex = cursor.getColumnIndex(COLUMN_TOTAL_DISCOUNT);
                int totalAmountIndex = cursor.getColumnIndex(COLUMN_TOTAL_AMOUNT);

                int id = cursor.getInt(idIndex);
                double totalPrice = cursor.getDouble(totalPriceIndex);
                String username = cursor.getString(usernameIndex);
                double totalShip = cursor.getDouble(totalShipIndex);
                double totalDiscount = cursor.getDouble(totalDiscountIndex);
                double totalAmount = cursor.getDouble(totalAmountIndex);

                cart = new Cart(id, totalPrice, username, totalShip, totalDiscount, totalAmount, new ArrayList<>());

        }
        if (cursor != null) {
            cursor.close();
        }
        return cart;
    }

    public void updateCart(Cart cart) {
    }
}
