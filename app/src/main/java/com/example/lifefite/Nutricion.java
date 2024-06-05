package com.example.lifefite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import org.imaginativeworld.whynotimagecarousel.CarouselItem;
import org.imaginativeworld.whynotimagecarousel.ImageCarousel;

import java.util.ArrayList;
import java.util.List;

public class Nutricion extends AppCompatActivity {

    //Reciclo el codigho del inicio para hacer el carrusel de las recetas
    private List<CarouselItem> recetasFit = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nutricion);

        ImageCarousel carousel2 = findViewById(R.id.carousel2);

        //Añadimos a la lista de recetasFir las imagenes del carousel
        recetasFit.add(new CarouselItem( R.drawable.receta1 ));
        recetasFit.add(new CarouselItem( R.drawable.receta2 ));
        recetasFit.add(new CarouselItem( R.drawable.receta3 ));

        //añadimos los datos
        carousel2.addData((recetasFit));
    }

    public void irAInfoUsuario(View v) {
        Intent intent = new Intent(this, InfoUsuario.class);
        startActivity(intent);
        finish();
    }

    public void irAHome(View v) {
        Intent intent = new Intent(this, Inicio.class);
        startActivity(intent);
        finish();
    }

    public void irADieta(View v) {
        Intent intent = new Intent(this, Dieta.class);
        startActivity(intent);
        finish();
    }

    public void irACalenadario(View view) {
        Intent intent = new Intent(this, Calendario.class);
        startActivity(intent);
        finish();
    }

    public void IrAProgreso(View view) {
        Intent intent = new Intent(this, Progreso.class);
        startActivity(intent);
        finish();
    }

    public void irANutricion(View view) {
        Intent intent = new Intent(this, Nutricion.class);
        startActivity(intent);
        finish();
    }

    public void irAChat(View view) {
        Intent intent = new Intent(this, chatIA.class);
        startActivity(intent);
        finish();
    }
}