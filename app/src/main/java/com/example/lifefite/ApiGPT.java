package com.example.lifefite;

import android.content.Context;
import android.util.Log;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

// Esta clase encapsula la llamada a la API
public class ApiGPT {

    // URL del servicio
    private static final String BASE_URL = "https://api.openai.com/v1/chat/completions";

    // Cola de respuestas
    private RequestQueue colaEspera;

    // Prompt inicial que define el contexto
    private static final String INITIAL_PROMPT = "Eres un asistente de fitness experto. Responde solo preguntas relacionadas con ejercicios, nutrición, y salud física.";

    public ApiGPT(Context context) {
        colaEspera = Volley.newRequestQueue(context);
    }

    public void generarRespuesta(String promptUsuario, Response.Listener<String> listener, Response.ErrorListener errorListener) {
        try {
            JSONObject estructura = new JSONObject();
            estructura.put("model", "gpt-3.5-turbo");
            estructura.put("temperature", 1);

            // Crear el array de mensajes con el prompt inicial y el mensaje del usuario
            JSONArray mensajesArray = new JSONArray();

            // Añadir el mensaje inicial
            JSONObject mensajeInicial = new JSONObject();
            mensajeInicial.put("role", "system");
            mensajeInicial.put("content", INITIAL_PROMPT);
            mensajesArray.put(mensajeInicial);

            // Añadir el mensaje del usuario
            JSONObject mensajeUsuario = new JSONObject();
            mensajeUsuario.put("role", "user");
            mensajeUsuario.put("content", promptUsuario);
            mensajesArray.put(mensajeUsuario);

            estructura.put("messages", mensajesArray);

            JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, BASE_URL, estructura,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                JSONObject mensaje = response.getJSONArray("choices")
                                        .getJSONObject(0)
                                        .getJSONObject("message");
                                String text = mensaje.getString("content");
                                listener.onResponse(text);
                            } catch (JSONException e) {
                                Log.e("Gpt3Api", "Error parseando respuesta", e);
                                errorListener.onErrorResponse(new VolleyError("Error parseando respuesta"));
                            }
                        }
                    }, errorListener) {
                @Override
                public Map<String, String> getHeaders() {
                    Map<String, String> headers = new HashMap<>();
                    headers.put("Authorization", "Bearer " + ConfigChat.Api_Key);
                    return headers;
                }
            };
            request.setRetryPolicy(new DefaultRetryPolicy(
                    6000,
                    DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                    DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

            colaEspera.add(request);

        } catch (JSONException e) {
            Log.e("Gpt3Api", "Error creando el request body", e);
            errorListener.onErrorResponse(new VolleyError("Error creando request"));
        }
    }
}
