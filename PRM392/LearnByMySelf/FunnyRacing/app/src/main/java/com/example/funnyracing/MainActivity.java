package com.example.funnyracing;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
TextView txtDiem;
CheckBox cb1,cb2,cb3;
SeekBar sb1,sb2,sb3;
ImageButton imgPlay;
int soDiem = 100;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        anhXa();

        //disabale seekbar when run
        sb1.setEnabled(false);
        sb2.setEnabled(false);
        sb3.setEnabled(false);

        txtDiem.setText(soDiem+ "");
        CountDownTimer countDownTimer = new CountDownTimer(60000, 300) {
            @Override
            public void onTick(long millisUntilFinished) {
                int n=5;
                Random ran = new Random();
                int one = ran.nextInt(n);
                int two = ran.nextInt(n);
                int three = ran.nextInt(n);

                //kiem tra Win1
                if(sb1.getProgress() >= sb1.getMax()){
                    this.cancel();
                    imgPlay.setVisibility(View.VISIBLE);
                    Toast.makeText(MainActivity.this,"One win",Toast.LENGTH_SHORT).show();
                    //kiem tra dat cuoc
                    if(cb1.isChecked()){
                        soDiem+=10;
                        Toast.makeText(MainActivity.this,"Correct",Toast.LENGTH_SHORT).show();
                    }else{
                        soDiem -=5;
                        Toast.makeText(MainActivity.this,"Incorrect",Toast.LENGTH_SHORT).show();
                    }
                    txtDiem.setText(soDiem+ "");
                    EnableCheckBox();
                }
                //kiem tra Win2
                if(sb2.getProgress() >= sb2.getMax()){
                    this.cancel();
                    imgPlay.setVisibility(View.VISIBLE);
                    Toast.makeText(MainActivity.this,"Two win",Toast.LENGTH_SHORT).show();
                    //kiem tra dat cuoc
                    if(cb2.isChecked()){
                        soDiem+=10;
                        Toast.makeText(MainActivity.this,"Correct",Toast.LENGTH_SHORT).show();

                    }else{
                        soDiem -=5;
                        Toast.makeText(MainActivity.this,"Incorrect",Toast.LENGTH_SHORT).show();

                    }
                    txtDiem.setText(soDiem+ "");
                    EnableCheckBox();
                }
                //kiem tra Win3
                if(sb3.getProgress() >= sb3.getMax()){
                    this.cancel();
                    //hien thi nut play when finish
                    imgPlay.setVisibility(View.VISIBLE);
                    Toast.makeText(MainActivity.this,"Three win",Toast.LENGTH_SHORT).show();
                    //kiem tra dat cuoc
                    if(cb3.isChecked()){
                        soDiem+=10;
                        Toast.makeText(MainActivity.this,"Correct",Toast.LENGTH_SHORT).show();

                    }else{
                        soDiem -=5;
                        Toast.makeText(MainActivity.this,"Incorrect",Toast.LENGTH_SHORT).show();

                    }
                    txtDiem.setText(soDiem+ "");
                    EnableCheckBox();
                }

                sb1.setProgress(sb1.getProgress()+one);
                sb2.setProgress(sb2.getProgress()+two);
                sb3.setProgress(sb3.getProgress()+three);
            }

            @Override
            public void onFinish() {

            }
        };
        imgPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //check bet
                if(cb1.isChecked() || cb2.isChecked() || cb3.isChecked()){
                    //setProgress to 0
                    sb1.setProgress(0);
                    sb2.setProgress(0);
                    sb3.setProgress(0);
                    //INVISIBLE play button when run
                    imgPlay.setVisibility(View.INVISIBLE); // an 1 view
                    //start a countdown timer
                    countDownTimer.start();
                    DisableCheckBox();
                }else{
                    Toast.makeText(MainActivity.this,"Please bet before play",Toast.LENGTH_SHORT).show();
                }
            }
        });

        cb1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean b) {
                if(b){
                    //bo check 2,3
                    cb2.setChecked(false);
                    cb3.setChecked(false);
                }
            }
        });

        cb2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean b) {
                if(b){
                    cb1.setChecked(false);
                    cb3.setChecked(false);
                }
            }
        });

        cb3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean b) {
                if(b){
                    cb1.setChecked(false);
                    cb2.setChecked(false);
                }
            }
        });

    }

    private void EnableCheckBox(){
        cb1.setEnabled(true);
        cb2.setEnabled(true);
        cb3.setEnabled(true);
    }

    private void DisableCheckBox(){
        cb1.setEnabled(false);
        cb2.setEnabled(false);
        cb3.setEnabled(false);
    }

    private void anhXa() {
        txtDiem = (TextView) findViewById(R.id.texviewDiem);
        imgPlay = (ImageButton) findViewById(R.id.buttonPlay);
        cb1 = (CheckBox) findViewById(R.id.checkbox1);
        cb2 = (CheckBox) findViewById(R.id.checkbox2);
        cb3 = (CheckBox) findViewById(R.id.checkbox3);
        sb1 = (SeekBar) findViewById(R.id.seekbar1);
        sb2 = (SeekBar) findViewById(R.id.seekbar2);
        sb3 = (SeekBar) findViewById(R.id.seekbar3);

    }


}