package com.sven.ou.module.lol.entity;

import com.sven.ou.common.entity.LolResult;

/**
 * Created by sven-ou on 2016/7/27.
 */
public class PlayerInfo extends LolResult{

    private String portrait;

    private String level;

    private String zhandouli;

    private String good;

    public void setPortrait(String portrait){
        this.portrait = portrait;
    }
    public String getPortrait(){
        return this.portrait;
    }
    public void setLevel(String level){
        this.level = level;
    }
    public String getLevel(){
        return this.level;
    }
    public void setZhandouli(String zhandouli){
        this.zhandouli = zhandouli;
    }
    public String getZhandouli(){
        return this.zhandouli;
    }
    public void setGood(String good){
        this.good = good;
    }
    public String getGood(){
        return this.good;
    }

    @Override
    public String toString() {
        return "PlayerInfo{" +
                "portrait='" + portrait + '\'' +
                ", level='" + level + '\'' +
                ", zhandouli='" + zhandouli + '\'' +
                ", good='" + good + '\'' +
                '}';
    }
}
