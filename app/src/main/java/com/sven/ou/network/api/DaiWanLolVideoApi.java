package com.sven.ou.network.api;

import com.sven.ou.common.entity.DaiWanLolResult;
import com.sven.ou.module.lol.entity.Author;
import com.sven.ou.module.lol.entity.Video;

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
    Observable<DaiWanLolResult<List<Author>>> getAuthors();

    /**
     * 解说视频
     * @param authorId 作者ID,参见GetAuthors
     * @param p 页数,从1开始
     */
    @GET("GetAuthorVideos")
    Observable<DaiWanLolResult<List<Video>>> getAuthorVideos(@Query("video") String authorId, @Query("p") int p);

    /**
     * 最新视频
     * @param p 页数,从1开始
     */
    @GET("GetNewstVideos")
    Observable<DaiWanLolResult<List<Video>>> getNewstVideos(@Query("p") int p);

    /**
     * 最新视频
     * @param hero 英雄ID,具体参见LOLAPI中英雄字典
     * @param p 页数,从1开始
     */
    @GET("GetHeroVideos")
    Observable<DaiWanLolResult<List<Video>>> getHeroVideos(@Query("hero") int hero, @Query("p") int p);

    /**
     * 最新视频
     * @param keyword 文章标题关键字
     * @param p 页数,从1开始
     */
    @GET("FindVideos")
    Observable<DaiWanLolResult<List<Video>>> findVideos(@Query("keyword") String keyword, @Query("p") int p);

}
