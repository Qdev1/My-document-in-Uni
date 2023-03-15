package com.example.practiceexamsubmission;

public class Customer {
    private String customerID;
    private String name;
    private int order_quantity;
    private String address;

    public Customer(String customerID, String name, int order_quantity, String address) {
        this.customerID = customerID;
        this.name = name;
        this.order_quantity = order_quantity;
        this.address = address;
    }

    public Customer() {
    }

    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getOrder_quantity() {
        return order_quantity;
    }

    public void setOrder_quantity(int order_quantity) {
        this.order_quantity = order_quantity;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "customerID='" + customerID + '\'' +
                ", name='" + name + '\'' +
                ", order_quantity=" + order_quantity +
                ", address='" + address + '\'' +
                '}';
    }
}
