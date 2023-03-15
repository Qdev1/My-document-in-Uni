package com.example.se1603_ksapplication.webservice;

import com.example.se1603_ksapplication.bean.PostInfo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface MyApiEndpointInterface {
    @GET("posts/{id}")
    Call<PostInfo> getPostInfo(@Path("id") String id);

    @GET("posts")
    Call<List<PostInfo>> getPostList();

}
