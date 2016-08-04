package com.sven.ou.module.lol.entity;

/**
 * 英雄熟练度排名的玩家信息
 */
public class SkillRank {
    /**
     * 排名
     */
    private int index;

    /**
     * 大区唯一标识
     */
    private int area_id;

    /**
     * 熟练度
     */
    private int cevValue;

    /**
     * 图标
     */
    private int iconId;

    /**
     * 用户唯一标识
     */
    private String uin;

    /**
     * 用户名称
     */
    private String uName;

    /**
     * 大区名称
     */
    private String areaName;

    public void setIndex(int index){
        this.index = index;
    }
    public int getIndex(){
        return this.index;
    }
    public void setArea_id(int area_id){
        this.area_id = area_id;
    }
    public int getArea_id(){
        return this.area_id;
    }
    public void setCevValue(int cevValue){
        this.cevValue = cevValue;
    }
    public int getCevValue(){
        return this.cevValue;
    }
    public void setIconId(int iconId){
        this.iconId = iconId;
    }
    public int getIconId(){
        return this.iconId;
    }
    public void setUin(String uin){
        this.uin = uin;
    }
    public String getUin(){
        return this.uin;
    }
    public void setUName(String uName){
        this.uName = uName;
    }
    public String getUName(){
        return this.uName;
    }
    public void setAreaName(String areaName){
        this.areaName = areaName;
    }
    public String getAreaName(){
        return this.areaName;
    }

    @Override
    public String toString() {
        return "SkillRank{" +
                "index=" + index +
                ", area_id=" + area_id +
                ", cevValue=" + cevValue +
                ", iconId=" + iconId +
                ", uin='" + uin + '\'' +
                ", uName='" + uName + '\'' +
                ", areaName='" + areaName + '\'' +
                '}';
    }
}
