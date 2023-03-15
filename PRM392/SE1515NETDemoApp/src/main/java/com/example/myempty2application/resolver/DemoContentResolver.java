package com.example.myempty2application.resolver;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.myempty2application.bean.UserInfo;
import com.example.myempty2application.provider.DemoContract;

import java.util.ArrayList;
import java.util.List;

public class DemoContentResolver extends ContentResolver {
    private Context context;
    /**
     * Note: passing a {@code null} context here could lead to unexpected behavior in certain
     * ContentResolver APIs so it is highly recommended to pass a non-null context here.
     *
     * @param context
     */
    public DemoContentResolver(@Nullable Context context) {
        super(context);
        this.context = context;
    }

    public void createUser(UserInfo userInfo) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DemoContract.FULL_NAME_COLUMN, userInfo.getFullName());
        contentValues.put(DemoContract.ACCOUNT_COLUMN, userInfo.getAccount());
        contentValues.put(DemoContract.PHONE_COLUMN, userInfo.getPhone());
        contentValues.put(DemoContract.PASSWORD_COLUMN, userInfo.getPassword());
        Uri uri = context.getContentResolver().insert(DemoContract.uri, contentValues);
        UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(DemoContract.AUTHORITY, DemoContract.CONTENT_PATH + "/" + userInfo.getAccount(), DemoContract.ONE_USER);
        if (uriMatcher.match(uri) == DemoContract.ONE_USER) {
            Toast.makeText(context, "Created User successfully.", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Failed to created User ", Toast.LENGTH_SHORT).show();
        }
    }
    public List<UserInfo> getAllUserInfo() {
        List<UserInfo> userInfoList = new ArrayList<>();
        Cursor cursor = context.getContentResolver().query(DemoContract.uri, null,
                null, null, null);
        while (cursor.moveToNext()) {
            UserInfo userInfo = new UserInfo();
            int columnIndex = cursor.getColumnIndex(DemoContract.FULL_NAME_COLUMN);
            userInfo.setFullName(cursor.getString(columnIndex));
            //Get data from Account Column
            columnIndex = cursor.getColumnIndex(DemoContract.ACCOUNT_COLUMN);
            userInfo.setAccount(cursor.getString(columnIndex));
            //
            columnIndex = cursor.getColumnIndex(DemoContract.PHONE_COLUMN);
            userInfo.setPhone(cursor.getString(columnIndex));
            //
            columnIndex = cursor.getColumnIndex(DemoContract.PASSWORD_COLUMN);
            userInfo.setPassword(cursor.getString(columnIndex));
            userInfoList.add(userInfo);
        }
        return userInfoList;
    }
}
