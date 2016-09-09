package com.sven.ou.network;

import com.sven.ou.LolApplication;
import com.sven.ou.common.config.Config;
import com.sven.ou.network.api.DaiWanLolDataApi;
import com.sven.ou.network.api.DaiWanLolVideoApi;
import com.sven.ou.network.interceptor.HttpInterceptor;

import java.io.File;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.CallAdapter;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class Network {

    private static final String TAG = Network.class.getSimpleName();

    private static final int HTTP_CACHE_SIZE = 100 * 1024 * 1024;

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
            //设置缓存路径
            File httpCacheDirectory = new File(LolApplication.instance.getCacheDir(), "responses");
            //设置缓存 100M
            Cache cache = new Cache(httpCacheDirectory, HTTP_CACHE_SIZE);

            okHttpClient = new OkHttpClient.Builder()
                    .addInterceptor(new HttpInterceptor())
                    .cache(cache)
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
