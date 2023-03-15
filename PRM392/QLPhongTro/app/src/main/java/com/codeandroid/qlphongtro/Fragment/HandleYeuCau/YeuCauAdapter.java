package com.codeandroid.qlphongtro.Fragment.HandleYeuCau;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.codeandroid.qlphongtro.Model.YeuCau;
import com.codeandroid.qlphongtro.R;

import java.util.List;

public class YeuCauAdapter extends RecyclerView.Adapter<YeuCauAdapter.ViewHolder> {
    List<YeuCau> list;
    Context context;

    IOnClickYeuCau iOnClickYeuCau;

    public IOnClickYeuCau getiOnClickYeuCau() {
        return iOnClickYeuCau;
    }

    public void setiOnClickYeuCau(IOnClickYeuCau iOnClickYeuCau) {
        this.iOnClickYeuCau = iOnClickYeuCau;
    }

    public YeuCauAdapter(List<YeuCau> list, Context context) {
        this.list = list;
        this.context = context;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_yeu_cau, parent, false);
        YeuCauAdapter.ViewHolder viewHolder =  new YeuCauAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        YeuCau yeuCau = list.get(position);
        holder.tvSoDT.setText("SDT người thuê: " + yeuCau.getSdtYC());
        holder.tvSoPhong.setText("Số phòng: " + yeuCau.getSoPhong());
        holder.tvXacNhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iOnClickYeuCau.iOnClickYeuCau(yeuCau, position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView tvSoDT, tvSoPhong, tvXacNhan;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvSoDT = itemView.findViewById(R.id.tvSoDT);
            tvSoPhong = itemView.findViewById(R.id.tvSoPhong);
            tvXacNhan = itemView.findViewById(R.id.tvXacNhan);
        }
    }
}
