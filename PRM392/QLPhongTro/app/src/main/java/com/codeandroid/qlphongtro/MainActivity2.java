package com.codeandroid.qlphongtro;

import static com.codeandroid.qlphongtro.LoginActivity.currentUser;
import static com.codeandroid.qlphongtro.LoginActivity.sqlHelper;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.transition.TransitionManager;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.codeandroid.qlphongtro.Model.PhongTro;
import com.codeandroid.qlphongtro.Model.TaiKhoan;
import com.codeandroid.qlphongtro.Model.TaiKhoanNH;
import com.codeandroid.qlphongtro.Model.ThanhToan;
import com.codeandroid.qlphongtro.Model.YeuCau;
import com.codeandroid.qlphongtro.NguoiThueHandle.ChuTroAdapter;
import com.codeandroid.qlphongtro.NguoiThueHandle.IOnClickDatPhong;
import com.codeandroid.qlphongtro.NguoiThueHandle.PhongTroConTrongAdapter;
import com.codeandroid.qlphongtro.NguoiThueHandle.ThanhToanAdapter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class MainActivity2 extends AppCompatActivity {
    TextView tvContactADMIN, tvLichSuTT, tvFullName, tvUsername, tvPhone, tvNgaySinh, tvQueQuan;
    TextView tvSoPhong, tvNgayThue, tvTienPhong, tvTienDien, tvTienNuoc, tvThanhToan, tvThongBao, tvThanhToanCoc;
    RecyclerView revCacPhongConTrong;

    EditText edtTimKiem;

    PhongTro phongTro;
    List<PhongTro> phongTroListConTrong;

    PhongTro phongTroBySDT;

    PhongTroConTrongAdapter troConTrongAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        ActionBar actionBar= getSupportActionBar();
        actionBar.hide();
        anhXa();
        tvFullName.setText(currentUser.getFullname());
        tvUsername.setText(currentUser.getUsername());
        tvPhone.setText(currentUser.getPhone());
        tvNgaySinh.setText(currentUser.getDob());
        tvQueQuan.setText(currentUser.getAddress());

        phongTro= sqlHelper.getPhongTroBySDT(currentUser.getPhone());
        if (phongTro!=null){
            tvSoPhong.setText("Phòng: "+phongTro.getSoPhong()+"");
            tvNgayThue.setText("Ngày thuê: "+ phongTro.getNgayThue());
            tvTienPhong.setText("Tiền phòng: "+phongTro.getTienPhong()+"");
            tvTienDien.setText("Tiền điện: "+phongTro.getTienDien()+"");
            tvTienNuoc.setText("Tiền nước: "+phongTro.getTienNuoc()+"");
        }
        if(phongTro != null) {
            if(phongTro.getTinhTrang() != 0) {
                tvThanhToanCoc.setVisibility(View.GONE);
            }

            else {
                tvThanhToanCoc.setVisibility(View.VISIBLE);
                tvThanhToanCoc.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        double t= phongTro.getTienPhong();
                        String ngay= new SimpleDateFormat("yyyy-MM-dd",
                                Locale.getDefault()).format(new Date());
//                        ThanhToan thanhToan= new ThanhToan(0, ngay, currentUser.getPhone(), phongTro.getSoPhong(), t, 0);
//                        sqlHelper.insertThanhToan(thanhToan);

                        final Dialog dialog = new Dialog(MainActivity2.this);
                        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                        dialog.setContentView(R.layout.dialog_xac_nhan_thanh_toan);

                        Window window = dialog.getWindow();
                        if (window == null) {
                            return;
                        }
                        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
                        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

                        WindowManager.LayoutParams windowAtributes = window.getAttributes();
                        windowAtributes.gravity = Gravity.CENTER_VERTICAL;
                        window.setAttributes(windowAtributes);

                        dialog.setCancelable(true);

                        TextView tvSoTK = dialog.findViewById(R.id.tvSoTK);
                        TextView tvTenTK = dialog.findViewById(R.id.tvTenTK);
                        TextView tvTenNH = dialog.findViewById(R.id.tvTenNH);

                        List<TaiKhoanNH> taiKhoanNHS = sqlHelper.getTKNH();
                        tvSoTK.setText("Số tài khoản: " + taiKhoanNHS.get(0).getSoTK());
                        tvTenNH.setText("Tên ngân hàng: " + taiKhoanNHS.get(0).getTenNH());
                        tvTenTK.setText("Tên chủ tài khoản: " + taiKhoanNHS.get(0).getTenTK());

                        TextView tvChuyenTien = dialog.findViewById(R.id.tvChuyenTien);
                        tvChuyenTien.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                phongTro.setTinhTrang(1);
                                sqlHelper.updatePhongTro(phongTro);
                                tvThanhToanCoc.setVisibility(View.GONE);
                                ThanhToan thanhToan1 = new ThanhToan(0, ngay, currentUser.getPhone(), phongTro.getSoPhong(), phongTro.getTienPhong(), "Tiền cọc",  0);
                                sqlHelper.insertThanhToan(thanhToan1);
                                Toast.makeText(MainActivity2.this, "Đã chuyển, xin chờ chủ trọ xác nhận", Toast.LENGTH_SHORT).show();
                                dialog.cancel();
                            }
                        });
                        dialog.show();
                    }
                });
            }
        }
        else
            tvThanhToanCoc.setVisibility(View.GONE);

        phongTroListConTrong= sqlHelper.getAllPhongTroConTrong();
        if(phongTroListConTrong.size() == 0) {
            revCacPhongConTrong.setVisibility(View.GONE);
            tvThongBao.setText("Không còn phòng trống");
//            edtTimKiem.setVisibility(View.GONE);
        } else {
            revCacPhongConTrong.setVisibility(View.VISIBLE);
            tvThongBao.setText("Các phòng hiện có");
        }
        troConTrongAdapter= new PhongTroConTrongAdapter(phongTroListConTrong, MainActivity2.this);
        RecyclerView.LayoutManager layoutManager= new LinearLayoutManager(MainActivity2.this, RecyclerView.VERTICAL, false);
        revCacPhongConTrong.setLayoutManager(layoutManager);
        revCacPhongConTrong.setAdapter(troConTrongAdapter);

        revCacPhongConTrong.setOnFlingListener(new RecyclerView.OnFlingListener() {
            @Override
            public boolean onFling(int velocityX, int velocityY) {
                return false;
            }
        });
        edtTimKiem.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                List<PhongTro> troTrongs = sqlHelper.getAllPhongTroConTrong();
                List<PhongTro> list = new ArrayList<>();
                double a = 0;
                if(editable.length() != 0)
                    a = Double.parseDouble(editable.toString());
                for (int i = 0 ; i < troTrongs.size() ; i++)
                    if(troTrongs.get(i).getTienPhong() <= a || editable.length() == 0)
                        list.add(troTrongs.get(i));
                troConTrongAdapter = new PhongTroConTrongAdapter(list, MainActivity2.this);
                RecyclerView.LayoutManager layoutManager= new LinearLayoutManager(MainActivity2.this, RecyclerView.VERTICAL, false);
                revCacPhongConTrong.setLayoutManager(layoutManager);
                revCacPhongConTrong.setAdapter(troConTrongAdapter);
                onClickThueTro();
            }
        });

        troConTrongAdapter.setiOnClickDatPhong(new IOnClickDatPhong() {
            @Override
            public void iOnClickDatPhong(int poss) {
                    phongTroListConTrong.get(poss).getSoPhong();
                    PhongTro phongTroTG = new PhongTro(0, phongTroListConTrong.get(poss).getNgayThue(),
                            phongTroListConTrong.get(poss).getTienPhong(),
                            phongTroListConTrong.get(poss).getTienNuoc(),
                            phongTroListConTrong.get(poss).getTienDien(),
                            phongTroListConTrong.get(poss).getSoPhong(),
                            phongTroListConTrong.get(poss).getTaiKhoan(),
                            1);
                    YeuCau yeuCau = new YeuCau(tvPhone.getText().toString(), phongTroListConTrong.get(poss).getSoPhong());
                    sqlHelper.insertYeuCau(yeuCau);
                    sqlHelper.updatePhongTro(phongTroTG);
            }

            @Override
            public void iOnClickHuyDatPhong(int poss) {
                phongTroListConTrong.get(poss).getSoPhong();
                PhongTro phongTroTG = new PhongTro(0, phongTroListConTrong.get(poss).getNgayThue(),
                        phongTroListConTrong.get(poss).getTienPhong(),
                        phongTroListConTrong.get(poss).getTienNuoc(),
                        phongTroListConTrong.get(poss).getTienDien(),
                        phongTroListConTrong.get(poss).getSoPhong(),
                        phongTroListConTrong.get(poss).getTaiKhoan(),
                        0);
                YeuCau yeuCau = new YeuCau(tvPhone.getText().toString(), phongTroListConTrong.get(poss).getSoPhong());
                sqlHelper.deleteYeuCau(yeuCau);
                sqlHelper.updatePhongTro(phongTroTG);
            }
        });

        tvContactADMIN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Dialog dialog = new Dialog(MainActivity2.this);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.layout_admin_infor);

                Window window = dialog.getWindow();
                if (window == null) {
                    return;
                }
                window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
                window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

                WindowManager.LayoutParams windowAtributes = window.getAttributes();
                windowAtributes.gravity = Gravity.CENTER_VERTICAL;
                window.setAttributes(windowAtributes);

                dialog.setCancelable(true);

                RecyclerView revChuTro= dialog.findViewById(R.id.revChuTro);
                List<TaiKhoan> list= sqlHelper.getAllTaiKhoanChuTro();
                ChuTroAdapter adapter= new ChuTroAdapter(list, MainActivity2.this);
                RecyclerView.LayoutManager layoutManager1= new LinearLayoutManager(MainActivity2.this, RecyclerView.VERTICAL, false);
                revChuTro.setLayoutManager(layoutManager1);
                revChuTro.setAdapter(adapter);

                dialog.show();
            }
        });
        tvThanhToan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (phongTro==null){
                    Toast.makeText(MainActivity2.this, "Bạn chưa thuê phòng trọ", Toast.LENGTH_SHORT).show();
                }else {
                    double t= phongTro.getTienPhong()+ phongTro.getTienNuoc()+ phongTro.getTienDien();
                    String ngay= new SimpleDateFormat("yyyy-MM-dd",
                            Locale.getDefault()).format(new Date());
                    String thang= new SimpleDateFormat("MM",
                            Locale.getDefault()).format(new Date());
                    AlertDialog.Builder builder= new AlertDialog.Builder(MainActivity2.this);
                    builder.setTitle("Thanh toán: "+ t+" VNĐ");
                    builder.setMessage("Đồng ý  thành toán?");
                    builder.setPositiveButton("Thanh Toán", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.cancel();
                            ThanhToan thanhToan= new ThanhToan(0, ngay, currentUser.getPhone(), phongTro.getSoPhong(), t, "Tiền trọ tháng " + thang, 1);
                            sqlHelper.insertThanhToan(thanhToan);
                            Toast.makeText(MainActivity2.this, "Thanh toán thành công", Toast.LENGTH_SHORT).show();
                        }
                    });
                    builder.setNegativeButton("HỦY", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.cancel();
                        }
                    });
                    builder.show();
                }
            }
        });

        tvLichSuTT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Dialog dialog = new Dialog(MainActivity2.this);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.layout_lich_su_tt);

                Window window = dialog.getWindow();
                if (window == null) {
                    return;
                }
                window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
                window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

                WindowManager.LayoutParams windowAtributes = window.getAttributes();
                windowAtributes.gravity = Gravity.CENTER_VERTICAL;
                window.setAttributes(windowAtributes);

                dialog.setCancelable(true);

                RecyclerView revLichSuTT= dialog.findViewById(R.id.revLichSuTT);

                List<ThanhToan> list= sqlHelper.getAllThanhToanTheoSDT(currentUser.getPhone(), 1);
                if (list.size()==0){
                    Toast.makeText(MainActivity2.this, "Lịch sử trống", Toast.LENGTH_SHORT).show();
                }else {
                    ThanhToanAdapter adapter= new ThanhToanAdapter(list, MainActivity2.this);
                    RecyclerView.LayoutManager layoutManager1= new LinearLayoutManager(MainActivity2.this, RecyclerView.VERTICAL, false);
                    revLichSuTT.setLayoutManager(layoutManager1);
                    revLichSuTT.setAdapter(adapter);
                }
                dialog.show();
            }
        });

    }

    private void anhXa() {
        tvContactADMIN= findViewById(R.id.tvContactAdmin);
        tvLichSuTT= findViewById(R.id.tvLichSuTT);
        tvFullName= findViewById(R.id.tvFullName);
        tvUsername= findViewById(R.id.tvUsername);
        tvPhone= findViewById(R.id.tvPhone);
        tvNgaySinh= findViewById(R.id.tvNSinh);
        tvQueQuan= findViewById(R.id.tvQue);
        tvSoPhong= findViewById(R.id.tvSoPhong);
        tvNgayThue= findViewById(R.id.tvNgayThue);
        tvTienPhong= findViewById(R.id.tvTienPhong);
        tvTienDien= findViewById(R.id.tvTienDien);
        tvTienNuoc= findViewById(R.id.tvTienNuoc);
        tvThanhToan= findViewById(R.id.tvThanhToan);
        tvThongBao = findViewById(R.id.tvThongBao);
        edtTimKiem = findViewById(R.id.edtTimKiem);
        tvThanhToanCoc = findViewById(R.id.tvThanhToanCoc);

        revCacPhongConTrong= findViewById(R.id.revCacPhongConTrong);

    }
    public void onClickThueTro() {
        troConTrongAdapter.setiOnClickDatPhong(new IOnClickDatPhong() {
            @Override
            public void iOnClickDatPhong(int poss) {
                phongTroListConTrong.get(poss).getSoPhong();
                PhongTro phongTroTG = new PhongTro(0, phongTroListConTrong.get(poss).getNgayThue(),
                        phongTroListConTrong.get(poss).getTienPhong(),
                        phongTroListConTrong.get(poss).getTienNuoc(),
                        phongTroListConTrong.get(poss).getTienDien(),
                        phongTroListConTrong.get(poss).getSoPhong(),
                        phongTroListConTrong.get(poss).getTaiKhoan(),
                        1);
                YeuCau yeuCau = new YeuCau(tvPhone.getText().toString(), phongTroListConTrong.get(poss).getSoPhong());
                sqlHelper.insertYeuCau(yeuCau);
                sqlHelper.updatePhongTro(phongTroTG);
            }

            @Override
            public void iOnClickHuyDatPhong(int poss) {
                phongTroListConTrong.get(poss).getSoPhong();
                PhongTro phongTroTG = new PhongTro(0, phongTroListConTrong.get(poss).getNgayThue(),
                        phongTroListConTrong.get(poss).getTienPhong(),
                        phongTroListConTrong.get(poss).getTienNuoc(),
                        phongTroListConTrong.get(poss).getTienDien(),
                        phongTroListConTrong.get(poss).getSoPhong(),
                        phongTroListConTrong.get(poss).getTaiKhoan(),
                        0);
                YeuCau yeuCau = new YeuCau(tvPhone.getText().toString(), phongTroListConTrong.get(poss).getSoPhong());
                sqlHelper.deleteYeuCau(yeuCau);
                sqlHelper.updatePhongTro(phongTroTG);
            }
        });
    }
}