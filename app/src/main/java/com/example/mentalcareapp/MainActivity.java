package com.example.mentalcareapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;

public class MainActivity extends AppCompatActivity {

    Button main_loginbutton;
    Button registerbutton;
    private EditText EditTextMail;
    private EditText EditTextPassword;

    private String Mail = "";
    private String Password = "";


//hey

    FirebaseAuth Auth;
    DatabaseReference Database;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Auth = FirebaseAuth.getInstance();

        EditTextMail = (EditText) findViewById(R.id.main_userbox);
        EditTextPassword = (EditText) findViewById(R.id.main_passwordbox);

    }


    private void loguearusuario(){
        Mail = EditTextMail.getText().toString();
        Password = EditTextPassword.getText().toString();

        if (TextUtils.isEmpty(Mail)) {
            Toast.makeText(MainActivity.this, "Debes ingresar el correo electrónico", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(Password)) {
            Toast.makeText(MainActivity.this, "Debes ingresar la contraseña", Toast.LENGTH_SHORT).show();
            return;
        }

        //Logear Usuario
        Auth.signInWithEmailAndPassword(Mail, Password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(MainActivity.this, "Bienvenido de nuevo a MentalCare", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent (MainActivity.this, MenuActivity.class);
                            startActivity(intent);
                            finish();
                        }
                        else {
                            Toast.makeText(MainActivity.this, "Correo y/o contraseña incorrecto", Toast.LENGTH_SHORT).show();

                        }
                    }
                });




    }


    //botones
    public void onClick(View v){
        switch (v.getId()){
            case R.id.main_loginbutton:


                loguearusuario();

                break;

            case R.id.registerbutton:
                Intent intent2 = new Intent( MainActivity.this, RegisterActivity.class);
                startActivity(intent2);
                break;
        }
    }
}

