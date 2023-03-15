package com.example.codepe;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.codepe.DOB.DataHelper;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    LinearLayout list;
    Button btAdd, btUpdate, btDelete, btList;
    EditText txtId, txtName, txtDOB, txtCore;
    DataHelper helper;
    ArrayList<String> id, name, dob, core;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btAdd = findViewById(R.id.btAdd);
        btUpdate = findViewById(R.id.btUpdate);
        btDelete = findViewById(R.id.btDelete);
        btList = findViewById(R.id.btList);
        list = findViewById(R.id.list);
        txtId = findViewById(R.id.txtId);
        txtName = findViewById(R.id.txtName);
        txtDOB = findViewById(R.id.txtDOB);
        txtCore = findViewById(R.id.txtCore);
        btAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DataHelper db = new DataHelper(MainActivity.this);
                db.addStudent(
                        txtId.getText().toString().trim(),
                        txtName.getText().toString().trim(),
                        txtDOB.getText().toString().trim(),
                        txtCore.getText().toString().trim());
            }
        });
        helper = new DataHelper(MainActivity.this);
        id = new ArrayList<>();
        name = new ArrayList<>();
        dob = new ArrayList<>();
        core = new ArrayList<>();

        displayData();
    }

    void displayData(){
        Cursor cursor = helper.readData();
        if (cursor.getCount() == 0){
            Toast.makeText(this, "No data", Toast.LENGTH_SHORT).show();
        }else {
            while (cursor.moveToNext()){
                id.add(cursor.getString(0));
                name.add(cursor.getString(1));
                dob.add(cursor.getString(2));
                core.add(cursor.getString(3));



            }
        }

    }


}