/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author Nezio
 */
public class User {
    String userID, account, password, fullname,email,city,role;
    String phoneNumber;

    public User() {
    }

    public User(String userID, String account, String password, String fullname, String email, String city, String role, String phoneNumber) {
        this.userID = userID;
        this.account = account;
        this.password = password;
        this.fullname = fullname;
        this.email = email;
        this.city = city;
        this.role = role;
        this.phoneNumber = phoneNumber;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
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

    @Override
    public String toString() {
        return "User{" + "userID=" + userID + ", account=" + account + ", password=" + password + ", fullname=" + fullname + ", email=" + email + ", city=" + city + ", role=" + role + ", phoneNumber=" + phoneNumber + '}';
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    
    
}
