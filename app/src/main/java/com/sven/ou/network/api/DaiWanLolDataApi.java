package com.sven.ou.network.api;

import com.sven.ou.common.entity.DaiWanLolResult;
import com.sven.ou.module.lol.entity.Area;
import com.sven.ou.module.lol.entity.Champion;
import com.sven.ou.module.lol.entity.UserArea;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by sven-ou on 2016/7/27.
 */
public interface DaiWanLolDataApi{
    /**
     * 获取指定用户名所在的区服信息及用户在区服中的基本信息。
     * @param keyword 关键字
     */
    @GET("UserArea")
    Observable<DaiWanLolResult<List<UserArea>>> getUserArea(@Query("keyword") String keyword);

    /**
     * 获取玩家前131個排名信息
     */
    @GET("Champion")
    Observable<DaiWanLolResult<List<Champion>>> getChampion();

    /**
     * 获取大区列表
     */
    @GET("Area")
    Observable<DaiWanLolResult<List<Area>>> getArea();
}
