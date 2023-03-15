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

public class ThongKePhongAdapter extends RecyclerView.Adapter<ThongKePhongAdapter.ViewHolder> {
    List<ThanhToan> list;
    int soPhong;
    Context context;

    public ThongKePhongAdapter(int soPhong, Context context) {
        this.list = sqlHelper.getAllThanhToanTheoSoPhong(soPhong);
        this.soPhong = soPhong;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_phong_thong_ke, parent, false);
        ThongKePhongAdapter.ViewHolder viewHolder = new ThongKePhongAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ThanhToan thanhToan= list.get(position);
        holder.tvPhong.setText(thanhToan.getSoPhong()+"");
        holder.tvTong.setText(thanhToan.getTongTien()+" VNĐ");
        holder.tvSDT.setText(thanhToan.getSdtTKTT());
        holder.tvNgayTra.setText(thanhToan.getNgayThanhToan());
    }

    @Override
    public int getItemCount() {
        return list==null?0:list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView tvPhong, tvTong, tvNgayTra, tvSDT;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvPhong= itemView.findViewById(R.id.tvPhongTK);
            tvTong= itemView.findViewById(R.id.tvTongCuaPhong);
            tvNgayTra= itemView.findViewById(R.id.tvNgayTra);
            tvSDT= itemView.findViewById(R.id.tvSDTNguoiTra);
        }
    }
}
