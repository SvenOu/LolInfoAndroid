package com.sven.ou.network;

import com.sven.ou.common.config.Config;
import com.sven.ou.network.api.DaiWanLolDataApi;
import com.sven.ou.network.api.DaiWanLolVideoApi;
import com.sven.ou.network.interceptor.HttpInterceptor;

import okhttp3.OkHttpClient;
import retrofit2.CallAdapter;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by sven-ou on 2016/7/18.
 */
public class Network {

    private static final String TAG = Network.class.getSimpleName();

    private static DaiWanLolDataApi daiWanLolDataApi;
    private static DaiWanLolVideoApi daiWanLolVideoApi;
    private static OkHttpClient okHttpClient;
    private static CallAdapter.Factory rxJavaCallAdapterFactory;
    private static Converter.Factory gsonConverterFactory;


    public static DaiWanLolDataApi getDaiWanLolDataApi() {
        if (daiWanLolDataApi == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .client(getOkHttpClient())
                    .baseUrl(Config.getDaiWanLolDataUrl())
                    .addConverterFactory(getGsonConverterFactory())
                    .addCallAdapterFactory(getRxJavaCallAdapterFactory())
                    .build();
            daiWanLolDataApi = retrofit.create(DaiWanLolDataApi.class);
        }
        return daiWanLolDataApi;
    }
    public static DaiWanLolVideoApi getDaiWanLolVideoApi() {
        if (daiWanLolVideoApi == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .client(getOkHttpClient())
                    .baseUrl(Config.getDaiWanLolVideoUrl())
                    .addConverterFactory(getGsonConverterFactory())
                    .addCallAdapterFactory(getRxJavaCallAdapterFactory())
                    .build();
            daiWanLolVideoApi = retrofit.create(DaiWanLolVideoApi.class);
        }
        return daiWanLolVideoApi;
    }

    private static OkHttpClient getOkHttpClient() {
        if(null == okHttpClient){
            okHttpClient = new OkHttpClient.Builder()
                    .addInterceptor(new HttpInterceptor())
                    .build();
        }
        return okHttpClient;
    }

    private static CallAdapter.Factory getRxJavaCallAdapterFactory() {
        if (null == rxJavaCallAdapterFactory) {
            rxJavaCallAdapterFactory = RxJavaCallAdapterFactory.create();

        }
        return rxJavaCallAdapterFactory;
    }

    private static Converter.Factory getGsonConverterFactory() {
        if (null == gsonConverterFactory) {
            gsonConverterFactory = GsonConverterFactory.create();
        }
        return gsonConverterFactory;
    }
}
