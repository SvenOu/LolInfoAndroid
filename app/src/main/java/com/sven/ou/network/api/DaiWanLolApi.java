package com.sven.ou.network.api;

import com.sven.ou.common.entity.DaiWanLolResult;
import com.sven.ou.module.lol.entity.UserArea;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by sven-ou on 2016/7/27.
 */
public interface DaiWanLolApi {
    @GET("UserArea")
    Observable<DaiWanLolResult<List<UserArea>>> getUserArea(@Query("keyword") String keyword);
}
