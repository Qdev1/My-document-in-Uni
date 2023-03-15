package com.example.se1603_ksapplication.contentresolver;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;

import androidx.annotation.Nullable;

import com.example.se1603_ksapplication.bean.UserInfo;
import com.example.se1603_ksapplication.contentprovider.DemoContentContract;

import java.util.ArrayList;
import java.util.List;

public class UserContentResolver extends ContentResolver {
    private Context context;
    /**
     * Note: passing a {@code null} context here could lead to unexpected behavior in certain
     * ContentResolver APIs so it is highly recommended to pass a non-null context here.
     *
     * @param context
     */
    public UserContentResolver(@Nullable Context context) {
        super(context);
        this.context = context;
    }

    public UserInfo getUserInfo(String userName) {
        Uri uri = Uri.parse(DemoContentContract.CONTENT_URI + "/" + userName);
        Cursor cursor = context.getContentResolver().query(uri, null,
                DemoContentContract.USER_NAME + "=?", new String[]{userName}, null);
        UserInfo userInfo = null;
        if (cursor != null && cursor.moveToNext()) {
            userInfo = new UserInfo();
            int index = cursor.getColumnIndex(DemoContentContract.PASSWORD);
            userInfo.setUserName(userName);
            userInfo.setPassword(cursor.getString(index));
            index = cursor.getColumnIndex(DemoContentContract.CAMPUS);
            userInfo.setCampus(cursor.getString(index));
        }
        return userInfo;
    }
    public List<UserInfo> getAllUsers() {
        Cursor cursor = context.getContentResolver().query(DemoContentContract.CONTENT_URI, null,
                null, null, null);
        UserInfo userInfo = null;
        List<UserInfo> userInfoList = new ArrayList<>();
        while(cursor.moveToNext()) {
            userInfo = new UserInfo();
            int index = cursor.getColumnIndex(DemoContentContract.PASSWORD);
            userInfo.setPassword(cursor.getString(index));
            index = cursor.getColumnIndex(DemoContentContract.CAMPUS);
            userInfo.setCampus(cursor.getString(index));
            index = cursor.getColumnIndex(DemoContentContract.USER_NAME);
            userInfo.setUserName(cursor.getString(index));
            userInfoList.add(userInfo);
        }
        return userInfoList;
    }
    public void createUser(UserInfo userInfo) {
        ContentValues values = new ContentValues();
        values.put(DemoContentContract.USER_NAME, userInfo.getUserName());
        values.put(DemoContentContract.PASSWORD, userInfo.getPassword());
        values.put(DemoContentContract.CAMPUS, userInfo.getCampus());
        values.put(DemoContentContract.USER_ROLE, userInfo.getRole());
        insert(DemoContentContract.CONTENT_URI, values);
    }
}
