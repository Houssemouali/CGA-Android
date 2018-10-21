package cga.esprit.tn.cga_pi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import cga.esprit.tn.entities.Expert;
import cga.esprit.tn.entities.InsuranceAgent;
import cga.esprit.tn.entities.Insured;


public class MainActivity extends AppCompatActivity {
    EditText emailBox, passwordBox;
    Button loginButton;
    TextView registerLink;
    public static Insured insured ;
    public static Expert expert ;
    public static InsuranceAgent insuranceAgent ;
    //String URL = "http://10.0.2.2:18080/cga-web/pidev/users/login";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        emailBox = (EditText)findViewById(R.id.emailBox);
        passwordBox = (EditText)findViewById(R.id.passwordBox);
        loginButton = (Button)findViewById(R.id.loginButton);
        registerLink = (TextView)findViewById(R.id.registerLink);



        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String URL = "http://10.0.2.2:18080/cga-web/pidev/users/"+emailBox.getText().toString()+"/"+passwordBox.getText().toString();


                JsonObjectRequest getRequest = new JsonObjectRequest(Request.Method.GET, URL, null,
                        new Response.Listener<JSONObject>()
                        {
                            @Override
                            public void onResponse(JSONObject response) {
                                // display response
                                Log.d("Response", response.toString());
                                if(response!=null){

                                    try {
                                        if(response.getString("role").equals("insured")){
                                            insured = new Insured();
                                            insured.setId(response.getInt("id"));
                                            insured.setFirstName(response.getString("firstName"));
                                            insured.setLastName(response.getString("lastName"));
                                            insured.setEmail(response.getString("email"));
                                            insured.setRole(response.getString("role"));
                                            insured.setDriverLicenseNumber(response.getInt("driverLicenseNumber"));
                                        }
                                        if(response.getString("role").equals("expert")){
                                            expert = new Expert();
                                            expert.setId(response.getInt("id"));
                                            expert.setFirstName(response.getString("firstName"));
                                            expert.setLastName(response.getString("lastName"));
                                            expert.setEmail(response.getString("email"));
                                            expert.setRole(response.getString("role"));
                                            expert.setExpertiseLevel(response.getString("expertiseLevel"));
                                        }
                                        if(response.getString("role").equals("insuranceAgent")){
                                            insuranceAgent = new InsuranceAgent();
                                            insuranceAgent.setId(response.getInt("id"));
                                            insuranceAgent.setFirstName(response.getString("firstName"));
                                            insuranceAgent.setLastName(response.getString("lastName"));
                                            insuranceAgent.setEmail(response.getString("email"));
                                            insuranceAgent.setRole(response.getString("role"));

                                        }

                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                    Toast.makeText(MainActivity.this, "Login Successful", Toast.LENGTH_LONG).show();
                                    startActivity(new Intent(MainActivity.this,Accueil.class));


                                }
                                else{
                                    Toast.makeText(MainActivity.this, "Incorrect Details", Toast.LENGTH_LONG).show();
                                }

                            }
                        },
                        new Response.ErrorListener()
                        {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast.makeText(MainActivity.this, "Incorrect Details", Toast.LENGTH_LONG).show();
                            }
                        }
                );
                RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
                queue.add(getRequest);
            }
        });



        registerLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Register.class));
            }
        });
    }
}
