package com.sven.ou.network;

import com.sven.ou.common.config.CommonConfig;
import com.sven.ou.network.api.LolApi;

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

    private static LolApi lolApi;
    private static OkHttpClient okHttpClient = new OkHttpClient();
    private static Converter.Factory gsonConverterFactory = GsonConverterFactory.create();
    private static CallAdapter.Factory rxJavaCallAdapterFactory = RxJavaCallAdapterFactory.create();

    public static LolApi getLolApi() {
        if (lolApi == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .client(okHttpClient)
                    .baseUrl(CommonConfig.getLolBaseUrl())
                    .addConverterFactory(gsonConverterFactory)
                    .addCallAdapterFactory(rxJavaCallAdapterFactory)
                    .build();
            lolApi = retrofit.create(LolApi.class);
        }
        return lolApi;
    }
}
