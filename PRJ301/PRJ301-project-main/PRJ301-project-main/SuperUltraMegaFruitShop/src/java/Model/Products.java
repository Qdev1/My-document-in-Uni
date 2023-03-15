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
public class Products {

    private int p_id;
    private String p_name;
    private int p_quantity;
    private int c_id;
    private Date date_manufacture;
    private Date date_expriration;
    private float p_price;
    private String p_img;
    private String desc;

    public Products() {
    }

    public Products(int p_id, String p_name, int p_quantity, int c_id, Date date_manufacture, Date date_expriration, float p_price, String p_img, String desc) {
        this.p_id = p_id;
        this.p_name = p_name;
        this.p_quantity = p_quantity;
        this.c_id = c_id;
        this.date_manufacture = date_manufacture;
        this.date_expriration = date_expriration;
        this.p_price = p_price;
        this.p_img = p_img;
        this.desc = desc;
    }

    public int getP_id() {
        return p_id;
    }

    public void setP_id(int p_id) {
        this.p_id = p_id;
    }

    public String getP_name() {
        return p_name;
    }

    public void setP_name(String p_name) {
        this.p_name = p_name;
    }

    public int getP_quantity() {
        return p_quantity;
    }

    public void setP_quantity(int p_quantity) {
        this.p_quantity = p_quantity;
    }

    public int getC_id() {
        return c_id;
    }

    public void setC_id(int c_id) {
        this.c_id = c_id;
    }

    public Date getDate_manufacture() {
        return date_manufacture;
    }

    public void setDate_manufacture(Date date_manufacture) {
        this.date_manufacture = date_manufacture;
    }

    public Date getDate_expriration() {
        return date_expriration;
    }

    public void setDate_expriration(Date date_expriration) {
        this.date_expriration = date_expriration;
    }

    public float getP_price() {
        return p_price;
    }

    public void setP_price(float p_price) {
        this.p_price = p_price;
    }

    public String getP_img() {
        return p_img;
    }

    public void setP_img(String p_img) {
        this.p_img = p_img;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Override
    public String toString() {
        return "Products{" + "p_id=" + p_id + ", p_name=" + p_name + ", p_quantity=" + p_quantity + ", c_id=" + c_id + ", date_manufacture=" + date_manufacture + ", date_expriration=" + date_expriration + ", p_price=" + p_price + ", p_img=" + p_img + ", desc=" + desc + '}';
    }

}
