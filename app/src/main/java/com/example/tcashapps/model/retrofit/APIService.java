package com.example.tcashapps.model.retrofit;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface APIService {

    @POST("login")
    @FormUrlEncoded
    Call<Login> loginUser(@Field("phone") String phone, @Field("password") String password);

    @POST("register")
    @FormUrlEncoded
    Call<Login> registerUser(@Field("name") String name, @Field("email") String email, @Field("password") String password, @Field("phone") String phone);

    @GET("me")
    @FormUrlEncoded
    Call<User> myProfile(@Field("token") String token);

    @GET("blog/blog")
    Call<ContentResponse> getBlog();

    @GET("blog/video")
    Call<ContentResponse> getVideo();
}
