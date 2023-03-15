package com.example.myempty2application;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myempty2application.entity.Address;
import com.example.myempty2application.repository.AddressRepository;

import java.util.UUID;

public class CreateAddressActivity extends AppCompatActivity {
    private AddressRepository addressRepository;
    private EditText editTextHouseNo, editTextStreet, editTextCity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_address);
        addressRepository = new AddressRepository(this);
        editTextHouseNo = findViewById(R.id.edt_house_no);
        editTextStreet = findViewById(R.id.edt_street);
        editTextCity = findViewById(R.id.edt_city);
    }

    /**
     * Handle action when the user click on "Save" button on the screen.
     * - Validate input data
     * - Call repository to store data into database
     * @param view
     */
    public void save(View view) {
        String  addressId = UUID.randomUUID().toString();
        String houseNo = editTextHouseNo.getText().toString().trim();
        String street = editTextStreet.getText().toString().trim();
        String city = editTextCity.getText().toString().trim();
        if (houseNo.length() <= 0) {
            editTextHouseNo.setError("House No is required");
            return;
        }
        Address address = new Address(addressId, houseNo, street, city);
        addressRepository.create(address);
        Toast.makeText(this, "Saved successfully.", Toast.LENGTH_LONG).show();
        //Clear data
        editTextHouseNo.setText("");
        editTextStreet.setText("");
        editTextCity.setText("");
    }
}