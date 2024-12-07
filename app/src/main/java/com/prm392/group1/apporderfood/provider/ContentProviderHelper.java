package com.prm392.group1.apporderfood.provider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;

import com.prm392.group1.apporderfood.helper.DatabaseHelper;

public class ContentProviderHelper extends ContentProvider {
    private static final String AUTHORITY = "com.prm392.group1.apporderfood.provider";
    private static final UriMatcher uriMatcher;
    private DatabaseHelper databaseHelper;

    static {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
    }

    @Override
    public boolean onCreate() {
        Context context = getContext();
        databaseHelper = new DatabaseHelper(context.getApplicationContext());
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        // Thực hiện truy vấn
        return null;
    }

    @Override
    public String getType(Uri uri) {
        // Trả về kiểu MIME của URI
        return null;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        // Thêm mới dữ liệu
        return null;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        // Xóa dữ liệu
        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        // Cập nhật dữ liệu
        return 0;
    }
}
