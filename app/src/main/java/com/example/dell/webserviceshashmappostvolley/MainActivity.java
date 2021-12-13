package com.example.dell.webserviceshashmappostvolley;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
EditText id,nombre,equipo,foto;
Button bt1,bt2,bt3,bt4;
String url="";
RequestQueue respuestaService;
ArrayList<String> datos=new ArrayList<>();//listas
ListView lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        relacionarVistas();

        ArrayAdapter adaptador=new ArrayAdapter(this,android.R.layout.simple_spinner_item,datos);
        lista.setAdapter(adaptador);

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(position==0){
                    //Intent intencion = new Intent(this, Segunda.class);
                    //intencion.putExtra("id",position);
                    //startActivity(intencion);
                }
            }
        });

    }
    public void relacionarVistas(){
        id=(EditText)findViewById(R.id.id);
        nombre=(EditText)findViewById(R.id.nombre);
        equipo=(EditText)findViewById(R.id.equipo);
        foto=(EditText)findViewById(R.id.foto);
        bt1=(Button)findViewById(R.id.bt1);
        bt2=(Button)findViewById(R.id.bt2);
        bt3=(Button)findViewById(R.id.bt3);
        bt4=(Button)findViewById(R.id.bt4);
        lista=(ListView)findViewById(R.id.lista);
    }

    public void insercion(View v){
        url="http://172.16.8.108/ejemplos/insercion.php";
        webServices();
    }

    public void busqueda(View v){
        url="http://192.168.43.42/ejemplos/buscar.php?id="+id.getText();

        JsonArrayRequest respuestaBusqueda=new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                JSONObject json=null;

                String datoTemp="";
                for (int i = 0; i <response.length() ; i++) {
                    try{
                        json=response.getJSONObject(i);
                       datoTemp+="Título: "+json.getString("titulo");
                       datoTemp+=" Ponente: " + json.getString("ponente");
                        datoTemp+=" Fecha: " + json.getString("fecha");
                        datos.add(datoTemp);
                        datoTemp="";
                    } catch (JSONException e) {
                        Toast.makeText(getApplicationContext(),"Error en el formato",Toast.LENGTH_SHORT).show();
                    }
                }
                Toast.makeText(getApplicationContext(),datoTemp,Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),error.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });

        respuestaService= Volley.newRequestQueue(this);
        respuestaService.add(respuestaBusqueda);
    }

    public void actualizacion(View v){
        url="http://172.16.11.176/ejemplos/editar.php";
    }

    public void eliminacion(View v){
        url="http://192.168.43.42/ejemplos/eliminar.php";
        StringRequest respuesta3= new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(getApplicationContext(), response, Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "Error de Comunicación", Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> idEliminar= new HashMap<String,String>();
                idEliminar.put("id",id.getText().toString());
                return idEliminar;
            }
        };

        respuestaService=Volley.newRequestQueue(this);
        respuestaService.add(respuesta3);
    }

    private void webServices(){
      //  Toast.makeText(getApplicationContext(),"in",Toast.LENGTH_SHORT).show();
        StringRequest respuesta= new StringRequest(Request.Method.POST,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(getApplicationContext(), response, Toast.LENGTH_SHORT).show();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
               Map<String,String> parametros=new HashMap<String, String>();//mapas de valores
                //parametros.put("id",id.getText().toString());
                parametros.put("nombre",nombre.getText().toString());
                parametros.put("equipo",equipo.getText().toString());
                parametros.put("foto",foto.getText().toString());
                return parametros;
            }
        };
        respuestaService= Volley.newRequestQueue(this);
        respuestaService.add(respuesta);
    }}
