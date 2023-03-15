package com.example.myempty2application.entity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import org.jetbrains.annotations.NotNull;

@Entity(tableName = "address_table")
public class Address {
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "address_id")
    private String addressId;
    @Nullable
    @ColumnInfo(name = "house_no")
    private String houseNo;
    @Nullable
    @ColumnInfo(name = "street")
    private String street;
    @NotNull
    @ColumnInfo(name = "city")
    private String city;

    public Address(@NonNull String addressId, @Nullable String houseNo, @Nullable String street, @NotNull String city) {
        this.addressId = addressId;
        this.houseNo = houseNo;
        this.street = street;
        this.city = city;
    }

    @NonNull
    public String getAddressId() {
        return addressId;
    }

    @Nullable
    public String getHouseNo() {
        return houseNo;
    }

    @Nullable
    public String getStreet() {
        return street;
    }

    @NotNull
    public String getCity() {
        return city;
    }
}
