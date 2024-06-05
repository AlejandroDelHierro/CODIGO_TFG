package com.example.lifefite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class ProgresoEjercicios extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progreso_ejercicios);
    }

    public void irANutricion(View v){
        Intent intent = new Intent(this, Nutricion.class);
        startActivity(intent);
        finish();
    }

    public void irAHome(View view) {
        Intent intent = new Intent(this, Inicio.class);
        startActivity(intent);
        finish();
    }

    public void irACalendario(View view) {
        Intent intent = new Intent(this, Calendario.class);
        startActivity(intent);
        finish();
    }

    public void irAProgreso(View view) {
        Intent intent = new Intent(this, Progreso.class);
        startActivity(intent);
        finish();
    }

    public void irAInfoUsuario(View view) {
        Intent intent = new Intent(this, InfoUsuario.class);
        startActivity(intent);
        finish();
    }

    public void irAChat(View view) {
        Intent intent = new Intent(this, chatIA.class);
        startActivity(intent);
        finish();
    }
}