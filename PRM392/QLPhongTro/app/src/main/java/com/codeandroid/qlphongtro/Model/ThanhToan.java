package com.codeandroid.qlphongtro.Model;

public class ThanhToan {
    private int id;
    private String ngayTT;
    private String sdtTKTT;
    private int soPhong;
    private double tongTien;
    private String noiDung;
    private int trangThai;

    public ThanhToan() {
    }

    public ThanhToan(int id, String ngayThanhToan, String sdtTKTT, int soPhong, double tongTien, String noiDung, int trangThai) {
        this.id = id;
        this.ngayTT = ngayThanhToan;
        this.sdtTKTT = sdtTKTT;
        this.soPhong = soPhong;
        this.tongTien = tongTien;
        this.noiDung = noiDung;
        this.trangThai = trangThai;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNgayThanhToan() {
        return ngayTT;
    }

    public void setNgayThanhToan(String ngayThanhToan) {
        this.ngayTT = ngayThanhToan;
    }

    public String getSdtTKTT() {
        return sdtTKTT;
    }

    public void setSdtTKTT(String sdtTKTT) {
        this.sdtTKTT = sdtTKTT;
    }

    public int getSoPhong() {
        return soPhong;
    }

    public void setSoPhong(int soPhong) {
        this.soPhong = soPhong;
    }

    public double getTongTien() {
        return tongTien;
    }

    public void setTongTien(double tongTien) {
        this.tongTien = tongTien;
    }

    public String getNoiDung() {
        return noiDung;
    }

    public void setNoiDung(String noiDung) {
        this.noiDung = noiDung;
    }

    public int getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }
}
