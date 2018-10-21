package com.example.najah.test;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.example.najah.test.Adabter.InsuranceAdabter;
import com.example.najah.test.Entities.Insurance;
import com.example.najah.test.util.MySingleton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class InsuranceActivity extends AppCompatActivity {

    public List<Insurance> lst_ins=new ArrayList<Insurance>();


    private ListView listView;
    InsuranceAdabter adapter;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insurance);
        listView = (ListView) findViewById(R.id.listinsurance);



        String json_url="http://10.0.2.2:18080/cga-web/pi/Assurance";
        JsonArrayRequest jar = new JsonArrayRequest(Request.Method.GET,json_url,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {




                        for (int i = 0; i < response.length(); i++) {
                            try {

                                JSONObject jsonObject=response.getJSONObject(i);
                                Insurance ins =new Insurance();

                                ins.setId(jsonObject.getInt("id"));
                                ins.setNom(jsonObject.getString("nom"));
                                ins.setAdresse(jsonObject.getString("adresse"));
                                ins.setEmail(jsonObject.getString("email"));
                                ins.setTelephone(jsonObject.getString("telephone"));



                                lst_ins.add(ins);
                                System.out.println("aaaaaaa"+lst_ins.toString());



                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }


                        adapter.notifyDataSetChanged();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {


                VolleyLog.d(InsuranceActivity.class.getSimpleName() , "Error: " + error.getMessage());
                //  hidePDialog();
            }
        });



        MySingleton.getIns(this).addToRequ(jar);
        adapter = new InsuranceAdabter(this,lst_ins);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(InsuranceActivity.this,AffActivity.class);
                Insurance m= lst_ins.get(position);
                System.out.println("iddddddd"+m.getId());
                intent.putExtra("id",m.getId());
                intent.putExtra("nom",m.getNom());
                intent.putExtra("adresse",m.getAdresse());
                intent.putExtra("email",m.getEmail());
                intent.putExtra("telephone",m.getTelephone()+"");
                startActivity(intent);

            }
        });


    }

    public void viewInsurance()
    {







    }







}
