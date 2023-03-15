package com.codeandroid.qlphongtro.NguoiThueHandle;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.codeandroid.qlphongtro.Model.ThanhToan;
import com.codeandroid.qlphongtro.R;

import org.w3c.dom.Text;

import java.util.List;

public class ThanhToanAdapter extends RecyclerView.Adapter<ThanhToanAdapter.ViewHolder> {
    List<ThanhToan> list;
    Context  context;

    public List<ThanhToan> getList() {
        return list;
    }

    public void setList(List<ThanhToan> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public ThanhToanAdapter(List<ThanhToan> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_lich_su_tt, parent, false);
        ThanhToanAdapter.ViewHolder viewHolders = new ThanhToanAdapter.ViewHolder(view);
        return viewHolders;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ThanhToan thanhToan= list.get(position);
        holder.tvNgay.setText("Ngày thanh toán: "+thanhToan.getNgayThanhToan());
        holder.tvTien.setText("Số tiền: "+thanhToan.getTongTien()+" VNĐ");
        holder.tvNoiDung.setText("Nội dung: " + thanhToan.getNoiDung());
    }

    @Override
    public int getItemCount() {
        return list==null?0:list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvNgay, tvTien, tvNoiDung;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNgay= itemView.findViewById(R.id.tvNgayTT);
            tvTien= itemView.findViewById(R.id.tvTienTT);
            tvNoiDung = itemView.findViewById(R.id.tvNoiDung);
        }
    }
}
