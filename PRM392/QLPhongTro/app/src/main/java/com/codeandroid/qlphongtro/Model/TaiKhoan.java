package com.codeandroid.qlphongtro.Model;

public class TaiKhoan {
    private int id;
    private String username;
    private String password;
    private String fullname;
    private String phone;
    private String address;
    private String dob;
    private String role;


    public TaiKhoan() {
    }

    public TaiKhoan(int id, String username, String password, String fullname, String phone, String address, String dob, String role) {
        this.id= id;
        this.username = username;
        this.password = password;
        this.fullname = fullname;
        this.phone = phone;
        this.address = address;
        this.dob = dob;
        this.role= role;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    @Override
    public String toString() {
        return "TaiKhoan{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", fullname='" + fullname + '\'' +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                ", dob='" + dob + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}
