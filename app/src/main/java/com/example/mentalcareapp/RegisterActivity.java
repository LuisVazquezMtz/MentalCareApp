package com.example.mentalcareapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
    }

    public void onClick(View v){
        switch (v.getId()){
            case R.id.register_nextbutton:
                Intent intent = new Intent( RegisterActivity.this, SecondRegisterActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.register_login:
                Intent intent2 = new Intent(RegisterActivity.this, MainActivity.class);
                startActivity(intent2);
                finish();
                break;
        }
    }
}