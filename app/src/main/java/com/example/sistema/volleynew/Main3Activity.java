package com.example.sistema.volleynew;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import android.widget.ArrayAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import java.util.HashMap;
import java.util.Map;

public class Main3Activity extends AppCompatActivity {

    RequestQueue requestQueue;
    String showUrl = "http://luisperalta.com/serviciowebapp/showStudents.php";
    ListView listaResultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);


        listaResultado = (ListView)findViewById(R.id.lista);

        requestQueue = Volley.newRequestQueue(getApplicationContext());


        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST,
                showUrl, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                System.out.println(response.toString());
                try {
                    JSONArray students = response.getJSONArray("students");
                    ArrayList<String> lista = new ArrayList<>();
                    for (int i = 0; i < students.length(); i++) {
                        JSONObject student = students.getJSONObject(i);

                        String firstname = student.getString("firstname");
                        String lastname = student.getString("lastname");
                        String age = student.getString("age");

                        //result.append(firstname + " " + lastname + " " + age + " \n");

                        lista.add(firstname + " " + lastname + " " + age);
                    }
                    //result.append("===\n");

                    ArrayAdapter<String> adaptador = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, lista);
                    listaResultado.setAdapter(adaptador);

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.append(error.getMessage());

            }
        });
        requestQueue.add(jsonObjectRequest);

    }
}
