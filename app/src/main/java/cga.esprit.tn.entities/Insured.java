package cga.esprit.tn.entities;


import java.io.Serializable;
import java.util.Date;
import java.util.List;

import cga.esprit.tn.entities.User;


/**
 * Entity implementation class for Entity: Insured
 *
 */


public class Insured extends User implements Serializable {


    private static final long serialVersionUID = 1L;

    private int driverLicenseNumber;
    private Date delivredIn;
    private String cin1;
    private String cin2;





    public Insured() {
        // TODO Auto-generated constructor stub
    }

    public Insured( String firstName, String lastName, String password,String email, String role,int driverLicenseNumber, Date delivredIn, String cin1, String cin2) {
        super(lastName, firstName,password, email, role);
        this.driverLicenseNumber = driverLicenseNumber;
        this.delivredIn = delivredIn;
        this.cin1 = cin1;
        this.cin2 = cin2;

    }

    public Insured( String firstName, String lastName, String password,String email,String role,int driverLicenseNumber ,int id) {
        super(lastName, firstName,password, email, role);
        this.id=id;
        this.driverLicenseNumber=driverLicenseNumber;
    }


    public int getDriverLicenseNumber() {
        return driverLicenseNumber;
    }

    public void setDriverLicenseNumber(int driverLicenseNumber) {
        this.driverLicenseNumber = driverLicenseNumber;
    }

    public Date getDelivredIn() {
        return delivredIn;
    }

    public void setDelivredIn(Date delivredIn) {
        this.delivredIn = delivredIn;
    }

    public String getCin1() {
        return cin1;
    }

    public void setCin1(String cin1) {
        this.cin1 = cin1;
    }

    public String getCin2() {
        return cin2;
    }

    public void setCin2(String cin2) {
        this.cin2 = cin2;
    }










}
