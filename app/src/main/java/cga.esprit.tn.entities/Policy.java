package com.example.ahmed.cgamobile.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by ahmed on 23/11/2017.
 */

public class Policy {
    @SerializedName("id")
    private int id;
    @SerializedName("wording")
    private String wording;
    @SerializedName("cost")
    private float cost;
    @SerializedName("description")
    private String description;
    @SerializedName("insurance")
    private Insurance insurance;
    public Policy(int id, String wording, float cost, String description, Insurance insurance) {
        super();
        this.id = id;
        this.wording = wording;
        this.cost = cost;
        this.description = description;
        this.insurance = insurance;
    }
    public Policy() {
        super();
        // TODO Auto-generated constructor stub
    }
    public Policy(String wording, float cost, String description, Insurance insurance) {
        super();

        this.wording = wording;
        this.cost = cost;
        this.description = description;
        this.insurance = insurance;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getWording() {
        return wording;
    }

    public void setWording(String wording) {
        this.wording = wording;
    }

    public float getCost() {
        return cost;
    }

    public void setCost(float cost) {
        this.cost = cost;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Insurance getInsurance() {
        return insurance;
    }

    public void setInsurance(Insurance insurance) {
        this.insurance = insurance;
    }
}
