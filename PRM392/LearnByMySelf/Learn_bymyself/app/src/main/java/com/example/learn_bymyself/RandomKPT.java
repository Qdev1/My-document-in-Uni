package com.example.learn_bymyself;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class RandomKPT extends AppCompatActivity {
TextView txtNumber;
EditText edtNumber1, edtNumber2;
Button btnRandom;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_random_kpt);

        khaiBao();
        btnRandom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String sNum1 = edtNumber1.getText().toString();
                String sNum2 = edtNumber2.getText().toString();

                if(sNum1.length() == 0||sNum2.isEmpty()){
                    Toast.makeText(RandomKPT.this,"Vui lòng nhập đủ số",Toast.LENGTH_SHORT).show();
                }else {
                    //ep kieu du lieu
                    int min = Integer.parseInt(sNum1);
                    int max = Integer.parseInt(sNum2);
                    //tạo số ngẫu nhiên
                    Random ran = new Random();
                    int n = ran.nextInt(max-min+1)+min;
                    txtNumber.setText(String.valueOf(n)); // so-> chuoi
                }
            }
        });
    }

    private void khaiBao(){
        txtNumber = (TextView) findViewById(R.id.textView);
        edtNumber1 = (EditText) findViewById(R.id.edittext1);
        edtNumber2 = (EditText) findViewById(R.id.edittext2);
        btnRandom = (Button)  findViewById(R.id.buttonRandom);
    }
}