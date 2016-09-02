package com.sven.ou.network.interceptor;

import com.sven.ou.LolApplication;
import com.sven.ou.common.config.Config;
import com.sven.ou.common.utils.Logger;
import com.sven.ou.common.utils.NetWorkUtil;

import java.io.IOException;

import okhttp3.CacheControl;
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

        // TODO: 2016/8/31  
//        if (NetWorkUtil.isOnline(LolApplication.instance)) {
//            int maxAge = 0 * 60; // 有网络时 设置缓存超时时间0个小时
//            Logger.i(TAG, "has network maxAge=" + maxAge);
//            response.newBuilder()
//                    .header("Cache-Control", "public, max-age=" + maxAge)
//                    .removeHeader("Pragma")// 清除头信息，因为服务器如果不支持，会返回一些干扰信息，不清除下面无法生效
//                    .build();
//        } else {
//            Logger.e(TAG, "network error");
//            int maxStale = 60 * 60 * 24 * 28; // 无网络时，设置超时为4周
//            Logger.e(TAG, "has maxStale=" + maxStale);
//            response.newBuilder()
//                    .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
//                    .removeHeader("Pragma")
//                    .build();
//            Logger.e(TAG, "response build maxStale=" + maxStale);
//        }

        return response;
    }

    private Request handlerRequest(Chain chain) {
        Request request = chain.request();
        if (null != request && null != request.url()) {
            return handlerHeaders(request);
        } else {
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
        } else if (requestUrl.startsWith(Config.getDaiWanLolVideoUrl())) {
            apiToken = Config.VIDEO_REQUEST_TOKEN;
        }
        Request.Builder builder = request.newBuilder()
                .header(KEY_DAIWAN_API_TOKEN, apiToken);

        // TODO: 2016/8/31
//        if (!NetWorkUtil.isOnline(LolApplication.instance)) {
//            builder.
//                    cacheControl(CacheControl.FORCE_CACHE)
//                    .build();
//            Logger.e(TAG, "no network");
//        }
        return builder.build();
    }
}
