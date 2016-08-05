package com.sven.ou.network.api;

import com.sven.ou.common.entity.DaiWanLolResult;
import com.sven.ou.common.entity.Page;
import com.sven.ou.module.lol.entity.Area;
import com.sven.ou.module.lol.entity.Battle;
import com.sven.ou.module.lol.entity.Champion;
import com.sven.ou.module.lol.entity.Combat;
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
 * 帶玩国服API接口
 */
public interface DaiWanLolDataApi{


    /*********************国服API********************/
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



    /*********************国服高级API********************/
    /**
     * 用户是否在线
     * @param keyword 用户名
     * @param vaid 大区ID
     */
    @GET("Online")
    Observable<DaiWanLolResult<List<OnlineStatus>>> getOnline(@Query("keyword") String keyword, @Query("vaid") int vaid);

    /**
     * 获取用户的每场的战斗基本信息
     * @param qquin 英雄联盟用户唯一标识，获取方式从API UserArea中进行获取
     * @param vaid 区服ID
     * @param p 分页信息
     */
    @GET("CombatList")
    Observable<DaiWanLolResult<List<Combat>>> getCombatList(@Query("qquin") String qquin, @Query("vaid") int vaid, @Query("p") int p);

    /**
     *获取每场的详细战斗详细数据
     * @param qquin 英雄联盟用户唯一标识，获取方式从API UserArea中进行获取
     * @param vaid 区服ID
     * @param gameid 英雄联盟游戏唯一标识
     */
    // TODO: 2016/8/5 需要把map换成pojo
    @GET("GameDetail")
    Observable<DaiWanLolResult<List<Map>>> getGameDetail(@Query("qquin") String qquin, @Query("vaid") int vaid, @Query("gameid") String gameid);



    /*********************国服API字典数据********************/
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


    /*********************国服API辅助接口********************/
    //不起作用的坑爹接口： GetAreaName(大区名称)，GetChampionSkin(皮肤英雄)，
    // GetitemIcon(装备图标),GetMapImage(地图图片), getGameMode(游戏模式)

    /*返回MAP的格式：
        {
            "return": "xxxxx"
        }
    */

    /**
     * 获取用户图标
     * @param iconid
     */
    @GET("GetUserIcon")
    Observable<DaiWanLolResult<List<Map>>> getUserIcon(@Query("iconid") int iconid);

    /**
     * 获取大区名称
     * @param id
     */
    @GET("GetAreaName")
    Observable<DaiWanLolResult<List<Map>>> getAreaName(@Query("id") int id);

    /**
     * 英雄详细数据信息，包括技能加点，天赋等
     * @param champion_id
     * @param skinid
     */
    @GET("GetChampionSkin")
    Observable<DaiWanLolResult<List<Map>>> getChampionSkin(@Query("champion_id") int champion_id, @Query("skinid") int skinid);

    /**
     * 获取英雄图标
     * @param id
     */
    @GET("GetChampionIcon")
    Observable<DaiWanLolResult<List<Map>>> getChampionIconById(@Query("id") int id);

    /**
     * 获取技能图标
     * @param summonspellid
     */
    @GET("GetSummonSpellIcon")
    Observable<DaiWanLolResult<List<Map>>> getSummonSpellIcon(@Query("summonspellid") int summonspellid);

    /**
     * 获取技能图标
     * @param itemid
     */
    @GET("GetitemIcon")
    Observable<DaiWanLolResult<List<Map>>> getitemIcon(@Query("itemid") int itemid);

    /**
     * 获取英雄中文名称
     * @param id
     */
    @GET("GetChampionCNName")
    Observable<DaiWanLolResult<List<Map>>> getChampionCNName(@Query("id") int id);

    /**
     * 获取英雄英文名称
     * @param id
     */
    @GET("GetChampionENName")
    Observable<DaiWanLolResult<List<Map>>> getChampionENName(@Query("id") int id);

    /**
     * 获取地图名称
     * @param id
     */
    @GET("GetMapName")
    Observable<DaiWanLolResult<List<Map>>> getMapName(@Query("id") int id);

    /**
     * 获取地图图片
     * @param id
     */
    @GET("GetMapImage")
    Observable<DaiWanLolResult<List<Map>>> getMapImage(@Query("id") int id);

    /**
     * 获取单场游戏评价信息
     * @param flag
     */
    @GET("GetJudgement")
    Observable<DaiWanLolResult<List<Map>>> getJudgement(@Query("flag") int flag);

    /**
     * 获取胜负名称
     * @param win
     */
    @GET("GetWin")
    Observable<DaiWanLolResult<List<Map>>> getWin(@Query("win") int win);

    /**
     * 获取游戏类型
     * @param game_type
     */
    @GET("GetGameType")
    Observable<DaiWanLolResult<List<Map>>> getGameType(@Query("game_type") int game_type);

    /**
     * 获取游戏类型
     * @param game_mode
     */
    @GET("GetGameMode")
    Observable<DaiWanLolResult<List<Map>>> getGameMode(@Query("game_mode") int game_mode);

}
