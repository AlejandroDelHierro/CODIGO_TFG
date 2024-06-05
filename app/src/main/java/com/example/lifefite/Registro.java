package com.example.lifefite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import org.checkerframework.checker.nullness.qual.NonNull;

public class Registro extends AppCompatActivity {

    EditText correoRegistro, contraRegistro;
    Button Registrarse;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        mAuth = FirebaseAuth.getInstance();

        correoRegistro = findViewById(R.id.textInputEditTextCorreo);
        contraRegistro = findViewById(R.id.textInputEditTextContra);
        Registrarse = findViewById(R.id.Registrarse);

        if (Registrarse != null) {
            Registrarse.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String correoUsuario = correoRegistro.getText().toString().trim();
                    String contraUsuario = contraRegistro.getText().toString().trim();

                    if (correoUsuario.isEmpty() || contraUsuario.isEmpty()) {
                        Toast.makeText(Registro.this, "Por favor, completa todos los campos", Toast.LENGTH_SHORT).show();
                    } else {
                        registrarUsuario(correoUsuario, contraUsuario);
                    }
                }
            });
        }
    }

    private void registrarUsuario(String correoUsuario, String contraUsuario) {
        mAuth.createUserWithEmailAndPassword(correoUsuario, contraUsuario)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(Registro.this, "Usuario registrado exitosamente", Toast.LENGTH_SHORT).show();
                            irALogin();
                        } else {
                            Toast.makeText(Registro.this, "Error al registrar el usuario", Toast.LENGTH_SHORT).show();
                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(Registro.this, "Error al registrar el usuario", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    public void irALogin() {
        Intent intent = new Intent(this, Login.class);
        startActivity(intent);
        finish();
    }
}
