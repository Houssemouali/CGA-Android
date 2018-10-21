package com.example.ahmed.cgamobile;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import com.example.ahmed.cgamobile.model.Policy;
import com.example.ahmed.cgamobile.model.User;
import com.example.ahmed.cgamobile.remote.APIUtil;
import com.example.ahmed.cgamobile.remote.PolicyService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddPolicyActivity extends AppCompatActivity {
  Button btnAddPol;
    Button btnGetPol;
    Button btnGetClient;
    ListView ListPolicy;
   PolicyService policyService;
    List <Policy> p = new ArrayList<Policy>();
    List <User> u = new ArrayList <User>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_policy);
        btnGetPol=(Button)findViewById(R.id.btnGetPol);
      btnAddPol=(Button)findViewById(R.id.btnAddPol);
        btnGetClient=(Button)findViewById(R.id.btnGetClient);
      ListPolicy=(ListView)findViewById(R.id.ListPolicy);
        policyService=APIUtil.getPolicyService();


        btnGetPol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Call <List<Policy>>call= policyService.findALL();
                call.enqueue(new Callback<List<Policy>>() {
                    @Override
                    public void onResponse(Call<List<Policy>> call, Response<List<Policy>> response) {
                        if (response.isSuccessful()){
                            p=response.body();
                            ListPolicy.setAdapter(new PolicyAdapter(AddPolicyActivity.this,R.layout.list_policy,p));

                        }

                    }

                    @Override
                    public void onFailure(Call<List<Policy>> call, Throwable t) {
                        Toast.makeText(getApplicationContext(),"failed", Toast.LENGTH_SHORT).show();

                    }
                });
            }
        });


        btnAddPol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AddPolicyActivity.this,ajouterPolicyActivity.class);
                AddPolicyActivity.this.startActivity(intent);

            }
        });

        btnGetClient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Call <List<User>>call= policyService.userList();
                call.enqueue(new Callback<List<User>>() {
                    @Override
                    public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                        if (response.isSuccessful()){
                            u=response.body();
                            ListPolicy.setAdapter(new ClientAdapter(AddPolicyActivity.this,R.layout.client,u));

                        }

                    }

                    @Override
                    public void onFailure(Call<List<User>> call, Throwable t) {
                        Toast.makeText(getApplicationContext(),"failed", Toast.LENGTH_SHORT).show();

                    }
                });
            }
        });

        Call <List<Policy>>call= policyService.findALL();
        call.enqueue(new Callback<List<Policy>>() {
            @Override
            public void onResponse(Call<List<Policy>> call, Response<List<Policy>> response) {
                if (response.isSuccessful()){
                    p=response.body();
                    ListPolicy.setAdapter(new PolicyAdapter(AddPolicyActivity.this,R.layout.list_policy,p));

                }

            }

            @Override
            public void onFailure(Call<List<Policy>> call, Throwable t) {
                Toast.makeText(getApplicationContext(),"failed", Toast.LENGTH_SHORT).show();

            }
        });

    }




}
