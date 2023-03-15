package com.example.learn_bymyself;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

public class SwitchKPT extends AppCompatActivity {
Switch swtWifi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_switch_kpt);

        swtWifi = (Switch) findViewById(R.id.switchWifi);
        swtWifi.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){ //==true
                    Toast.makeText(SwitchKPT.this,"Bat mo wifi",Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(SwitchKPT.this,"Ban da tat wifi",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}