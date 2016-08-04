package com.sven.ou.network.api;

import com.sven.ou.common.entity.DaiWanLolResult;
import com.sven.ou.common.entity.Page;
import com.sven.ou.module.lol.entity.Area;
import com.sven.ou.module.lol.entity.Champion;
import com.sven.ou.module.lol.entity.OnlineStatus;
import com.sven.ou.module.lol.entity.SkillRank;
import com.sven.ou.module.lol.entity.UserArea;
import com.sven.ou.module.lol.entity.UserHotInfo;
import com.sven.ou.module.lol.entity.thisweek.Hero;

import java.util.List;
import java.util.Map;

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
     * 获取用户基本信息
     * @param qquin 英雄联盟用户唯一标识，获取方式从API UserArea中进行获取
     * @param vaid 大区ID
     *
     */
    @GET("UserHotInfo")
    Observable<DaiWanLolResult<List<UserHotInfo>>> getUserHotInfo(@Query("qquin") String qquin, @Query("vaid") int vaid);

    /**
     * 获取本周限免英雄
     */
    @GET("Free")
    Observable<DaiWanLolResult<List<Map<String, Hero>>>> getFree();

    /**
     * 获取指定英雄玩家排行
     * @param championid 英雄联盟用户唯一标识，获取方式从API UserArea中进行获取
     * @param p 第p页
     */
    @GET("ChampionRank")
    Observable<DaiWanLolResult<List<Page<List<SkillRank>>>>> getSkillRank(@Query("championid") String championid,@Query("p") int p);

    /**
     * 英雄详细数据信息，包括技能加点，天赋等,
     * 数据结构相对复杂
     * @param championid 英雄联盟用户唯一标识，获取方式从API UserArea中进行获取
     */
    @GET("GetChampionDetail")
    Observable<DaiWanLolResult<List<Map>>> getChampionDetail(@Query("champion_id") String championid);


    @GET("Online")
    Observable<DaiWanLolResult<List<OnlineStatus>>> getOnline(@Query("keyword") String keyword, @Query("vaid") String vaid);

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
