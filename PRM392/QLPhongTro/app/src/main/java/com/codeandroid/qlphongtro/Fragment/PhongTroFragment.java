package com.codeandroid.qlphongtro.Fragment;

import static com.codeandroid.qlphongtro.LoginActivity.sqlHelper;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.codeandroid.qlphongtro.Fragment.HandlePhongTro.IOnClickPhongTro;
import com.codeandroid.qlphongtro.Fragment.HandlePhongTro.PhongTroAdapter;
import com.codeandroid.qlphongtro.Fragment.HandleYeuCau.IOnClickYeuCau;
import com.codeandroid.qlphongtro.Fragment.HandleYeuCau.YeuCauAdapter;
import com.codeandroid.qlphongtro.Model.PhongTro;
import com.codeandroid.qlphongtro.Model.YeuCau;
import com.codeandroid.qlphongtro.R;
import com.codeandroid.qlphongtro.databinding.FragmentPhongTroBinding;

import java.util.ArrayList;
import java.util.List;


public class PhongTroFragment extends Fragment {

    FragmentPhongTroBinding binding;
    PhongTroAdapter phongTroAdapter;
    List<PhongTro> phongTroList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_phong_tro, container, false);
        phongTroList = sqlHelper.getAllPhongTro();
        phongTroAdapter = new PhongTroAdapter(phongTroList, getContext());
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        binding.revPhongTro.setLayoutManager(layoutManager);
        binding.revPhongTro.setAdapter(phongTroAdapter);
        phongTroAdapter.setiOnClickPhongTro(new IOnClickPhongTro() {
            @Override
            public void iOnClickSua(PhongTro phongTro, int poss) {
                final Dialog dialog = new Dialog(getContext());
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.layout_update_phong_tro);

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

                EditText edtTD = dialog.findViewById(R.id.edtTD);
                EditText edtSP = dialog.findViewById(R.id.edtSp);
                EditText edtTP = dialog.findViewById(R.id.edtTP);
                EditText edtTN = dialog.findViewById(R.id.edtTN);
                EditText edtSDT = dialog.findViewById(R.id.edtSDT);
                EditText edtNT = dialog.findViewById(R.id.edtNt);
                Button btnSua = dialog.findViewById(R.id.btnSua);

                edtTD.setText(phongTro.getTienDien() + "");
                edtSP.setText(phongTro.getSoPhong() + "");
                edtTN.setText(phongTro.getTienNuoc() + "");
                edtTP.setText(phongTro.getTienPhong() + "");
                edtSDT.setText(phongTro.getTaiKhoan() + "");
                edtNT.setText(phongTro.getNgayThue() + "");

                btnSua.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String ngayThue = edtNT.getText().toString().trim();
                        int soPhong = Integer.parseInt(edtSP.getText().toString().trim());
                        double tienPhong = Double.parseDouble(edtTP.getText().toString().trim());
                        double tienDien = Double.parseDouble(edtTD.getText().toString().trim());
                        double tienNuoc = Double.parseDouble(edtTN.getText().toString().trim());
                        String sdt = edtSDT.getText().toString().trim();
                        PhongTro phongTro1 = new PhongTro(0, ngayThue, tienPhong, tienNuoc, tienDien, soPhong, sdt);
                        sqlHelper.updatePhongTro(phongTro1);
                        phongTroList.get(poss).setTienPhong(tienPhong);
                        phongTroList.get(poss).setTienDien(tienDien);
                        phongTroList.get(poss).setTienNuoc(tienNuoc);
                        phongTroList.get(poss).setNgayThue(ngayThue);
                        phongTroList.get(poss).setSoPhong(soPhong);
                        phongTroList.get(poss).setTaiKhoan(sdt);
                        phongTroAdapter.setList(phongTroList);
                        dialog.cancel();
                        Toast.makeText(getContext(), "Sửa thành công", Toast.LENGTH_SHORT).show();
                    }
                });

                dialog.show();
            }

            @Override
            public void iOnClickXoa(PhongTro phongTro, int poss) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle("Xóa phòng trọ");
                builder.setMessage("Bạn có chắc muốn xóa phòng trọ");
                builder.setPositiveButton("XÓA", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        sqlHelper.deletePhongTro(phongTro.getSoPhong());
                        phongTroList.remove(poss);
                        phongTroAdapter.setList(phongTroList);
                        phongTroAdapter.notifyDataSetChanged();
                        binding.revPhongTro.setAdapter(phongTroAdapter);
                        dialogInterface.cancel();
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

            @Override
            public void iOnClickYeuCau(int soPhong) {
                final Dialog dialog = new Dialog(getContext());
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.layout_list_yeu_cau);

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
                List<YeuCau> yeuCaus = sqlHelper.getAllYeuCauBySoPhong(soPhong);
                RecyclerView rcv_listYC = dialog.findViewById(R.id.rcv_listYC);
                TextView tvDong = dialog.findViewById(R.id.tvDong);
                tvDong.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.cancel();
                        binding.revPhongTro.setAdapter(phongTroAdapter);
                    }
                });

                RecyclerView.LayoutManager manager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
                rcv_listYC.setLayoutManager(manager);
                YeuCauAdapter yeuCauAdapter = new YeuCauAdapter(yeuCaus,getContext());
                rcv_listYC.setAdapter(yeuCauAdapter);
                yeuCauAdapter.setiOnClickYeuCau(new IOnClickYeuCau() {
                    @Override
                    public void iOnClickYeuCau(YeuCau yeuCau, int position) {
                        PhongTro phongTro = sqlHelper.getPhongTroBySoPhong(yeuCau.getSoPhong());
                        phongTro.setTaiKhoan(yeuCau.getSdtYC());
                        phongTro.setTinhTrang(0);
                        List<YeuCau> yeuCaus = sqlHelper.getAllYeuCauBySDT(yeuCau.getSdtYC());
                        for (int i = 0 ; i < yeuCaus.size() ; i++) {
                            PhongTro phongTroX = sqlHelper.getPhongTroBySoPhong(yeuCaus.get(i).getSoPhong());
                            phongTro.setTinhTrang(0);
                            sqlHelper.updatePhongTro(phongTroX);
                        }
                        sqlHelper.updatePhongTro(phongTro);
                        sqlHelper.deleteYeuCau(yeuCau);
                        dialog.cancel();
                        phongTroList = sqlHelper.getAllPhongTro();
                        phongTroAdapter.setList(phongTroList);
                        phongTroAdapter.notifyDataSetChanged();
                        binding.revPhongTro.setAdapter(phongTroAdapter);
                        Toast.makeText(getContext(), "Cho thuê phòng thành công", Toast.LENGTH_SHORT).show();
                    }
                });
                dialog.show();
                binding.revPhongTro.setAdapter(phongTroAdapter);
            }
        });

        binding.btnThemPhongTro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Dialog dialog = new Dialog(getContext());
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.layout_update_phong_tro);

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

                EditText edtTD = dialog.findViewById(R.id.edtTD);
                EditText edtSP = dialog.findViewById(R.id.edtSp);
                EditText edtTP = dialog.findViewById(R.id.edtTP);
                EditText edtTN = dialog.findViewById(R.id.edtTN);
                EditText edtSDT = dialog.findViewById(R.id.edtSDT);
                EditText edtNT = dialog.findViewById(R.id.edtNt);
                Button btnSua = dialog.findViewById(R.id.btnSua);
                btnSua.setText("Thêm");

                btnSua.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String ngayThue= edtNT.getText().toString().trim();
                        int soPhong= Integer.parseInt(edtSP.getText().toString().trim());
                        double tienPhong= Double.parseDouble(edtTP.getText().toString().trim());
                        double tienDien= Double.parseDouble(edtTD.getText().toString().trim());
                        double tienNuoc= Double.parseDouble(edtTN.getText().toString().trim());
                        String sdt= edtSDT.getText().toString().trim();
                        PhongTro phongTro= new PhongTro(0, ngayThue, tienPhong, tienNuoc, tienDien, soPhong, sdt);
                        sqlHelper.insertPhongTro(phongTro);
                        phongTroList.add(phongTro);
                        phongTroAdapter.setList(phongTroList);
                        dialog.cancel();
                    }
                });

                dialog.show();
            }
        });


        return binding.getRoot();
    }
}