package com.example.myempty2application;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.myempty2application.bean.UserInfo;
import com.example.myempty2application.resolver.DemoContentResolver;

import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private Spinner spinnerCampus;
    private EditText editTextUserName, editTextPasswoord;
    private RadioButton radioButtonManager, radioButtonStaff, radioButtonAdmin;
    private CheckBox checkBoxRemember;
    private String role, campus;
    private DemoContentResolver demoContentResolver;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        spinnerCampus = findViewById(R.id.spinner);
        radioButtonAdmin = findViewById(R.id.radioButtonAdmin);
        radioButtonAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Admin is selected", Toast.LENGTH_LONG).show();
                role = "Admin";
            }
        });
        radioButtonStaff = findViewById(R.id.radioButtonStaff);
        radioButtonStaff.setOnClickListener(v -> {
            Toast.makeText(getApplicationContext(), "Staff is selected", Toast.LENGTH_LONG).show();
            role = "Staff";
        });
        radioButtonManager = findViewById(R.id.radioButtonManager);
        radioButtonManager.setOnClickListener(v -> {
            Toast.makeText(getApplicationContext(), "Manager is selected", Toast.LENGTH_LONG).show();
            role = "Manager";
        });
        editTextUserName = findViewById(R.id.editTextUserName);
        editTextPasswoord = findViewById(R.id.editTextPassword);
        checkBoxRemember = findViewById(R.id.checkBoxRememberPassword);

        //Set listener for spinner
        spinnerCampus.setOnItemSelectedListener(this);
        //Create adapter for Spinner
        ArrayAdapter<CharSequence> arrayAdapter = ArrayAdapter.createFromResource(this, R.array.campus_array,
                android.R.layout.simple_spinner_item);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCampus.setAdapter(arrayAdapter);

        // Get Share Preferences
        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences(MY_SHARED_PREF,
                Context.MODE_PRIVATE);
        if (sharedPreferences.getBoolean(LOGGED, false) == true) {
            Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
            String userName = sharedPreferences.getString(USER_NAME, null);
            role  = sharedPreferences.getString(USER_PW, null);
//            intent.putExtra("UserName", userName);
//            intent.putExtra("Role", role);
//            startActivity(intent);
        }
        demoContentResolver = new DemoContentResolver(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
//        Toast.makeText(getApplicationContext(), "Selected Item: " + adapterView.getSelectedItem().toString(),
//                Toast.LENGTH_SHORT).show();
        campus = adapterView.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
    @Override
    protected void onStart() {
        super.onStart();
        // More codes before Activity started
    }
    @Override
    protected void onResume() {
        super.onResume();
    }
    @Override
    protected void onPause() {
        super.onPause();
    }

    /**
     *
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    /**
     * Handle action when user click on button Login
     * @param view
     */
    private final static String USER_NAME = "UID";
    private final static String USER_PW = "PW";
    private final static String ROLE = "Role";
    private final static String LOGGED = "LOGGED";
    private final static String MY_SHARED_PREF = "MySharedPref";
    public void onLogin(View view) {
        if (editTextUserName.getText().toString().length() > 0) {
//            SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences(MY_SHARED_PREF,
//                    Context.MODE_PRIVATE);
//            SharedPreferences.Editor editor = sharedPreferences.edit();
//            editor.putBoolean(LOGGED, true);
//            editor.putString(USER_NAME, editTextUserName.getText().toString());
//            editor.putString(USER_PW, editTextPasswoord.getText().toString());
//            editor.putString(ROLE, role);
//            editor.commit();
            Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
            intent.putExtra("UserName", editTextUserName.getText().toString());
            intent.putExtra("Role", role);
            startActivity(intent);
//            List<UserInfo> userInfoList = demoContentResolver.getAllUserInfo();
//            Toast.makeText(this, "Number of Users is " + (userInfoList != null ? userInfoList.size() : 0), Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "User name and Password is required", Toast.LENGTH_LONG).show();
            editTextUserName.setError("User name is required");
        }

    }

    /**
     * Start UserRegistrationActivity when the user click on Register
     * @param view
     */
    public void registerUser(View view) {
        Intent intent = new Intent(this, UserRegistrationActivity.class);
        startActivity(intent);
    }
}