package com.example.ahmed.cgamobile;

import
        android.content.Context;
import android.content.Intent;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ahmed.cgamobile.model.Policy;
import com.example.ahmed.cgamobile.model.User;
import com.example.ahmed.cgamobile.remote.APIUtil;
import com.example.ahmed.cgamobile.remote.PolicyService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by ahmed on 23/11/2017.
 */

 public class ClientAdapter extends ArrayAdapter<User> {

    private Context context;
    private List<User>Users;

    public ClientAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<User>objects) {
        super(context, resource, objects);
        this.context=context;
        this.Users=objects;

    }
    @Override
 public View getView (final int pos , View convetView , final ViewGroup parent ){
        LayoutInflater inflater = (LayoutInflater ) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View rowView=inflater.inflate(R.layout.client,parent,false);

        TextView name = ( TextView)rowView.findViewById(R.id.name);
        TextView prenom= ( TextView)rowView.findViewById(R.id.prenom);

        ImageButton sms = ( ImageButton)rowView.findViewById(R.id.sms);
        ImageButton email = ( ImageButton)rowView.findViewById(R.id.email);
        name.setText(String.valueOf(Users.get(pos).getFirstName()));
        prenom.setText(Users.get(pos).getLastName());


        sms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PolicyService policyService;
                policyService= APIUtil.getPolicyService();
                Call<String>call =policyService.sms();
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

            }
        });

        email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PolicyService policyService;
                policyService= APIUtil.getPolicyService();
                Call<String>call =policyService.email();
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

            }
        });

        return rowView;
    }








 }
