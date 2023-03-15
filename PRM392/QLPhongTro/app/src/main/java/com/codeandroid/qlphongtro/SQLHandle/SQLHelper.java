package com.codeandroid.qlphongtro.SQLHandle;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.codeandroid.qlphongtro.Model.PhongTro;
import com.codeandroid.qlphongtro.Model.TaiKhoan;
import com.codeandroid.qlphongtro.Model.TaiKhoanNH;
import com.codeandroid.qlphongtro.Model.ThanhToan;
import com.codeandroid.qlphongtro.Model.YeuCau;

import java.util.ArrayList;
import java.util.List;

public class SQLHelper extends SQLiteOpenHelper {
    static final String DB_NAME = "QLPHONGTRO";
    static final int DB_VERSION = 2;
    static final String DB_TABLE_TK = "TAIKHOAN";
    static final String DB_TABLE_PT = "PHONGTRO";
    static final String DB_TABLE_TT = "THANHTOAN";
    static final String DB_TABLE_YC = "YEUCAU";
    static final String DB_TABLE_TKNH = "TAIKHOANNGANHANG";

    SQLiteDatabase sqLiteDatabase;
    ContentValues contentValues;

    Context context;

    public SQLHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + DB_TABLE_TK + "(id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, " +
                "username TEXT, password TEXT, fullname TEXT, phone TEXT, address TEXT, dob TEXT, role Text)";

        String query2 = "CREATE TABLE " + DB_TABLE_PT + "(id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, " +
                " ngayThue TEXT, tienPhong TEXT, tienNuoc TEXT, tienDien TEXT, soPhong NUMBER, sdtTK TEXT, tinhTrang NUMBER)";

        String query3 = "CREATE TABLE " + DB_TABLE_TT + "(id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, " +
                "ngayTT TEXT, sdtTKTT TEXT, soPhong NUMBER, tongTien TEXT, noiDung TEXT, trangThai NUMBER)";

        String query4 = "CREATE TABLE " + DB_TABLE_YC + "(id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, " +
                "sdtYC TEXT, soPhong NUMBER)";

        String query5 = "CREATE TABLE " + DB_TABLE_TKNH + "(id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, " +
                "soTK TEXT, tenTK TEXT, tenNH TEXT)";

