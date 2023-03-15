package com.codeandroid.qlphongtro;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.codeandroid.qlphongtro.Model.TaiKhoan;
import com.codeandroid.qlphongtro.Model.TaiKhoanNH;
import com.codeandroid.qlphongtro.SQLHandle.SQLHelper;

import java.util.List;

public class LoginActivity extends AppCompatActivity {
    private static final String SHARE_PRE_NAME = "ACC";
    EditText edtUsername, edtPassword, edtRepassword, edtFullName, edtPhone, edtAddress, edtNgaySinh;
    TextView tvError, tvDK;
    Button btnDN, btnDK;
    RadioGroup rgGroup;
    RadioButton rbNguoiThue, rbChuTro;
    CheckBox btnSavepassword;
    String role= "USER";
    boolean isDK= true;
    public static SQLHelper sqlHelper;
    public static TaiKhoan currentUser=null;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        anhXa();
        ActionBar actionBar= getSupportActionBar();
        actionBar.hide();
        btnSavepassword.setChecked(true);
        sharedPreferences= getSharedPreferences(SHARE_PRE_NAME, MODE_PRIVATE);
        edtUsername.setText(sharedPreferences.getString("username", ""));
        edtPassword.setText(sharedPreferences.getString("password", ""));

        sqlHelper= new SQLHelper(LoginActivity.this);

