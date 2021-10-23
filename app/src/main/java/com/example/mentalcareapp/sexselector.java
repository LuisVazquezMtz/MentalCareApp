package com.example.mentalcareapp;

import java.util.ArrayList;

public class sexselector {
    private  static ArrayList<sexselector> SexArrayList;

    private  int id;
    private  String name;

    public sexselector(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public static void initSex(){
        SexArrayList = new ArrayList<>();

        sexselector Masculino = new sexselector(0,"Masculino");
        SexArrayList.add(Masculino);

        sexselector Femenino = new sexselector(1,"Femenino");
        SexArrayList.add(Femenino);

        sexselector Otro = new sexselector(2,"Otro");
        SexArrayList.add(Otro);
    }

    public static ArrayList<sexselector> getSexArrayList() {
        return SexArrayList;
    }

    public static String[] sexnames(){
        String[] names = new String[SexArrayList.size()];
        for (int i = 0; i < SexArrayList.size();i++){
            names[i] = SexArrayList.get(i).name;
        }
        return names;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
