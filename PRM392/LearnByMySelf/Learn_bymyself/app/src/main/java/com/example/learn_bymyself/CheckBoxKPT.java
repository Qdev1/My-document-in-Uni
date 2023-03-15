package com.example.learn_bymyself;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Toast;

public class CheckBoxKPT extends AppCompatActivity {
CheckBox cbA,cbI,cbP;
Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_box_kpt);

        AnhXa();

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String monHoc = "Ban da chon mon hoc: \n";
                if(cbA.isChecked()){
                    monHoc += cbA.getText()+"\n";
                }
                if(cbI.isChecked()){
                    monHoc += cbI.getText()+"\n";
                }
                if(cbP.isChecked()){
                    monHoc += cbP.getText()+"\n";
                }
                Toast.makeText(CheckBoxKPT.this,monHoc,Toast.LENGTH_SHORT).show();
            }
        });

    //catch even when click on CheckBox
        cbA.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    Toast.makeText(CheckBoxKPT.this,"Ban da chon A",Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(CheckBoxKPT.this,"Ban da bo chon A",Toast.LENGTH_SHORT).show();

                }
            }
        });
    }

    private void AnhXa() {
        cbA = (CheckBox) findViewById(R.id.checkBox);
        cbI = (CheckBox) findViewById(R.id.checkBox2);
        cbP = (CheckBox) findViewById(R.id.checkBox3);
        btn = (Button) findViewById(R.id.button2);
    }
}