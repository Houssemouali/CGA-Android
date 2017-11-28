package cga.esprit.tn.cga_pi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import static cga.esprit.tn.cga_pi.MainActivity.expert;
import static cga.esprit.tn.cga_pi.MainActivity.insuranceAgent;
import static cga.esprit.tn.cga_pi.MainActivity.insured;

public class Accueil extends AppCompatActivity {

    Button profile;
    Button logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accueil);
        profile = (Button) findViewById(R.id.prof);

        profile.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                startActivity(new Intent(Accueil.this, Profile.class));
            }
        });
        logout = (Button) findViewById(R.id.logout);
        logout.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                if (insured!=null) {insured=null;}
                if (expert!=null) {expert=null;}
                if (insuranceAgent!=null) {expert=null;}

                startActivity(new Intent(Accueil.this, MainActivity.class));
            }
        });
    }
}
