package com.example.lifefite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class InfoUsuario extends AppCompatActivity {

    TextView nombreUsu, fecha_creacion;
    Button CerrarSesion;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_usuario);
        mAuth = FirebaseAuth.getInstance();

        CerrarSesion = findViewById(R.id.cerrarSesion);
        nombreUsu = findViewById(R.id.nombreUsu);
        fecha_creacion = findViewById(R.id.fecha_creacion);

        //Configuramos el boton para que cuando sea pulsado realice las dos funciones que queremos
        CerrarSesion.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                logOut();
                volverALogin();
            }
        });

        // Obtenemos la instancia de FirebaseAuth
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        // Obtenemos el usuario actualmente autenticado
        FirebaseUser user = mAuth.getCurrentUser();

        // Si el usuario está autenticado, mostramos su nombre en el TextView que dará la bienvenida
        if (user != null) {
            // Obtenemos el nombre de usuario del correo electrónico
            String correo = user.getEmail();
            String nombreUsuario = correo.split("@")[0]; // Obtiene el nombre del usuario desde el principio hasta el @

            // Separamos la primera letra de las demas y la ponemos en mayuscula
            String primeraLetraMayus = nombreUsuario.substring(0, 1).toUpperCase();

            // Juntamos la letra que hemos separado con el resto
            nombreUsuario = primeraLetraMayus + nombreUsuario.substring(1);

            // Cambiamos el texto del textView para obtener el resultado que queremos
            nombreUsu.setText(nombreUsuario);

            // Obtenemos la fecha de creacion del usuario
            long fechaDeCreacion = user.getMetadata().getCreationTimestamp();

            // Le ponemos un formato de fecha
            SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
            String formattedDate = formato.format(new Date(fechaDeCreacion));

            // Establecemos el texto en el textView
            fecha_creacion.setText(formattedDate);
        }
    }

    //Lo pongo en ingles ya que uso mucho la expresion cerrarSesion y puede ser problematico
    private void logOut(){
        mAuth.signOut();
        Toast.makeText(InfoUsuario.this, "Sesion Cerrada correctamente", Toast.LENGTH_SHORT).show();
    }

    private void volverALogin(){
        Intent intent = new Intent(this, Login.class);

        //Ahora limpiamos la aplicacion para que otro usuario pueda acceder a ella sin que interfiera las actividades que ha realizado el antiguo usuario
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    public void volverAInicio(View view) {
        Intent intent = new Intent(this, Inicio.class);
        startActivity(intent);
        finish();
    }

    public void irAFormulario(View view) {
        Intent intent = new Intent(this, FormularioPeso.class);
        startActivity(intent);
        finish();
    }
}