        db.execSQL(query);
        db.execSQL(query2);
        db.execSQL(query3);
        db.execSQL(query4);
        db.execSQL(query5);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion != newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + DB_TABLE_TK);
            db.execSQL("DROP TABLE IF EXISTS " + DB_TABLE_PT);
            db.execSQL("DROP TABLE IF EXISTS " + DB_TABLE_TT);
            db.execSQL("DROP TABLE IF EXISTS " + DB_TABLE_YC);
            db.execSQL("DROP TABLE IF EXISTS " + DB_TABLE_TKNH);
            onCreate(db);
        }
    }

    public void insertTKNH(TaiKhoanNH taiKhoanNH) {
        sqLiteDatabase = this.getWritableDatabase();
        contentValues = new ContentValues();
        contentValues.put("soTK", taiKhoanNH.getSoTK());
        contentValues.put("tenTK", taiKhoanNH.getTenTK());
        contentValues.put("tenNH", taiKhoanNH.getTenNH());
        sqLiteDatabase.insert(DB_TABLE_TKNH, null, contentValues);
        sqLiteDatabase.close();
    }

    public void updateTKNH(TaiKhoanNH taiKhoanNH, String soTKX) {
        sqLiteDatabase = getWritableDatabase();
        contentValues = new ContentValues();
        contentValues.put("soTK", taiKhoanNH.getSoTK());
        contentValues.put("tenTK", taiKhoanNH.getTenTK());
        contentValues.put("tenNH", taiKhoanNH.getTenNH());
        sqLiteDatabase.update(DB_TABLE_TKNH, contentValues, "soTK=?", new String[]{soTKX + ""});
        sqLiteDatabase.close();
    }

    public List<TaiKhoanNH> getTKNH() {
        sqLiteDatabase = getReadableDatabase();
        List<TaiKhoanNH> list = new ArrayList<>();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM " + DB_TABLE_TKNH, new String[]{});
        while (cursor.moveToNext()) {
            @SuppressLint("Range") int id = cursor.getInt(cursor.getColumnIndex("id"));
            @SuppressLint("Range") String soTK = cursor.getString(cursor.getColumnIndex("soTK"));
            @SuppressLint("Range") String tenTK = cursor.getString(cursor.getColumnIndex("tenTK"));
            @SuppressLint("Range") String tenNH = cursor.getString(cursor.getColumnIndex("tenNH"));

            list.add(new TaiKhoanNH(soTK, tenTK, tenNH));
        }
        sqLiteDatabase.close();
        return list;
    }

    public boolean insertUser(TaiKhoan user) {
        if (checkExistsTK(user.getUsername())) {
            Toast.makeText(context, "Username đã tồn tại", Toast.LENGTH_SHORT).show();
            return false;
        }
        sqLiteDatabase = getWritableDatabase();
        contentValues = new ContentValues();
        contentValues.put("username", user.getUsername());
        contentValues.put("password", user.getPassword());
        contentValues.put("fullname", user.getFullname());
        contentValues.put("phone", user.getPhone());
        contentValues.put("address", user.getAddress());
        contentValues.put("dob", user.getDob());
        contentValues.put("role", user.getRole());
        sqLiteDatabase.insert(DB_TABLE_TK, null, contentValues);
        sqLiteDatabase.close();
        return true;
    }

    public void deleteTaiKhoan(String username) {
        sqLiteDatabase = getWritableDatabase();
        sqLiteDatabase.delete(DB_TABLE_TK, "username=?", new String[]{username + ""});
        sqLiteDatabase.close();
    }

    public TaiKhoan getTKByPhone(String phone) {
        sqLiteDatabase = getReadableDatabase();
        TaiKhoan taiKhoan = null;
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM " + DB_TABLE_TK + " WHERE phone=?", new String[]{phone + ""});
        while (cursor.moveToNext()) {
            @SuppressLint("Range") int id = cursor.getInt(cursor.getColumnIndex("id"));
            @SuppressLint("Range") String password = cursor.getString(cursor.getColumnIndex("password"));
            @SuppressLint("Range") String username = cursor.getString(cursor.getColumnIndex("username"));
            @SuppressLint("Range") String fullname = cursor.getString(cursor.getColumnIndex("fullname"));
            @SuppressLint("Range") String address = cursor.getString(cursor.getColumnIndex("address"));
            @SuppressLint("Range") String dob = cursor.getString(cursor.getColumnIndex("dob"));
            @SuppressLint("Range") String role = cursor.getString(cursor.getColumnIndex("role"));

            taiKhoan = new TaiKhoan(id, username, password, fullname, phone, address, dob, role);
            sqLiteDatabase.close();
            return taiKhoan;
        }

        sqLiteDatabase.close();
        return taiKhoan;
    }


    public TaiKhoan login(String username, String pass) {
        sqLiteDatabase = getReadableDatabase();
        TaiKhoan taiKhoan = null;
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM " + DB_TABLE_TK + " WHERE username=?", new String[]{username + ""});
        while (cursor.moveToNext()) {
            @SuppressLint("Range") int id = cursor.getInt(cursor.getColumnIndex("id"));
            @SuppressLint("Range") String password = cursor.getString(cursor.getColumnIndex("password"));
            @SuppressLint("Range") String fullname = cursor.getString(cursor.getColumnIndex("fullname"));
            @SuppressLint("Range") String phone = cursor.getString(cursor.getColumnIndex("phone"));
            @SuppressLint("Range") String address = cursor.getString(cursor.getColumnIndex("address"));
            @SuppressLint("Range") String dob = cursor.getString(cursor.getColumnIndex("dob"));
            @SuppressLint("Range") String role = cursor.getString(cursor.getColumnIndex("role"));
            if (pass.compareTo(password) == 0) {
                taiKhoan = new TaiKhoan(id, username, password, fullname, phone, address, dob, role);
                sqLiteDatabase.close();
                return taiKhoan;
            }
        }
        sqLiteDatabase.close();
        return taiKhoan;
    }


    public boolean checkExistsTK(String username) {
        sqLiteDatabase = getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM " + DB_TABLE_TK + " WHERE username=?", new String[]{username + ""});
        if (cursor.getCount() == 1) {
            sqLiteDatabase.close();
            return true;
        }
        sqLiteDatabase.close();
        return false;
    }

    public List<TaiKhoan> getAllTaiKhoan() {
        List<TaiKhoan> list = new ArrayList<>();
        sqLiteDatabase = getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM " + DB_TABLE_TK + " WHERE role=?", new String[]{"USER"});
        while (cursor.moveToNext()) {
            @SuppressLint("Range") int id = cursor.getInt(cursor.getColumnIndex("id"));
            @SuppressLint("Range") String username = cursor.getString(cursor.getColumnIndex("username"));
            @SuppressLint("Range") String fullname = cursor.getString(cursor.getColumnIndex("fullname"));
            @SuppressLint("Range") String password = cursor.getString(cursor.getColumnIndex("password"));
            @SuppressLint("Range") String phone = cursor.getString(cursor.getColumnIndex("phone"));
            @SuppressLint("Range") String address = cursor.getString(cursor.getColumnIndex("address"));
            @SuppressLint("Range") String dob = cursor.getString(cursor.getColumnIndex("dob"));

            list.add(new TaiKhoan(id, username, password, fullname, phone, address, dob, "USER"));
        }
        sqLiteDatabase.close();
        return list;
    }

    public List<TaiKhoan> getAllTaiKhoanChuTro() {
        List<TaiKhoan> list = new ArrayList<>();
        sqLiteDatabase = getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM " + DB_TABLE_TK + " WHERE role=?", new String[]{"ADMIN"});
        while (cursor.moveToNext()) {
            @SuppressLint("Range") int id = cursor.getInt(cursor.getColumnIndex("id"));
            @SuppressLint("Range") String username = cursor.getString(cursor.getColumnIndex("username"));
            @SuppressLint("Range") String fullname = cursor.getString(cursor.getColumnIndex("fullname"));
            @SuppressLint("Range") String password = cursor.getString(cursor.getColumnIndex("password"));
            @SuppressLint("Range") String phone = cursor.getString(cursor.getColumnIndex("phone"));
            @SuppressLint("Range") String address = cursor.getString(cursor.getColumnIndex("address"));
            @SuppressLint("Range") String dob = cursor.getString(cursor.getColumnIndex("dob"));

            list.add(new TaiKhoan(id, username, password, fullname, phone, address, dob, "USER"));
        }
        sqLiteDatabase.close();
        return list;
    }
    //"username TEXT, password TEXT, fullname TEXT, phone TEXT, address TEXT, dob TEXT, role Text)";

    //phong tro
    public List<PhongTro> getAllPhongTro() {
        List<PhongTro> list = new ArrayList<>();
        sqLiteDatabase = getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM " + DB_TABLE_PT, new String[]{});
        while (cursor.moveToNext()) {
            @SuppressLint("Range") int id = cursor.getInt(cursor.getColumnIndex("id"));
            @SuppressLint("Range") String ngayThue = cursor.getString(cursor.getColumnIndex("ngayThue"));
            @SuppressLint("Range") double tienPhong = cursor.getDouble(cursor.getColumnIndex("tienPhong"));
            @SuppressLint("Range") double tienNuoc = cursor.getDouble(cursor.getColumnIndex("tienNuoc"));
            @SuppressLint("Range") double tienDien = cursor.getDouble(cursor.getColumnIndex("tienDien"));
            @SuppressLint("Range") int soPhong = cursor.getInt(cursor.getColumnIndex("soPhong"));
            @SuppressLint("Range") String sdt = cursor.getString(cursor.getColumnIndex("sdtTK"));
            @SuppressLint("Range") int tinhTrang = cursor.getInt(cursor.getColumnIndex("tinhTrang"));

            list.add(new PhongTro(id, ngayThue, tienPhong, tienNuoc, tienDien, soPhong, sdt, tinhTrang));
        }
        sqLiteDatabase.close();
        return list;
    }

    public List<PhongTro> getAllPhongTroConTrong() {
        List<PhongTro> list = new ArrayList<>();
        sqLiteDatabase = getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM " + DB_TABLE_PT + " WHERE sdtTK='null' or sdtTK=?", new String[]{""});
        while (cursor.moveToNext()) {
            @SuppressLint("Range") int id = cursor.getInt(cursor.getColumnIndex("id"));
            @SuppressLint("Range") String ngayThue = cursor.getString(cursor.getColumnIndex("ngayThue"));
            @SuppressLint("Range") double tienPhong = cursor.getDouble(cursor.getColumnIndex("tienPhong"));
            @SuppressLint("Range") double tienNuoc = cursor.getDouble(cursor.getColumnIndex("tienNuoc"));
            @SuppressLint("Range") double tienDien = cursor.getDouble(cursor.getColumnIndex("tienDien"));
            @SuppressLint("Range") int soPhong = cursor.getInt(cursor.getColumnIndex("soPhong"));
            @SuppressLint("Range") String sdt = cursor.getString(cursor.getColumnIndex("sdtTK"));
            @SuppressLint("Range") int tinhTrang = cursor.getInt(cursor.getColumnIndex("tinhTrang"));

            list.add(new PhongTro(id, ngayThue, tienPhong, tienNuoc, tienDien, soPhong, sdt, tinhTrang));
        }
        sqLiteDatabase.close();
        return list;
    }

    public List<PhongTro> getAllPhongTroConTrongByGia(String gia) {
        List<PhongTro> list = new ArrayList<>();
        sqLiteDatabase = getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM " + DB_TABLE_PT + " WHERE (sdtTK='null' and tienPhong<?) or (sdtTK=? and tienPhong<?)", new String[]{Integer.parseInt(gia) + "", "", Integer.parseInt(gia) + ""});
        while (cursor.moveToNext()) {
            @SuppressLint("Range") int id = cursor.getInt(cursor.getColumnIndex("id"));
            @SuppressLint("Range") String ngayThue = cursor.getString(cursor.getColumnIndex("ngayThue"));
            @SuppressLint("Range") double tienPhong = cursor.getDouble(cursor.getColumnIndex("tienPhong"));
            @SuppressLint("Range") double tienNuoc = cursor.getDouble(cursor.getColumnIndex("tienNuoc"));
            @SuppressLint("Range") double tienDien = cursor.getDouble(cursor.getColumnIndex("tienDien"));
            @SuppressLint("Range") int soPhong = cursor.getInt(cursor.getColumnIndex("soPhong"));
            @SuppressLint("Range") String sdt = cursor.getString(cursor.getColumnIndex("sdtTK"));
            @SuppressLint("Range") int tinhTrang = cursor.getInt(cursor.getColumnIndex("tinhTrang"));

            list.add(new PhongTro(id, ngayThue, tienPhong, tienNuoc, tienDien, soPhong, sdt, tinhTrang));
        }
        sqLiteDatabase.close();
        return list;
    }

    public PhongTro getPhongTroBySDT(String sdt) {
        PhongTro phongTro = null;
        sqLiteDatabase = getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM " + DB_TABLE_PT + " WHERE sdtTK=?", new String[]{sdt + ""});
        while (cursor.moveToNext()) {
            @SuppressLint("Range") int id = cursor.getInt(cursor.getColumnIndex("id"));
            @SuppressLint("Range") String ngayThue = cursor.getString(cursor.getColumnIndex("ngayThue"));
            @SuppressLint("Range") double tienPhong = cursor.getDouble(cursor.getColumnIndex("tienPhong"));
            @SuppressLint("Range") double tienNuoc = cursor.getDouble(cursor.getColumnIndex("tienNuoc"));
            @SuppressLint("Range") double tienDien = cursor.getDouble(cursor.getColumnIndex("tienDien"));
            @SuppressLint("Range") int soPhong = cursor.getInt(cursor.getColumnIndex("soPhong"));
            @SuppressLint("Range") int tinhTrang = cursor.getInt(cursor.getColumnIndex("tinhTrang"));

            phongTro = new PhongTro(id, ngayThue, tienPhong, tienNuoc, tienDien, soPhong, sdt, tinhTrang);
        }
        sqLiteDatabase.close();
        return phongTro;
    }

    public PhongTro getPhongTroBySoPhong(int soPhongX) {
        PhongTro phongTro = null;
        sqLiteDatabase = getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM " + DB_TABLE_PT + " WHERE soPhong=?", new String[]{soPhongX + ""});
        while (cursor.moveToNext()) {
            @SuppressLint("Range") int id = cursor.getInt(cursor.getColumnIndex("id"));
            @SuppressLint("Range") String ngayThue = cursor.getString(cursor.getColumnIndex("ngayThue"));
            @SuppressLint("Range") double tienPhong = cursor.getDouble(cursor.getColumnIndex("tienPhong"));
            @SuppressLint("Range") double tienNuoc = cursor.getDouble(cursor.getColumnIndex("tienNuoc"));
            @SuppressLint("Range") double tienDien = cursor.getDouble(cursor.getColumnIndex("tienDien"));
            @SuppressLint("Range") int soPhong = cursor.getInt(cursor.getColumnIndex("soPhong"));

            phongTro = new PhongTro(id, ngayThue, tienPhong, tienNuoc, tienDien, soPhong, null);
        }
        sqLiteDatabase.close();
        return phongTro;
    }

    public void deletePhongTro(int soPhong) {
        sqLiteDatabase = getWritableDatabase();
        sqLiteDatabase.delete(DB_TABLE_PT, "soPhong=?", new String[]{soPhong + ""});
        sqLiteDatabase.close();
    }

    public void updatePhongTro(PhongTro phongTro) {
        sqLiteDatabase = getWritableDatabase();
        contentValues = new ContentValues();
        contentValues.put("soPhong", phongTro.getSoPhong());
        contentValues.put("tienPhong", phongTro.getTienPhong());
        contentValues.put("tienDien", phongTro.getTienDien());
        contentValues.put("tienNuoc", phongTro.getTienNuoc());
        contentValues.put("ngayThue", phongTro.getNgayThue());
        contentValues.put("sdtTK", phongTro.getTaiKhoan() == null ? "" : phongTro.getTaiKhoan());
        contentValues.put("tinhTrang", phongTro.getTinhTrang());
        sqLiteDatabase.update(DB_TABLE_PT, contentValues, "soPhong=?", new String[]{phongTro.getSoPhong() + ""});
        sqLiteDatabase.close();
    }

    public void insertPhongTro(PhongTro phongTro) {
        sqLiteDatabase = this.getWritableDatabase();
        contentValues = new ContentValues();
        contentValues.put("ngayThue", phongTro.getNgayThue());
        contentValues.put("tienPhong", phongTro.getTienPhong());
        contentValues.put("tienNuoc", phongTro.getTienNuoc());
        contentValues.put("tienDien", phongTro.getTienDien());
        contentValues.put("soPhong", phongTro.getSoPhong());
        contentValues.put("sdtTK", phongTro.getTaiKhoan());
        contentValues.put("tinhTrang", 0);
        sqLiteDatabase.insert(DB_TABLE_PT, null, contentValues);
        sqLiteDatabase.close();
    }

    public void insertYeuCau(YeuCau yeuCau) {
        sqLiteDatabase = this.getWritableDatabase();
        contentValues = new ContentValues();
        contentValues.put("sdtYC", yeuCau.getSdtYC());
        contentValues.put("soPhong", yeuCau.getSoPhong());
        sqLiteDatabase.insert(DB_TABLE_YC, null, contentValues);
        sqLiteDatabase.close();
    }

    public void deleteYeuCau(YeuCau yeuCau) {
        sqLiteDatabase = getWritableDatabase();
        sqLiteDatabase.delete(DB_TABLE_YC, "sdtYC=?", new String[]{yeuCau.getSdtYC() + ""});
        sqLiteDatabase.close();
    }

    public List<YeuCau> getAllYeuCauBySDT(String sdtX) {
        List<YeuCau> list = new ArrayList<>();
        sqLiteDatabase = getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM " + DB_TABLE_YC + " WHERE sdtYC=?", new String[]{sdtX + ""});
        while (cursor.moveToNext()) {
            @SuppressLint("Range") int id = cursor.getInt(cursor.getColumnIndex("id"));
            @SuppressLint("Range") String sdtYC = cursor.getString(cursor.getColumnIndex("sdtYC"));
            @SuppressLint("Range") int soPhong = cursor.getInt(cursor.getColumnIndex("soPhong"));

            list.add(new YeuCau(sdtYC, soPhong));
        }
        sqLiteDatabase.close();
        return list;
    }

    public List<YeuCau> getAllYeuCauBySoPhong(int soPhongX) {
        List<YeuCau> list = new ArrayList<>();
        sqLiteDatabase = getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM " + DB_TABLE_YC + " WHERE soPhong=?", new String[]{soPhongX + ""});
        while (cursor.moveToNext()) {
            @SuppressLint("Range") int id = cursor.getInt(cursor.getColumnIndex("id"));
            @SuppressLint("Range") String sdtYC = cursor.getString(cursor.getColumnIndex("sdtYC"));
            @SuppressLint("Range") int soPhong = cursor.getInt(cursor.getColumnIndex("soPhong"));

            list.add(new YeuCau(sdtYC, soPhong));
        }
        sqLiteDatabase.close();
        return list;
    }

