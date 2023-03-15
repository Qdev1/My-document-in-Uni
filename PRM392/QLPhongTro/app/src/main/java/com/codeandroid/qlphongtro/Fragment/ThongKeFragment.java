package com.codeandroid.qlphongtro.Fragment;

import static com.codeandroid.qlphongtro.LoginActivity.sqlHelper;

import android.graphics.Color;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.codeandroid.qlphongtro.Fragment.HandleThongKe.ListThongKeAdapter;
import com.codeandroid.qlphongtro.Fragment.HandleThongKe.ListThongKePhongAdapter;
import com.codeandroid.qlphongtro.Fragment.HandleThongKe.ThongKeAdapter;
import com.codeandroid.qlphongtro.Fragment.HandleThongKe.ThongKePhongAdapter;
import com.codeandroid.qlphongtro.Model.PhongTro;
import com.codeandroid.qlphongtro.Model.ThanhToan;
import com.codeandroid.qlphongtro.R;
import com.codeandroid.qlphongtro.databinding.FragmentThongKeBinding;

import java.util.ArrayList;
import java.util.List;


public class ThongKeFragment extends Fragment {
    FragmentThongKeBinding binding;
    ListThongKeAdapter listThongKeAdapter;
    ListThongKePhongAdapter thongKePhongAdapter;
    List<ThanhToan> listThanhToan;
    List<String> listThang;
    List<PhongTro> listPhong;
    List<Integer> listSoPhong;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding= DataBindingUtil.inflate(inflater, R.layout.fragment_thong_ke, container, false);
        listThanhToan = sqlHelper.getAllThanhToan(1);
        listThang = chiaThang(listThanhToan);
        listThongKeAdapter = new ListThongKeAdapter(listThang, listThanhToan, getContext());
        RecyclerView.LayoutManager layoutManager= new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        binding.revThongKe.setLayoutManager(layoutManager);
        binding.revThongKe.setAdapter(listThongKeAdapter);
        binding.tvCaXom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listThanhToan = sqlHelper.getAllThanhToan(1);
                listThang = chiaThang(listThanhToan);
                listThongKeAdapter = new ListThongKeAdapter(listThang, listThanhToan, getContext());
                binding.revThongKe.setAdapter(listThongKeAdapter);
                binding.tvCaXom.setTextColor(Color.parseColor("#FFFFEB3B"));
                binding.tvPhongTro.setTextColor(Color.parseColor("#FFFFFF"));
            }
        });
        binding.tvPhongTro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listPhong = sqlHelper.getAllPhongTro();
                listSoPhong = chiaPhong(listPhong);
                thongKePhongAdapter = new ListThongKePhongAdapter(listSoPhong, listThanhToan, getContext());
                RecyclerView.LayoutManager layoutManager= new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
                binding.revThongKe.setLayoutManager(layoutManager);
                thongKePhongAdapter.notifyDataSetChanged();
                binding.revThongKe.setAdapter(thongKePhongAdapter);
                binding.tvCaXom.setTextColor(Color.parseColor("#FFFFFF"));
                binding.tvPhongTro.setTextColor(Color.parseColor("#FFFFEB3B"));
            }
        });
        return binding.getRoot();
    }

    public List<String> chiaThang(List<ThanhToan> thanhToans) {
        List<String> listThangs = new ArrayList<>();
        if(thanhToans.size() > 0) {
            String thang = thanhToans.get(0).getNgayThanhToan().trim().split("-")[1];
            listThangs.add(thang);
            for(int i = 1 ; i < thanhToans.size() ; i++ ) {
                String thangtg = thanhToans.get(i).getNgayThanhToan().trim().split("-")[1];
                if(thang.compareTo(thangtg) != 0) {
                    listThangs.add(thangtg);
                    thang = thangtg + "";
                }
            }
        }
        return listThangs;
    }
    public List<Integer> chiaPhong(List<PhongTro> phongTros) {
        List<Integer> list = new ArrayList<>();
        if(phongTros.size() > 0) {
            for(int i = 0 ; i < phongTros.size() ; i++)
                list.add(phongTros.get(i).getSoPhong());
        }
        return list;
    }
}
