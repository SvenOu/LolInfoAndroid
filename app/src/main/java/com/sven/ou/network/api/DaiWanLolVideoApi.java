package com.sven.ou.network.api;

import com.sven.ou.common.entity.DaiWanLolResult;
import com.sven.ou.module.lol.entity.Authors;
import com.sven.ou.module.lol.entity.UserArea;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * 英雄联盟国服视频API
 */
public interface DaiWanLolVideoApi{
    /**
     * 获取所有解说的信息
     */
    @GET("GetAuthors")
    Observable<DaiWanLolResult<List<Authors>>> getAuthors();
}
