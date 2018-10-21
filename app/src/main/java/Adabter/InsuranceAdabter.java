package com.example.najah.test.Adabter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.najah.test.Entities.Insurance;

import java.util.List;
import com.example.najah.test.R;



public class InsuranceAdabter extends BaseAdapter {

    private Activity activity;
    private LayoutInflater inflater;

    private List<Insurance> insuranceItems;
    Insurance insurance;

    public InsuranceAdabter(Activity activity, List<Insurance> insuranceItems) {
        this.activity = activity;
        this.insuranceItems = insuranceItems;
    }


    @Override
    public int getCount() {
        return insuranceItems.size();
    }

    @Override
    public Object getItem(int position) {
        return insuranceItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (inflater == null)
            inflater = (LayoutInflater) activity
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null)
            convertView = inflater.inflate(R.layout.insurance, null);



        TextView nom = (TextView) convertView.findViewById(R.id.name);


        insurance = insuranceItems.get(position);



        nom.setText(insurance.getNom());


        return convertView;
    }











}
