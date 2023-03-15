package com.codeandroid.qlphongtro.NguoiThueHandle;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.codeandroid.qlphongtro.Fragment.HandlePhongTro.PhongTroAdapter;
import com.codeandroid.qlphongtro.Model.PhongTro;
import com.codeandroid.qlphongtro.Model.YeuCau;
import com.codeandroid.qlphongtro.R;

import java.util.List;

public class PhongTroConTrongAdapter extends RecyclerView.Adapter<PhongTroConTrongAdapter.ViewHolder> {
    List<PhongTro> list;
    Context context;
    IOnClickDatPhong iOnClickDatPhong;
    
    public IOnClickDatPhong getiOnClickDatPhong() {
        return iOnClickDatPhong;
    }
    
    public void setiOnClickDatPhong(IOnClickDatPhong iOnClickDatPhong) {
        this.iOnClickDatPhong = iOnClickDatPhong;
    }

    public List<PhongTro> getList() {
        return list;
    }

    public void setList(List<PhongTro> list) {
        this.list = list;
    }

    public PhongTroConTrongAdapter(List<PhongTro> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_phong_tro, parent, false);
        PhongTroConTrongAdapter.ViewHolder viewHolders = new PhongTroConTrongAdapter.ViewHolder(view);
        return viewHolders;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        PhongTro phongTro= list.get(position);
        holder.lnBTN.setVisibility(View.GONE);
        holder.tvSDT.setVisibility(View.GONE);
        holder.tvNgayThue.setVisibility(View.GONE);
        holder.tvSoPhong.setText("Số Phòng: "+ phongTro.getSoPhong()+" VNĐ");
        holder.tvTienPhong.setText("Tiền Phòng: "+ phongTro.getTienPhong()+" VNĐ");
        holder.tvTienDien.setText("Tiền Điện: "+ phongTro.getTienDien()+" VNĐ");
        holder.tvTienNuoc.setText("Tiền Nước: "+ phongTro.getTienNuoc()+" VNĐ");
        if(phongTro.getTinhTrang() == 0) {
            holder.tvDatPhong.setVisibility(View.VISIBLE);
            holder.tvHuyDatPhong.setVisibility(View.GONE);
//            holder.tvDatPhong.setEnabled(false);
        }
        else {
            holder.tvDatPhong.setVisibility(View.GONE);
            holder.tvHuyDatPhong.setVisibility(View.VISIBLE);
//            holder.tvHuyDatPhong.setEnabled(false);
        }

        holder.tvDatPhong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iOnClickDatPhong.iOnClickDatPhong(position);
                Toast.makeText(view.getContext(), "Đã gửi yêu cầu đặt phòng", Toast.LENGTH_SHORT).show();
                holder.tvDatPhong.setVisibility(View.GONE);
                holder.tvHuyDatPhong.setVisibility(View.VISIBLE);
            }
        });
        holder.tvHuyDatPhong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iOnClickDatPhong.iOnClickHuyDatPhong(position);
                Toast.makeText(view.getContext(), "Đã hủy đặt phòng", Toast.LENGTH_SHORT).show();
                holder.tvDatPhong.setVisibility(View.VISIBLE);
                holder.tvHuyDatPhong.setVisibility(View.GONE);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list==null?0:list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvSoPhong, tvNgayThue, tvTienPhong, tvTienDien, tvTienNuoc, tvSDT, tvDatPhong, tvHuyDatPhong;
        LinearLayout lnBTN;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvSoPhong= itemView.findViewById(R.id.tvSoPhong);
            tvNgayThue= itemView.findViewById(R.id.tvNgayThue);
            tvTienPhong= itemView.findViewById(R.id.tvTienPhong);
            tvTienDien= itemView.findViewById(R.id.tvTienDien);
            tvTienNuoc= itemView.findViewById(R.id.tvTienNuoc);
            tvSDT= itemView.findViewById(R.id.tvSDT);
            lnBTN= itemView.findViewById(R.id.lnBTN);
            tvDatPhong = itemView.findViewById(R.id.tvDatPhong);
            tvHuyDatPhong = itemView.findViewById(R.id.tvHuyDatPhong);
        }
    }
}