        tvDK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isDK){
                    edtUsername.setText("");
                    edtPassword.setText("");
                    edtFullName.setVisibility(View.VISIBLE);
                    edtRepassword.setVisibility(View.VISIBLE);
                    edtPhone.setVisibility(View.VISIBLE);
                    edtAddress.setVisibility(View.VISIBLE);
                    edtNgaySinh.setVisibility(View.VISIBLE);
                    btnDK.setVisibility(View.VISIBLE);
                    rgGroup.setVisibility(View.VISIBLE);
                    btnDN.setVisibility(View.GONE);
                    tvError.setText("");
                    tvDK.setText("Quay về giao diện đăng nhập");
                    isDK=false;
                }else {
                    edtFullName.setVisibility(View.GONE);
                    edtRepassword.setVisibility(View.GONE);
                    edtPhone.setVisibility(View.GONE);
                    edtAddress.setVisibility(View.GONE);
                    edtNgaySinh.setVisibility(View.GONE);
                    btnDK.setVisibility(View.GONE);
                    rgGroup.setVisibility(View.GONE);
                    btnDN.setVisibility(View.VISIBLE);
                    tvDK.setText("Đăng ký tài khoản");
                    isDK= true;
                }
            }
        });

        btnDK.setOnClickListener(new View.OnClickListener() {
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
                    if (rbChuTro.isChecked()){
                        role= "ADMIN";
                    }else {
                        role= "USER";
                    }
                    TaiKhoan taiKhoan= new TaiKhoan(0, username, password, fullname, phone, address, ngaySinh, role);
                    if (sqlHelper.insertUser(taiKhoan)) {
                        if(role == "ADMIN") {
                            final Dialog dialog = new Dialog(LoginActivity.this);
                            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                            dialog.setContentView(R.layout.dialog_thong_tin_the);

                            Window window = dialog.getWindow();
                            if (window == null) {
                                return;
                            }
                            window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
                            window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

                            WindowManager.LayoutParams windowAtributes = window.getAttributes();
                            windowAtributes.gravity = Gravity.CENTER;
                            window.setAttributes(windowAtributes);

                            dialog.setCancelable(true);

                            EditText edtSTK = dialog.findViewById(R.id.edtSTK);
                            EditText edtTenSTK = dialog.findViewById(R.id.edtTenSTK);
                            EditText edtTenNH = dialog.findViewById(R.id.edtTenNH);
                            Button btnXacNhan = dialog.findViewById(R.id.btnXacNhan);

                            btnXacNhan.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    if(edtSTK.getText().length() > 0 && edtTenNH.getText().length() > 0 && edtTenSTK.getText().length() > 0) {
                                        TaiKhoanNH taiKhoanNH = new TaiKhoanNH(edtSTK.getText().toString(), edtTenSTK.getText().toString(), edtTenNH.getText().toString());
                                        List<TaiKhoanNH> list = sqlHelper.getTKNH();
                                        Log.e("TAGss", list.size() + " ");
                                        if(list.size() > 0)
                                            sqlHelper.updateTKNH(taiKhoanNH, list.get(0).getSoTK());
                                        else
                                            sqlHelper.insertTKNH(taiKhoanNH);
                                        Toast.makeText(LoginActivity.this, "Đăng ký thành công. Hãy đăng nhập", Toast.LENGTH_SHORT).show();
                                        edtFullName.setVisibility(View.GONE);
                                        edtRepassword.setVisibility(View.GONE);
                                        edtPhone.setVisibility(View.GONE);
                                        edtAddress.setVisibility(View.GONE);
                                        edtNgaySinh.setVisibility(View.GONE);
                                        btnDK.setVisibility(View.GONE);
                                        rgGroup.setVisibility(View.GONE);
                                        tvDK.setText("Đăng ký tài khoản");
                                        isDK= true;
                                        btnDN.setVisibility(View.VISIBLE);
                                        dialog.cancel();
                                    } else {
                                        Toast.makeText(LoginActivity.this, "Chưa điền đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                            dialog.show();
                        }
                        else {
                            Toast.makeText(LoginActivity.this, "Đăng ký thành công. Hãy đăng nhập", Toast.LENGTH_SHORT).show();
                            edtFullName.setVisibility(View.GONE);
                            edtRepassword.setVisibility(View.GONE);
                            edtPhone.setVisibility(View.GONE);
                            edtAddress.setVisibility(View.GONE);
                            edtNgaySinh.setVisibility(View.GONE);
                            btnDK.setVisibility(View.GONE);
                            rgGroup.setVisibility(View.GONE);
                            tvDK.setText("Đăng ký tài khoản");
                            isDK= true;
                            btnDN.setVisibility(View.VISIBLE);
                        }
                    }else {
                        tvError.setText("Tài khoản đã tồn tại");
                    }

                }
            }
        });

        btnDN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username= edtUsername.getText().toString().trim();
                String password= edtPassword.getText().toString().trim();
                currentUser= sqlHelper.login(username, password);
                if (currentUser==null){
                    tvError.setText("Tên đăng nhập hoặc mật khẩu sai");
                    Toast.makeText(LoginActivity.this, "Sai TK hoặc MK", Toast.LENGTH_SHORT).show();
                }else {
                    if (currentUser.getRole().compareTo("ADMIN")==0){
                        Toast.makeText(LoginActivity.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(LoginActivity.this, MainActivity.class));
                    }else {
                        Toast.makeText(LoginActivity.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(LoginActivity.this, MainActivity2.class));
                    }

                }

                if (btnSavepassword.isChecked()){
                    saveAccount(username, password);
                }
            }
        });





    }

    private void saveAccount(String username, String password) {
        sharedPreferences = getSharedPreferences(SHARE_PRE_NAME,MODE_PRIVATE);
        editor = sharedPreferences.edit();
        editor.putString("username", username);
        editor.putString("password", password);
        editor.commit();
    }

    private void anhXa() {
        edtUsername= findViewById(R.id.edtUser);
        edtPassword= findViewById(R.id.edtPass);
        edtRepassword= findViewById(R.id.edtRePass);
        edtFullName= findViewById(R.id.edtFullName);
        edtPhone= findViewById(R.id.edtPhoneNumber);
        edtAddress= findViewById(R.id.edtAddress);
        edtNgaySinh= findViewById(R.id.edtNgaySinh);

        tvError= findViewById(R.id.tvErr);
        tvDK= findViewById(R.id.tvDK);

        btnDK= findViewById(R.id.btnDK);
        btnDN= findViewById(R.id.btnDN);

        rgGroup= findViewById(R.id.rgGroup);
        rbChuTro= findViewById(R.id.rbChuTro);
        rbNguoiThue= findViewById(R.id.rbNguoiThue);

        btnSavepassword= findViewById(R.id.btnSavepassword);
    }
}