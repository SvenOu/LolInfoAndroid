package com.sven.ou.network.api;

import com.sven.ou.common.entity.DaiWanLolResult;
import com.sven.ou.module.lol.entity.Authors;
import com.sven.ou.module.lol.entity.UserArea;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by sven-ou on 2016/7/27.
 */
public interface DaiWanLolVideoApi{
    /**
     * 获取所有解说的信息
     */
    @GET("GetAuthors")
    Observable<DaiWanLolResult<List<Authors>>> getAuthors();
}
