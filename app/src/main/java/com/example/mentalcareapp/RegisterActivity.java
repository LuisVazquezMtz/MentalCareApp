package com.example.mentalcareapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {

    // Variables de los cuadros de texto del primer registro //
    private EditText EditTextMail;
    private EditText EditTextPassword;
    private EditText EditTextConPassword;
    private EditText EditTextPhone;
    private EditText EditTextSecondPhone;
    private Button ButtonNext;


    // VARIABLES QUE GUARDAN EL TEXTO INGRESADO //

    private String Mail = "";
    private String Password = "";
    private String ConPassword = "";
    private String Phone = "";
    private String SecondPhone = "";

    FirebaseAuth Auth;
    DatabaseReference Database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

            // DEFINCION DE VARIABLES //
        EditTextMail = (EditText) findViewById(R.id.register_mailbox);
        EditTextPassword = (EditText) findViewById(R.id.register_passwordlbox);
        EditTextConPassword = (EditText) findViewById(R.id.register_confirmpasswordlbox);
        EditTextPhone = (EditText) findViewById(R.id.register_phonebox);
        EditTextSecondPhone = (EditText) findViewById(R.id.register_secondphonebox);
        ButtonNext = (Button) findViewById(R.id.register_nextbutton);

        Auth = FirebaseAuth.getInstance();
        Database = FirebaseDatabase.getInstance().getReference();
    }


    public void onClick(View v){
        switch (v.getId()){
            case R.id.register_nextbutton:

                Mail = EditTextMail.getText().toString();
                Password = EditTextPassword.getText().toString();
                ConPassword = EditTextConPassword.getText().toString();
                Phone = EditTextPhone.getText().toString();
                SecondPhone = EditTextSecondPhone.getText().toString();

                if (!Mail.isEmpty() && !Mail.isEmpty() && !Mail.isEmpty() && !Mail.isEmpty()){

                    if(Password.length() >= 6){

                        if (Password.equals(ConPassword)){
                            registerUser();
                        }
                        else {
                            Toast.makeText(RegisterActivity.this, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else {
                        Toast.makeText(RegisterActivity.this, "La contraseña nececita al menos 6 caracteres", Toast.LENGTH_SHORT).show();
                    }
                }
                else{
                    Toast.makeText(RegisterActivity.this, "Faltan datos por llenar", Toast.LENGTH_SHORT).show();
                }

            /*    */

                break;
            case R.id.register_login:
                Intent intent2 = new Intent(RegisterActivity.this, MainActivity.class);
                startActivity(intent2);
                finish();
                break;
        }
    }

    public void registerUser(){
        Auth.createUserWithEmailAndPassword(Mail,Password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){

                    Map<String, Object> map = new HashMap<>();
                    map.put( "mail", Mail);
                    map.put( "password", Password);
                    map.put( "phone", Phone);
                    map.put( "secondPhone", SecondPhone);

                    String id = Auth.getCurrentUser().getUid();

                    Database.child("Users").child(id).setValue(map).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task2) {
                            if(task2.isSuccessful()){

                                Intent intent = new Intent( RegisterActivity.this, SecondRegisterActivity.class);
                                startActivity(intent);
                                finish();
                            }
                            else{
                                Toast.makeText(RegisterActivity.this, "No se crearon los datos correctamente", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

                }
                else{
                    Toast.makeText(RegisterActivity.this, "Error al registrar usuario", Toast.LENGTH_SHORT).show();

                }
            }
        });

    }
}