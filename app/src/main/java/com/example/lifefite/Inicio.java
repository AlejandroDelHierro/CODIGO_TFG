package com.example.lifefite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.imaginativeworld.whynotimagecarousel.CarouselItem;
import org.imaginativeworld.whynotimagecarousel.ImageCarousel;

import java.util.ArrayList;
import java.util.List;

public class Inicio extends AppCompatActivity {

    //Creamos la lista para el carousel
    private List<CarouselItem> videosGym = new ArrayList<>();
    private List<CarouselItem> recetasFit = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);

        //Llamamos a los carousel
        ImageCarousel carousel = findViewById(R.id.carousel);
        ImageCarousel carousel2 = findViewById(R.id.carousel2);
        // Llamamos al textView
        TextView textViewBienvenido = findViewById(R.id.bienvenida);

        //Añadimos a la lista las fotos que queremos en el carousel de videos de gym
        videosGym.add(new CarouselItem( R.drawable.video1 ));
        videosGym.add(new CarouselItem( R.drawable.video2 ));
        videosGym.add(new CarouselItem( R.drawable.video3 ));

        //Añadimos a la lista de recetasFir las imagenes del carousel
        recetasFit.add(new CarouselItem( R.drawable.receta1 ));
        recetasFit.add(new CarouselItem( R.drawable.receta2 ));
        recetasFit.add(new CarouselItem( R.drawable.receta3 ));

        //añadimos los datos
        carousel.addData(videosGym);
        carousel2.addData((recetasFit));

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
            textViewBienvenido.setText("Bienvenido, " + nombreUsuario);
        }
    }

    public void irAInfoUsuario(View view) {
        Intent intent = new Intent(this, InfoUsuario.class);
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
