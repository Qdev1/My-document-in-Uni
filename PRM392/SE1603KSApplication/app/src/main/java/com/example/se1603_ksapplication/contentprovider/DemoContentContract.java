package com.example.se1603_ksapplication.contentprovider;

import android.content.ContentResolver;
import android.content.UriMatcher;
import android.net.Uri;

public class DemoContentContract {
    public final static String USER_TABLE = "user_table";
    public final static String USER_NAME = "user_name";
    public final static String PASSWORD = "password";
    public final static String USER_ROLE = "role";
    public final static String CAMPUS = "campus";
    public final static String CONTENT_PATH = USER_TABLE;
    public final static String AUTHORITY = "com.example.se1603_ksapplication.contentprovider.UserContentProvider";
    public final static int ONE_USER = 1;
    public final static int ALL_USERS = 2;
    public final static Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/" + CONTENT_PATH);
    public static UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
    static {
        uriMatcher.addURI(AUTHORITY, CONTENT_PATH, ALL_USERS);
        uriMatcher.addURI(AUTHORITY, CONTENT_PATH + "/*", ONE_USER);
    }
    public final static String ONE_RECORD_MINE_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE + "/mt_user";
    public final static String MULTI_RECORD_MINE_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE + "/mt_user";
}
