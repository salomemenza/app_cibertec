package com.example.salomon.aplicacionmovil.data.api.retrofit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PokemonRetrofitClient{
        public static final String BASE_URL = "https://pokeapi.co/api/v2/";
        private static Retrofit mRetrofit = null;

        public static Retrofit getClient(){
            if (mRetrofit==null) {

                HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
                // set your desired log level
                logging.setLevel(HttpLoggingInterceptor.Level.BODY);

                OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
                // add your other interceptors …

                // add logging as last interceptor
                httpClient.addInterceptor(logging);// <-- this is the important line!

                mRetrofit = new Retrofit.Builder()
                        .baseUrl(BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .client(httpClient.build())
                        .build();
            }
            return mRetrofit;
        }
}
