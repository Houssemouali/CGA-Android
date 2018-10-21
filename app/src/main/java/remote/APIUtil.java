package com.example.ahmed.cgamobile.remote;

/**
 * Created by ahmed on 23/11/2017.
 */

public class APIUtil {
private APIUtil(){

};
public static final String API_URL="http://ae877d74.ngrok.io/cga-web/pidev/CgaPolicy/";


    public static PolicyService getPolicyService(){
       return RetrofiPolicy.getClient(API_URL).create(PolicyService.class);
    }

}
