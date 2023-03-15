/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.sql.Date;

/**
 *
 * @author duonn
 */
public class Orders {

    private int O_id;
    private int a_id;
    private double totalPrice;
    private Date created_Date;
    private int s_id;
    private String note;

    public Orders() {
    }

    public Orders(int a_id, double totalPrice, int s_id, String note) {
        this.a_id = a_id;
        this.totalPrice = totalPrice;
        this.s_id = s_id;
        this.note = note;
    }

    public Orders(int O_id, int a_id, double totalPrice, Date created_Date, int s_id, String note) {
        this.O_id = O_id;
        this.a_id = a_id;
        this.totalPrice = totalPrice;
        this.created_Date = created_Date;
        this.s_id = s_id;
        this.note = note;
    }

    public int getO_id() {
        return O_id;
    }

    public void setO_id(int O_id) {
        this.O_id = O_id;
    }

    public int getA_id() {
        return a_id;
    }

    public void setA_id(int a_id) {
        this.a_id = a_id;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Date getCreated_Date() {
        return created_Date;
    }

    public void setCreated_Date(Date created_Date) {
        this.created_Date = created_Date;
    }

    public int getS_id() {
        return s_id;
    }

    public void setS_id(int s_id) {
        this.s_id = s_id;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

}
