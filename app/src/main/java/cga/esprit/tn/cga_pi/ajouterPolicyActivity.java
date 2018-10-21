package com.example.ahmed.cgamobile;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.ahmed.cgamobile.model.Insurance;
import com.example.ahmed.cgamobile.model.Policy;
import com.example.ahmed.cgamobile.remote.APIUtil;
import com.example.ahmed.cgamobile.remote.PolicyService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ajouterPolicyActivity extends AppCompatActivity {
    EditText wordingE;
    EditText costE;
    EditText desE;
    Button btnAdd;
    Button btnback;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajouter_policy);
        btnAdd=(Button)findViewById(R.id.btnAdd);
        btnback=(Button)findViewById(R.id.btnback);
        wordingE=(EditText)findViewById(R.id.wordingE);
        costE=(EditText)findViewById(R.id.costE);
        desE=(EditText)findViewById(R.id.desE);
        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ajouterPolicyActivity.this,PolicyActivity.class);
                ajouterPolicyActivity.this.startActivity(intent);

            }
        });


        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if ((wordingE.getText().toString()).equals("")){
                    new AlertDialog.Builder(ajouterPolicyActivity.this)
                            .setTitle("ERROR")
                            .setMessage("champ Wording obligatoire")
                            .setCancelable(false)
                            .setPositiveButton("ok", new OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    // Whatever...
                                }
                            }).show();}

              else  if ((costE.getText().toString()).equals("")){
                    new AlertDialog.Builder(ajouterPolicyActivity.this)
                            .setTitle("ERROR")
                            .setMessage("champ Price obligatoire")
                            .setCancelable(false)
                            .setPositiveButton("ok", new OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    // Whatever...
                                }
                            }).show();}

              else  if ((desE.getText().toString()).equals("")){
                    new AlertDialog.Builder(ajouterPolicyActivity.this)
                            .setTitle("ERROR")
                            .setMessage("champ description obligatoire")
                            .setCancelable(false)
                            .setPositiveButton("ok", new OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    // Whatever...
                                }
                            }).show();}
             else {
                    Policy p = new Policy();
                    p.setDescription(desE.getText().toString());
                    p.setWording(wordingE.getText().toString());
                    p.setCost(Float.valueOf(costE.getText().toString()));
                    Insurance in = new Insurance();
                    in.setId(3);
                    p.setInsurance(in);

                    PolicyService policyService;
                    policyService = APIUtil.getPolicyService();
                    Call<Policy> call = policyService.addPolicy(p);
                    call.enqueue(new Callback<Policy>() {
                        @Override
                        public void onResponse(Call<Policy> call, Response<Policy> response) {
                            if (response.isSuccessful()) {


                            }

                        }

                        @Override
                        public void onFailure(Call<Policy> call, Throwable t) {


                        }
                    });

                    new AlertDialog.Builder(ajouterPolicyActivity.this)
                            .setTitle("Sucess")
                            .setMessage("add with sucess")
                            .setCancelable(false)
                            .setPositiveButton("ok", new OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    // Whatever...
                                }
                            }).show();


                }
            }




        });




    }

}
