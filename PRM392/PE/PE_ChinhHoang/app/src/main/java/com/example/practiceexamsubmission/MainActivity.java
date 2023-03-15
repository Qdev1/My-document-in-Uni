package com.example.practiceexamsubmission;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private EditText edt_id;
    private EditText edt_name;
    private EditText edt_quantity;
    private EditText edt_address;
    private Button btn_add, btn_update, btn_del, btn_list;
    private RecyclerView recyclerView;
    private CustomerAdapter customerAdapter;
    private MyDBOpenHelper openHelper;
    private List<Customer> CustomerList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        ((Button)findViewById(R.id.btn_list)).setOnClickListener(this);
        edt_id = findViewById(R.id.edt_id);
        edt_name = findViewById(R.id.edt_name);
        edt_quantity = findViewById(R.id.edt_quantity);
        edt_address = findViewById(R.id.edt_address);

        btn_add = findViewById(R.id.btn_add);
        btn_update = findViewById(R.id.btn_update);
        btn_del = findViewById(R.id.btn_del);
        btn_list = findViewById(R.id.btn_list);
        recyclerView = findViewById(R.id.recyclerView);

        openHelper = new MyDBOpenHelper(this);
        CustomerList = new ArrayList<>();

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    String Cid = edt_id.getText().toString();
                    String Cname = edt_name.getText().toString();
                    int order_quantity = Integer.parseInt(edt_quantity.getText().toString());
                    String address = edt_address.getText().toString();

                    if (!openHelper.checkExistedCustomer(Cid)) {
                        Customer customer = new Customer(Cid, Cname, order_quantity, address );
                        boolean checkInsertData = openHelper.insertCustomer(customer);
                        if (checkInsertData) {
                            Toast.makeText(MainActivity.this, "Success!", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(MainActivity.this, "Fail!", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Customer customer = new Customer();
                        customer.setCustomerID(Cid);
                        customer.setName(Cname);
                        customer.setOrder_quantity(order_quantity);
                        customer.setAddress(address);
                        int i = openHelper.updateCustomer(customer);
                        if (i >= 1) {
                            Toast.makeText(MainActivity.this, "Success!", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(MainActivity.this, "Fail!", Toast.LENGTH_SHORT).show();
                        }
                    }

                    CustomerList = openHelper.getAllCustomer();
                    customerAdapter = new CustomerAdapter(MainActivity.this, CustomerList);
                    recyclerView.setAdapter(customerAdapter);
                    recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                } catch (Exception e) {
                    Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });

        btn_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String Cid = edt_id.getText().toString().trim();
                String Cname = edt_name.getText().toString().trim();
                String order_quantity = edt_quantity.getText().toString().trim();
                String address = edt_address.getText().toString().trim();

                ArrayList<String> arrSearch = new ArrayList<>();
                arrSearch.add(Cid);
                arrSearch.add(Cname);
                arrSearch.add(order_quantity);
                arrSearch.add(address);

                List<Customer> CustomerList = openHelper.searchCustomer(arrSearch);

                customerAdapter = new CustomerAdapter(MainActivity.this, CustomerList);
                recyclerView.setAdapter(customerAdapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
            }
        });

        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {

                    String Cid = edt_id.getText().toString().trim();
                    String Cname = edt_name.getText().toString().trim();
                    int order_quantity = Integer.parseInt(edt_quantity.getText().toString().trim());
                    String address = edt_address.getText().toString().trim();

                    Customer c = openHelper.getCustomer(Cid);
                    c.setName(Cname);
                    c.setOrder_quantity(order_quantity);
                    c.setAddress(address);

                    openHelper.updateCustomer(c);
                    Toast.makeText(MainActivity.this, "Success!", Toast.LENGTH_SHORT).show();

                    CustomerList = openHelper.getAllCustomer();
                    customerAdapter = new CustomerAdapter(MainActivity.this, CustomerList);
                    recyclerView.setAdapter(customerAdapter);
                    recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                } catch (Exception e) {
                    Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });

        btn_del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {

                    String number = edt_id.getText().toString().trim();
                    Customer c = openHelper.getCustomer(number);
                    openHelper.deleteCustomer(c);
                    Toast.makeText(MainActivity.this, "Success!", Toast.LENGTH_SHORT).show();

                    CustomerList = openHelper.getAllCustomer();
                    customerAdapter = new CustomerAdapter(MainActivity.this, CustomerList);
                    recyclerView.setAdapter(customerAdapter);
                    recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                } catch (Exception e) {
                    Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });

        CustomerList = openHelper.getAllCustomer();
        customerAdapter = new CustomerAdapter(MainActivity.this, CustomerList);
        recyclerView.setAdapter(customerAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
    }
}