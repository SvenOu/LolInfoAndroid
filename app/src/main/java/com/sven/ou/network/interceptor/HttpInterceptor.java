package com.sven.ou.network.interceptor;

import com.sven.ou.common.config.Config;
import com.sven.ou.common.utils.Logger;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by sven-ou on 2016/7/29.
 */
public class HttpInterceptor implements Interceptor {
    private static final String TAG = HttpInterceptor.class.getSimpleName();

    private static final String KEY_DAIWAN_API_TOKEN = "DAIWAN-API-TOKEN";

    @Override
    public Response intercept(Interceptor.Chain chain) throws IOException {

        Request request = handlerRequest(chain);

        long t1 = System.nanoTime();
        Logger.i(TAG, String.format("Sending request %s on %s%n%s",
                request.url(), chain.connection(), request.headers()));

        Response response = chain.proceed(request);

        long t2 = System.nanoTime();

        Logger.i(TAG, String.format("Received response for %s in %.1fms%n%s",
                response.request().url(), (t2 - t1) / 1e6d, response.headers()));

        return response;
    }

    private Request handlerRequest(Chain chain) {
        Request request = chain.request();
        if (null != request && null != request.url()) {
            return handlerHeaders(request);
        }else {
            Logger.e(TAG, "request or url is null !");
        }
        return request;
    }

    /**
     * 为不同的API添加不同的头部令牌
     */
    private Request handlerHeaders(Request request) {
        String apiToken = null;
        String requestUrl = request.url().toString();
        if (requestUrl.startsWith(Config.getDaiWanLolDataUrl())) {
            apiToken = Config.PUBLICK_LOL_REQUEST_TOKEN;
        }else if(requestUrl.startsWith(Config.getDaiWanLolVideoUrl())){
            apiToken = Config.VIDEO_REQUEST_TOKEN;
        }
        return request.newBuilder()
                .header(KEY_DAIWAN_API_TOKEN, apiToken)
                .build();
    }
}
