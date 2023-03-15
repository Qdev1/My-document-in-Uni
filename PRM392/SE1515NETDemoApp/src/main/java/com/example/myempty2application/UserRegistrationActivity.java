package com.example.myempty2application;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myempty2application.dao.DatabaseHelper;

public class UserRegistrationActivity extends AppCompatActivity {
    private DatabaseHelper databaseHelper;
    private EditText editTextFullName, editTextPassword, editTextPhone, editTextAccount;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_registration);
        editTextFullName = findViewById(R.id.edt_full_name);
        editTextAccount = findViewById(R.id.edt_account);
        editTextPhone = findViewById(R.id.edt_phone);
        editTextPassword = findViewById(R.id.edt_password);
        databaseHelper = new DatabaseHelper(this);
    }
//    private final static String DATABASE = "SampleDB";
//    private final static String USER_TABLE = "user_table";
//    private final static String ACCOUNT_COLUMN = "account";
//    private final static String FULL_NAME_COLUMN = "full_name";
//    private final static String PASSWORD_COLUMN = "password";
//    private final static String PHONE_COLUMN = "phone";
//    private final static int VERSION = 1;
    public void save(View view) {
//        String createTableSQL = "CREATE TABLE IF NOT EXISTS " + USER_TABLE + "(" + ACCOUNT_COLUMN + " TEXT PRIMARY KEY, "
//                + FULL_NAME_COLUMN + " TEXT NOT NULL, "
//                + PHONE_COLUMN + " TEXT, "
//                + PASSWORD_COLUMN + " TEXT)";
//        SQLiteDatabase sqLiteDatabase = openOrCreateDatabase(DATABASE, MODE_PRIVATE, null);
//        sqLiteDatabase.execSQL(createTableSQL);
        String account  = editTextAccount.getText().toString().trim();
        String fullName = editTextFullName.getText().toString().trim();
        String phone = editTextPhone.getText().toString().trim();
        String password = editTextPassword.getText().toString();
        boolean isValid = true;
        if (account.length() <= 0) {
            editTextAccount.setError("Account is required.");
            isValid = false;
        }
        if (fullName.length() <= 0) {
            editTextFullName.setError("Full name is required.");
            isValid = false;
        }
        if (password.length() <= 0) {
            editTextPassword.setError("Password is required");
            isValid = false;
        }

        if (isValid) {
//            ContentValues contentValues = new ContentValues();
//            contentValues.put(FULL_NAME_COLUMN, fullName);
//            contentValues.put(ACCOUNT_COLUMN, account);
//            contentValues.put(PHONE_COLUMN, phone);
//            contentValues.put(PASSWORD_COLUMN, password);
//            sqLiteDatabase.insertOrThrow(USER_TABLE, null, contentValues);
            databaseHelper.insertUser(fullName, account, phone, password);
            Toast.makeText(this, "Registered user successfully", Toast.LENGTH_LONG).show();
        }
    }
}