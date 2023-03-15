package com.example.se1603_ksapplication;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.Image;
import android.os.Build;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.se1603_ksapplication.bean.UserInfo;
import com.example.se1603_ksapplication.common.IntentKeys;
import com.squareup.picasso.Picasso;

public class ProfileDetailActivity extends AppCompatActivity {
    private TextView tvFirstName, tvLastName, tvAddress;
    private Button btEditProfile;
    private UserInfo userInfo;
    //Define a object of ActivityResultLauncher which is used to start an activity for get data back
    private ActivityResultLauncher<Intent> mStartActivity = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == 2) {
                        Intent data = result.getData();
                        //Get data from Intent that had pushed back from EditProfileActivity
                        String firstName = data.getStringExtra(IntentKeys.FIRST_NAME);
                        String lastName = data.getStringExtra(IntentKeys.LAST_NAME);
                        String address = data.getStringExtra(IntentKeys.ADDRESS);
                        //Set data to fields on the screen
                        tvLastName.setText("Last Name:" + lastName);
                        tvFirstName.setText("First Name:" + firstName);
                        tvAddress.setText("Address:" + address);
                    }
                }
            });
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_detail);
        tvFirstName = findViewById(R.id.tvFirstName);
        tvLastName = findViewById(R.id.tvLastName);
        btEditProfile = findViewById(R.id.btEditProfile);
        ImageView imageViewAvatar = findViewById(R.id.imageView);
        String imageURL = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTEZaeFcW54_Ul9FHhw0u7TP8iuXTDNYE954718B3E&s";
        Picasso.with(this).load(imageURL)
                .placeholder(R.drawable.ic_loading_error)
                .error(R.drawable.ic_loading_error).into(imageViewAvatar);
        btEditProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfileDetailActivity.this, EditProfileActivity.class);
                intent.putExtra(IntentKeys.USER_NAME, userInfo.getUserName());
                intent.putExtra(IntentKeys.FIRST_NAME, userInfo.getFirstName());
                intent.putExtra(IntentKeys.LAST_NAME, userInfo.getLastName());
                //startActivity(intent); // Can not get data back from EditProfileActivity
                //startActivityForResult(intent, 2); // For getting data back from EditProfileActivity
                mStartActivity.launch(intent); // New way to start an activity to get data back
            }
        });
        //Register context menu for First Name TextView
        registerForContextMenu(tvFirstName);
    }
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.menu_context, menu);
    }
    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_edit:
                doActionEdit();
                return true;
            case R.id.menu_share:
                return true;
        }
        return super.onContextItemSelected(item);
    }
    private void doActionEdit() {

    }
    @SuppressLint("RestrictedApi")
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        if (menu instanceof MenuBuilder) {
            ((MenuBuilder) menu).setOptionalIconsVisible(true);
        }
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_setting:
                showSetting();
                return true;
            case R.id.menu_favorite:
                showFavourite();
                return true;
            case R.id.menu_request_location:
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    requestLocation();
                }
                return true;
            case R.id.menu_send_notify:
                sendNotification();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
    private void showFavourite() {
        Intent intent = new Intent(this, SettingActivity.class);
        startActivity(intent);
    }
    private void showSetting() {

    }
    private final String CHANNEL_ID = "001";
    private void sendNotification() {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_action_setting)
                .setContentTitle("Demo Notification")
                .setContentText("Please .......");
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel =
                    new NotificationChannel(CHANNEL_ID, "Demo Notification Channel", NotificationManager.IMPORTANCE_HIGH);
            notificationManager.createNotificationChannel(channel);
        } else {
            builder.setPriority(NotificationCompat.PRIORITY_HIGH);
        }
        //Add activity to handle action when user tap on notification
        Intent intent = new Intent(this, NotificationHandleActivity.class);
        PendingIntent pendingIntent= PendingIntent.getActivity(this, 0, intent, 0);
        builder.setContentIntent(pendingIntent);
        //Send notification
        notificationManager.notify(2, builder.build());
    }
    private final int REQUEST_LOCATION = 2;
    @RequiresApi(api = Build.VERSION_CODES.M)
    private void requestLocation() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (shouldShowRequestPermissionRationale(Manifest.permission.ACCESS_FINE_LOCATION)) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(this)
                            .setTitle("Reason for Request Location")
                            .setMessage("Please grant permission to access location service")
                            .setCancelable(false)
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    // Handle action when user click OK button
                                }
                            });

                }
            }
            requestPermissions(new String[]{Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION);
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case REQUEST_LOCATION:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "Location service is granted", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(this, "Location service is NOT granted", Toast.LENGTH_LONG).show();
                }
                break;
        }
    }
    /**
     * The old method to get data back from EditProfileActivity
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == 2) { // ResultCode had been set in EditProfileActivity
            //Get data from Intent that had pushed back from EditProfileActivity
//            String firstName = data.getStringExtra(IntentKeys.FIRST_NAME);
//            String lastName = data.getStringExtra(IntentKeys.LAST_NAME);
//            String address = data.getStringExtra(IntentKeys.ADDRESS);
            //Set data to fields on the screen
//            tvLastName.setText("Last Name:" + lastName);
//            tvFirstName.setText("First Name:" + firstName);
//            tvAddress.setText("Address:" + address);
        }
    }
    private UserInfo getUserInfo() {
        UserInfo userInfo = new UserInfo();
        Intent intent = getIntent();
        if (intent != null) {
            userInfo.setUserName(intent.getStringExtra(IntentKeys.USER_NAME));
            userInfo.setFirstName("Hung");
            userInfo.setLastName("Nguyen Van");
        }
        return userInfo;
    }
    @Override
    protected void onStart() {
        super.onStart();
        userInfo = getUserInfo();
        tvFirstName.setText(userInfo.getFirstName());
        tvLastName.setText(userInfo.getLastName());
    }
    @Override
    protected void onStop() {
        super.onStop();
        userInfo = null;
    }
}