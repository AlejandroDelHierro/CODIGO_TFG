package com.example.lifefite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import org.imaginativeworld.whynotimagecarousel.CarouselItem;
import org.imaginativeworld.whynotimagecarousel.ImageCarousel;

import java.util.ArrayList;
import java.util.List;

public class Dieta extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dieta);


    }

    public void volverACalendario(View v){
        Intent intent = new Intent(this, Calendario.class);
        startActivity(intent);
        finish();
    }
}