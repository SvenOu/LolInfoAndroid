package com.sven.ou.module.lol.entity;

import java.io.Serializable;

/**
 * Created by sven-ou on 2016/8/4.
 */
public class OnlineStatus implements Serializable {
    private String status;

    private String gameId;

    public void setStatus(String status){
        this.status = status;
    }
    public String getStatus(){
        return this.status;
    }
    public void setGameId(String gameId){
        this.gameId = gameId;
    }
    public String getGameId(){
        return this.gameId;
    }

    @Override
    public String toString() {
        return "OnlineStatus{" +
                "status='" + status + '\'' +
                ", gameId='" + gameId + '\'' +
                '}';
    }
}
