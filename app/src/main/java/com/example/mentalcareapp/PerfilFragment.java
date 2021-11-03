package com.example.mentalcareapp;

import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class PerfilFragment extends Fragment {

        private TextView NombreUsuarioPerfil;
        private DatabaseReference mDataBase;


    DatabaseReference Database;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        //Consulta a base de datos
        View view = inflater.inflate(R.layout.fragment_perfil, container, false);


        NombreUsuarioPerfil = (TextView)view.findViewById(R.id.NombreUsuarioPerfil);
        mDataBase = FirebaseDatabase.getInstance().getReference();


        mDataBase.child("Persona").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {


                if(snapshot.exists()){

                    String nombre = snapshot.child("Nombre").getValue().toString();
                    NombreUsuarioPerfil.setText(nombre);
                    System.out.println(nombre);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        //final consulta

        return inflater.inflate(R.layout.fragment_perfil, container, false);




    }


}


