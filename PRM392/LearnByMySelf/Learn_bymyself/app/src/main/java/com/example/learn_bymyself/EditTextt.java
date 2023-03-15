package com.example.learn_bymyself;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class EditTextt extends AppCompatActivity {
EditText edtNoidung;
Button btnButon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_text);

        edtNoidung = (EditText) findViewById(R.id.editTextTextPersonName2);
        btnButon = (Button) findViewById(R.id.bntClick);
        edtNoidung.setText("Hihihi");
        btnButon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String noidung = edtNoidung.getText().toString();
                Toast.makeText(EditTextt.this,noidung,Toast.LENGTH_LONG).show();
            }
        });
    }
}