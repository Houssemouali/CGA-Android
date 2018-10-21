package com.example.ahmed.cgamobile;

import android.app.AlertDialog;
import android.content.DialogInterface;
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

public class UpdateActivity extends AppCompatActivity {
    EditText wordingU;
    EditText costU;
    EditText desU;
    Button btnUp;
    Button btnback;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        btnUp=(Button)findViewById(R.id.btnUp);
        wordingU=(EditText)findViewById(R.id.wordingU);
        costU=(EditText)findViewById(R.id.costU);
        desU=(EditText)findViewById(R.id.desU);
        Bundle bundle = getIntent().getExtras();
        btnback=(Button)findViewById(R.id.btnback);
           wordingU.setText(bundle.getString("wording"));
        costU.setText(bundle.getString("cost"));
        desU.setText(bundle.getString("des"));
       final int id =bundle.getInt("in");
        final int idP=bundle.getInt("id");

        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UpdateActivity.this,PolicyActivity.class);
                UpdateActivity.this.startActivity(intent);

            }
        });


        btnUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if ((wordingU.getText().toString()).equals("")){
                    new AlertDialog.Builder(UpdateActivity.this)
                            .setTitle("ERROR")
                            .setMessage("champ Wording obligatoire")
                            .setCancelable(false)
                            .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    // Whatever...
                                }
                            }).show();}

                else  if ((costU.getText().toString()).equals("")){
                    new AlertDialog.Builder(UpdateActivity.this)
                            .setTitle("ERROR")
                            .setMessage("champ Price obligatoire")
                            .setCancelable(false)
                            .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    // Whatever...
                                }
                            }).show();}

                else  if ((desU.getText().toString()).equals("")){
                    new AlertDialog.Builder(UpdateActivity.this)
                            .setTitle("ERROR")
                            .setMessage("champ description obligatoire")
                            .setCancelable(false)
                            .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    // Whatever...
                                }
                            }).show();}

                else {
                    Policy p = new Policy();
                    p.setId(idP);
                    p.setDescription(desU.getText().toString());
                    p.setWording(wordingU.getText().toString());
                    p.setCost(Float.valueOf(costU.getText().toString()));
                    Insurance in = new Insurance();
                    in.setId(id);
                    p.setInsurance(in);

                    PolicyService policyService;
                    policyService = APIUtil.getPolicyService();
                    Call<Policy> call = policyService.updatePolicy(p);
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

                    new AlertDialog.Builder(UpdateActivity.this)
                            .setTitle("Sucess")
                            .setMessage("Update with sucess")
                            .setCancelable(false)
                            .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    // Whatever...

                                    Intent intent = new Intent(UpdateActivity.this,PolicyActivity.class);
                                    UpdateActivity.this.startActivity(intent);

                                }
                            }).show();



                }




            }




        });
    }
}
