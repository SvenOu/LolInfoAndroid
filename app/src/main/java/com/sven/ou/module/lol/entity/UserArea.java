package com.sven.ou.module.lol.entity;

import java.io.Serializable;

/**
 * 用户名所在的区服信息及用户在区服中的基本信息
 */
public class UserArea implements Serializable {
    /**
     * 大区ID
     */
    private int area_id;

    /**
     * 用户唯一码
     */
    private String qquin;

    /**
     * 用户头像
     */
    private int icon_id;

    private String name;

    /**
     * 级别
     */
    private int level;

    /**
     * 段位：
     * 0-Challenger
     * 1-Diamond
     * 2-Platinum
     * 3-Gold
     * 4-Silver
     * 5-Bronze
     * 6-Master
     */
    private int tier;

    /**
     * 子段位：
     * 0-4 分别为I-IV
     */
    private int queue;

    /**
     * 胜点
     */
    private int win_point;

    public void setArea_id(int area_id){
        this.area_id = area_id;
    }
    public int getArea_id(){
        return this.area_id;
    }
    public void setQquin(String qquin){
        this.qquin = qquin;
    }
    public String getQquin(){
        return this.qquin;
    }
    public void setIcon_id(int icon_id){
        this.icon_id = icon_id;
    }
    public int getIcon_id(){
        return this.icon_id;
    }
    public void setName(String name){
        this.name = name;
    }
    public String getName(){
        return this.name;
    }
    public void setLevel(int level){
        this.level = level;
    }
    public int getLevel(){
        return this.level;
    }
    public void setTier(int tier){
        this.tier = tier;
    }
    public int getTier(){
        return this.tier;
    }
    public void setQueue(int queue){
        this.queue = queue;
    }
    public int getQueue(){
        return this.queue;
    }
    public void setWin_point(int win_point){
        this.win_point = win_point;
    }
    public int getWin_point(){
        return this.win_point;
    }

    @Override
    public String toString() {
        return "UserArea{" +
                "area_id=" + area_id +
                ", qquin='" + qquin + '\'' +
                ", icon_id=" + icon_id +
                ", name='" + name + '\'' +
                ", level=" + level +
                ", tier=" + tier +
                ", queue=" + queue +
                ", win_point=" + win_point +
                '}';
    }
}
