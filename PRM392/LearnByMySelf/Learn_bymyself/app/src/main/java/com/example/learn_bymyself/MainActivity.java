package com.example.learn_bymyself;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView txtNoidung; //toan cuc
    Button bamNut;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //anh xa
        txtNoidung = (TextView) findViewById(R.id.textView2);
        bamNut = (Button) findViewById(R.id.button);
//        //viet code
//        txtNoidung.setText("Lập trình android");

        bamNut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //viet code
                txtNoidung.setText("Lập trình android");
            }
        });
    }
}