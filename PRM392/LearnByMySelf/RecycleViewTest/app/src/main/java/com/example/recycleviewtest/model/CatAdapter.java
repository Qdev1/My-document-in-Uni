package com.example.recycleviewtest.model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recycleviewtest.R;

import java.util.List;

public class CatAdapter extends RecyclerView.Adapter<CatAdapter.CatViewHolder> {
private List<Cat> mList;
private Context context;

    public CatAdapter(List<Cat> mList, Context context) {
        this.mList = mList;
        this.context = context;
    }

    @NonNull
    @Override
    public CatViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item,parent,false);
        return new CatViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CatViewHolder holder, int position){
    Cat cat = mList.get(position);
    if(cat!= null)
        return;
    holder.img.setImageResource(cat.getImg());
    holder.tv.setText(cat.getName());
    holder.cView.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Toast.makeText(context.getApplicationContext(), cat.getName(), Toast.LENGTH_SHORT).show();
        }
    });
    }

    @Override
    public int getItemCount() {
     if(mList!= null){
         return mList.size();
     }
        return 0;
    }

    public class CatViewHolder extends RecyclerView.ViewHolder{
        private ImageView img;
        private TextView tv;
        private CardView cView;
        public CatViewHolder(@NonNull View view) {
            super(view);
            img = view.findViewById(R.id.img);
            tv = view.findViewById(R.id.tname);
            cView = view.findViewById(R.id.cview);
        }
    }
}
