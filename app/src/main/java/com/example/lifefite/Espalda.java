package com.example.lifefite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import org.checkerframework.checker.nullness.qual.NonNull;

import java.util.HashMap;
import java.util.Map;

public class Espalda extends AppCompatActivity {

    private ImageView jalonPecho, remoGironda, remoUni, hombro, curl1, curl2, irEjercicio1, irEjercicio2, irEjercicio3, irEjercicio4, irEjercicio5, irEjercicio6;
    private VideoView videoJalon, videoGironda, videoRemoUni, videoHombro, videoCurl1, videoCurl2;
    private TextView ejer1serie1, ejer1serie2, ejer1serie3, textoSerie1Ejer2, textoSerie2Ejer2, textoSerie3Ejer2, textoSerie1Ejer3, textoSerie2Ejer3, textoSerie3Ejer3, textoSerie1Ejer4, textoSerie2Ejer4, textoSerie3Ejer4, textoSerie1Ejer5, textoSerie2Ejer5, textoSerie1Ejer6, textoSerie2Ejer6,pesoEjer6serie1,repeticionesEjer6Serie2,pesoEjer6serie2;
    private EditText repeticionesEjer1, pesoEjer1serie1, repeticionesEjer3, pesoEjer1serie3, repeticionesEjer2, pesoEjer1serie2, repeticionesEjer2Serie1, pesoEjer2serie1, repeticionesEjer2Serie2, pesoEjer2serie2, repeticionesEjer2Serie3, pesoEjer2serie3, repeticionesEjer3Serie1,pesoEjer3serie1,repeticionesEjer3Serie2, pesoEjer3serie2,repeticionesEjer3Serie3,pesoEjer3serie3,repeticionesEjer4Serie1,pesoEjer4serie1,repeticionesEjer4Serie2,pesoEjer4serie2,repeticionesEjer4Serie3,pesoEjer4serie3,repeticionesEjer5Serie1,pesoEjer5serie1, repeticionesEjer5Serie2, pesoEjer5serie2,repeticionesEjer6Serie1;
    private Button guardarEjercicio1, guardarEjercicio2, guardarEjercicio3, guardarEjercicio4, guardarEjercicio5, guardarEjercicio6;
    private LinearLayout datosEjer1Serie1, datosEjer1Serie2, datosEjer1Serie3, datosEjer2Serie1, datosEjer2Serie2, datosEjer2Serie3, datosEjer3Serie1, datosEjer3Serie2, datosEjer3Serie3, datosEjer4Serie1, datosEjer4Serie2, datosEjer4Serie3, datosEjer5Serie1, datosEjer5Serie2, datosEjer6Serie1, datosEjer6Serie2;
    private FirebaseFirestore BBDD;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_espalda);

        // Inicializamos todos los componentes necesarios
        jalonPecho = findViewById(R.id.imagenEjercicio1);
        remoGironda = findViewById(R.id.imagenEjercicio2);
        remoUni = findViewById(R.id.imagenEjercicio3);
        hombro = findViewById(R.id.imagenEjercicio4);
        curl1 = findViewById(R.id.imagenEjercicio5);
        curl2 = findViewById(R.id.imagenEjercicio6);

        //Textos de series
        ejer1serie1 = findViewById(R.id.ejer1serie1);
        ejer1serie2 = findViewById(R.id.ejer1serie2);
        ejer1serie3 = findViewById(R.id.ejer1serie3);
        textoSerie1Ejer2 = findViewById(R.id.textoSerie1Ejer2);
        textoSerie2Ejer2 = findViewById(R.id.textoSerie2Ejer2);
        textoSerie3Ejer2 = findViewById(R.id.textoSerie3Ejer2);
        textoSerie1Ejer3 = findViewById(R.id.textoSerie1Ejer3);
        textoSerie2Ejer3 = findViewById(R.id.textoSerie2Ejer3);
        textoSerie3Ejer3 = findViewById(R.id.textoSerie3Ejer3);
        textoSerie1Ejer4 = findViewById(R.id.textoSerie1Ejer4);
        textoSerie2Ejer4 = findViewById(R.id.textoSerie2Ejer4);
        textoSerie3Ejer4 = findViewById(R.id.textoSerie3Ejer4);
        textoSerie1Ejer5 = findViewById(R.id.textoSerie1Ejer5);
        textoSerie2Ejer5 = findViewById(R.id.textoSerie2Ejer5);
        textoSerie1Ejer6 = findViewById(R.id.textoSerie1Ejer6);
        textoSerie2Ejer6 = findViewById(R.id.textoSerie2Ejer6);

        //Botones de guardar
        guardarEjercicio1 = findViewById(R.id.guardarEjercicio1);
        guardarEjercicio2 = findViewById(R.id.guardarEjercicio2);
        guardarEjercicio3 = findViewById(R.id.guardarEjercicio3);
        guardarEjercicio4 = findViewById(R.id.guardarDatosPeso);
        guardarEjercicio5 = findViewById(R.id.guardarEjercicio5);
        guardarEjercicio6 = findViewById(R.id.guardarEjercicio6);

        //Inserts de las series
        datosEjer1Serie1 = findViewById(R.id.datosEjer1Serie1);
        datosEjer1Serie2 = findViewById(R.id.datosEjer1Serie2);
        datosEjer1Serie3 = findViewById(R.id.datosEjer1Serie3);
        datosEjer2Serie1 = findViewById(R.id.datosEjer2Serie1);
        datosEjer2Serie2 = findViewById(R.id.datosEjer2Serie2);
        datosEjer2Serie3 = findViewById(R.id.datosEjer2Serie3);
        datosEjer3Serie1 = findViewById(R.id.datosEjer3Serie1);
        datosEjer3Serie2 = findViewById(R.id.datosEjer3Serie2);
        datosEjer3Serie3 = findViewById(R.id.datosEjer3Serie3);
        datosEjer4Serie1 = findViewById(R.id.datosEjer4Serie1);
        datosEjer4Serie2 = findViewById(R.id.datosEjer4Serie2);
        datosEjer4Serie3 = findViewById(R.id.datosPesoActual);
        datosEjer5Serie1 = findViewById(R.id.datosEjer5Serie1);
        datosEjer5Serie2 = findViewById(R.id.datosEjer5Serie2);
        datosEjer6Serie1 = findViewById(R.id.datosEjer6Serie1);
        datosEjer6Serie2 = findViewById(R.id.datosEjer6Serie2);

        //Abrir desplegable de los ejercicios
        irEjercicio1 = findViewById(R.id.irEjercicio1);
        irEjercicio2 = findViewById(R.id.irEjercicio2);
        irEjercicio3 = findViewById(R.id.irEjercicio3);
        irEjercicio4 = findViewById(R.id.irEjercicio4);
        irEjercicio5 = findViewById(R.id.irEjercicio5);
        irEjercicio6 = findViewById(R.id.irEjercicio6);

        //Videos de los ejercicios
        videoJalon = findViewById(R.id.videoJalon);
        videoGironda = findViewById(R.id.videoGironda);
        videoRemoUni = findViewById(R.id.videoRemoUni);
        videoHombro = findViewById(R.id.videoHombro);
        videoCurl1 = findViewById(R.id.videoCurl1);
        videoCurl2 = findViewById(R.id.videoCurl2);


        // Inicializamos los EditText por donde pasaran los datos de las repeticiones
        repeticionesEjer1 = findViewById(R.id.repeticionesEjer1);
        pesoEjer1serie1 = findViewById(R.id.pesoEjer1serie1);
        repeticionesEjer3 = findViewById(R.id.repeticionesEjer3);
        pesoEjer1serie3 = findViewById(R.id.pesoEjer1serie3);
        repeticionesEjer2 = findViewById(R.id.repeticionesEjer2);
        pesoEjer1serie2 = findViewById(R.id.pesoEjer1serie2);
        repeticionesEjer2Serie1 = findViewById(R.id.repeticionesEjer2Serie1);
        pesoEjer2serie1 = findViewById(R.id.pesoEjer2serie1);
        repeticionesEjer2Serie2 = findViewById(R.id.repeticionesEjer2Serie2);
        pesoEjer2serie2 = findViewById(R.id.pesoEjer2serie2);
        repeticionesEjer2Serie3 = findViewById(R.id.repeticionesEjer2Serie3);
        pesoEjer2serie3 = findViewById(R.id.pesoEjer2serie3);
        repeticionesEjer3Serie1 = findViewById(R.id.repeticionesEjer3Serie1);
        pesoEjer3serie1 = findViewById(R.id.pesoEjer3serie1);
        repeticionesEjer3Serie2 = findViewById(R.id.repeticionesEjer3Serie2);
        pesoEjer3serie2 = findViewById(R.id.pesoEjer3serie2);
        repeticionesEjer3Serie3 = findViewById(R.id.repeticionesEjer3Serie3);
        pesoEjer3serie3 = findViewById(R.id.pesoEjer3serie3);
        repeticionesEjer4Serie1 = findViewById(R.id.repeticionesEjer4Serie1);
        pesoEjer4serie1 = findViewById(R.id.pesoEjer4serie1);
        repeticionesEjer4Serie2 = findViewById(R.id.repeticionesEjer4Serie2);
        pesoEjer4serie2 = findViewById(R.id.pesoEjer4serie2);
        repeticionesEjer4Serie3 = findViewById(R.id.pesoActualMetrica);
        pesoEjer4serie3 = findViewById(R.id.pesoObjetivoformulario);
        repeticionesEjer5Serie1 = findViewById(R.id.repeticionesEjer5Serie1);
        pesoEjer5serie1 = findViewById(R.id.pesoEjer5serie1);
        repeticionesEjer5Serie2 = findViewById(R.id.repeticionesEjer5Serie2);
        pesoEjer5serie2 = findViewById(R.id.pesoEjer5serie2);
        repeticionesEjer6Serie1 = findViewById(R.id.repeticionesEjer6Serie1);
        pesoEjer6serie1 = findViewById(R.id.pesoEjer6serie1);
        repeticionesEjer6Serie2 = findViewById(R.id.repeticionesEjer6Serie2);
        pesoEjer6serie2 = findViewById(R.id.pesoEjer6serie2);

        //Inicializo la base de datos
        BBDD = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();

        //Llamada a los videos

        //Video del jalon
        String rutaVideoJalon = "videosEspalda/jalon.mp4";

        StorageReference referencia1 = FirebaseStorage.getInstance().getReference().child(rutaVideoJalon);

        referencia1.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {

                videoJalon.setVideoURI(uri);

                MediaController controladorMedia = new MediaController(Espalda.this);
                videoJalon.setMediaController(controladorMedia);
                controladorMedia.setAnchorView(videoJalon);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(Exception e) {
                Log.e("Firebase", "Error al descargar el video ");
            }
        });

        //Video de Remo gironda
        String rutaVideoGironda = "videosEspalda/gironda.mp4";

        StorageReference referencia2 = FirebaseStorage.getInstance().getReference().child(rutaVideoGironda);

        referencia2.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {

                videoGironda.setVideoURI(uri);

                MediaController controladorMedia = new MediaController(Espalda.this);
                videoGironda.setMediaController(controladorMedia);
                controladorMedia.setAnchorView(videoGironda);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(Exception e) {
                Log.e("Firebase", "Error al descargar el video ");
            }
        });

        //Video de Remo Unilateral
        String rutaVideoUni = "videosEspalda/unilateral.mp4";

        StorageReference referencia3 = FirebaseStorage.getInstance().getReference().child(rutaVideoUni);

        referencia3.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {

                videoRemoUni.setVideoURI(uri);

                MediaController controladorMedia = new MediaController(Espalda.this);
                videoRemoUni.setMediaController(controladorMedia);
                controladorMedia.setAnchorView(videoRemoUni);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(Exception e) {
                Log.e("Firebase", "Error al descargar el video ");
            }
        });

        //Video de Hombro
        String rutaVideoHombro = "videosEspalda/posterior.mp4";

        StorageReference referencia4 = FirebaseStorage.getInstance().getReference().child(rutaVideoHombro);

        referencia4.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {

                videoHombro.setVideoURI(uri);

                MediaController controladorMedia = new MediaController(Espalda.this);
                videoHombro.setMediaController(controladorMedia);
                controladorMedia.setAnchorView(videoHombro);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(Exception e) {
                Log.e("Firebase", "Error al descargar el video ");
            }
        });

        //Video de Hombro
        String rutaVideoCurl1 = "videosEspalda/curl1.mp4";

        StorageReference referencia5 = FirebaseStorage.getInstance().getReference().child(rutaVideoCurl1);

        referencia5.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {

                videoCurl1.setVideoURI(uri);

                MediaController controladorMedia = new MediaController(Espalda.this);
                videoCurl1.setMediaController(controladorMedia);
                controladorMedia.setAnchorView(videoCurl1);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(Exception e) {
                Log.e("Firebase", "Error al descargar el video ");
            }
        });

        //Video de Hombro
        String rutaVideoCurl2 = "videosEspalda/curl2.mp4";

        StorageReference referencia6 = FirebaseStorage.getInstance().getReference().child(rutaVideoCurl2);

        referencia6.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {

                videoCurl2.setVideoURI(uri);

                MediaController controladorMedia = new MediaController(Espalda.this);
                videoCurl2.setMediaController(controladorMedia);
                controladorMedia.setAnchorView(videoCurl2);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(Exception e) {
                Log.e("Firebase", "Error al descargar el video ");
            }
        });


        //Hacemos referencia al storage de firebase
        FirebaseStorage almacenamiento = FirebaseStorage.getInstance();
        StorageReference refAlmacenamiento = almacenamiento.getReference();

        //Hacemos referencia a la imagen que queremos mostrar
        StorageReference imagenRef = refAlmacenamiento.child("jalon.png");

        //Descargamos la imagen y si ha funcionado la cargara en el imageView
        imagenRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Glide.with(Espalda.this)
                        .load(uri)
                        .into(jalonPecho);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(Exception e) {
                Log.e("Firebase", "Error al descargar la imagen ");
            }
        });


        StorageReference imagenRef2 = refAlmacenamiento.child("remoGironda.jpg");
        imagenRef2.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Glide.with(Espalda.this)
                        .load(uri)
                        .into(remoGironda);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(Exception e) {
                Log.e("Firebase", "Error al descargar la imagen ");
            }
        });

        StorageReference imagenRef3 = refAlmacenamiento.child("remoUni.png");
        imagenRef3.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Glide.with(Espalda.this)
                        .load(uri)
                        .into(remoUni);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(Exception e) {
                Log.e("Firebase", "Error al descargar la imagen ");
            }
        });

        StorageReference imagenRef4 = refAlmacenamiento.child("hombro.png");
        imagenRef4.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Glide.with(Espalda.this)
                        .load(uri)
                        .into(hombro);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(Exception e) {
                Log.e("Firebase", "Error al descargar la imagen ");
            }
        });

        StorageReference imagenRef5 = refAlmacenamiento.child("curl1.png");
        imagenRef5.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Glide.with(Espalda.this)
                        .load(uri)
                        .into(curl1);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(Exception e) {
                Log.e("Firebase", "Error al descargar la imagen ");
            }
        });

        StorageReference imagenRef6 = refAlmacenamiento.child("curl2.png");
        imagenRef6.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Glide.with(Espalda.this)
                        .load(uri)
                        .into(curl2);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(Exception e) {
                Log.e("Firebase", "Error al descargar la imagen ");
            }
        });


        //Abrir los menus para rellenar los datos de cada ejercicio
        irEjercicio1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                mostrarEjercicio1();
            }
        });

        guardarEjercicio1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertarEjercicioConTresSeries("ejercicio1",
                        repeticionesEjer1.getText().toString(),pesoEjer1serie1.getText().toString(),
                        repeticionesEjer2.getText().toString(),pesoEjer1serie2.getText().toString(),
                        repeticionesEjer3.getText().toString(),pesoEjer1serie3.getText().toString());
            }
        });

        irEjercicio2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                mostrarEjercicio2();
            }
        });

        guardarEjercicio2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertarEjercicioConTresSeries("ejercicio2",
                        repeticionesEjer2Serie1.getText().toString(),pesoEjer2serie1.getText().toString(),
                        repeticionesEjer2Serie2.getText().toString(),pesoEjer2serie2.getText().toString(),
                        repeticionesEjer2Serie3.getText().toString(),pesoEjer2serie3.getText().toString());
            }
        });

        irEjercicio3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                mostrarEjercicio3();
            }
        });

        guardarEjercicio3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertarEjercicioConTresSeries("ejercicio3",
                        repeticionesEjer3Serie1.getText().toString(),pesoEjer3serie1.getText().toString(),
                        repeticionesEjer3Serie2.getText().toString(),pesoEjer3serie2.getText().toString(),
                        repeticionesEjer3Serie3.getText().toString(),pesoEjer3serie3.getText().toString());
            }
        });


        irEjercicio4.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                mostrarEjercicio4();
            }
        });

        guardarEjercicio4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertarEjercicioConTresSeries("ejercicio4",
                        repeticionesEjer4Serie1.getText().toString(),pesoEjer4serie1.getText().toString(),
                        repeticionesEjer4Serie2.getText().toString(),pesoEjer4serie2.getText().toString(),
                        repeticionesEjer4Serie3.getText().toString(),pesoEjer4serie3.getText().toString());
            }
        });

        irEjercicio5.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                mostrarEjercicio5();
            }
        });

        guardarEjercicio5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertarEjercicioConDosSeries("ejercicio5",
                        repeticionesEjer5Serie1.getText().toString(),pesoEjer5serie1.getText().toString(),
                        repeticionesEjer5Serie2.getText().toString(),pesoEjer5serie2.getText().toString());
            }
        });


        irEjercicio6.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                mostrarEjercicio6();
            }
        });

        guardarEjercicio6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertarEjercicioConDosSeries("ejercicio6",
                        repeticionesEjer6Serie1.getText().toString(),pesoEjer6serie1.getText().toString(),
                        repeticionesEjer6Serie2.getText().toString(),pesoEjer6serie2.getText().toString());
            }
        });
    }

    private void ocultarLosRegistros(){
        //Ejercicio 1
        videoJalon.setVisibility(View.GONE);
        ejer1serie1.setVisibility(View.GONE);
        ejer1serie2.setVisibility(View.GONE);
        ejer1serie3.setVisibility(View.GONE);
        guardarEjercicio1.setVisibility(View.GONE);
        datosEjer1Serie1.setVisibility(View.GONE);
        datosEjer1Serie2.setVisibility(View.GONE);
        datosEjer1Serie3.setVisibility(View.GONE);

        //Ejercicio 2
        videoGironda.setVisibility(View.GONE);
        textoSerie1Ejer2.setVisibility(View.GONE);
        textoSerie2Ejer2.setVisibility(View.GONE);
        textoSerie3Ejer2.setVisibility(View.GONE);
        guardarEjercicio2.setVisibility(View.GONE);
        datosEjer2Serie1.setVisibility(View.GONE);
        datosEjer2Serie2.setVisibility(View.GONE);
        datosEjer2Serie3.setVisibility(View.GONE);

        //Ejercicio 3
        videoRemoUni.setVisibility(View.GONE);
        textoSerie1Ejer3.setVisibility(View.GONE);
        textoSerie2Ejer3.setVisibility(View.GONE);
        textoSerie3Ejer3.setVisibility(View.GONE);
        guardarEjercicio3.setVisibility(View.GONE);
        datosEjer3Serie1.setVisibility(View.GONE);
        datosEjer3Serie2.setVisibility(View.GONE);
        datosEjer3Serie3.setVisibility(View.GONE);

        //Ejercicio 4
        videoHombro.setVisibility(View.GONE);
        textoSerie1Ejer4.setVisibility(View.GONE);
        textoSerie2Ejer4.setVisibility(View.GONE);
        textoSerie3Ejer4.setVisibility(View.GONE);
        guardarEjercicio4.setVisibility(View.GONE);
        datosEjer4Serie1.setVisibility(View.GONE);
        datosEjer4Serie2.setVisibility(View.GONE);
        datosEjer4Serie3.setVisibility(View.GONE);

        //Ejercicio 5
        videoCurl1.setVisibility(View.GONE);
        textoSerie1Ejer5.setVisibility(View.GONE);
        textoSerie2Ejer5.setVisibility(View.GONE);
        guardarEjercicio5.setVisibility(View.GONE);
        datosEjer5Serie1.setVisibility(View.GONE);
        datosEjer5Serie2.setVisibility(View.GONE);

        //Ejercicio 6
        videoCurl2.setVisibility(View.GONE);
        textoSerie1Ejer6.setVisibility(View.GONE);
        textoSerie2Ejer6.setVisibility(View.GONE);
        guardarEjercicio6.setVisibility(View.GONE);
        datosEjer6Serie1.setVisibility(View.GONE);
        datosEjer6Serie2.setVisibility(View.GONE);
    }

    private void mostrarEjercicio1(){
        ocultarLosRegistros();
        videoJalon.setVisibility(View.VISIBLE);
        ejer1serie1.setVisibility(View.VISIBLE);
        ejer1serie2.setVisibility(View.VISIBLE);
        ejer1serie3.setVisibility(View.VISIBLE);
        guardarEjercicio1.setVisibility(View.VISIBLE);
        datosEjer1Serie1.setVisibility(View.VISIBLE);
        datosEjer1Serie2.setVisibility(View.VISIBLE);
        datosEjer1Serie3.setVisibility(View.VISIBLE);
    }

    private void mostrarEjercicio2(){
        ocultarLosRegistros();
        videoGironda.setVisibility(View.VISIBLE);
        textoSerie1Ejer2.setVisibility(View.VISIBLE);
        textoSerie2Ejer2.setVisibility(View.VISIBLE);
        textoSerie3Ejer2.setVisibility(View.VISIBLE);
        guardarEjercicio2.setVisibility(View.VISIBLE);
        datosEjer2Serie1.setVisibility(View.VISIBLE);
        datosEjer2Serie2.setVisibility(View.VISIBLE);
        datosEjer2Serie3.setVisibility(View.VISIBLE);
    }

    private void mostrarEjercicio3(){
        ocultarLosRegistros();
        videoRemoUni.setVisibility(View.VISIBLE);
        textoSerie1Ejer3.setVisibility(View.VISIBLE);
        textoSerie2Ejer3.setVisibility(View.VISIBLE);
        textoSerie3Ejer3.setVisibility(View.VISIBLE);
        guardarEjercicio3.setVisibility(View.VISIBLE);
        datosEjer3Serie1.setVisibility(View.VISIBLE);
        datosEjer3Serie2.setVisibility(View.VISIBLE);
        datosEjer3Serie3.setVisibility(View.VISIBLE);
    }

    private void mostrarEjercicio4(){
        ocultarLosRegistros();
        videoHombro.setVisibility(View.VISIBLE);
        textoSerie1Ejer4.setVisibility(View.VISIBLE);
        textoSerie2Ejer4.setVisibility(View.VISIBLE);
        textoSerie3Ejer4.setVisibility(View.VISIBLE);
        guardarEjercicio4.setVisibility(View.VISIBLE);
        datosEjer4Serie1.setVisibility(View.VISIBLE);
        datosEjer4Serie2.setVisibility(View.VISIBLE);
        datosEjer4Serie3.setVisibility(View.VISIBLE);
    }

    private void mostrarEjercicio5(){
        ocultarLosRegistros();
        videoCurl1.setVisibility(View.VISIBLE);
        textoSerie1Ejer5.setVisibility(View.VISIBLE);
        textoSerie2Ejer5.setVisibility(View.VISIBLE);
        guardarEjercicio5.setVisibility(View.VISIBLE);
        datosEjer5Serie1.setVisibility(View.VISIBLE);
        datosEjer5Serie2.setVisibility(View.VISIBLE);
    }

    private void mostrarEjercicio6(){
        ocultarLosRegistros();
        videoCurl2.setVisibility(View.VISIBLE);
        textoSerie1Ejer6.setVisibility(View.VISIBLE);
        textoSerie2Ejer6.setVisibility(View.VISIBLE);
        guardarEjercicio6.setVisibility(View.VISIBLE);
        datosEjer6Serie1.setVisibility(View.VISIBLE);
        datosEjer6Serie2.setVisibility(View.VISIBLE);
    }

    //Creo la funcion que recogera los datos de los ejercicios, la funcion tendra que recoger 5 valores externos para efectuarse
    private void insertarEjercicioConTresSeries(String nombreEjercicio, String rep1, String peso1, String rep2, String peso2, String rep3, String peso3) {
        if (rep1.isEmpty() || peso1.isEmpty() || rep2.isEmpty() || peso2.isEmpty() || rep3.isEmpty() || peso3.isEmpty()) {
            Toast.makeText(Espalda.this, "Por favor, rellene todos los datos", Toast.LENGTH_SHORT).show();
            return;
        }

        String idUsuario = auth.getCurrentUser().getUid();

        Map<String, Object> datosEjercicio = new HashMap<>();
        datosEjercicio.put("fecha", FieldValue.serverTimestamp());
        datosEjercicio.put("idUsuario", idUsuario);

        Map<String, Object> serie1 = new HashMap<>();
        serie1.put("repeticiones", rep1);
        serie1.put("peso", peso1);

        Map<String, Object> serie2 = new HashMap<>();
        serie2.put("repeticiones", rep2);
        serie2.put("peso", peso2);

        Map<String, Object> serie3 = new HashMap<>();
        serie3.put("repeticiones", rep3);
        serie3.put("peso", peso3);

        datosEjercicio.put("serie1", serie1);
        datosEjercicio.put("serie2", serie2);
        datosEjercicio.put("serie3", serie3);

        BBDD.collection("ejerciciosEspalda").document(nombreEjercicio).set(datosEjercicio).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(Espalda.this, "Ejercicio guardado", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(Espalda.this, "Error al guardar ejercicio", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void insertarEjercicioConDosSeries(String nombreEjercicio, String rep1, String peso1, String rep2, String peso2) {
        if (rep1.isEmpty() || peso1.isEmpty() || rep2.isEmpty() || peso2.isEmpty()) {
            Toast.makeText(Espalda.this, "Por favor, rellene todos los datos", Toast.LENGTH_SHORT).show();
            return;
        }

        String idUsuario = auth.getCurrentUser().getUid();

        Map<String, Object> datosEjercicio = new HashMap<>();
        datosEjercicio.put("fecha", FieldValue.serverTimestamp());
        datosEjercicio.put("idUsuario", idUsuario);

        Map<String, Object> serie1 = new HashMap<>();
        serie1.put("repeticiones", rep1);
        serie1.put("peso", peso1);

        Map<String, Object> serie2 = new HashMap<>();
        serie2.put("repeticiones", rep2);
        serie2.put("peso", peso2);

        datosEjercicio.put("serie1", serie1);
        datosEjercicio.put("serie2", serie2);

        BBDD.collection("ejerciciosEspalda").document(nombreEjercicio).set(datosEjercicio).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(Espalda.this, "Ejercicio guardado", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(Espalda.this, "Error al guardar ejercicio", Toast.LENGTH_SHORT).show();
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