package com.example.lifefite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class Cardio extends AppCompatActivity {

    private Button iniciarCardio;
    private TextView cuentaAtras;
    private CountDownTimer temporizador;
    private boolean enFuncionamiento = false;
    private long tiempoRestante = 0;
    private ProgressBar barraProgreso;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cardio);

        iniciarCardio = findViewById(R.id.iniciarCardio);
        cuentaAtras = findViewById(R.id.cuentaAtras);
        barraProgreso = findViewById(R.id.barraProgresoCardio);
    }

    public void IniciarPausarContador(View v){
        if (!enFuncionamiento){
            String textoCuentaAtras = cuentaAtras.getText().toString();
            String[] tiempo = textoCuentaAtras.split(":");
            if (tiempo.length == 2) {
                int minutos = Integer.parseInt(tiempo[0]);
                int segundos = Integer.parseInt(tiempo[1]);
                int tiempoInicialSegundos = minutos * 60 + segundos;
                iniciarTemporizador(tiempoInicialSegundos * 1000);
            } else {
                Toast.makeText(Cardio.this, "Hubo un error con el temporizador", Toast.LENGTH_SHORT).show();
            }
        } else{
            temporizador.cancel();
            iniciarCardio.setText("Iniciar Cardio");
            enFuncionamiento = false;
        }
    }

    private void iniciarTemporizador(long tiempoInicial) {
        temporizador = new CountDownTimer(tiempoInicial, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                tiempoRestante = millisUntilFinished;
                int segundosRestantes = (int) (millisUntilFinished / 1000);
                int minutosRestantes = segundosRestantes / 60;
                int segundosRestantesMostrar = segundosRestantes % 60;
                cuentaAtras.setText(String.format("%02d:%02d", minutosRestantes, segundosRestantesMostrar));

                //Calculamos el progreso de la barra y lo establecemos
                int progreso = (int) ((tiempoRestante * 100) / (tiempoInicial));
                barraProgreso.setProgress(progreso);
            }

            @Override
            public void onFinish() {
                cuentaAtras.setText("00:00");
                iniciarCardio.setText("Reiniciar");
                enFuncionamiento = false;

                //Restablecemos la barra cuando se reinicia el temporizador
                barraProgreso.setProgress(100);
            }
        };

        temporizador.start();
        iniciarCardio.setText("Pausar");
        enFuncionamiento = true;

        //Configuramos la barra para que en el inicio este llena
        barraProgreso.setProgress(100);
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (enFuncionamiento) {
            temporizador.cancel();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (enFuncionamiento) {
            iniciarTemporizador(tiempoRestante);
        }
    }

    public void volverACalendario(View v){
        Intent intent = new Intent(this, Calendario.class);
        startActivity(intent);
        finish();
    }

    public void irAInfoUsuario(View v){
        Intent intent = new Intent(this, InfoUsuario.class);
        startActivity(intent);
        finish();
    }

    public void irAProgreso(View v){
        Intent intent = new Intent(this, Progreso.class);
        startActivity(intent);
        finish();
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
}
