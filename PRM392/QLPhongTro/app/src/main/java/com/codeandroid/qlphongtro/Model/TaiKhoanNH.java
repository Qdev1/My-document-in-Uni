package com.codeandroid.qlphongtro.Model;

public class TaiKhoanNH {
    private String soTK;
    private String tenTK;
    private String tenNH;

    public TaiKhoanNH(String soTK, String tenTK, String tenNH) {
        this.soTK = soTK;
        this.tenTK = tenTK;
        this.tenNH = tenNH;
    }

    public String getSoTK() {
        return soTK;
    }

    public void setSoTK(String soTK) {
        this.soTK = soTK;
    }

    public String getTenTK() {
        return tenTK;
    }

    public void setTenTK(String tenTK) {
        this.tenTK = tenTK;
    }

    public String getTenNH() {
        return tenNH;
    }

    public void setTenNH(String tenNH) {
        this.tenNH = tenNH;
    }
}
