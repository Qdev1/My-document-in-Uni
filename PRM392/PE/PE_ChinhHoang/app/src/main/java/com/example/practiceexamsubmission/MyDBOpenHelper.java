package com.example.practiceexamsubmission;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class MyDBOpenHelper extends SQLiteOpenHelper {
    public static final String TABLE_NAME = "customer";
    public static final String CUSTOMERID_COLUMN = "customerid";
    public static final String CUSTOMERNAME_COLUMN = "name";
    public static final String ORDERQUANTITY_COLUMN = "order_quantity";
    public static final String ADDRESS_COLUMN = "address";

    public MyDBOpenHelper(Context context) {
        super(context, "customer", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {       //create db
         String query = String.format("CREATE TABLE %s ( %s TEXT PRIMARY KEY, %s TEXT NOT NULL, %s  INTEGER NOT NULL , %s TEXT NOT NULL )",
                TABLE_NAME, CUSTOMERID_COLUMN, CUSTOMERNAME_COLUMN, ORDERQUANTITY_COLUMN, ADDRESS_COLUMN);
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {   //upgrade db
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean insertCustomer(Customer customer) {
        SQLiteDatabase DB = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(CUSTOMERID_COLUMN, customer.getCustomerID());
        contentValues.put(CUSTOMERNAME_COLUMN, customer.getName());
        contentValues.put(ORDERQUANTITY_COLUMN,customer.getOrder_quantity());
        contentValues.put(ADDRESS_COLUMN, customer.getAddress());

        long result = DB.insert(TABLE_NAME, null, contentValues);
        DB.close();
        if (result == -1) {
            return false;
        }
        return true;
    }

    public void deleteCustomer(Customer customer) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, CUSTOMERID_COLUMN + " = ?", new String[]{customer.getCustomerID()});
        db.close();
    }

    public boolean checkExistedCustomer(String id) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "select * from " + TABLE_NAME + " where " + CUSTOMERID_COLUMN + " = '" + id + "'";
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.getCount() <= 0) {
            cursor.close();
            return false;
        }
        cursor.close();
        return true;
    }

    public Customer getCustomer(String id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME, new String[]{CUSTOMERID_COLUMN, CUSTOMERNAME_COLUMN,
                        ORDERQUANTITY_COLUMN, ADDRESS_COLUMN}, CUSTOMERID_COLUMN + "= ?",
                new String[]{id}, null, null, null, null);
        if (cursor != null) cursor.moveToFirst();
        Customer customer = new Customer(cursor.getString(0),
                cursor.getString(1),Integer.parseInt(cursor.getString(2)),  cursor.getString(3));
        return customer;
    }

    public List<Customer> getAllCustomer() {
        List<Customer> CustomerList = new ArrayList<>();
        String selectAll = "SELECT * FROM " +TABLE_NAME;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectAll, null);

        if (cursor.moveToFirst()) {
            do {
                Customer c = new Customer();
                c.setCustomerID(cursor.getString(0));
                c.setName(cursor.getString(1));
                c.setOrder_quantity(Integer.parseInt(cursor.getString(2)));
                c.setAddress(cursor.getString(3));
                CustomerList.add(c);    // them vao list
            } while (cursor.moveToNext());
        }
        return CustomerList;
    }

    public List<Customer> searchCustomer(ArrayList<String> arrSearch) {
        List<Customer> CustomerList = new ArrayList<>();
        String selectAll = "select * from " + TABLE_NAME + " WHERE 1=1 ";

        selectAll += " AND " + CUSTOMERID_COLUMN + " LIKE '%" + arrSearch.get(0) + "%'";
        selectAll += " AND " + CUSTOMERNAME_COLUMN + " LIKE '%" + arrSearch.get(1) + "%'";
        selectAll += " AND " + ORDERQUANTITY_COLUMN + " LIKE '%" + arrSearch.get(2) + "%'";
        selectAll += " AND " + ADDRESS_COLUMN + " LIKE '%" + arrSearch.get(3) + "%'";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectAll, null);
        if (cursor.moveToFirst()) {
            do {
                Customer c = new Customer();
                c.setCustomerID(cursor.getString(0));
                c.setName(cursor.getString(1));
                c.setOrder_quantity(Integer.parseInt(cursor.getString(2)));
                c.setAddress(cursor.getString(3));
                CustomerList.add(c);
            } while (cursor.moveToNext());
        }
        return CustomerList;
    }

    public int updateCustomer(Customer customer) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(CUSTOMERNAME_COLUMN, customer.getName());
        values.put(ORDERQUANTITY_COLUMN, customer.getOrder_quantity());
        values.put(ADDRESS_COLUMN, customer.getAddress());
        return db.update(TABLE_NAME, values, CUSTOMERID_COLUMN + " = ?",
                new String[]{customer.getCustomerID()});
    }
}
