package com.example.myempty2application;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.Scanner;

public class HomeActivity extends AppCompatActivity {
    private TextView textViewSelectedAddress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        TextView textViewUserName = findViewById(R.id.textViewUserNameLabel);
        TextView textViewRole = findViewById(R.id.textViewRoleLabel);
        Intent  intent = getIntent();
        String role = intent.getStringExtra("Role");
        textViewRole.setText(role);
        String userName = intent.getStringExtra("UserName");
        textViewUserName.setText(userName);
        textViewSelectedAddress = findViewById(R.id.textViewSelectedAddress);
        Log.d("onCreate", "role:" + role);
        getDataFromFile();
    }

    /**
     * Get data from sample.txt file in /raw folder
     */
    private void getDataFromFile() {
        Scanner scanner = new Scanner(getResources().openRawResource(R.raw.sample));
        StringBuffer buffer = new StringBuffer();
        while (scanner.hasNext()) {
            buffer.append(scanner.nextLine());
        }
        textViewSelectedAddress.setText(buffer.toString());
    }
    /**
     * Handle action when user click on "OnCallPhone"
     * @param view
     */
    public void onPhoneCall(View view) {
        Uri uri =  Uri.parse("tel:01912210727");
        startActivity(new Intent(Intent.ACTION_DIAL, uri));
    }

    /**
     * Handle action when the user click on "onSelectAddress" button
     * @param view
     */
    public void onSelectAddress(View view) {
        Intent intent = new Intent(HomeActivity.this, AddressListActivity.class);
        startActivityForResult(intent, 2);
    }
    public void onSelectProduct(View view) {
        Intent intent = new Intent(HomeActivity.this, ProductListActivity.class);
        startActivity(intent);
    }
    /**
     * Handle the result when AddressListActivity finish
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 2) {
            String selectedAddress = data.getStringExtra("ADDRESS");
            //Set data for displaying on screen
            textViewSelectedAddress.setText(textViewSelectedAddress.getText() + " " + selectedAddress);
        }
    }

    /**
     * Start PostListActivity to display posts got from webservice
     * @param view
     */
    public void getPostList(View view) {
        Intent intent = new Intent(this, PostListActivity.class);
        startActivity(intent);
    }
}