package com.prm392.group1.apporderfood.provider;


import android.content.ContentProvider;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;

import androidx.annotation.NonNull;

import com.prm392.group1.apporderfood.database.CartDatabase;
import com.prm392.group1.apporderfood.database.CartItemDatabase;
import com.prm392.group1.apporderfood.helper.DatabaseHelper;

import java.util.Objects;

public class CartItemProvider extends ContentProvider {

    private static final UriMatcher sUriMatcher = buildUriMatcher();

    private static final String AUTHORITY = "com.prm392.group1.apporderfood.provider.CartItemProvider";
    private static final String BASE_PATH = "cart_items";
    public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/" + BASE_PATH);
    public static final int CARTS = 100;
    public static final int CART_ID = 101;
    private SQLiteDatabase db;
    DatabaseHelper dbHelper;

    @Override
    public boolean onCreate() {
        Context context = getContext();
        dbHelper = new DatabaseHelper(context);
        db = dbHelper.getWritableDatabase();
        return true;
    }

    private static UriMatcher buildUriMatcher() {
        final UriMatcher matcher = new UriMatcher(UriMatcher.NO_MATCH);
        final String authority = AUTHORITY;
        matcher.addURI(authority, BASE_PATH, CARTS);
        matcher.addURI(authority, BASE_PATH + "/#", CART_ID);

        return matcher;
    }

    @Override
    public String getType(@NonNull Uri uri) {
        switch (sUriMatcher.match(uri)) {
            case CARTS:
                return ContentResolver.CURSOR_DIR_BASE_TYPE;
            case CART_ID:
                return ContentResolver.CURSOR_ITEM_BASE_TYPE;
            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }

    }

    @Override
    public Cursor query(@NonNull Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        Cursor ret;
        switch (sUriMatcher.match(uri)) {
            case CARTS: {
                ret = db.query(
                        CartItemDatabase.TB_NAME,
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        sortOrder
                );
            }
            break;
            case CART_ID: {
                long cartId = Long.parseLong(uri.getPathSegments().get(1));
                ret = db.query(
                        CartItemDatabase.TB_NAME,
                        projection,
                        CartItemDatabase.COLUMN_CART_ID + " = ?",
                        new String[]{String.valueOf(cartId)},
                        null,
                        null,
                        sortOrder
                );
            }
            break;
            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }

        ret.setNotificationUri(getContext().getContentResolver(), uri);
        return ret;

    }

    @Override
    public Uri insert(@NonNull Uri uri, ContentValues values) {
        long rowID = db.insert(CartItemDatabase.TB_NAME, "", values);
        if (rowID > 0) {
            Objects.requireNonNull(getContext()).getContentResolver().notifyChange(uri, null);
            return uri;
        }
        throw new SQLiteException("Failed to add a record into " + uri);
    }

    @Override
    public int update(@NonNull Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        int count;

        switch (sUriMatcher.match(uri)) {
            case CARTS:
                count = db.update(CartItemDatabase.TB_NAME, values, selection, selectionArgs);
                break;
            case CART_ID:
                String cartId = uri.getPathSegments().get(1);
                count = db.update(CartItemDatabase.TB_NAME, values,
                        CartItemDatabase.COLUMN_ID + " = ?",
                        new String[]{cartId});
                break;
            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }

        getContext().getContentResolver().notifyChange(uri, null);
        return count;
    }

    @Override
    public int delete(@NonNull Uri uri, String selection, String[] selectionArgs) {
        int count = db.delete(CartItemDatabase.TB_NAME, selection, selectionArgs);
        Objects.requireNonNull(getContext()).getContentResolver().notifyChange(uri, null);
        return count;
    }
}
