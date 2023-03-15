package com.example.test01;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private EditText editUsername, editPassword;
    private Button btLogin;
    private RadioButton radioManger, radioStaff;
    private CheckBox checkBoxRemember;
    private boolean remember;
    private String role;
    private String campus;
    private Spinner spinerCampus;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btLogin = findViewById(R.id.LoginButton);
        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             Toast.makeText(MainActivity.this,"login button is clicking, remember password + ", Toast.LENGTH_LONG).show();

            }
        });
        editUsername = findViewById(R.id.editUserName);
        editPassword = findViewById(R.id.editPass);
        radioManger = findViewById(R.id.radioButton2);
        radioStaff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                role = "mamager";
            }
        });
        radioStaff= findViewById(R.id.radioButton3);
        radioStaff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                role = "staff";
            }
        });
        checkBoxRemember = findViewById(R.id.checkBox);
        checkBoxRemember.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                remember= ((CheckBox) view).isChecked();
            }
        });
        spinerCampus = findViewById(R.id.spinerCampus);
        ArrayAdapter<CharSequence> arrayAdapter = ArrayAdapter.createFromResource(this,R.array.campus, android.R.layout.simple_spinner_item);
        spinerCampus.setAdapter(arrayAdapter);
        spinerCampus.setOnClickListener(this);

    }
    public void onLogin(View view){
        Toast.makeText(this,"login button is clicking", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        campus = parent.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}