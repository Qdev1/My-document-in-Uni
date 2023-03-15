package com.example.pe_prm392;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class MainActivity extends AppCompatActivity {
    SQLiteDatabase db;
    DbHelper helper;
    EditText jobId;
    EditText jobName;
    EditText jobStatus;
    EditText jobDes;
    TextView messText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().setTitle("NGO QUOC BAO - HE150436");
        setContentView(R.layout.activity_main);
        jobId = findViewById(R.id.jobId);
        jobName = findViewById(R.id.jobName);
        jobStatus = findViewById(R.id.jobStatus);
        jobDes = findViewById(R.id.jobDes);
        messText = findViewById(R.id.messText);
    }

    public boolean checkJob(String id) {
        helper = new DbHelper(this, null);
        db = helper.getWritableDatabase();
        String sql = "SELECT * FROM Job WHERE Id LIKE " + "\"" + jobId.getText().toString() + "\"";
        Cursor c = db.rawQuery(sql, null);
        if (c.moveToFirst()) {
            c.close();
            return true;
        } else {
            db.close();
            return false;
        }
    }

    public void addJob(View v) {
        String id = jobId.getText().toString();
        String name = jobName.getText().toString();
        String status = jobStatus.getText().toString();
        String des = jobDes.getText().toString();
        if (!id.equals("") && !name.equals("") && !status.equals("") && !des.equals("")) {
            if (!checkJob(jobId.getText().toString())) {
                helper = new DbHelper(this, null);
                db = helper.getWritableDatabase();

                String sql = "INSERT INTO Job (Id, name, status, description) VALUES (?,?,?,?) ";
                db.execSQL(sql, new String[]{
                        jobId.getText().toString(),
                        jobName.getText().toString(),
                        jobStatus.getText().toString(),
                        jobDes.getText().toString(),
                });
                db.close();
                Toast.makeText(getApplicationContext(), "\tJob has been added.", Toast.LENGTH_SHORT).show();
                jobId.setText("");
                jobName.setText("");
                jobStatus.setText("");
                jobDes.setText("");
            } else {
                Toast.makeText(getApplicationContext(), "Job id duplicate!!!", Toast.LENGTH_SHORT).show();
                jobId.setText("");
                jobName.setText("");
                jobStatus.setText("");
                jobDes.setText("");
            }
        } else {
            Toast.makeText(getApplicationContext(), "Please enter the data...", Toast.LENGTH_SHORT).show();
        }
    }

    public void editJob(View v) {
        String id = jobId.getText().toString();
        String name = jobName.getText().toString();
        String status = jobStatus.getText().toString();
        String des = jobDes.getText().toString();
        if (!id.equals("") && !name.equals("") && !status.equals("") && !des.equals("")) {
            helper = new DbHelper(this, null);
            db = helper.getWritableDatabase();
            String sql = "UPDATE Job SET name = ?, status = ?, description = ? WHERE Id = ?";
            db.execSQL(sql, new String[]{
                    jobName.getText().toString(),
                    jobStatus.getText().toString(),
                    jobDes.getText().toString(),
                    jobId.getText().toString(),
            });
            db.close();
            Toast.makeText(getApplicationContext(), "Update Successfully!!!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getApplicationContext(), "Please enter the data...", Toast.LENGTH_SHORT).show();
        }
    }

    public void listJob(View v) {
        helper = new DbHelper(this, null);
        db = helper.getWritableDatabase();
        String sql = "SELECT * FROM Job WHERE name LIKE ? AND status LIKE ? and description LIKE ?";
        String[] params;
        if (!jobId.getText().toString().equals("")) {
            sql += " AND Id LIKE ? ";
            params = new String[]{
                    "%" + jobName.getText().toString() + "%",
                    "%" + jobStatus.getText().toString() + "%",
                    "%" + jobDes.getText().toString() + "%",
                    jobId.getText().toString()
            };
        } else {
            params = new String[]{
                    "%" + jobName.getText().toString() + "%",
                    "%" + jobStatus.getText().toString() + "%",
                    "%" + jobDes.getText().toString() + "%",
            };
        }
        Cursor c = db.rawQuery(sql, params);

        List<Job> jobList = new ArrayList<>();
        while (c.moveToNext()) {
            @SuppressLint("Range") String id = c.getString(c.getColumnIndex("Id"));
            @SuppressLint("Range") String name = c.getString(c.getColumnIndex("name"));
            @SuppressLint("Range") String status = c.getString(c.getColumnIndex("status"));
            @SuppressLint("Range") String description = c.getString(c.getColumnIndex("description"));
            Job job = new Job(id, name, status, description);
            jobList.add(job);
        }
        db.close();

        if (jobList.size() == 0) {
            messText.setText("\tNo data found!!!!");
        } else messText.setText("\tTotal List = " + jobList.size());
        JobAdapter adapter = new JobAdapter(jobList);
        RecyclerView rec = findViewById(R.id.rc_view);
        RecyclerView.LayoutManager layout = new LinearLayoutManager(this);
        rec.setLayoutManager(layout);
        rec.setAdapter(adapter);
    }

    public void deleteJob(View v) {
        String id = jobId.getText().toString();
        if (!id.equals("")) {
            helper = new DbHelper(this, null);
            db = helper.getWritableDatabase();
            db.delete("Job", "Id =" + "\"" + id + "\"", null);
            db.close();
            Toast.makeText(getApplicationContext(), "Delete Success!!!", Toast.LENGTH_SHORT).show();
            jobId.setText("");
        } else {
            Toast.makeText(getApplicationContext(), "Please enter job id...", Toast.LENGTH_SHORT).show();
        }
    }
    private void clickItem(View v) {
        Toast.makeText(getApplicationContext(), "DA DUOC CLICK", Toast.LENGTH_SHORT).show();
    }

}