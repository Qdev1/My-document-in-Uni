/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author duonn
 */
public class OrderDetail {

    private int ot_id;
    private int o_id;
    private String pname;
    private String pImg;
    private double p_Price;
    private int p_Quantity;

    public OrderDetail() {
    }

    public OrderDetail(int ot_id, int o_id, String pname, String pImg, double p_Price, int p_Quantity) {
        this.ot_id = ot_id;
        this.o_id = o_id;
        this.pname = pname;
        this.pImg = pImg;
        this.p_Price = p_Price;
        this.p_Quantity = p_Quantity;
    }

    public int getOt_id() {
        return ot_id;
    }

    public void setOt_id(int ot_id) {
        this.ot_id = ot_id;
    }

    public int getO_id() {
        return o_id;
    }

    public void setO_id(int o_id) {
        this.o_id = o_id;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public String getpImg() {
        return pImg;
    }

    public void setpImg(String pImg) {
        this.pImg = pImg;
    }

    public double getP_Price() {
        return p_Price;
    }

    public void setP_Price(double p_Price) {
        this.p_Price = p_Price;
    }

    public int getP_Quantity() {
        return p_Quantity;
    }

    public void setP_Quantity(int p_Quantity) {
        this.p_Quantity = p_Quantity;
    }

}
