package com.example.sistema.volleynew;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    EditText firstname, lastname, age;
    Button insert, show, mostrarlista;
    RequestQueue requestQueue;
    String insertUrl = "http://luisperalta.com/serviciowebapp/insertStudent.php";
    String showUrl = "http://luisperalta.com/serviciowebapp/showStudents.php";
    TextView result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firstname = (EditText) findViewById(R.id.editText);
        lastname = (EditText) findViewById(R.id.editText2);
        age = (EditText) findViewById(R.id.editText3);
        insert = (Button) findViewById(R.id.insert);
        show = (Button) findViewById(R.id.showstudents);
        mostrarlista = (Button) findViewById(R.id.liststudents);
        result = (TextView) findViewById(R.id.textView);


        requestQueue = Volley.newRequestQueue(getApplicationContext());


        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StringRequest request = new StringRequest(Request.Method.POST, insertUrl, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        System.out.println(response.toString());
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }) {

                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String,String> parameters  = new HashMap<String, String>();
                        parameters.put("firstname",firstname.getText().toString());
                        parameters.put("lastname",lastname.getText().toString());
                        parameters.put("age",age.getText().toString());

                        return parameters;
                    }
                };
                requestQueue.add(request);
            }

        });

        show.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {


                Intent intent = new Intent(getApplicationContext(),Main2Activity.class);

                startActivity(intent);

            }
        });

        mostrarlista.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {


                Intent intent = new Intent(getApplicationContext(),Main3Activity.class);

                startActivity(intent);

            }
        });
    }
}
