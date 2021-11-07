package com.example.mentalcareapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;


public class PerfilFragment extends Fragment {


        private DatabaseReference mDataBase;
        private String name;
        private String lastname;
        private String secondlastname;
        private String correo;
        private String numero;
        private String numero2;

        private String direccion;
        private String especializacion;
        private String workplace;
        private String estudios;


        private String TipoCuenta;
        private String TipoUser = "User";

        private LinearLayout InfoProfesional;
        private String idUser;

    DatabaseReference Database;
    FirebaseAuth Auth;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        //Consulta a base de datos
        View view = inflater.inflate(R.layout.fragment_perfil, container, false);

        Auth = FirebaseAuth.getInstance();

        TextView NombreUsuarioPerfil;
        TextView CorreoUsuarioPerfil;
        TextView TelefonoUsuarioPerfil;
        TextView TelefonoCasaUsuarioPerfil;

        TextView WorkplacePerfil;
        TextView EstudiosPerfil;
        TextView EspecializacionPerfil;
        TextView UbicacionPerfil;

        NombreUsuarioPerfil= (TextView)view.findViewById(R.id.NombreUsuarioPerfil);
        CorreoUsuarioPerfil= (TextView)view.findViewById(R.id.CorreoUsuarioPerfil);
        TelefonoUsuarioPerfil= (TextView)view.findViewById(R.id.TelefonoUsuarioPerfil);
        TelefonoCasaUsuarioPerfil= (TextView)view.findViewById(R.id.TelefonoCasaUsuarioPerfil);

        EspecializacionPerfil = (TextView)view.findViewById(R.id.EspecializacionPerfil);
        UbicacionPerfil =  (TextView)view.findViewById(R.id.UbicacionPerfi);
        EstudiosPerfil = (TextView)view.findViewById(R.id.EstudiosPerfil);
        WorkplacePerfil = (TextView)view.findViewById(R.id.WorkplacePerfi);

        InfoProfesional= (LinearLayout)view.findViewById(R.id.info_profesional);

        mDataBase = FirebaseDatabase.getInstance().getReference();

        idUser = Auth.getCurrentUser().getUid();


        //Verificar tipo de cuenta
        mDataBase.child("Users").child(idUser).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    TipoCuenta = snapshot.child("Tipo").getValue().toString();

                    System.out.println("Consulta el tipo de cuenta:"+TipoCuenta);

                    //Consulta de datos is es tipo usuario
                    if(TipoCuenta.equals("User")){
                        System.out.println("Consulta tipos user Correcta");

                        mDataBase.child("Users").child(idUser).addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {

                                if(snapshot.exists()){
                                    name = snapshot.child("Personal Info").child("names").getValue().toString();
                                    lastname = snapshot.child("Personal Info").child("lastname").getValue().toString();
                                    secondlastname = snapshot.child("Personal Info").child("secondLastName").getValue().toString();

                                    correo = snapshot.child("mail").getValue().toString();
                                    numero = snapshot.child("phone").getValue().toString();
                                    numero2 = snapshot.child("secondPhone").getValue().toString();


                                    NombreUsuarioPerfil.setText(name +" "+lastname+" "+secondlastname);
                                    CorreoUsuarioPerfil.setText(correo);
                                    TelefonoUsuarioPerfil.setText(numero);
                                    TelefonoCasaUsuarioPerfil.setText(numero2);

                                }
                            }
                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {
                                System.out.println("Error al cargar el perfil");

                            }
                        });
                    }
                    //final consulta

                    //Consulta si es tipo Admin
                    if(TipoCuenta.equals("Admin")){

                        InfoProfesional.setVisibility(View.VISIBLE);
                        System.out.println("Consulta tipos Admin Correcta");

                        mDataBase.child("Users").child(idUser).addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {

                                if(snapshot.exists()){
                                    name = snapshot.child("Personal Info").child("names").getValue().toString();
                                    lastname = snapshot.child("Personal Info").child("lastname").getValue().toString();
                                    secondlastname = snapshot.child("Personal Info").child("secondLastName").getValue().toString();

                                    correo = snapshot.child("mail").getValue().toString();
                                    numero = snapshot.child("phone").getValue().toString();
                                    numero2 = snapshot.child("secondPhone").getValue().toString();

                                    workplace = snapshot.child("Professional Info").child("Workplace").getValue().toString();
                                    direccion = snapshot.child("Professional Info").child("Address").getValue().toString();
                                    estudios = snapshot.child("Professional Info").child("Studies").getValue().toString();
                                    especializacion = snapshot.child("Professional Info").child("Specialization").getValue().toString();


                                    NombreUsuarioPerfil.setText(name +" "+lastname+" "+secondlastname);
                                    CorreoUsuarioPerfil.setText(correo);
                                    TelefonoUsuarioPerfil.setText(numero);
                                    TelefonoCasaUsuarioPerfil.setText(numero2);

                                    EspecializacionPerfil.setText(especializacion);
                                    UbicacionPerfil.setText(direccion);
                                    WorkplacePerfil.setText(workplace);
                                    EstudiosPerfil.setText(estudios);

                                }
                            }
                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {
                                System.out.println("Error al cargar el perfil");

                            }
                        });
                    }
                    // Final de la consulta

                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    // fin de consulta tipo de datos


        return view;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}


