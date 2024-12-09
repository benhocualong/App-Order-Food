package com.prm392.group1.apporderfood.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;

import com.prm392.group1.apporderfood.model.CartItem;
import com.prm392.group1.apporderfood.provider.CartItemProvider;

import java.util.ArrayList;
import java.util.List;

public class CartItemDatabase {

    public static final String TB_NAME = "cart_items";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_PRODUCT_NAME = "product_name";
    public static final String COLUMN_PRODUCT_IMAGE = "product_image";
    public static final String COLUMN_PRODUCT_QUANTITY = "quantity";
    public static final String COLUMN_ORDER_PRICE = "order_price";
    public static final String COLUMN_PRODUCT_PRICE = "product_price";
    public static final String COLUMN_CART_ID = "cart_id";


    private Context context;

    public CartItemDatabase(Context context) {
        this.context = context;
    }

    public List<CartItem> getAllCartItems() {
        Uri uri = Uri.parse(CartItemProvider.CONTENT_URI.toString());
        Cursor cursor = context.getContentResolver().query(uri, null, null, null, null);
        List<CartItem> items = new ArrayList<>();
        if (cursor != null && cursor.moveToFirst()) {
            do {
                int idIndex = cursor.getColumnIndex(COLUMN_ID);
                int productImageIndex = cursor.getColumnIndex(COLUMN_PRODUCT_NAME);
                int productNameIndex = cursor.getColumnIndex(COLUMN_PRODUCT_IMAGE);
                int quantityIndex = cursor.getColumnIndex(COLUMN_PRODUCT_QUANTITY);
                int orderPriceIndex = cursor.getColumnIndex(COLUMN_ORDER_PRICE);
                int productPriceIndex = cursor.getColumnIndex(COLUMN_PRODUCT_PRICE);
                int cartIdIndex = cursor.getColumnIndex(COLUMN_CART_ID);
                int id = cursor.getInt(idIndex);
                String productImage = cursor.getString(productImageIndex);
                String productName = cursor.getString(productNameIndex);
                int quantity = cursor.getInt(quantityIndex);
                int orderPrice = cursor.getInt(orderPriceIndex);
                int productPrice = cursor.getInt(productPriceIndex);
                int cartId = cursor.getInt(cartIdIndex);
                CartItem item = new CartItem(id, productName, productImage, quantity, orderPrice, productPrice, cartId);
                items.add(item);
            } while (cursor.moveToNext());
        }
        if (cursor != null) {
            cursor.close();
        }
        return items;
    }

    public int updateCartItem(int id, int newQuantity) {
        ContentValues values = new ContentValues();
        values.put("quantity", newQuantity);
        Uri uri = Uri.parse(CartItemProvider.CONTENT_URI.toString() + "/" + id);
        return context.getContentResolver().update(uri, values, null, null);
    }
}
