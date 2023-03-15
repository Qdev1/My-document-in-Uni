package com.example.pe_prm;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {

    public static final String TABLE_NAME = " peprm ";
    public static final String NUMBER_COLUMN = "number";
    public static final String CUSTOMERNAME_COLUMN = "customer_name";
    public static final String DATE_COLUMN = "date";
    public static final String LINECOUNT_COLUMN = "line_count";

    public DBHelper(Context context) {
        super(context, "PEPRM" , null, 1);
    }

    String sql = "CREATE TABLE " + TABLE_NAME + "("
            + NUMBER_COLUMN + " TEXT PRIMARY KEY, " +
            DATE_COLUMN + " TEXT NOT NULL, "
            + LINECOUNT_COLUMN + " INTEGER NOT NULL , "
            + CUSTOMERNAME_COLUMN + " TEXT NOT NULL )";
    @Override
    public void onCreate(SQLiteDatabase db){

        db.execSQL(sql);
    }
    @Override
    public void onUpgrade(SQLiteDatabase DB, int i, int ii) {
        DB.execSQL("drop table if exists " +TABLE_NAME);
        onCreate(DB);
    }

    public boolean insertOrder(Order order) {
        SQLiteDatabase DB = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(NUMBER_COLUMN, order.getNumber());
        contentValues.put(DATE_COLUMN, order.getDate());
        contentValues.put(LINECOUNT_COLUMN,order.getLine_count());
        contentValues.put(CUSTOMERNAME_COLUMN, order.getCustomer_name());

        long result = DB.insert(TABLE_NAME, null, contentValues);
        DB.close();
        if (result==-1) {
            return false;
        }
        return true;
    }

    public void deleteOrder(Order order) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, NUMBER_COLUMN + " = ?",
                new String[]{order.getNumber()});
        db.close();
    }


    public boolean checkExistedOrder(String number) {
        SQLiteDatabase db = this.getReadableDatabase();

        String query = "select * from " + TABLE_NAME + " where " + NUMBER_COLUMN + " = " + number;
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.getCount() <= 0) {
            cursor.close();
            return false;
        }
        cursor.close();
        return true;
    }

    public Order getOrder(String number) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_NAME, new String[]{NUMBER_COLUMN,
                        DATE_COLUMN, LINECOUNT_COLUMN, CUSTOMERNAME_COLUMN}, NUMBER_COLUMN + "= ?",
                new String[]{number}, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        String aNum = cursor.getString(0);
        String string = cursor.getString(1);
        int string1 = cursor.getInt(2);
        String string2 = cursor.getString(3);

        Order order = new Order(cursor.getString(0),
                cursor.getString(1),Integer.parseInt(cursor.getString(2)),  cursor.getString(3) );
        return order;
    }

    public List<Order> getAllOrders() {
        List<Order> OrderList = new ArrayList<>();

        String selectAll = "SELECT  * FROM " +TABLE_NAME;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectAll, null);

        // looping and adding to list
        if (cursor.moveToFirst()) {
            do {
                Order o = new Order();
                o.setNumber(cursor.getString(0));
                o.setDate(cursor.getString(1));
                o.setLine_count(Integer.parseInt(cursor.getString(2)));
                o.setCustomer_name(cursor.getString(3));
                // Adding to list
                OrderList.add(o);
            } while (cursor.moveToNext());
        }

        // return list
        return OrderList;
    }

    public List<Order> searchOrder(ArrayList<String> arrSearch) {
        List<Order> OrderList = new ArrayList<>();

        String selectAll = "select * from " +TABLE_NAME+ "WHERE 1=1 ";

        selectAll += " AND " + NUMBER_COLUMN + " LIKE '%" + arrSearch.get(0) + "%'";
        selectAll += " AND " + DATE_COLUMN + " LIKE '%" + arrSearch.get(1) + "%'";
        selectAll += " AND " + LINECOUNT_COLUMN + " LIKE '%" + arrSearch.get(2) + "%'";
        selectAll += " AND " + CUSTOMERNAME_COLUMN + " LIKE '%" + arrSearch.get(3) + "%'";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectAll, null);

        // looping and adding to list
        if (cursor.moveToFirst()) {
            do {
                Order o = new Order();
                o.setNumber(cursor.getString(0));
                o.setDate(cursor.getString(1));
                o.setLine_count(Integer.parseInt(cursor.getString(2)));
                o.setCustomer_name(cursor.getString(3));
                // Adding to list
                OrderList.add(o);
            } while (cursor.moveToNext());
        }

        // return list
        return OrderList;
    }

    public int updateOrder(Order order) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(DATE_COLUMN, order.getDate());
        values.put(LINECOUNT_COLUMN, order.getLine_count());
        values.put(CUSTOMERNAME_COLUMN, order.getCustomer_name());

        return db.update(TABLE_NAME, values, NUMBER_COLUMN + " = ?",
                new String[]{order.getNumber()});
    }


}
