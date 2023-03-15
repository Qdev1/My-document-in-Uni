package com.codeandroid.qlphongtro.Fragment.HandleThongKe;

import static com.codeandroid.qlphongtro.LoginActivity.sqlHelper;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.codeandroid.qlphongtro.Model.ThanhToan;
import com.codeandroid.qlphongtro.R;

import java.util.List;

public class ListThongKePhongAdapter extends RecyclerView.Adapter<ListThongKePhongAdapter.ViewHolder> {
    List<ThanhToan> listThanhToan;
    List<Integer> listSoPhong;
    Context context;
    double tien=0;

    public ListThongKePhongAdapter(List<Integer> listSoPhong, List<ThanhToan> listThanhToan, Context context) {
        this.listThanhToan = listThanhToan;
        this.listSoPhong = listSoPhong;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_list_thong_ke, parent, false);
        ListThongKePhongAdapter.ViewHolder viewHolder = new ListThongKePhongAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tvThangThongKe.setText("Phòng số " + listSoPhong.get(position));
        listThanhToan = sqlHelper.getAllThanhToanTheoSoPhong(listSoPhong.get(position));
        for (int i=0;  i<listThanhToan.size(); i++){
            tien+=listThanhToan.get(i).getTongTien();
        }
        holder.tvTongTienThang.setText("Tổng tiền: " + tien + "VNĐ");
        tien = 0;
        ThongKePhongAdapter thongKeAdapter= new ThongKePhongAdapter(listSoPhong.get(position), context);
        RecyclerView.LayoutManager layoutManager= new LinearLayoutManager(context, RecyclerView.VERTICAL, false);
        holder.rcvListThongKe.setLayoutManager(layoutManager);
        holder.rcvListThongKe.setAdapter(thongKeAdapter);
    }

    @Override
    public int getItemCount() {
        return listSoPhong == null ? 0 : listSoPhong.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView tvThangThongKe, tvTongTienThang;
        RecyclerView rcvListThongKe;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvThangThongKe = itemView.findViewById(R.id.tvThangThongKe);
            rcvListThongKe = itemView.findViewById(R.id.rcvListThongKe);
            tvTongTienThang = itemView.findViewById(R.id.tvTongTienThang);
        }
    }
}
