package com.example.se1603_ksapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.se1603_ksapplication.common.IntentKeys;

public class EditProfileActivity extends AppCompatActivity {
    private EditText editTextFirstName, editTextLastName, editTextAddress;
    private String lastName,firstName, address;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        editTextFirstName = findViewById(R.id.edtFirstName);
        editTextLastName = findViewById(R.id.edtLastName);
        editTextAddress = findViewById(R.id.edtAddress);
        // Get data from ProfileDetailActivity
        Intent intent = getIntent();
        lastName = intent.getStringExtra(IntentKeys.LAST_NAME);
        firstName = intent.getStringExtra(IntentKeys.FIRST_NAME);
        //Set data to fields on the EditProfileActivity
        editTextFirstName.setText(firstName);
        editTextLastName.setText(lastName);

        //
        Button btSave = findViewById(R.id.btSave);
        btSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent();
                firstName = editTextFirstName.getText().toString().trim();
                lastName = editTextLastName.getText().toString().trim();
                address = editTextAddress.getText().toString().trim();
                if (firstName.length() <= 0 || lastName.length() <= 0 || address.length() <= 0) {
                    Toast.makeText(EditProfileActivity.this, "First Name, Last Name, and Address are required.", Toast.LENGTH_SHORT).show();
                    return;
                }
                //Build intent to push data back to ProfileDetailActivity
                intent1.putExtra(IntentKeys.FIRST_NAME, firstName);
                intent1.putExtra(IntentKeys.LAST_NAME, lastName);
                intent1.putExtra(IntentKeys.ADDRESS, address);
                //Set result to push data back to ProfileDetailActivity
                setResult(2, intent1);
                finish(); // Complete activity
            }
        });
    }
}