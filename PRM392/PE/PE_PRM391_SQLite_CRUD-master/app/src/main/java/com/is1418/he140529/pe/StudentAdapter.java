package com.is1418.he140529.pe;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.ViewHolder> {
    private Context context;
    private List<StudentModel> studentModelList;

    public StudentAdapter(Context context, List<StudentModel> studentModelList) {
        this.context = context;
        this.studentModelList = studentModelList;
    }

    @NonNull
    @Override
    public StudentAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.student_items, parent, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull StudentAdapter.ViewHolder holder, int position) {
        StudentModel studentModel = studentModelList.get(position);

        holder.id.setText(String.valueOf(studentModel.getId()));
        holder.fullName.setText(studentModel.getFullName());
        holder.dob.setText(studentModel.getDob());
        holder.averageScore.setText(String.valueOf(studentModel.getAverageScore()));
    }

    @Override
    public int getItemCount() {
        return studentModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView id, fullName, dob, averageScore;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            id = itemView.findViewById(R.id.student_id);
            fullName = itemView.findViewById(R.id.full_name);
            dob = itemView.findViewById(R.id.birthday);
            averageScore = itemView.findViewById(R.id.average_score);
        }
    }
}
