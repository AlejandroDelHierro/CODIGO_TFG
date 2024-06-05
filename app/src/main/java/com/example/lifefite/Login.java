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

public class Login extends AppCompatActivity {

    Button IniciarSesion;
    EditText correo, contra;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAuth = FirebaseAuth.getInstance();


        correo = findViewById(R.id.textInputEditTextCorreo);
        contra = findViewById(R.id.textInputEditTextContra);

        IniciarSesion = findViewById(R.id.IniciarSesion);

        IniciarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String correoUsuario = correo.getText().toString().trim();
                String contraUsuario = contra.getText().toString().trim();

                if (correoUsuario.isEmpty() || contraUsuario.isEmpty()){
                    Toast.makeText(Login.this, "Ingresa todos los datos", Toast.LENGTH_SHORT).show();

                }else{
                    loginUsuario(correoUsuario, contraUsuario);
                }
            }
        });
    }

    private void loginUsuario(String correoUsuario, String contraUsuario) {
        mAuth.signInWithEmailAndPassword(correoUsuario, contraUsuario).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if(task.isSuccessful()){
                    finish();
                    startActivity(new Intent(Login.this, Inicio.class));
                    Toast.makeText(Login.this, "Inicio de sesión completado", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(Login.this, "Usuario y contraseña no coinciden", Toast.LENGTH_SHORT).show();
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

                Toast.makeText(Login.this, "Error al iniciar sesión", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void irARegistro(View view){
        Intent navegarRegistro = new Intent(this, Registro.class);
        startActivity(navegarRegistro);
    }
}