// ngayThue TEXT, tienPhong TEXT, tienNuoc TEXT, tienDien TEXT, soPhong NUMBER, sdtTK TEXT)";

    //thanh toans
    public void insertThanhToan(ThanhToan thanhToan) {
        sqLiteDatabase = this.getWritableDatabase();
        contentValues = new ContentValues();
        contentValues.put("ngayTT", thanhToan.getNgayThanhToan());
        contentValues.put("sdtTKTT", thanhToan.getSdtTKTT());
        contentValues.put("soPhong", thanhToan.getSoPhong());
        contentValues.put("tongTien", thanhToan.getTongTien());
        contentValues.put("noiDung", thanhToan.getNoiDung());
        contentValues.put("trangThai", thanhToan.getTrangThai());
        sqLiteDatabase.insert(DB_TABLE_TT, null, contentValues);
        sqLiteDatabase.close();
    }

    public void updateThanhToan(ThanhToan thanhToan) {
        sqLiteDatabase = getWritableDatabase();
        contentValues = new ContentValues();
        contentValues.put("ngayTT", thanhToan.getNgayThanhToan());
        contentValues.put("sdtTKTT", thanhToan.getSdtTKTT());
        contentValues.put("soPhong", thanhToan.getSoPhong());
        contentValues.put("tongTien", thanhToan.getTongTien());
        contentValues.put("noiDung", thanhToan.getNoiDung());
        contentValues.put("trangThai", thanhToan.getTrangThai());
        sqLiteDatabase.update(DB_TABLE_TT, contentValues, "soPhong=?", new String[]{thanhToan.getSoPhong() + ""});
        sqLiteDatabase.close();
    }

