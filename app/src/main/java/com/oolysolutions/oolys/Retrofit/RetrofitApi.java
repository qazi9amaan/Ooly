package com.oolysolutions.oolys.Retrofit;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;

import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitApi {

    Retrofit retrofit;

    public RetrofitApi() {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();


        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

         retrofit = new Retrofit.Builder()
                .baseUrl("https://api.ooly.me/test/")
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

    }

    public Api get(){
        Api retrofitAPI = retrofit.create(Api.class);
        return retrofitAPI;
    }
}
