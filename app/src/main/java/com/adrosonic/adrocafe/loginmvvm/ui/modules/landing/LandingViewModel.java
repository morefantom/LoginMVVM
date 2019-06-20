package com.adrosonic.adrocafe.loginmvvm.ui.modules.landing;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.adrosonic.adrocafe.loginmvvm.data.ApiResponse;
import com.adrosonic.adrocafe.loginmvvm.repository.remote.API;

import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LandingViewModel extends AndroidViewModel {
    public LandingViewModel(@NonNull Application application) {
        super(application);
        loadData();
    }

    private MutableLiveData<List<ApiResponse>> apiResponses = new MutableLiveData<>();

    LiveData<List<ApiResponse>> getResponses(){
        return apiResponses;
    }

    private void loadData() {
        API.apiService().getResponse().enqueue(new Callback<List<ApiResponse>>() {
            @Override
            public void onResponse(Call<List<ApiResponse>> call, Response<List<ApiResponse>> response) {
                if (response.isSuccessful()){
                    if (response.body() != null){
                        apiResponses.setValue(response.body());
                    }
                }
            }

            @Override
            public void onFailure(Call<List<ApiResponse>> call, Throwable t) {
                Log.i("response", t.getMessage());
            }
        });
    }
}
