package com.sven.ou.module.lol.entity.thisweek;

import java.io.Serializable;

/**
 * 暂无，可通key过接口GetChampionImage，GetChampionCNName，GetChampionENName 获取相关信息
 */
public class Image implements Serializable{
    private String full;

    private String sprite;

    private String group;

    private int x;

    private int y;

    private int w;

    private int h;

    public void setFull(String full){
        this.full = full;
    }
    public String getFull(){
        return this.full;
    }
    public void setSprite(String sprite){
        this.sprite = sprite;
    }
    public String getSprite(){
        return this.sprite;
    }
    public void setGroup(String group){
        this.group = group;
    }
    public String getGroup(){
        return this.group;
    }
    public void setX(int x){
        this.x = x;
    }
    public int getX(){
        return this.x;
    }
    public void setY(int y){
        this.y = y;
    }
    public int getY(){
        return this.y;
    }
    public void setW(int w){
        this.w = w;
    }
    public int getW(){
        return this.w;
    }
    public void setH(int h){
        this.h = h;
    }
    public int getH(){
        return this.h;
    }

    @Override
    public String toString() {
        return "Image{" +
                "full='" + full + '\'' +
                ", sprite='" + sprite + '\'' +
                ", group='" + group + '\'' +
                ", x=" + x +
                ", y=" + y +
                ", w=" + w +
                ", h=" + h +
                '}';
    }
}
