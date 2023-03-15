package com.example.myempty2application.provider;

import android.content.ContentResolver;
import android.content.UriMatcher;
import android.net.Uri;

public class DemoContract {
    public final static int ALL_USER = 2;
    public final static int ONE_USER = 1;
    public final static String USER_TABLE = "user_table";
    public final static String ACCOUNT_COLUMN = "account";
    public final static String FULL_NAME_COLUMN = "full_name";
    public final static String PASSWORD_COLUMN = "password";
    public final static String PHONE_COLUMN = "phone";
    public final static String AUTHORITY = "com.example.myempty2application.provider.DemoContentProvider";
    public final static String CONTENT_PATH = USER_TABLE;
    public final static Uri uri = Uri.parse("content://" + AUTHORITY + "/"  + CONTENT_PATH);
    public final static String CONTENT_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE + "/mt_user";
    public final static String CONTENT_ITEM_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE + "/mt_user";
    public static UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
    static {
        uriMatcher.addURI(AUTHORITY, CONTENT_PATH, ALL_USER);
        uriMatcher.addURI(AUTHORITY, CONTENT_PATH + "/*", ONE_USER);
    }
}
