package com.example.mentalcareapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.NumberPicker;
import android.widget.TextView;

import java.util.ArrayList;

public class SecondRegisterActivity extends AppCompatActivity {


    private NumberPicker numberPicker;
    private NumberPicker sexPicker;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_register);

        numberPicker = (NumberPicker) findViewById(R.id.sRegister_AgeBox);

        numberPicker.setMaxValue(100);
        numberPicker.setMinValue(0);
        numberPicker.setValue(1);

        sexPicker = (NumberPicker) findViewById(R.id.sRegister_sexBox);
        sexselector.initSex();
        sexPicker.setMaxValue(sexselector.getSexArrayList().size()-1);
        sexPicker.setMinValue(0);
        sexPicker.setDisplayedValues(sexselector.sexnames());


    }



    public void onClick(View v){
        switch (v.getId()){
            case R.id.sRegister_registerbutton:
                Intent intent = new Intent( SecondRegisterActivity.this, MenuActivity.class);
                startActivity(intent);
                finish();
                break;

        }
    }


}