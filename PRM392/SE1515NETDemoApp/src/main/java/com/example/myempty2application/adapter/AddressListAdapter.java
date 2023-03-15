package com.example.myempty2application.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myempty2application.R;
import com.example.myempty2application.bean.AddressInfo;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AddressListAdapter extends RecyclerView.Adapter<AddressListAdapter.AddressViewHolder> {
    private Context context;
    private List<AddressInfo> addressInfoList;
    public AddressListAdapter(Context context, List<AddressInfo> addressInfoList) {
        this.context = context;
        this.addressInfoList = addressInfoList;
    }
    @NonNull
    @Override
    public AddressViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.address_item, parent, false);
        return new AddressViewHolder(view, this);
    }

    @Override
    public void onBindViewHolder(@NonNull AddressViewHolder holder, int position) {
        AddressInfo addressInfo = addressInfoList.get(position);
        holder.textViewAddress.setText(addressInfoList.get(position).getAddress());
        holder.textViewCity.setText(addressInfoList.get(position).getCity());

        if (addressInfo.getImage() == null || addressInfo.getImage().length() == 0) {
            Picasso.with(context).load("error")
                    .error(R.drawable.ic_errror)
                    .into(holder.imageView);
        } else {
            Picasso.with(context).load(addressInfo.getImage())
                    .error(R.drawable.ic_errror).into(holder.imageView);
        }
    }

    @Override
    public int getItemCount() {
        if (addressInfoList == null) {
            return 0;
        } else {
            return addressInfoList.size();
        }
    }

    class AddressViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        AddressListAdapter addressListAdapter;
        TextView textViewAddress, textViewCity, textViewPopupMenu;
        ImageView imageView;
        public AddressViewHolder(View view, AddressListAdapter addressListAdapter) {
            super(view);
            this.addressListAdapter = addressListAdapter;
            textViewAddress = view.findViewById(R.id.textViewAddressItem);
            textViewCity = view.findViewById(R.id.textViewCity);
            imageView = view.findViewById(R.id.imageAddress);
            textViewCity.setOnClickListener(this);
            textViewAddress.setOnClickListener(this);
            //Get object
            textViewPopupMenu = view.findViewById(R.id.textViewPopupMenu);
           ((Activity) context).registerForContextMenu(textViewAddress);
            textViewPopupMenu.setOnClickListener(v -> {
                PopupMenu popupMenu = new PopupMenu(context, textViewPopupMenu);
                popupMenu.inflate(R.menu.menu_popup);
                popupMenu.setOnMenuItemClickListener(item -> {
                    switch (item.getItemId()) {
                        case R.id.menuItemPopup1:
                            Toast.makeText(context, "Popop Menu 1 Search selected", Toast.LENGTH_LONG).show();
                            break;
                        case R.id.menuItemPopup2:
                            break;
                        case R.id.menuItemPopup3:
                            break;
                    }
                    return false;
                });
                popupMenu.show();
            });
        }
        public AddressViewHolder(@NonNull View itemView) {
            super(itemView);
        }

        @Override
        public void onClick(View v) {
            textViewCity.setText("City is clicked!");
        }
    }
}
