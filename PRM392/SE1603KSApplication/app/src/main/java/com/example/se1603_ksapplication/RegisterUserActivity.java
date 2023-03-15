package com.example.se1603_ksapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.se1603_ksapplication.bean.UserInfo;
import com.example.se1603_ksapplication.dao.DatabaseHelper;

public class RegisterUserActivity extends AppCompatActivity {

    private DatabaseHelper databaseHelper = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);
        databaseHelper = new DatabaseHelper(getApplicationContext());
        Button buttonRegister = findViewById(R.id.bt_register);
        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText editTextUserName = findViewById(R.id.edt_reg_user_name);
                EditText editTextPassword = findViewById(R.id.edt_reg_password);
                UserInfo userInfo = new UserInfo();
                userInfo.setUserName(editTextUserName.getText().toString().trim());
                userInfo.setPassword(editTextPassword.getText().toString().trim());
                userInfo.setCampus("Ho Chi minh");
                userInfo.setRole("Manager");
                if (userInfo.getUserName().length() > 0
                        && userInfo.getPassword().length() > 0) {
                    databaseHelper.insertUser(userInfo);
                    Toast.makeText(RegisterUserActivity.this, "Registered successfully", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(RegisterUserActivity.this, "User name and Password are required", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}