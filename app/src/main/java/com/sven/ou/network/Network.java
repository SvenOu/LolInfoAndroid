package com.sven.ou.network;

import com.sven.ou.common.config.Config;
import com.sven.ou.network.api.DaiWanLolApi;
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

    private static DaiWanLolApi daiWanLolApi;
    private static OkHttpClient okHttpClient;
    private static CallAdapter.Factory rxJavaCallAdapterFactory;
    private static Converter.Factory gsonConverterFactory;


    public static DaiWanLolApi getDaiWanLolApi() {
        if (daiWanLolApi == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .client(getOkHttpClient())
                    .baseUrl(Config.getDaiWanLolBaseUrl())
                    .addConverterFactory(getGsonConverterFactory())
                    .addCallAdapterFactory(getRxJavaCallAdapterFactory())
                    .build();
            daiWanLolApi = retrofit.create(DaiWanLolApi.class);
        }
        return daiWanLolApi;
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
