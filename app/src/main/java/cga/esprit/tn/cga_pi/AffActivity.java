package com.example.najah.test;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;

public class AffActivity extends AppCompatActivity {
    private Button delete;
    private Bundle bundle;
    TextView test, email, adresse, telephone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aff);
        test = (TextView) findViewById(R.id.nomtv);
        email = (TextView) findViewById(R.id.emailtv);
        adresse = (TextView) findViewById(R.id.adressetv);
        telephone = (TextView) findViewById(R.id.teltv);
        delete = (Button) findViewById(R.id.delete);
        bundle = getIntent().getExtras();
        String testn = bundle.getString("nom");
        String teste = bundle.getString("email");
        String testa = bundle.getString("adresse");
        String testt = bundle.getString("telephone");
        System.out.println("delete"+bundle.getInt("id"));

        test.setText(testn);
        email.setText(teste);
        adresse.setText(testa);
        telephone.setText(testt);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("delete"+bundle.getInt("id"));
                DeleteAss(bundle.getInt("id"));

            }
        });
    }

    public void DeleteAss(int id) {
        String json_url_delete = "http://10.0.2.2:18080/cga-web/pi/Assurance?idass="+id;
        JsonArrayRequest jar = new JsonArrayRequest(Request.Method.DELETE, json_url_delete,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

    }
}
