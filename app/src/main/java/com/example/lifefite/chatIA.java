package com.example.lifefite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;

public class chatIA extends AppCompatActivity {

    private ApiGPT apiGPT;
    private EditText preguntaUsuario;
    private LinearLayout layoutChat;
    private ProgressBar barraProgreso;
    private Button botonMandarPregunta;

    private static final String MESSAGE_TYPE_RESPONSE = "RESPONSE";
    private static final String MESSAGE_TYPE_REQUEST = "REQUEST";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_ia);

        apiGPT = new ApiGPT(this);
        preguntaUsuario = findViewById(R.id.preguntaUsuario);
        layoutChat = findViewById(R.id.layoutResponses);
        barraProgreso = findViewById(R.id.barraProgreso);
        botonMandarPregunta = findViewById(R.id.botonMandarPregunta);

        botonMandarPregunta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String prompt = preguntaUsuario.getText().toString();
                if (!prompt.isEmpty()) {
                    mensajesChat(prompt, MESSAGE_TYPE_REQUEST);
                    preguntaUsuario.setText(""); // Limpiar el contenido del EditText
                    barraProgreso.setVisibility(View.VISIBLE);
                    apiGPT.generarRespuesta(prompt, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            barraProgreso.setVisibility(View.GONE);
                            mensajesChat(response, MESSAGE_TYPE_RESPONSE);
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            barraProgreso.setVisibility(View.GONE);
                            mensajesChat("Error: " + error.getMessage(), MESSAGE_TYPE_RESPONSE);
                        }
                    });
                }
            }
        });
    }

    private void mensajesChat(String mensaje, String tipo) {
        if (mensaje.startsWith("\n\n")) {
            mensaje = mensaje.replaceFirst("\n", "");
        } else if (mensaje.startsWith("\n")) {
            mensaje = mensaje.replaceFirst("\n", "");
        }
        TextView textView = new TextView(this);
        textView.setText(mensaje);

        LinearLayout.LayoutParams parametros = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        parametros.setMargins(16, 8, 16, 8);

        if (MESSAGE_TYPE_REQUEST.equals(tipo)) {
            textView.setBackgroundResource(R.drawable.mensaje_usuario_fondo);
            parametros.gravity = Gravity.END; // Alinear a la derecha
        } else {
            textView.setBackgroundResource(R.drawable.mensaje_ia_fondo);
            parametros.gravity = Gravity.START; // Alinear a la izquierda
            textView.setTypeface(null, Typeface.BOLD_ITALIC);
        }

        textView.setLayoutParams(parametros);
        layoutChat.addView(textView);
    }

    public void irAInicio(View view) {
        Intent intent = new Intent(this, Inicio.class);
        startActivity(intent);
        finish();
    }
}
