package com.example.pe_prm;

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
    private EditText edt_number, edt_date, edt_lineCount, edt_customerName;
    private Button btn_add, btn_update, btn_delete, btn_list;
    private RecyclerView recyclerView;
    private DBHelper DB;
    private List<Order> OrderList;
    private OrderAdapter OrderAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edt_number = findViewById(R.id.edt_number);
        edt_date = findViewById(R.id.edt_date);
        edt_lineCount = findViewById(R.id.edt_lineCount);
        edt_customerName = findViewById(R.id.edt_customerName);

        btn_add = findViewById(R.id.btn_add);
        btn_update = findViewById(R.id.btn_update);
        btn_delete = findViewById(R.id.btn_delete);
        btn_list = findViewById(R.id.btn_list);
        recyclerView = findViewById(R.id.recyclerView);

        DB = new DBHelper(this);
        OrderList = new ArrayList<>();
        // add
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {

                    String number = edt_number.getText().toString();
                    String date = edt_date.getText().toString();
                    int line_count = Integer.parseInt(edt_lineCount.getText().toString());
                    String customer_name = edt_customerName.getText().toString();

                    if (!DB.checkExistedOrder(number)) {
                        Order order = new Order(number, date, line_count, customer_name );
                        boolean checkInsertData = DB.insertOrder(order);
                        if (checkInsertData) {
                            Toast.makeText(MainActivity.this, "Add successfully!", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(MainActivity.this, "Add failed!", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Order order = new Order();
                        order.setNumber(number);
                        order.setDate(date);
                        order.setLine_count(line_count);
                        order.setCustomer_name(customer_name);
                        int i = DB.updateOrder(order);
                        if (i >= 1) {
                            Toast.makeText(MainActivity.this, "Update successfully!", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(MainActivity.this, "Update failed!", Toast.LENGTH_SHORT).show();
                        }
                    }

                    OrderList = DB.getAllOrders();
                    OrderAdapter = new OrderAdapter(MainActivity.this, OrderList);
                    recyclerView.setAdapter(OrderAdapter);
                    recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                } catch (Exception e) {
                    Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });

        // list
        btn_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String number = edt_number.getText().toString().trim();
                String date = edt_date.getText().toString().trim();
                String line_count = edt_lineCount.getText().toString().trim();
                String customer_name = edt_customerName.getText().toString().trim();

                ArrayList<String> arrSearch = new ArrayList<>();
                arrSearch.add(number);
                arrSearch.add(date);
                arrSearch.add(line_count);
                arrSearch.add(customer_name);

                List<Order> OrderList = DB.searchOrder(arrSearch);

                OrderAdapter = new OrderAdapter(MainActivity.this, OrderList);
                recyclerView.setAdapter(OrderAdapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
            }
        });

        // delete
        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {

                    String number = edt_number.getText().toString().trim();
                    Order o = DB.getOrder(number);
                    DB.deleteOrder(o);
                    Toast.makeText(MainActivity.this, "Delete successfully!", Toast.LENGTH_SHORT).show();

                    OrderList = DB.getAllOrders();
                    OrderAdapter = new OrderAdapter(MainActivity.this, OrderList);
                    recyclerView.setAdapter(OrderAdapter);
                    recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                } catch (Exception e) {
                    Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });

        // update
        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {

                    String number = edt_number.getText().toString().trim();
                    String date = edt_date.getText().toString().trim();
                    int lineCount = Integer.parseInt(edt_lineCount.getText().toString().trim());
                    String customerName = edt_customerName.getText().toString().trim();

                    Order o = DB.getOrder(number);
                    o.setDate(date);
                    o.setLine_count(lineCount);
                    o.setCustomer_name(customerName);

                    DB.updateOrder(o);
                    Toast.makeText(MainActivity.this, "Update successfully!", Toast.LENGTH_SHORT).show();

                    OrderList = DB.getAllOrders();
                    OrderAdapter = new OrderAdapter(MainActivity.this, OrderList);
                    recyclerView.setAdapter(OrderAdapter);
                    recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                } catch (Exception e) {
                    Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
        OrderList = DB.getAllOrders();
        OrderAdapter = new OrderAdapter(MainActivity.this, OrderList);
        recyclerView.setAdapter(OrderAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
    }

}