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

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.codeandroid.qlphongtro.Fragment.HandleNguoiThue.IOnClickTaiKhoan;
import com.codeandroid.qlphongtro.Fragment.HandleNguoiThue.NguoiThueAdapter;
import com.codeandroid.qlphongtro.Model.TaiKhoan;
import com.codeandroid.qlphongtro.R;
import com.codeandroid.qlphongtro.databinding.FragmentNguoiThueBinding;

import java.util.List;

public class NguoiThueFragment extends Fragment {
    List<TaiKhoan> taiKhoanList;
    FragmentNguoiThueBinding binding;
    NguoiThueAdapter nguoiThueAdapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding= DataBindingUtil.inflate(inflater, R.layout.fragment_nguoi_thue, container, false);
        taiKhoanList= sqlHelper.getAllTaiKhoan();
        nguoiThueAdapter= new NguoiThueAdapter(taiKhoanList, getContext());
        RecyclerView.LayoutManager layoutManager= new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        binding.revNguoiThue.setLayoutManager(layoutManager);
        binding.revNguoiThue.setAdapter(nguoiThueAdapter);

        nguoiThueAdapter.setiOnClickTaiKhoan(new IOnClickTaiKhoan() {
            @Override
            public void iOnClickTaiKhoan(TaiKhoan taiKhoan, int poss) {
                AlertDialog.Builder builder= new AlertDialog.Builder(getContext());
                builder.setTitle("Xác nhận xóa tài khoản");
                builder.setMessage("Bạn có muốn xóa tài khoản này không");
                builder.setPositiveButton("XOÁ", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        taiKhoanList.remove(poss);
                        sqlHelper.deleteTaiKhoan(taiKhoan.getUsername());
                        nguoiThueAdapter.setList(taiKhoanList);
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
        });
        binding.btnThemNguoiThue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Dialog dialog = new Dialog(getContext());
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.activity_login);

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

                EditText edtUsername, edtPassword, edtRepassword, edtFullName, edtPhone, edtAddress, edtNgaySinh;
                TextView tvError, tvDK;
                Button btnDN, btnDK;
                RadioGroup rgGroup;
                RadioButton rbNguoiThue, rbChuTro;
                CheckBox btnSavepassword;
                edtUsername= dialog.findViewById(R.id.edtUser);
                edtPassword= dialog.findViewById(R.id.edtPass);
                edtRepassword= dialog.findViewById(R.id.edtRePass);
                edtFullName= dialog.findViewById(R.id.edtFullName);
                edtPhone= dialog.findViewById(R.id.edtPhoneNumber);
                edtAddress= dialog.findViewById(R.id.edtAddress);
                edtNgaySinh= dialog.findViewById(R.id.edtNgaySinh);

                tvError= dialog.findViewById(R.id.tvErr);
                tvDK= dialog.findViewById(R.id.tvDK);

                btnDK= dialog.findViewById(R.id.btnDK);
                btnDN= dialog.findViewById(R.id.btnDN);

                rgGroup= dialog.findViewById(R.id.rgGroup);
                rbChuTro= dialog.findViewById(R.id.rbChuTro);
                rbNguoiThue= dialog.findViewById(R.id.rbNguoiThue);

                btnSavepassword= dialog.findViewById(R.id.btnSavepassword);

                edtFullName.setVisibility(View.VISIBLE);
                edtRepassword.setVisibility(View.VISIBLE);
                edtPhone.setVisibility(View.VISIBLE);
                edtAddress.setVisibility(View.VISIBLE);
                edtNgaySinh.setVisibility(View.VISIBLE);
                btnDN.setVisibility(View.VISIBLE);
                btnDK.setVisibility(View.GONE);
                rgGroup.setVisibility(View.GONE);
                tvError.setVisibility(View.VISIBLE);
                tvDK.setVisibility(View.GONE);
                btnSavepassword.setVisibility(View.GONE);
                rbNguoiThue.setVisibility(View.GONE);
                rbChuTro.setVisibility(View.GONE);

                btnDN.setText("Thêm");

                btnDN.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String username = edtUsername.getText().toString().trim();
                        String password = edtPassword.getText().toString().trim();
                        String rePassword = edtRepassword.getText().toString().trim();
                        String fullname = edtFullName.getText().toString().trim();
                        String phone = edtPhone.getText().toString().trim();
                        String address = edtAddress.getText().toString().trim();
                        String ngaySinh = edtNgaySinh.getText().toString().trim();
                        if (username.compareTo("")==0||password.compareTo("")==0||rePassword.compareTo("")==0||
                                fullname.compareTo("")==0 || phone.compareTo("")==0||
                                address.compareTo("")==0|| ngaySinh.compareTo("")==0){
                            tvError.setText("Hãy nhập đủ thông tin");
                        }else if (password.compareTo(rePassword)!=0){
                            tvError.setText("Mật khẩu phải trùng khớp");
                        }else {
                            TaiKhoan taiKhoan= new TaiKhoan(0, username, password, fullname, phone, address, ngaySinh, "USER");
                            sqlHelper.insertUser(taiKhoan);
                            taiKhoanList.add(taiKhoan);
                            nguoiThueAdapter.setList(taiKhoanList);
                            Toast.makeText(getContext(), "Thêm tài khoản người thuê thành công", Toast.LENGTH_SHORT).show();
                            dialog.cancel();
                        }
                    }
                });

                dialog.show();
            }
        });

        return binding.getRoot();
    }
}