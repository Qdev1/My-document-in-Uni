package com.example.testview2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class ListViewTest extends AppCompatActivity {
ListView lvMonHoc;
ArrayList<String> arrayCouse;
Button btnThem, btnUpdate;
EditText edtMonHoc;

int viTri = -1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view_test);
        //anh xa
        lvMonHoc = (ListView) findViewById(R.id.listviewMonHoc);
        btnThem = (Button)findViewById(R.id.button);
        edtMonHoc = (EditText) findViewById(R.id.edittextMonHoc);
        btnUpdate = (Button)findViewById(R.id.button2);
        //create list
        arrayCouse = new ArrayList<>();
        arrayCouse.add("android");
        arrayCouse.add("ios");
        arrayCouse.add("PHP");
        arrayCouse.add("C/C++");
        arrayCouse.add("Java");

        //do du lieu vao adapter
        ArrayAdapter adapter = new ArrayAdapter(ListViewTest.this,
                android.R.layout.simple_list_item_1,
                arrayCouse );
        //lay du lieu tu apdater roi in ra
        lvMonHoc.setAdapter(adapter);

        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String  monHoc = edtMonHoc.getText().toString();
                arrayCouse.add(monHoc);
                adapter.notifyDataSetChanged();
            }
        });

        lvMonHoc.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                edtMonHoc.setText(arrayCouse.get(i));
                viTri  = i;
            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                arrayCouse.set(viTri,edtMonHoc.getText().toString());
                adapter.notifyDataSetChanged(); // cap nhat lai gia tri sau khi sửa đổi
            }
        });

        //bat su kien click
//        lvMonHoc.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                //i: tra ve vi tri nguoi dung click tren listview -> 0
//                Toast.makeText(ListViewTest.this,arrayCouse.get(i),Toast.LENGTH_SHORT).show();
//            }
//        });

        //long click
        lvMonHoc.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {

                arrayCouse.remove(i);
                adapter.notifyDataSetChanged();

                return false;
            }
        });
    }
}