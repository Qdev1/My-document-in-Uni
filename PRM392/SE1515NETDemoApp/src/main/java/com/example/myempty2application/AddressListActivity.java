package com.example.myempty2application;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.myempty2application.adapter.AddressListAdapter;
import com.example.myempty2application.bean.AddressInfo;

import java.util.ArrayList;
import java.util.List;

public class AddressListActivity extends AppCompatActivity {
    private List<AddressInfo> addressInfoList;
    private Dialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address_list);
        RecyclerView recyclerViewAddressList = findViewById(R.id.recycleViewAddress);
        addressInfoList = getAddressInfoList();
        AddressListAdapter addressListAdapter =
                new AddressListAdapter(AddressListActivity.this, addressInfoList);
        recyclerViewAddressList.setAdapter(addressListAdapter);
        recyclerViewAddressList.setLayoutManager(new LinearLayoutManager(this));

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Confirm  Selected Address ");
        builder.setMessage("Please confirm that you selected the address");
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent();
                intent.putExtra("ADDRESS", "Ho chi minh city");
                setResult(2, intent);
                finish();
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getBaseContext(), "Cancel select addresss", Toast.LENGTH_LONG).show();
            }
        });
        dialog = builder.create();
        //Register context menu for recycle view
        //registerForContextMenu(recyclerViewAddressList);
    }
    @Override
    public void onCreateContextMenu(ContextMenu menu, View view, ContextMenu.ContextMenuInfo menuInfo) {
        getMenuInflater().inflate(R.menu.menu_context, menu);
        super.onCreateContextMenu(menu, view, menuInfo);
    }
    @Override
    public boolean onContextItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.menuItemSearch:
                Toast.makeText(this, "Menu Search selected", Toast.LENGTH_LONG).show();
                break;
            case R.id.menuItemEdit:
                break;
            case  R.id.menuItemDelete:
                break;
        }
        return super.onContextItemSelected(menuItem);
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
    private final int REQUEST_CODE = 3;
    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.menuItemCreateRequest:
                //Handle click here
                Intent intent = new Intent(this, CreateAddressActivity.class);
                startActivityForResult(intent, REQUEST_CODE);
                break;
            case R.id.menuItemRequestHistory:
                //Handle click here
                break;
            case R.id.menuItemFavourites:
                //Handle click here
                break;
            case R.id.menuItemRequétLocation:
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    requestLocationPermission();
                }
                break;
            case R.id.menuItemSendNotification:
                sendNotification();
                break;
            case R.id.menuItemCancelNotification:
                cancelNotification();
                break;
        }
        return super.onOptionsItemSelected(menuItem);
    }
    private final String CHANNEL_ID = "01001";
    private void sendNotification() {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID);
        builder.setSmallIcon(R.drawable.ic_search_black_24dp);
        builder.setContentTitle(getString(R.string.notification_title));
        builder.setContentText(getString(R.string.notification_content));
        Intent intent = new Intent(this, NotificationMessageActivity.class);

        // Handle action when the user tap on notification
        intent.putExtra("DATA", "Hello!!");
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);
        builder.setContentIntent(pendingIntent);
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel notificationChannel = new NotificationChannel(CHANNEL_ID,
                    "Demo Notification App", NotificationManager.IMPORTANCE_HIGH);
            notificationManager.createNotificationChannel(notificationChannel);
        } else {
            builder.setPriority(NotificationCompat.PRIORITY_HIGH);
        }
        notificationManager.notify(1010, builder.build());
    }
    private void cancelNotification() {
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.cancel(1010);
    }
    private final static int REQUEST_lOCATION = 2;
    @RequiresApi(api = Build.VERSION_CODES.M)
    public void requestLocationPermission() {
        if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            if(shouldShowRequestPermissionRationale(Manifest.permission.ACCESS_FINE_LOCATION)) {
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("Reason for Request Location");
                builder.setMessage("Please grant location service permission");
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION,
                                Manifest.permission.ACCESS_COARSE_LOCATION}, REQUEST_lOCATION);
                    }
                });
                builder.create().show();
            } else {
                requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.ACCESS_COARSE_LOCATION}, REQUEST_lOCATION);
            }
        } else {
            Toast.makeText(this, "Location permission granted!!!", Toast.LENGTH_LONG).show();
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResult) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResult);
        switch (requestCode) {
            case REQUEST_lOCATION:
                if (requestCode == REQUEST_lOCATION && grantResult.length > 0
                        && grantResult[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "Location permission granted by user", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(this, "Location permission is NOT granted!!!", Toast.LENGTH_LONG).show();
                }
                break;
        }
    }
    public void selectAddress(View view) {
        dialog.show();
    }
    private List<AddressInfo> getAddressInfoList() {
        List<AddressInfo> addressInfos = new ArrayList<>();
        String url = "https://images.pexels.com/photos/11470549/pexels-photo-11470549.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1";
        AddressInfo addressInfo = new AddressInfo();
        addressInfo.setAddress("Hang bai 1");
        addressInfo.setCity("Ha noi");
        addressInfo.setImage(url);
        addressInfos.add(addressInfo);

        addressInfo = new AddressInfo();
        addressInfo.setAddress("Hang bai 2");
        addressInfo.setCity("Ha noi");
        addressInfo.setImage(url);
        addressInfos.add(addressInfo);

        addressInfo = new AddressInfo();
        addressInfo.setAddress("Hang bai 3");
        addressInfo.setCity("Ha noi");
        addressInfo.setImage(url);
        addressInfos.add(addressInfo);

        addressInfo = new AddressInfo();
        addressInfo.setAddress("Hang bai 4");
        addressInfo.setCity("Ha noi");
        addressInfo.setImage("https://cdn.pixabay.com/photo/2016/12/27/17/10/pendulum-1934311_960_720.jpg");
        addressInfos.add(addressInfo);
        return addressInfos;
    }
}