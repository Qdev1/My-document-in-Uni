package com.codeandroid.qlphongtro.Model;

public class YeuCau {
    //sdtYC TEXT, soPhong NUMBER, chuTro TEXT
    private String sdtYC;
    private int soPhong;

    public YeuCau(String sdtYC, int soPhong) {
        this.sdtYC = sdtYC;
        this.soPhong = soPhong;
    }

    public String getSdtYC() {
        return sdtYC;
    }

    public void setSdtYC(String sdtYC) {
        this.sdtYC = sdtYC;
    }

    public int getSoPhong() {
        return soPhong;
    }

    public void setSoPhong(int soPhong) {
        this.soPhong = soPhong;
    }
}
