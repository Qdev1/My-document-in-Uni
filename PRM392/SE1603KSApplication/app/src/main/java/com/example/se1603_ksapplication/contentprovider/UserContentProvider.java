package com.example.se1603_ksapplication.contentprovider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.se1603_ksapplication.dao.DatabaseHelper;

public class UserContentProvider extends ContentProvider {
    private DatabaseHelper databaseHelper = null;
    @Override
    public boolean onCreate() {
        databaseHelper = new DatabaseHelper(getContext());
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection,
                        @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        Cursor cursor = null;
        switch (DemoContentContract.uriMatcher.match(uri)) {
            case DemoContentContract.ALL_USERS:
                cursor = databaseHelper.getReadableDatabase().query(DemoContentContract.USER_TABLE,
                        null, null, null, null, null, sortOrder);
                break;
            case DemoContentContract.ONE_USER:
                cursor = databaseHelper.getReadableDatabase().query(DemoContentContract.USER_TABLE,
                        projection, selection, selectionArgs, null, null, null);
                break;
        }
        return cursor;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        switch (DemoContentContract.uriMatcher.match(uri)) {
            case DemoContentContract.ALL_USERS:
                return DemoContentContract.MULTI_RECORD_MINE_TYPE;
            case DemoContentContract.ONE_USER:
                return DemoContentContract.ONE_RECORD_MINE_TYPE;
        }
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        long id = databaseHelper.getWritableDatabase()
                .insertOrThrow(DemoContentContract.USER_TABLE, null, values);
        return Uri.parse(DemoContentContract.CONTENT_URI + "/" + id);
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
