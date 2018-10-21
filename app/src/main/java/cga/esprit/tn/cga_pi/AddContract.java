package com.example.asus.cga;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.android.volley.Response;
import org.json.JSONException;
import org.json.JSONObject;
import com.android.volley.VolleyError;

import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Map;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

public class AddContract extends AppCompatActivity  {
    EditText start,end,cost;
    Button btnPost, calcul;
    String myText;
    private static String url = "https://89228a85.ngrok.io/cga-web/rest/contract?insured_id=1";
    Calendar mycal=Calendar.getInstance();
    Calendar mycal1=Calendar.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contract);
        start = (EditText) findViewById(R.id.startDate);
        end = (EditText) findViewById(R.id.endDate);
        cost = (EditText) findViewById(R.id.cost);
        btnPost = (Button) findViewById(R.id.btnPost);
        calcul = (Button) findViewById(R.id.calcul);
        end.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(AddContract.this, date1, mycal1.get(Calendar.YEAR), mycal.get(Calendar.MONTH), mycal.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(AddContract.this, date, mycal.get(Calendar.YEAR), mycal.get(Calendar.MONTH), mycal.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
        btnPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendRequest();
                if (dates()){
                Toast.makeText(getBaseContext(), "Sending Contract!", Toast.LENGTH_LONG).show();

                Intent i = new Intent(AddContract.this,MainActivity.class);
                startActivity(i);
            }}
        });
        calcul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculercost();
            }
        });

    }



    private void sendRequest() {
        if(!dates())
            Toast.makeText(getBaseContext(), "End Date should be after Start Date!", Toast.LENGTH_LONG).show();
        RequestQueue queue = Volley.newRequestQueue(this);


        final JSONObject jsonObject = new JSONObject();
        try {


            jsonObject.put("startDate",start.getText().toString());
            jsonObject.put("endDate", end.getText().toString());
            jsonObject.put("costContract", cost.getText().toString());

        } catch (JSONException e) {
            // handle exception
        }


        JsonObjectRequest putRequest = new JsonObjectRequest(Request.Method.POST, url, jsonObject,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        // response
                        Log.d("Response", response.toString());
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // error
                        Log.d("Error.Response", error.toString());
                    }
                }
        ) {

            @Override
            public Map<String, String> getHeaders()
            {
                Map<String, String> headers = new HashMap<String, String>();
                headers.put("Content-Type", "application/json");
                headers.put("Accept", "application/json");
                return headers;
            }

            @Override
            public byte[] getBody() {

                try {
                    Log.i("json", jsonObject.toString());
                    return jsonObject.toString().getBytes("UTF-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                return null;
            }
        };

        queue.add(putRequest);
    }
    final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            mycal.set(Calendar.YEAR, year);
            mycal.set(Calendar.MONTH, month);
            mycal.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            String format = "yyyy-MM-dd";
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format, Locale.FRANCE);
            start.setText(simpleDateFormat.format(mycal.getTime()));
        }
    };
    final DatePickerDialog.OnDateSetListener date1 = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            mycal1.set(Calendar.YEAR, year);
            mycal1.set(Calendar.MONTH, month);
            mycal1.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            String format = "yyyy-MM-dd";
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format, Locale.FRANCE);
            end.setText(simpleDateFormat.format(mycal1.getTime()));
        }
    };
    public void calculercost(){

        long duree = mycal1.getTimeInMillis()-mycal.getTimeInMillis();
        float jr = TimeUnit.DAYS.convert(duree, TimeUnit.MILLISECONDS);

        cost.setText(jr*2+"");
        }
    public boolean dates(){

        long duree = mycal1.getTimeInMillis()-mycal.getTimeInMillis();
        float jr = TimeUnit.DAYS.convert(duree, TimeUnit.MILLISECONDS);

        if (jr >= 0 ){
            return true;
        }else
            return  false;
    }
}