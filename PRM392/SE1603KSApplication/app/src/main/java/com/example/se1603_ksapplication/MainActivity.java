package com.example.se1603_ksapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.se1603_ksapplication.bean.UserInfo;
import com.example.se1603_ksapplication.common.IntentKeys;
import com.example.se1603_ksapplication.contentresolver.UserContentResolver;
import com.example.se1603_ksapplication.dao.DatabaseHelper;
import com.example.se1603_ksapplication.service.MyDemoService;


public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private EditText edtUserName, edtPassword;
    private Button btLogin;
    private RadioButton radioManager, radioStaff;
    private CheckBox checkBoxRemember;
    private boolean remember;
    private String role, campus;
    private Spinner spinnerCampus;
    private DatabaseHelper databaseHelper = null;
    private UserContentResolver userContentResolver;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        databaseHelper = new DatabaseHelper(getApplicationContext());
        userContentResolver = new UserContentResolver(this);
        TextView textViewRegister = findViewById(R.id.tv_register);
        Button buttonStartService = findViewById(R.id.bt_start_service);
        buttonStartService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Start service
                Intent intent = new Intent(MainActivity.this, MyDemoService.class);
                startService(intent);
            }
        });
//        Button buttonStopService = findViewById(R.id.bt_stop_service);
//        buttonStopService.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                //Stop service
//                Intent intent = new Intent(MainActivity.this, MyDemoService.class);
//                stopService(intent);
//            }
//        });

        Button buttonDemoFragment = findViewById(R.id.bt_demo_fragment);
        buttonDemoFragment.setOnClickListener(v -> {
            //Start DemoFragmentActivity
            Intent intent = new Intent(MainActivity.this, DemoFragmentActivity.class);
            startActivity(intent);
        });

        Button buttonDemoMap = findViewById(R.id.bt_demo_map);
        buttonDemoMap.setOnClickListener(v -> {
            //Start DemoFragmentActivity
            Intent intent = new Intent(MainActivity.this, DemoMapActivity.class);
            startActivity(intent);
        });

        textViewRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, RegisterUserActivity.class);
                startActivity(intent);
            }
        });
        btLogin = findViewById(R.id.btLogin);

        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//               Toast.makeText(MainActivity.this,
//                       "Login button is clicked, remember password = " + remember + ", role =" + role, Toast.LENGTH_LONG).show();
                String userName = edtUserName.getText().toString().trim();
                String password = edtPassword.getText().toString().trim();
                if (userName.length() > 0 && password.length() > 0) {
                    SharedPreferences sharedPreferences = getSharedPreferences("UserData", Context.MODE_PRIVATE);
                    UserInfo userInfo = databaseHelper.select(userName);
                    //String storedUsername = sharedPreferences.getString(IntentKeys.USER_NAME, null);
                    //String storedPassword = sharedPreferences.getString(IntentKeys.PASSWORD, null);
                    // Get userInfo from ContentProvider
                    UserInfo userInfoFromProvider = userContentResolver.getUserInfo(userName);
                    Toast.makeText(MainActivity.this, "User from ContentProvider "
                            + ((userInfoFromProvider == null ? " null " : userInfoFromProvider.getUserName())),
                            Toast.LENGTH_LONG).show();
                    if (userInfo != null && password.equals(userInfo.getPassword())) {
                        sharedPreferences.edit()
                                .putBoolean("REMEMBER", checkBoxRemember.isChecked())
                                .commit();
                        Intent intent = new Intent(MainActivity.this, ProfileDetailActivity.class);
                        intent.putExtra(IntentKeys.USER_NAME, userName);
                        intent.putExtra(IntentKeys.PASSWORD, password);
                        startActivity(intent);
                    } else {
                        Toast.makeText(MainActivity.this, "Username or password is NOT correct ,"
                                + ((userInfoFromProvider == null) ? " null" : userInfoFromProvider.getUserName()
                                + ", "+ userInfoFromProvider.getPassword()), Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(MainActivity.this, "User name & password are empty", Toast.LENGTH_SHORT).show();
                }
            }
        });
        edtUserName = findViewById(R.id.edtUserName);
        edtPassword = findViewById(R.id.edtPassword);
        radioManager = findViewById(R.id.radioButtonManager);
        radioManager.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                role = "Manager";
            }
        });
        radioStaff = findViewById(R.id.radioButtonStaff);
        radioStaff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                role = "Staff";
            }
        });
        checkBoxRemember = findViewById(R.id.checkBoxRemember);
        checkBoxRemember.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                remember = ((CheckBox) view).isChecked();
            }
        });
        spinnerCampus = findViewById(R.id.spinnerCampus);
        ArrayAdapter<CharSequence> arrayAdapter = ArrayAdapter.createFromResource(this, R.array.campus, android.R.layout.simple_spinner_item);
        spinnerCampus.setAdapter(arrayAdapter);
        spinnerCampus.setOnItemSelectedListener(this);
        //
        SharedPreferences sharedPreferences = getSharedPreferences("UserData", Context.MODE_PRIVATE);
        String storedUsername = sharedPreferences.getString(IntentKeys.USER_NAME, null);
        if (storedUsername == null) { // Put data into SharedPreferences
            sharedPreferences.edit()
                    .putString(IntentKeys.USER_NAME, "hoangdt")
                    .putString(IntentKeys.PASSWORD, "12345")
                    .commit();
        } else {
            //Check whether user has remembered password. If user has remembered password then start ProfileActivity
            if (sharedPreferences.getBoolean("REMEMBER", false)) {
                String storedPassword = sharedPreferences.getString(IntentKeys.PASSWORD, null);
                Intent intent = new Intent(this, ProfileDetailActivity.class);
                intent.putExtra(IntentKeys.USER_NAME, storedUsername);
                intent.putExtra(IntentKeys.PASSWORD, storedPassword);
                startActivity(intent);
            }
        }
    }
    public void onLogin(View view) {
        //Toast.makeText(this, "Login button is clicked", Toast.LENGTH_LONG).show();
        String userName = edtUserName.getText().toString().trim();
        String password = edtPassword.getText().toString().trim();
        if (userName.length() > 0 && password.length() > 0) {
            SharedPreferences sharedPreferences = getSharedPreferences("UserData", Context.MODE_PRIVATE);
            String storedUsername = sharedPreferences.getString(IntentKeys.USER_NAME, null);
            String storedPassword = sharedPreferences.getString(IntentKeys.PASSWORD, null);
            if (userName.equalsIgnoreCase(storedUsername) && password.equals(storedPassword)) {
                sharedPreferences.edit()
                        .putBoolean("REMEMBER", checkBoxRemember.isChecked())
                        .commit();
                Intent intent = new Intent(this, ProfileDetailActivity.class);
                intent.putExtra(IntentKeys.USER_NAME, userName);
                intent.putExtra(IntentKeys.PASSWORD, password);
                startActivity(intent);
            } else {
                Toast.makeText(this, "Username or password is NOT correct", Toast.LENGTH_LONG).show();
            }
        } else {
            Toast.makeText(this, "User name & password are empty", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        campus = parent.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    protected void onStart() {
        super.onStart();
        //Code here to process
        // Load data and fill data before screen is displayed
    }
    @Override
    protected void onResume() {
        super.onResume();
        // Code here
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Code here
        role = null;
    }
    @Override
    protected void onRestart() {
        super.onRestart();
        //Code here
    }
    public void onListWord(View view) {
        Intent intent = new Intent(this, WordListActivity.class);
        startActivity(intent);
    }
}