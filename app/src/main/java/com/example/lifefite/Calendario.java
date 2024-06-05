package com.example.lifefite;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class Calendario extends AppCompatActivity {

    private TextView cogerFecha;
    private Button botonLunes, botonMartes, botonMiercoles, botonJueves, botonViernes, botonSabado, botonDomingo;
    private ImageView cardioFoto, dietaFoto, rutinaFoto, metricasFoto, fotoRutinaPierna, fotoRutinaEspalda;
    private Button irACardio, irADieta, irARutina, buttonMetricas, irAPierna, irAEspalda;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendario);

        // Inicializar TextView para mostrar la fecha actual
        cogerFecha = findViewById(R.id.cogerFecha);

        // Obtener fecha actual y mostrarla en el TextView
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        String fechaActual = dateFormat.format(calendar.getTime());
        cogerFecha.setText(fechaActual);

        // Inicializo los botones de los días
        botonLunes = findViewById(R.id.lunes);
        botonMartes = findViewById(R.id.martes);
        botonMiercoles = findViewById(R.id.miercoles);
        botonJueves = findViewById(R.id.jueves);
        botonViernes = findViewById(R.id.viernes);
        botonSabado = findViewById(R.id.sabado);
        botonDomingo = findViewById(R.id.domingo);

        // Inicializo las imágenes
        cardioFoto = findViewById(R.id.cardioFoto);
        dietaFoto = findViewById(R.id.dietaFoto);
        rutinaFoto = findViewById(R.id.rutinaFoto);
        metricasFoto = findViewById(R.id.metricasFoto);
        fotoRutinaPierna = findViewById(R.id.piernaFoto);
        fotoRutinaEspalda = findViewById(R.id.espaldaFoto);

        // Inicializo los botones de las distintas actividades
        irACardio = findViewById(R.id.irACardio);
        irADieta = findViewById(R.id.irADieta);
        irARutina = findViewById(R.id.irARutina);
        buttonMetricas = findViewById(R.id.button8);
        irAPierna = findViewById(R.id.irAPierna);
        irAEspalda = findViewById(R.id.irAEspalda);


        //Hago onClick en los diferentes botones para llamar a las funciones y poder mostrar los elementos de cada dia
        botonLunes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mostrarOpcionesParaLunes();
            }
        });

        botonMartes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mostrarOpcionesParaMartes();
            }
        });

        botonMiercoles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mostrarOpcionesParaMiercoles();
            }
        });

        botonJueves.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mostrarOpcionesParaJueves();
            }
        });

        botonViernes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mostrarOpcionesParaViernes();
            }
        });

        botonSabado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mostrarOpcionesParaSabado();
            }
        });

        botonDomingo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mostrarOpcionesParaDomingo();
            }
        });

        // Ocultar todas las opciones al inicio
        ocultarTodasLasOpciones();

        // Mostrar opciones iniciales
        cardioFoto.setVisibility(View.VISIBLE);
        irACardio.setVisibility(View.VISIBLE);
        dietaFoto.setVisibility(View.VISIBLE);
        irADieta.setVisibility(View.VISIBLE);
        rutinaFoto.setVisibility(View.VISIBLE);
        irARutina.setVisibility(View.VISIBLE);
        metricasFoto.setVisibility(View.GONE);
        buttonMetricas.setVisibility(View.GONE);
        irAPierna.setVisibility(View.GONE);
        fotoRutinaPierna.setVisibility(View.GONE);
        irAEspalda.setVisibility(View.GONE);
        fotoRutinaEspalda.setVisibility(View.GONE);
    }

    private void ocultarTodasLasOpciones() {
        cardioFoto.setVisibility(View.GONE);
        dietaFoto.setVisibility(View.GONE);
        rutinaFoto.setVisibility(View.GONE);
        metricasFoto.setVisibility(View.GONE);
        irACardio.setVisibility(View.GONE);
        irADieta.setVisibility(View.GONE);
        irARutina.setVisibility(View.GONE);
        buttonMetricas.setVisibility(View.GONE);
        irAPierna.setVisibility(View.GONE);
        fotoRutinaPierna.setVisibility(View.GONE);
        irAEspalda.setVisibility(View.GONE);
        fotoRutinaEspalda.setVisibility(View.GONE);
    }

    private void mostrarOpcionesParaLunes() {
        ocultarTodasLasOpciones();
        //Imagenes
        cardioFoto.setVisibility(View.VISIBLE);
        dietaFoto.setVisibility(View.VISIBLE);
        rutinaFoto.setVisibility(View.VISIBLE);

        //Botones
        irACardio.setVisibility(View.VISIBLE);
        irADieta.setVisibility(View.VISIBLE);
        irARutina.setVisibility(View.VISIBLE);
    }

    private void mostrarOpcionesParaMartes() {
        ocultarTodasLasOpciones();
        cardioFoto.setVisibility(View.VISIBLE);
        dietaFoto.setVisibility(View.VISIBLE);

        irACardio.setVisibility(View.VISIBLE);
        irADieta.setVisibility(View.VISIBLE);
    }

    private void mostrarOpcionesParaMiercoles() {
        ocultarTodasLasOpciones();
        cardioFoto.setVisibility(View.VISIBLE);
        dietaFoto.setVisibility(View.VISIBLE);
        fotoRutinaPierna.setVisibility(View.VISIBLE);

        irACardio.setVisibility(View.VISIBLE);
        irADieta.setVisibility(View.VISIBLE);
        irAPierna.setVisibility(View.VISIBLE);
    }

    private void mostrarOpcionesParaJueves() {
        ocultarTodasLasOpciones();
        cardioFoto.setVisibility(View.VISIBLE);
        dietaFoto.setVisibility(View.VISIBLE);

        irACardio.setVisibility(View.VISIBLE);
        irADieta.setVisibility(View.VISIBLE);
    }

    private void mostrarOpcionesParaViernes() {
        ocultarTodasLasOpciones();
        cardioFoto.setVisibility(View.VISIBLE);
        dietaFoto.setVisibility(View.VISIBLE);
        fotoRutinaEspalda.setVisibility(View.VISIBLE);

        irACardio.setVisibility(View.VISIBLE);
        irADieta.setVisibility(View.VISIBLE);
        irAEspalda.setVisibility(View.VISIBLE);
    }

    private void mostrarOpcionesParaSabado() {
        ocultarTodasLasOpciones();
        cardioFoto.setVisibility(View.VISIBLE);
        dietaFoto.setVisibility(View.VISIBLE);
        metricasFoto.setVisibility(View.VISIBLE);

        irACardio.setVisibility(View.VISIBLE);
        irADieta.setVisibility(View.VISIBLE);
        buttonMetricas.setVisibility(View.VISIBLE);
    }

    private void mostrarOpcionesParaDomingo() {
        ocultarTodasLasOpciones();
        cardioFoto.setVisibility(View.VISIBLE);
        dietaFoto.setVisibility(View.VISIBLE);

        irACardio.setVisibility(View.VISIBLE);
        irADieta.setVisibility(View.VISIBLE);
    }



    public void irAHome(View view) {
        Intent intent = new Intent(this, Inicio.class);
        startActivity(intent);
        finish();
    }

    public void irACardio(View v){
        Intent intent = new Intent(this, Cardio.class);
        startActivity(intent);
        finish();
    }

    public void irANutricion(View v){
        Intent intent = new Intent(this, Nutricion.class);
        startActivity(intent);
        finish();
    }

    //Falta por hacer el xml
    public void irARutina(View v){
        Intent intent = new Intent(this, Inicio.class);
        startActivity(intent);
        finish();
    }
    public void irAProgreso(View v){
        Intent intent = new Intent(this, Progreso.class);
        startActivity(intent);
        finish();
    }

    public void irADieta(View v){
        Intent intent = new Intent(this, Dieta.class);
        startActivity(intent);
        finish();
    }

    public void irAMedirPeso(View v){
        Intent intent = new Intent(this, Metrica.class);
        startActivity(intent);
        finish();
    }

    public void irAInfoUsuario(View v){
        Intent intent = new Intent(this, InfoUsuario.class);
        startActivity(intent);
        finish();
    }

    public void irAPecho(View v){
        Intent intent = new Intent(this, Pecho.class);
        startActivity(intent);
        finish();
    }

    public void irAPierna(View v){
        Intent intent = new Intent(this, Pierna.class);
        startActivity(intent);
        finish();
    }

    public void IrAEspalda(View v){
        Intent intent = new Intent(this, Espalda.class);
        startActivity(intent);
        finish();
    }

    public void irAChat(View view) {
        Intent intent = new Intent(this, chatIA.class);
        startActivity(intent);
        finish();
    }
}
