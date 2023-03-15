package com.is1418.he140529.pe;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private EditText edt_id, edt_fullName, edt_birthday, edt_averageScore;
    private Button btn_add, btn_update, btn_delete, btn_list;
    private RecyclerView recyclerView;

    private DBHelper DB;

    private List<StudentModel> studentModelList;

    private StudentAdapter studentAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bindingView();

        DB = new DBHelper(this);
        studentModelList = new ArrayList<>();
        // add student
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    studentModelList.clear();

                    int id = Integer.parseInt(edt_id.getText().toString());
                    String fullname = edt_fullName.getText().toString();
                    String dob = edt_birthday.getText().toString();
                    String averageScore = edt_averageScore.getText().toString();

                    if (!DB.checkExistStudentInDB(id)) {
                        StudentModel studentModel = new StudentModel(id, fullname, dob, Float.parseFloat(averageScore));
                        boolean checkInsertData = DB.insertStudent(studentModel);
                        if (checkInsertData) {
                            Toast.makeText(MainActivity.this, "Add successfully!", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(MainActivity.this, "Add failed!", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        StudentModel studentModel = new StudentModel();
                        studentModel.setId(id);
                        studentModel.setFullName(fullname);
                        studentModel.setDob(dob);
                        studentModel.setAverageScore(Float.parseFloat(averageScore));
                        int i = DB.updateStudent(studentModel);
                        if (i >= 1) {
                            Toast.makeText(MainActivity.this, "Update successfully!", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(MainActivity.this, "Update failed!", Toast.LENGTH_SHORT).show();
                        }
                    }

                    studentModelList = DB.getAllStudents();
                    studentAdapter = new StudentAdapter(MainActivity.this, studentModelList);
                    recyclerView.setAdapter(studentAdapter);
                    recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                } catch (Exception e) {
                    Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });

        // list student
        btn_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                studentModelList.clear();
                String id = edt_id.getText().toString().trim();
                String fullname = edt_fullName.getText().toString().trim();
                String dob = edt_birthday.getText().toString().trim();
                String averageScore = edt_averageScore.getText().toString().trim();

                ArrayList<String> arrSearch = new ArrayList<>();
                arrSearch.add(id);
                arrSearch.add(fullname);
                arrSearch.add(dob);
                arrSearch.add(averageScore);

                List<StudentModel> studentModelList = DB.searchStudents(arrSearch);

                studentAdapter = new StudentAdapter(MainActivity.this, studentModelList);
                recyclerView.setAdapter(studentAdapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
            }
        });

        // delete student
        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    studentModelList.clear();
                    int id = Integer.parseInt(edt_id.getText().toString().trim());
                    StudentModel studentModel = DB.getStudent(id);
                    DB.deleteStudent(studentModel);
                    Toast.makeText(MainActivity.this, "Delete successfully!", Toast.LENGTH_SHORT).show();

                    studentModelList = DB.getAllStudents();
                    studentAdapter = new StudentAdapter(MainActivity.this, studentModelList);
                    recyclerView.setAdapter(studentAdapter);
                    recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                } catch (Exception e) {
                    Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });

        // update student
        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    studentModelList.clear();
                    int id = Integer.parseInt(edt_id.getText().toString().trim());
                    String fullname = edt_fullName.getText().toString().trim();
                    String dob = edt_birthday.getText().toString().trim();
                    String score = edt_averageScore.getText().toString().trim();
                    StudentModel studentModel = DB.getStudent(id);
                    studentModel.setFullName(fullname);
                    studentModel.setDob(dob);
                    studentModel.setAverageScore(Float.parseFloat(score));
                    DB.updateStudent(studentModel);
                    Toast.makeText(MainActivity.this, "Update successfully!", Toast.LENGTH_SHORT).show();

                    studentModelList = DB.getAllStudents();
                    studentAdapter = new StudentAdapter(MainActivity.this, studentModelList);
                    recyclerView.setAdapter(studentAdapter);
                    recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                } catch (Exception e) {
                    Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
        studentModelList = DB.getAllStudents();
        studentAdapter = new StudentAdapter(MainActivity.this, studentModelList);
        recyclerView.setAdapter(studentAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
    }

    private void bindingView() {
        edt_id = findViewById(R.id.edt_id);
        edt_fullName = findViewById(R.id.edt_fullName);
        edt_birthday = findViewById(R.id.edt_birthday);
        edt_averageScore = findViewById(R.id.edt_averageScore);
        btn_add = findViewById(R.id.btn_add);
        btn_update = findViewById(R.id.btn_update);
        btn_delete = findViewById(R.id.btn_delete);
        btn_list = findViewById(R.id.btn_list);
        recyclerView = findViewById(R.id.recyclerView);
    }
}