package cga.esprit.tn.cga_pi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;


import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;

import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;


import java.util.HashMap;
import java.util.Map;


import customfonts.MyEditText;

import static cga.esprit.tn.cga_pi.MainActivity.insured;
import static cga.esprit.tn.cga_pi.MainActivity.expert;
import static cga.esprit.tn.cga_pi.MainActivity.insuranceAgent;
import static cga.esprit.tn.cga_pi.UploadActivity.bitmap;
import static cga.esprit.tn.cga_pi.UploadActivity.imageProfile;


public class Profile extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        final ImageView cars = (ImageView) findViewById(R.id.cars);
        final ImageView imageProf = (ImageView) findViewById(R.id.profileImage);
        final customfonts.MyTextView libelleNivExp = (customfonts.MyTextView) findViewById(R.id.licenceNum);
        final customfonts.MyTextView modifText = (customfonts.MyTextView) findViewById(R.id.modifText);
        final customfonts.MyTextView userName = (customfonts.MyTextView) findViewById(R.id.username1);
        final customfonts.MyTextView profileName = (customfonts.MyTextView) findViewById(R.id.profileName);
        final customfonts.MyTextView firstName = (customfonts.MyTextView) findViewById(R.id.profileFirstName);
        final customfonts.MyTextView email = (customfonts.MyTextView) findViewById(R.id.profileEmail);
        final customfonts.MyTextView licenceNumber = (customfonts.MyTextView) findViewById(R.id.permit);
        customfonts.MyTextView updateImage = (customfonts.MyTextView)findViewById(R.id.updateImage);
        final Switch modif = (Switch) findViewById(R.id.switch2);
        final Button update = (Button) findViewById(R.id.modif);
        final LinearLayout layoutButton = (LinearLayout)findViewById(R.id.layout);
        final LinearLayout layoutSwich = (LinearLayout)findViewById(R.id.layoutModif);
        final LinearLayout layoutLN = (LinearLayout)findViewById(R.id.layoutLN);


        if(bitmap!=null) {
            imageProf.setImageBitmap(bitmap);
        }



        if(expert!=null) {

            layoutButton.removeView(update);
            layoutSwich.removeView(modifText);
            layoutSwich.removeView(modif);
            libelleNivExp.setHint("Niveau expertise");
            licenceNumber.setHint(expert.getExpertiseLevel());
            profileName.setText(expert.getLastName() + " " + expert.getFirstName());
            userName.setHint(expert.getLastName());
            firstName.setHint(expert.getFirstName());
            email.setHint(expert.getEmail());
        }

        if(insuranceAgent!=null) {

            layoutButton.removeView(update);
            layoutSwich.removeView(modifText);
            layoutSwich.removeView(modif);
            layoutLN.removeView(licenceNumber);
            layoutLN.removeView(libelleNivExp);
            profileName.setText(insuranceAgent.getLastName() + " " + insuranceAgent.getFirstName());
            userName.setHint(insuranceAgent.getLastName());
            firstName.setHint(insuranceAgent.getFirstName());
            email.setHint(insuranceAgent.getEmail());
        }

if(insured!=null) {
    layoutButton.removeView(update);
    profileName.setText(insured.getLastName() + " " + insured.getFirstName());
    userName.setHint(insured.getLastName());
    firstName.setHint(insured.getFirstName());
    email.setHint(insured.getEmail());
    licenceNumber.setHint("" + insured.getDriverLicenseNumber() + "");

    modif.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
        customfonts.MyEditText editFirst = new customfonts.MyEditText(Profile.this);
        customfonts.MyEditText editLast = new customfonts.MyEditText(Profile.this);
        customfonts.MyEditText editEmail = new customfonts.MyEditText(Profile.this);
        customfonts.MyEditText editLN = new customfonts.MyEditText(Profile.this);

        LinearLayout layoutFirstName = (LinearLayout) findViewById(R.id.layoutFirstName);
        LinearLayout layoutLastName = (LinearLayout) findViewById(R.id.layoutLastName);
        LinearLayout layoutEmail = (LinearLayout) findViewById(R.id.layoutEmail);
        LinearLayout layoutLn = (LinearLayout) findViewById(R.id.layoutLN);


        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            if (modif.isChecked()) {

                layoutButton.addView(update);
                final String url = "http://10.0.2.2:18080/cga-web/pidev/users";
                update.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        StringRequest putRequest = new StringRequest(Request.Method.PUT, url,
                                new Response.Listener<String>() {
                                    @Override
                                    public void onResponse(String response) {
                                        // response
                                        Log.d("Response", response);
                                        if (response.equals("true")) {
                                            Toast.makeText(Profile.this, "Modification réussie", Toast.LENGTH_LONG).show();
                                        } else {
                                            Toast.makeText(Profile.this, "modification non abouti", Toast.LENGTH_LONG).show();
                                        }
                                    }
                                },
                                new Response.ErrorListener() {
                                    @Override
                                    public void onErrorResponse(VolleyError error) {
                                        // error

                                    }
                                }
                        ) {

                            @Override
                            protected Map<String, String> getParams() {
                                Map<String, String> params = new HashMap<String, String>();
                                params.put("id", insured.getId() + "");

                                params.put("firstName", editLast.getText().toString());
                                insured.setFirstName(editLast.getText().toString());
                                params.put("lastName", editFirst.getText().toString());
                                insured.setLastName(editFirst.getText().toString());
                                params.put("email", editEmail.getText().toString());
                                insured.setEmail(editEmail.getText().toString());
                                params.put("driverLicenceNumber", editLN.getText().toString());
                                insured.setDriverLicenseNumber(Integer.parseInt(editLN.getText().toString()));

                                return params;
                            }

                        };
                        RequestQueue queue = Volley.newRequestQueue(Profile.this);
                        queue.add(putRequest);

                        startActivity(new Intent(Profile.this, Profile.class));
                    }

                });


                editLast.setText(userName.getHint());
                editFirst.setText(firstName.getHint());
                editEmail.setText(email.getHint());
                editLN.setText(licenceNumber.getHint());

                layoutFirstName.removeView(userName);
                layoutLastName.removeView(firstName);
                layoutEmail.removeView(email);
                layoutLn.removeView(licenceNumber);

                layoutFirstName.addView(editFirst);
                layoutLastName.addView(editLast);
                layoutEmail.addView(editEmail);
                layoutLn.addView(editLN);

            } else {
                layoutFirstName.removeView(editFirst);
                layoutLastName.removeView(editLast);
                layoutEmail.removeView(editEmail);
                layoutLn.removeView(editLN);

                layoutFirstName.addView(userName);
                layoutLastName.addView(firstName);
                layoutEmail.addView(email);
                layoutLn.addView(licenceNumber);
                layoutButton.removeView(update);


            }
        }
    });

}
        cars.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Profile.this, ReportActivity.class));
            }
        });
        updateImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Profile.this, UploadActivity.class));
            }
        });

    }
}
