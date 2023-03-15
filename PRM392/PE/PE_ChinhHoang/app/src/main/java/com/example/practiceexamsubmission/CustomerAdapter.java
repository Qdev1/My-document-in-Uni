package com.example.practiceexamsubmission;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class CustomerAdapter extends RecyclerView.Adapter<CustomerAdapter.ViewHolder> {
    private Context context;
    private List<Customer> CustomerList;

    public CustomerAdapter(Context context, List<Customer> customerList) {
        this.context = context;
        CustomerList = customerList;
    }
    @NonNull
    @Override
    public CustomerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.customer_item, parent, false);
        return new CustomerAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomerAdapter.ViewHolder holder, int position) {
        Customer c = CustomerList.get(position);
        holder.customerId.setText(c.getCustomerID());
        holder.customerName.setText(c.getName());
        holder.orderQuantity.setText(String.valueOf(c.getOrder_quantity()));
        holder.address.setText(c.getAddress());
    }

    @Override
    public int getItemCount() {
        return CustomerList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView customerId;
        private TextView customerName;
        private TextView orderQuantity;
        private TextView address;

        public ViewHolder(@NonNull View view) {
            super(view);
            customerId = itemView.findViewById(R.id.customer_id);
            customerName = itemView.findViewById(R.id.customer_name);
            orderQuantity = itemView.findViewById(R.id.order_quantity);
            address = itemView.findViewById(R.id.address);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos= getAdapterPosition();
                    CustomerList.get(pos);
                    Log.d(TAG,"ID" + customerId.getText() + "Customer name:" + customerName.getText() );
                }
            });
        }
    }
}