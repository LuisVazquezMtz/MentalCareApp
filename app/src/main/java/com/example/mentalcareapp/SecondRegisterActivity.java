package com.example.mentalcareapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.TextView;

import java.util.ArrayList;

public class SecondRegisterActivity extends AppCompatActivity {


    private NumberPicker numberPicker;
    private NumberPicker sexPicker;

    private EditText EditTextNames;
    private EditText EditTextLastName;
    private EditText EditTextSecondLastName;

    //Variables que guardan la info

    private String Names = "";
    private String LastName = "";
    private String SecondLastName = "";
    private String Age = "";
    private String Sex = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_register);

           // Configuracion Numbers Pickers
        numberPicker = (NumberPicker) findViewById(R.id.sRegister_AgeBox);

        numberPicker.setMaxValue(100);
        numberPicker.setMinValue(0);
        numberPicker.setValue(1);

        sexPicker = (NumberPicker) findViewById(R.id.sRegister_sexBox);
        sexselector.initSex();
        sexPicker.setMaxValue(sexselector.getSexArrayList().size()-1);
        sexPicker.setMinValue(0);
        sexPicker.setDisplayedValues(sexselector.sexnames());

            //Defincion de variables
        EditTextNames = (EditText) findViewById(R.id.sRegister_namebox);
        EditTextLastName = (EditText) findViewById(R.id.sRegister_lastnamebox);
        EditTextSecondLastName = (EditText) findViewById(R.id.sRegister_slastnamebox);
        numberPicker =  findViewById(R.id.sRegister_AgeBox);

    }



    public void onClick(View v){
        switch (v.getId()){
            case R.id.sRegister_registerbutton:






                RegisterActivity llamada = new RegisterActivity();



                Intent intent = new Intent( SecondRegisterActivity.this, MenuActivity.class);
                startActivity(intent);
                finish();
                break;

        }
    }


}