//    "(id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, " +
//            "ngayTT TEXT, sdtTKTT TEXT, soPhong NUMBER, tongTien TEXT, noiDung TEXT, trangThai NUMBER)";

    public List<ThanhToan> getAllThanhToan(int trangThaiX) {
        List<ThanhToan> list = new ArrayList<>();
        sqLiteDatabase = getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM " + DB_TABLE_TT + " WHERE trangThai=?", new String[]{trangThaiX + ""});
        while (cursor.moveToNext()) {
            @SuppressLint("Range") int id = cursor.getInt(cursor.getColumnIndex("id"));
            @SuppressLint("Range") String ngayTT = cursor.getString(cursor.getColumnIndex("ngayTT"));
            @SuppressLint("Range") String sdt = cursor.getString(cursor.getColumnIndex("sdtTKTT"));
            @SuppressLint("Range") int soPhong = cursor.getInt(cursor.getColumnIndex("soPhong"));
            @SuppressLint("Range") double tien = cursor.getDouble(cursor.getColumnIndex("tongTien"));
            @SuppressLint("Range") String noiDung = cursor.getString(cursor.getColumnIndex("noiDung"));
            @SuppressLint("Range") int trangThai = cursor.getInt(cursor.getColumnIndex("trangThai"));

            list.add(new ThanhToan(id, ngayTT, sdt, soPhong, tien, noiDung, trangThai));
        }
        sqLiteDatabase.close();
        return list;
    }

    public ThanhToan getAllThanhToanTheoSoPhongVaTienCoc(int soPhongX, String tienCoc) {
        ThanhToan thanhToan = null;
        sqLiteDatabase = getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM " + DB_TABLE_TT+" WHERE soPhong=? AND noiDung = ?", new String[]{soPhongX+"", tienCoc});
        while (cursor.moveToNext()) {
            @SuppressLint("Range") int id = cursor.getInt(cursor.getColumnIndex("id"));
            @SuppressLint("Range") String ngayTT = cursor.getString(cursor.getColumnIndex("ngayTT"));
            @SuppressLint("Range") String sdt = cursor.getString(cursor.getColumnIndex("sdtTKTT"));
            @SuppressLint("Range") int soPhong = cursor.getInt(cursor.getColumnIndex("soPhong"));
            @SuppressLint("Range") double tien = cursor.getDouble(cursor.getColumnIndex("tongTien"));
            @SuppressLint("Range") String noiDung = cursor.getString(cursor.getColumnIndex("noiDung"));
            @SuppressLint("Range") int trangThai = cursor.getInt(cursor.getColumnIndex("trangThai"));

            thanhToan = new ThanhToan(id, ngayTT, sdt, soPhong, tien, noiDung, trangThai);
        }
        sqLiteDatabase.close();
        return thanhToan;
    }

    public List<ThanhToan> getAllThanhToanTheoSoPhong(int soPhongX) {
        List<ThanhToan> list = new ArrayList<>();
        sqLiteDatabase = getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM " + DB_TABLE_TT+" WHERE soPhong=? AND trangThai = 1", new String[]{soPhongX+""});
        while (cursor.moveToNext()) {
            @SuppressLint("Range") int id = cursor.getInt(cursor.getColumnIndex("id"));
            @SuppressLint("Range") String ngayTT = cursor.getString(cursor.getColumnIndex("ngayTT"));
            @SuppressLint("Range") String sdt = cursor.getString(cursor.getColumnIndex("sdtTKTT"));
            @SuppressLint("Range") int soPhong = cursor.getInt(cursor.getColumnIndex("soPhong"));
            @SuppressLint("Range") double tien = cursor.getDouble(cursor.getColumnIndex("tongTien"));
            @SuppressLint("Range") String noiDung = cursor.getString(cursor.getColumnIndex("noiDung"));
            @SuppressLint("Range") int trangThai = cursor.getInt(cursor.getColumnIndex("trangThai"));

            list.add(new ThanhToan(id, ngayTT, sdt, soPhong, tien, noiDung, trangThai));
        }
        sqLiteDatabase.close();
        return list;
    }

    public List<ThanhToan> getAllThanhToanTheoSDT(String sdt, int trangThaiX) {
        List<ThanhToan> list = new ArrayList<>();
        sqLiteDatabase = getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM " + DB_TABLE_TT+" WHERE sdtTKTT=? and trangThai=?", new String[]{sdt+"", trangThaiX +""});
        while (cursor.moveToNext()) {
            @SuppressLint("Range") int id = cursor.getInt(cursor.getColumnIndex("id"));
            @SuppressLint("Range") String ngayTT = cursor.getString(cursor.getColumnIndex("ngayTT"));
            @SuppressLint("Range") int soPhong = cursor.getInt(cursor.getColumnIndex("soPhong"));
            @SuppressLint("Range") double tien = cursor.getDouble(cursor.getColumnIndex("tongTien"));
            @SuppressLint("Range") String noiDung = cursor.getString(cursor.getColumnIndex("noiDung"));
            @SuppressLint("Range") int trangThai = cursor.getInt(cursor.getColumnIndex("trangThai"));

            list.add(new ThanhToan(id, ngayTT, sdt, soPhong, tien, noiDung, trangThai));
        }
        sqLiteDatabase.close();
        return list;
    }

    public List<ThanhToan> getAllThanhToanTheoTrangThai(int trangThaiX) {
        List<ThanhToan> list = new ArrayList<>();
        sqLiteDatabase = getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM " + DB_TABLE_TT+" WHERE trangThai=?", new String[]{trangThaiX+""});
        while (cursor.moveToNext()) {
            @SuppressLint("Range") int id = cursor.getInt(cursor.getColumnIndex("id"));
            @SuppressLint("Range") String ngayTT = cursor.getString(cursor.getColumnIndex("ngayTT"));
            @SuppressLint("Range") String sdt = cursor.getString(cursor.getColumnIndex("sdtTKTT"));
            @SuppressLint("Range") int soPhong = cursor.getInt(cursor.getColumnIndex("soPhong"));
            @SuppressLint("Range") double tien = cursor.getDouble(cursor.getColumnIndex("tongTien"));
            @SuppressLint("Range") String noiDung = cursor.getString(cursor.getColumnIndex("noiDung"));
            @SuppressLint("Range") int trangThai = cursor.getInt(cursor.getColumnIndex("trangThai"));

            list.add(new ThanhToan(id, ngayTT, sdt, soPhong, tien, noiDung, trangThai));
        }
        sqLiteDatabase.close();
        return list;
    }

    public List<ThanhToan> getAllThanhToanTheoThang(String thang) {
        List<ThanhToan> list = new ArrayList<>();
        sqLiteDatabase = getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT *, strftime('%m',ngayTT) AS thang FROM " + DB_TABLE_TT + " WHERE thang = ? AND trangThai = 1" , new String[]{thang + ""});
        while (cursor.moveToNext()) {
            @SuppressLint("Range") int id = cursor.getInt(cursor.getColumnIndex("id"));
            @SuppressLint("Range") String ngayTT = cursor.getString(cursor.getColumnIndex("ngayTT"));
            @SuppressLint("Range") String sdt = cursor.getString(cursor.getColumnIndex("sdtTKTT"));
            @SuppressLint("Range") int soPhong = cursor.getInt(cursor.getColumnIndex("soPhong"));
            @SuppressLint("Range") double tien = cursor.getDouble(cursor.getColumnIndex("tongTien"));
            @SuppressLint("Range") String noiDung = cursor.getString(cursor.getColumnIndex("noiDung"));
            @SuppressLint("Range") int trangThai = cursor.getInt(cursor.getColumnIndex("trangThai"));

            list.add(new ThanhToan(id, ngayTT, sdt, soPhong, tien, noiDung, trangThai));
        }
        sqLiteDatabase.close();
        return list;
    }

    //  private String ngayTT;
    //    private String sdtTKTT;
    //    private int soPhong;
    //    private double tongTien;
    //"ngayTT TEXT, sdtTKTT TEXT, soPhong NUMBER, tongTien TEXT)";
}
