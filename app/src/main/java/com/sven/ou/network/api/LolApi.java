package com.sven.ou.network.api;

import com.sven.ou.module.lol.entity.PlayerInfo;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by sven-ou on 2016/7/27.
 */
public interface LolApi {
    @GET("playerinfo.php/")
    Observable<PlayerInfo> getPlayerInfo(@Query("serverName") String serverName, @Query("playerName") String playerName);

    @GET("record.php/")
    Observable<Record> getRecord(@Query("serverName") String serverName, @Query("playerName") String playerName);
}
