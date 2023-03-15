package com.example.myempty2application.provider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.myempty2application.dao.DatabaseHelper;

public class DemoContentProvider extends ContentProvider {
    private DatabaseHelper databaseHelper;
    @Override
    public boolean onCreate() {
        databaseHelper = new DatabaseHelper(getContext());
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection,
                        @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        Cursor cursor = null;
        switch (DemoContract.uriMatcher.match(uri)) {
            case DemoContract.ALL_USER:
                cursor = databaseHelper.getAllUserInfo();
                break;
            case DemoContract.ONE_USER:
                cursor = databaseHelper.getOneUserInfo(uri.getLastPathSegment());
                break;
            default:

        }
        return cursor;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        switch (DemoContract.uriMatcher.match(uri)) {
            case DemoContract.ALL_USER:
                return DemoContract.CONTENT_TYPE;
            case DemoContract.ONE_USER:
                return DemoContract.CONTENT_ITEM_TYPE;
        }
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        // Check uri
        String account = databaseHelper.insertUser(values);
        return Uri.parse(DemoContract.uri + "/" + account);
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }
}
