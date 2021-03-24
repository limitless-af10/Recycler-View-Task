package com.example.test.networking;

import com.example.test.models.UsersResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("api/")
    Call<UsersResponse> getRandomUsers(@Query("results") int results);

   // @GET("api/?results=100/?inc=picture,name,email")
    //Call<UsersResponse> getSeedUsers();

    @GET("API/")
    Call<UsersResponse> getSeedUsers(
            @Query("results") int param1,
            @Query("inc") String param2);
}
