package com.example.mentalcareapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.Switch;
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

    public String idUsertemp = "";
    // Variables de los cuadros de texto del primer registro //
    private EditText EditTextMail;
    private EditText EditTextPassword;
    private EditText EditTextConPassword;
    private EditText EditTextPhone;
    private EditText EditTextSecondPhone;
    private Button ButtonNext;

    private NumberPicker numberPicker;
    private NumberPicker sexPicker;
    private EditText EditTextNames;
    private EditText EditTextLastName;
    private EditText EditTextSecondLastName;


    // VARIABLES QUE GUARDAN EL TEXTO INGRESADO //

    private String Mail = "";
    private String Password = "";
    private String ConPassword = "";
    private String Phone = "";
    private String SecondPhone = "";

    private String Names = "";
    private String LastName = "";
    private String SecondLastName = "";
    private String Age = "";
    private String Sex = "";

    private int typeUser;  // Cliente = 0, Especialisata = 1 //

    private Switch switchTypeUser;
    private LinearLayout infoEspecialista;

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
        ButtonNext = (Button) findViewById(R.id.sRegister_registerbutton);

            //Funciones Firebase
        Auth = FirebaseAuth.getInstance();
        Database = FirebaseDatabase.getInstance().getReference();


        // Codigo del retirado Second Register //

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
        sexPicker = findViewById(R.id.sRegister_sexBox);

        switchTypeUser = (Switch) findViewById(R.id.switchUser);
        infoEspecialista = (LinearLayout) findViewById(R.id.info_especialista);
    }


    public void onClick(View v){
        switch (v.getId()){
            // Tipo de usuario ///
            case R.id.switchUser:
                System.out.println("AAAAAAAAAAAA");

                    if (switchTypeUser.isChecked()) {
                        typeUser = 1;
                        infoEspecialista.setVisibility(LinearLayout.VISIBLE);
                        System.out.println("ENTRA");

                    } else {
                        typeUser = 0;
                        infoEspecialista.setVisibility(LinearLayout.GONE);
                        System.out.println("SALE");
                    }
                break;

            case R.id.sRegister_registerbutton:

                Mail = EditTextMail.getText().toString();
                Password = EditTextPassword.getText().toString();
                ConPassword = EditTextConPassword.getText().toString();
                Phone = EditTextPhone.getText().toString();
                SecondPhone = EditTextSecondPhone.getText().toString();

                Names = EditTextNames.getText().toString();
                LastName = EditTextLastName.getText().toString();
                SecondLastName = EditTextSecondLastName.getText().toString();
                Age = String.valueOf(numberPicker.getValue());

                Sex = String.valueOf(sexselector.getSexArrayList().get(sexPicker.getValue()));


                if (!Mail.isEmpty() && !Password.isEmpty() && !ConPassword.isEmpty() && !Phone.isEmpty() && !Names.isEmpty() && !LastName.isEmpty() &&
                        !SecondLastName.isEmpty() && !Age.isEmpty() && !Sex.isEmpty()){

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

                break;


                //Boton, volver a login//
            case R.id.register_login:

                Intent intent2 = new Intent(RegisterActivity.this, MainActivity.class);
                startActivity(intent2);
                finish();
                break;
        }
    }

    //  CARGAR REGISTRO  //
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

                    Map<String, Object> map2 = new HashMap<>();
                    map2.put("names", Names);
                    map2.put("lastname", LastName);
                    map2.put("secondLastName", SecondLastName);
                    map2.put("Age", Age);
                    map2.put("Sex", Sex);

                    String id = Auth.getCurrentUser().getUid();
                        idUsertemp = id;

                    Database.child("Users").child(id).setValue(map).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task2) {
                            if(task2.isSuccessful()){ }
                            else{
                                Toast.makeText(RegisterActivity.this, "No se crearon los datos correctamente", Toast.LENGTH_SHORT).show();

                            }
                        }
                    });

                    Database.child("Users").child(id).child("info").setValue(map2).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task2) {
                            if(task2.isSuccessful()){

                                Intent intent = new Intent( RegisterActivity.this, MenuActivity.class);
                                startActivity(intent);
                                finish();

                                Toast.makeText(RegisterActivity.this, "Se creo la cuenta con exito", Toast.LENGTH_SHORT).show();
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