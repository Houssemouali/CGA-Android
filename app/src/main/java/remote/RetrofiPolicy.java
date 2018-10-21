package com.example.ahmed.cgamobile.remote;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by ahmed on 23/11/2017.
 */

public class RetrofiPolicy {
private static Retrofit retrofit=null;

    public static Retrofit getClient(String url) {
       if (retrofit==null){
         retrofit=new Retrofit.Builder().baseUrl(url).addConverterFactory(GsonConverterFactory.create()).build() ;
       }
     return retrofit;
    }
}
