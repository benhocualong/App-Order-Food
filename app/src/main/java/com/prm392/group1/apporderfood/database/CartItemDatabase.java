package com.prm392.group1.apporderfood.database;

import android.content.ContentUris;
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

    public List<CartItem> getAllCartItemsByCartId(int cartId) {
        Uri cartUri = ContentUris.withAppendedId(CartItemProvider.CONTENT_URI, cartId);
        Cursor cursor = context.getContentResolver().query(cartUri, null, null, null, null);
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
                double orderPrice = cursor.getDouble(orderPriceIndex);
                double productPrice = cursor.getDouble(productPriceIndex);
                int cartIdDb = cursor.getInt(cartIdIndex);
                CartItem item = new CartItem(id, productName, productImage, quantity, orderPrice, productPrice, cartIdDb);
                items.add(item);
            } while (cursor.moveToNext());
        }
        if (cursor != null) {
            cursor.close();
        }
        return items;
    }

    public int updateCartItem(CartItem cartItem) {
        ContentValues values = new ContentValues();
        values.put(CartItemDatabase.COLUMN_PRODUCT_QUANTITY, cartItem.getQuantity());
        values.put(CartItemDatabase.COLUMN_ORDER_PRICE, cartItem.getProductOrderPrice());
        Uri uri = Uri.parse(CartItemProvider.CONTENT_URI.toString() + "/" + cartItem.getId());
        return context.getContentResolver().update(uri, values, null, null);
    }
}
