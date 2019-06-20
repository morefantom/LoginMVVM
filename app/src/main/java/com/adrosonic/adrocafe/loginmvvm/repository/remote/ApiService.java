package com.adrosonic.adrocafe.loginmvvm.repository.remote;

import com.adrosonic.adrocafe.loginmvvm.data.ApiResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {

    @GET("/posts")
    Call<List<ApiResponse>> getResponse();
}
