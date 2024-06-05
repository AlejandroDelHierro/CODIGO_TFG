package com.example.lifefite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;

import org.checkerframework.checker.nullness.qual.NonNull;

import java.util.HashMap;
import java.util.Map;

public class FormularioPeso extends AppCompatActivity {

    private EditText pesoActualFormulario, pesoObjetivoformulario;
    private Button guardarDatosPeso, irAFormulario;
    private FirebaseFirestore BBDD;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_peso);

        pesoActualFormulario = findViewById(R.id.pesoActualMetrica);
        pesoObjetivoformulario = findViewById(R.id.pesoObjetivoformulario);
        irAFormulario = findViewById(R.id.irAFormulario);

        guardarDatosPeso = findViewById(R.id.guardarDatosPeso);

        BBDD = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();

        guardarDatosPeso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertarPesoUsuario(pesoActualFormulario.getText().toString(),
                        pesoObjetivoformulario.getText().toString());
            }
        });

    }


    private void insertarPesoUsuario(String pesoInicio, String pesoObjetivo) {
        if (pesoInicio.isEmpty() || pesoObjetivo.isEmpty()) {
            Toast.makeText(FormularioPeso.this, "Por favor, rellene todos los datos", Toast.LENGTH_SHORT).show();
            return;
        }

        // Obtener el usuario actual
        FirebaseUser currentUser = auth.getCurrentUser();
        if (currentUser == null) {
            Toast.makeText(FormularioPeso.this, "Error: No se ha autenticado el usuario", Toast.LENGTH_SHORT).show();
            return;
        }
        String idUsuario = currentUser.getUid();

        // Crear un mapa para los datos del peso
        Map<String, Object> datosPeso = new HashMap<>();
        datosPeso.put("pesoInicio", pesoInicio);
        datosPeso.put("pesoObjetivo", pesoObjetivo);
        datosPeso.put("fecha", FieldValue.serverTimestamp());
        datosPeso.put("usuarioID", idUsuario);

        // Insertar o actualizar los datos en la base de datos
        BBDD.collection("peso").document("pesoUsuario").set(datosPeso, SetOptions.merge()).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(FormularioPeso.this, "Datos de peso guardados", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(FormularioPeso.this, "Error al guardar datos de peso", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void volverAInfo(View view) {
        Intent intent = new Intent(this, InfoUsuario.class);
        startActivity(intent);
        finish();
    }
}