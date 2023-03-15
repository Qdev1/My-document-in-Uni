package com.codeandroid.qlphongtro.NguoiThueHandle;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.codeandroid.qlphongtro.Model.TaiKhoan;
import com.codeandroid.qlphongtro.R;

import org.w3c.dom.Text;

import java.util.List;

public class ChuTroAdapter extends RecyclerView.Adapter<ChuTroAdapter.ViewHolder> {
    List<TaiKhoan> list;
    Context context;

    public List<TaiKhoan> getList() {
        return list;
    }

    public void setList(List<TaiKhoan> list) {
        this.list = list;
    }

    public ChuTroAdapter(List<TaiKhoan> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_chu_tro, parent, false);
        ChuTroAdapter.ViewHolder viewHolders = new ChuTroAdapter.ViewHolder(view);
        return viewHolders;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        TaiKhoan taiKhoan= list.get(position);
        holder.tvSDT.setText(taiKhoan.getPhone());
        holder.tvTen.setText(taiKhoan.getFullname());
    }

    @Override
    public int getItemCount() {
        return list==null?0:list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvTen, tvSDT;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTen= itemView.findViewById(R.id.tvTenChuTro);
            tvSDT= itemView.findViewById(R.id.tvSDTChuTro);
        }
    }
}
