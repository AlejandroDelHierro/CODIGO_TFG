package com.example.lifefite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import org.checkerframework.checker.nullness.qual.NonNull;

public class Progreso extends AppCompatActivity {

    private TextView pesoInicio, pesoActual, pesoObjetivo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progreso);

        pesoInicio = findViewById(R.id.pesoInicio);
        pesoActual = findViewById(R.id.pesoActual);
        pesoObjetivo = findViewById(R.id.pesoObjetivo);

        FirebaseFirestore db = FirebaseFirestore.getInstance();

        // Obtener la referencia al documento del usuario actual en la colección 'peso'
        DocumentReference docRef = db.collection("peso").document("pesoUsuario");

        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        // Obtener los valores del documento
                        String pesoInicioValue = document.getString("pesoInicio");
                        String pesoActualValue = document.getString("pesoActual");
                        String pesoObjetivoValue = document.getString("pesoObjetivo");

                        // Establecer los valores en los TextView
                        pesoInicio.setText(pesoInicioValue + " Kg");
                        pesoActual.setText(pesoActualValue + " Kg");
                        pesoObjetivo.setText(pesoObjetivoValue + " Kg");
                    } else {
                        Log.d("Progreso", "No existe el documento");
                    }
                } else {
                    Log.d("Progreso", "Hubo un fallo", task.getException());
                }
            }
        });

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
        Intent intent = new Intent(this, ProgresoEjercicios.class);
        startActivity(intent);
        finish();
    }

    public void irAProgresoEjercicios(View view) {
        Intent intent = new Intent(this, ProgresoEjercicios.class);
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