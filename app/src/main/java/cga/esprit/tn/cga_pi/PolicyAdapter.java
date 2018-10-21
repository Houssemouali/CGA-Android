package com.example.ahmed.cgamobile;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


import com.example.ahmed.cgamobile.model.Policy;
import com.example.ahmed.cgamobile.remote.APIUtil;
import com.example.ahmed.cgamobile.remote.PolicyService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by ahmed on 23/11/2017.
 */

 public class PolicyAdapter extends ArrayAdapter<Policy> {

    private Context context;
    private List<Policy>policies;

    public PolicyAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<Policy>objects) {
        super(context, resource, objects);
        this.context=context;
        this.policies=objects;

    }
    @Override
 public View getView (final int pos , View convetView , final ViewGroup parent ){
        LayoutInflater inflater = (LayoutInflater ) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View rowView=inflater.inflate(R.layout.list_policy,parent,false);
        TextView txtPolicyWording = ( TextView)rowView.findViewById(R.id.txtPolicyWording);
        TextView txtPolicyCost = ( TextView)rowView.findViewById(R.id.txtPolicyCost);
        TextView txtPolicydes = ( TextView)rowView.findViewById(R.id.txtPolicyDesc);
        TextView txtPolicyIns = ( TextView)rowView.findViewById(R.id.txtPolicyIns);
        txtPolicyWording.setText(policies.get(pos).getWording());
        txtPolicyCost.setText(String.valueOf(policies.get(pos).getCost()+"DT"));
        txtPolicydes.setText(policies.get(pos).getDescription());
        txtPolicyIns.setText(String.format(String.valueOf(policies.get(pos).getInsurance().getId())));

        Button btnSupprimer =(Button)rowView.findViewById(R.id.btnSupprimer);
        btnSupprimer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PolicyService policyService;
                policyService= APIUtil.getPolicyService();
                Call<String>call =policyService.deletePolicy(policies.get(pos).getId());
                call.enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String>call, Response<String> response) {
                        if (response.isSuccessful()){


                        }

                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {

                 
                    }
                });
                Intent intent = new Intent(context,PolicyActivity.class);
                context.startActivity(intent);


            }
        });
        Button btnUpdate =(Button)rowView.findViewById(R.id.btnUpdate);
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent intent = new Intent(context,UpdateActivity.class);
                intent.putExtra("wording",policies.get(pos).getWording());
                intent.putExtra("des",policies.get(pos).getDescription());
                intent.putExtra("cost",String.format(String.valueOf(policies.get(pos).getCost())));
                intent.putExtra("id",policies.get(pos).getId());
                intent.putExtra("in",policies.get(pos).getInsurance().getId());
                context.startActivity(intent);


            }
        });



        return rowView;
    }








 }
