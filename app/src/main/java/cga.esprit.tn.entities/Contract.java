package com.example.asus.cga.Entity;



public class Contract {
    private String id,etat,dateSinding,police,assurence;
    public Contract(String id, String etat, String dateSinding, String assurence){

        this.id=id;
this.etat = etat;
      this.dateSinding=dateSinding;

        this.assurence=assurence;


    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public String getDateSinding() {
        return dateSinding;
    }

    public void setDateSinding(String dateSinding) {
        this.dateSinding = dateSinding;
    }

    public String getAssurence() {
        return assurence;
    }

    public void setAssurence(String assurence) {
        this.assurence = assurence;
    }
}
