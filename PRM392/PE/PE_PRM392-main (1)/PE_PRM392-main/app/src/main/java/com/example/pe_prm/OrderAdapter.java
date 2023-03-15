package com.example.pe_prm;

import static android.content.ContentValues.TAG;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.ViewHolder> {
    private Context context;
    private List<Order> OrderList;

    public OrderAdapter(Context context, List<Order> orderList) {
        this.context = context;
        OrderList = orderList;
    }

    @NonNull
    @Override
    public OrderAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.order_items, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderAdapter.ViewHolder holder, int position) {
        Order o = OrderList.get(position);
        holder.number.setText(o.getNumber());
        holder.date.setText(o.getDate());
        holder.lineCount.setText(String.valueOf(o.getLine_count()));
        holder.customerName.setText(o.getCustomer_name());
    }

    @Override
    public int getItemCount() {
        return OrderList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView number, date, lineCount, customerName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            number = itemView.findViewById(R.id.order_number);
            date = itemView.findViewById(R.id.date);
            lineCount = itemView.findViewById(R.id.line_count);
            customerName = itemView.findViewById(R.id.customer_name);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int pos= getAdapterPosition();
                    OrderList.get(pos);
                    Log.d(TAG,"number"+number.getText()+"date:" + date.getText() );
                }
            });
        }
    }
}
