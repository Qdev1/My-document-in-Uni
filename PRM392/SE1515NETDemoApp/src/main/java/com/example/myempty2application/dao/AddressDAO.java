package com.example.myempty2application.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.myempty2application.entity.Address;

import java.util.List;

@Dao
public interface AddressDAO {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Address address);

    @Query("SELECT * FROM address_table")
    List<Address> getAllAddress();

    @Query("SELECT * FROM address_table WHERE address_id = :addressId")
    List<Address> getAddress(String addressId);
}
