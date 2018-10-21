package com.example.najah.test;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RelativeLayout;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class AddInsActivity extends AppCompatActivity {
    private Button addIsn;
    private EditText nom;
    private EditText adresse;
    private EditText email;
    private EditText tel;
    private String urlpost;
   private RelativeLayout rootView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_ins);
        nom=(EditText) findViewById(R.id.nom);
        adresse=(EditText) findViewById(R.id.adresse);
        email=(EditText) findViewById(R.id.email);
        tel=(EditText) findViewById(R.id.tel);
        addIsn=(Button) findViewById(R.id.buttonCnx);
        addIsn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              ajouterins();

            }
        });






    }

    public void ajouterins(){
        urlpost = "http://10.0.2.2:18080/cga-web/pi/Assurance?idAgent=3";

                Map<String,String> params = new HashMap<String, String>();
                params.put("nom",nom.getText().toString());
                params.put("email",email.getText().toString());
                params.put("adresse",adresse.getText().toString());
                params.put("telephone",tel.getText().toString());

               JsonObjectRequest jsonRequest = new JsonObjectRequest (Request.Method.POST, urlpost,new JSONObject(params),
                        new Response.Listener<JSONObject>() {

                            @Override
                            public void onResponse(JSONObject response) {


                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {

                                Intent list=new Intent(AddInsActivity.this,InsuranceActivity.class);
                                startActivity(list);
                            }
                        })

                {

                    @Override
                    public Map<String, String> getHeaders()  {
                        HashMap<String, String> headers = new HashMap<String, String>();
                        headers.put("Content-Type", "application/json");
                        return headers;
                    }
                };

                RequestQueue requestQueue = Volley.newRequestQueue(AddInsActivity.this);
                requestQueue.add(jsonRequest);

            }





    }


