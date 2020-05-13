package com.sumit.sbnriassignment.repo.services;

import com.sumit.sbnriassignment.BuildConfig;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by sumitsaini on 17/06/2019 AD.
 */

public class RetrofitService {

    private static final String BASE_URL = "https://api.github.com/";
    private static Retrofit retrofit;

    private RetrofitService() {
    }

    /**
     * Single instance retrofit class method
     * @return
     */
    public static Retrofit getInstance() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder().baseUrl(BASE_URL).client(getOkHttpClient()).addCallAdapterFactory(RxJava2CallAdapterFactory.create()).addConverterFactory(GsonConverterFactory.create()).build();
        }
        return retrofit;

    }

    /**
     * this method setup http client and logging and intercepator
     * @return
     */
    public static OkHttpClient getOkHttpClient() {

        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient.Builder okHttpClientBuilder = new OkHttpClient.Builder();
        okHttpClientBuilder.connectTimeout(30, TimeUnit.SECONDS);
        okHttpClientBuilder.readTimeout(30, TimeUnit.SECONDS);
        okHttpClientBuilder.writeTimeout(30, TimeUnit.SECONDS);
        okHttpClientBuilder.addInterceptor(logging);
        okHttpClientBuilder.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
                Request.Builder requestBuilder = request.newBuilder().header("Accept", "application/json").header("application", "Android").addHeader("appVersion", "" + getAppVersion());
                return chain.proceed(requestBuilder.build());
            }
        });

        okHttpClientBuilder.hostnameVerifier(new HostnameVerifier() {
            @Override
            public boolean verify(String s, SSLSession sslSession) {
                if (BuildConfig.DEBUG) {
                    return true;
                } else {
                    return true;
                }
            }
        });

        return okHttpClientBuilder.build();

    }

    public static int getAppVersion() {
        return BuildConfig.VERSION_CODE;
    }


}
