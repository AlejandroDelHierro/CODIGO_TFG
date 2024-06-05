package com.example.lifefite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;

import org.checkerframework.checker.nullness.qual.NonNull;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class Metrica extends AppCompatActivity {

    private TextView fechaActual;
    private Button guardarMetrica;
    private EditText pesoActualMetrica;
    private FirebaseFirestore BBDD;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_metrica);

        fechaActual = findViewById(R.id.fechaActual);

        guardarMetrica = findViewById(R.id.guardarMetrica);

        pesoActualMetrica = findViewById(R.id.pesoActualMetrica);

        BBDD = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();

        // Obtener fecha actual y mostrarla en el TextView
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        String cogerFecha = dateFormat.format(calendar.getTime());
        fechaActual.setText(cogerFecha);

        guardarMetrica.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertarPesoUsuario(pesoActualMetrica.getText().toString());
            }
        });
    }

    private void insertarPesoUsuario(String pesoActual) {
        if (pesoActual.isEmpty()) {
            Toast.makeText(Metrica.this, "Por favor, rellene todos los datos", Toast.LENGTH_SHORT).show();
            return;
        }

        // Obtener el usuario actual
        FirebaseUser currentUser = auth.getCurrentUser();
        if (currentUser == null) {
            Toast.makeText(Metrica.this, "Error: No se ha autenticado el usuario", Toast.LENGTH_SHORT).show();
            return;
        }
        String idUsuario = currentUser.getUid();

        // Crear un mapa para los datos del peso
        Map<String, Object> datosPeso = new HashMap<>();
        datosPeso.put("pesoActual", pesoActual);
        datosPeso.put("idUsuario", idUsuario);

        // Insertar o actualizar los datos en la base de datos
        BBDD.collection("peso").document("pesoUsuario").set(datosPeso, SetOptions.merge()).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(Metrica.this, "Datos de peso guardados", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(Metrica.this, "Error al guardar datos de peso", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void volverACalendario(View v){
        Intent intent = new Intent(this, Calendario.class);
        startActivity(intent);
        finish();
    }
}