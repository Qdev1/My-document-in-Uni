package com.example.pe_prm;

public class Order {

    private String number;
    private String date;
    private int line_count;
    private String customer_name;

    public Order(String number, String date, int line_count, String customer_name) {
        this.number = number;
        this.date = date;
        this.line_count = line_count;
        this.customer_name = customer_name;
    }

    public Order() {
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getLine_count() {
        return line_count;
    }

    public void setLine_count(int line_count) {
        this.line_count = line_count;
    }

    public String getCustomer_name() {
        return customer_name;
    }

    public void setCustomer_name(String customer_name) {
        this.customer_name = customer_name;
    }

    @Override
    public String toString() {
        return "Order{" +
                "number='" + number + '\'' +
                ", date='" + date + '\'' +
                ", line_count=" + line_count +
                ", customer_name='" + customer_name + '\'' +
                '}';
    }
}
