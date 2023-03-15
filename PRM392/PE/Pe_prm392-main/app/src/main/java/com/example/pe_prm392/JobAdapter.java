package com.example.pe_prm392;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class JobAdapter extends RecyclerView.Adapter<JobAdapter.JobHolder> {
    List<Job> jobList;

    public JobAdapter(List<Job> jobList) {
        this.jobList = jobList;
    }

    @NonNull
    @Override
    public JobAdapter.JobHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_job, parent, false);
        return new JobHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull JobAdapter.JobHolder holder, int position) {
        Job job = jobList.get(position);
        String result = "\tid: " + job.getId() + ",\tname: " + job.getName() + ", \tstatus: "
                + job.getStatus() + ", \tdescription: " + job.getDescription() + ".";
        holder.itemJob.setText(result);
    }

    @Override
    public int getItemCount() {
        return jobList.size();
    }

    public class JobHolder extends RecyclerView.ViewHolder {
        TextView itemJob;

        public JobHolder(@NonNull View itemView) {
            super(itemView);
            itemJob = itemView.findViewById(R.id.itemJob);
        }
    }
}
