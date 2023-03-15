package com.example.myempty2application.webservice;

import com.example.myempty2application.bean.PostInfo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface MyApiEndpointInterface {

    @GET("posts/{id}")
    Call<PostInfo> getPostInfo(@Path("id") String id);

    @GET("posts")
    Call<List<PostInfo>> getPostInfoList();
}
