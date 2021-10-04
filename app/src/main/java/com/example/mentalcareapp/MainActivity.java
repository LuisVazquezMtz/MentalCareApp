package com.example.mentalcareapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button main_loginbutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

        //Metodo para navehar recomendado

    // XDDDDD

    //botones
    public void onClick(View v){
        switch (v.getId()){
            case R.id.main_loginbutton:
                Intent intent = new Intent (MainActivity.this, MenuActivity.class);
                startActivity(intent);
                finish();
                break;

            case R.id.registerbutton:
                Intent intent2 = new Intent( MainActivity.this, RegisterActivity.class);
                startActivity(intent2);
                break;
        }
    }


}