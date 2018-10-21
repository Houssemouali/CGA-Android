package com.example.ahmed.cgamobile.remote;

import com.example.ahmed.cgamobile.model.Policy;
import com.example.ahmed.cgamobile.model.User;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

/**
 * Created by ahmed on 23/11/2017.
 */

public interface PolicyService {
    @GET("getPolicy")
    Call<List<Policy> > findALL();
    @POST("add")
    Call <Policy> addPolicy (@Body Policy p);
    @PUT("edit")
    Call <Policy> updatePolicy (@Body Policy p);
    @DELETE("{id}")
    Call <String> deletePolicy (@Path("id") int id);
    @GET("getClient")
    Call<List<User> > userList();
    @POST("sms")
    Call <String> sms ();
    @POST("email")
    Call <String> email ();

}
