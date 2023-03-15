package com.example.myempty2application.repository;

import android.content.Context;


import com.example.myempty2application.dao.AddressDAO;
import com.example.myempty2application.dao.AddressRoomDatabase;
import com.example.myempty2application.entity.Address;

import java.util.List;

/**
 *
 */
public class AddressRepository {
    private AddressDAO addressDAO;
    public AddressRepository(Context context) {
        addressDAO = AddressRoomDatabase.getDatabase(context).addressDAO();
    }

    /**
     * Get all address records in address_table in database
     * @return
     */
    public List<Address> getAllAddress() {
        return addressDAO.getAllAddress();
    }
    public List<Address> getAddress(String addressId) {
        return addressDAO.getAddress(addressId);
    }
    public void create(Address address) {
        addressDAO.insert(address);
    }
}
