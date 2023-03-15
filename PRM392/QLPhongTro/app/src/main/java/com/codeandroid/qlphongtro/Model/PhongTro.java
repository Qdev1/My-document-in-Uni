package com.codeandroid.qlphongtro.Model;

public class PhongTro {
    private int id;
    private String ngayThue;
    private double tienPhong;
    private double tienNuoc;
    private double tienDien;
    private int soPhong;
    private String sdtTK;
    private int tinhTrang; // 0 là chưa đặt, 1 là có người đặt or có yêu cầu đặt.

    public PhongTro() {
        tinhTrang = 0;
    }

    public PhongTro(int id, String ngayThue, double tienPhong, double tienNuoc, double tienDien, int soPhong, String taiKhoan) {
        this.id = id;
        this.ngayThue = ngayThue;
        this.tienPhong= tienPhong;
        this.tienNuoc = tienNuoc;
        this.tienDien = tienDien;
        this.soPhong = soPhong;
        this.sdtTK = taiKhoan;
    }

    public PhongTro(int id, String ngayThue, double tienPhong, double tienNuoc, double tienDien, int soPhong, String taiKhoan, int tinhTrang) {
        this.id = id;
        this.ngayThue = ngayThue;
        this.tienPhong= tienPhong;
        this.tienNuoc = tienNuoc;
        this.tienDien = tienDien;
        this.soPhong = soPhong;
        this.sdtTK = taiKhoan;
        this.tinhTrang = tinhTrang;
    }

    public double getTienPhong() {
            return tienPhong;
    }

    public void setTienPhong(double tienPhong) {
        this.tienPhong = tienPhong;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNgayThue() {
        return ngayThue;
    }

    public void setNgayThue(String ngayThue) {
        this.ngayThue = ngayThue;
    }

    public double getTienNuoc() {
        return tienNuoc;
    }

    public void setTienNuoc(double tienNuoc) {
        this.tienNuoc = tienNuoc;
    }

    public double getTienDien() {
        return tienDien;
    }

    public void setTienDien(double tienDien) {
        this.tienDien = tienDien;
    }

    public int getSoPhong() {
        return soPhong;
    }

    public void setSoPhong(int soPhong) {
        this.soPhong = soPhong;
    }

    public String getTaiKhoan() {
        return sdtTK;
    }

    public void setTaiKhoan(String taiKhoan) {
        this.sdtTK = taiKhoan;
    }

    public int getTinhTrang() {
        return tinhTrang;
    }

    public void setTinhTrang(int tinhTrang) {
        this.tinhTrang = tinhTrang;
    }
}
