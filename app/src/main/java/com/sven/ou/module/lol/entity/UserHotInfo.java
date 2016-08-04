package com.sven.ou.module.lol.entity;

/**
 * 用户基本信息
 */
public class UserHotInfo {
    /**
     * 区服ID
     */
    private int area_id;

    /**
     * 用户唯一码
     */
    private String qquin;

    /**
     * 用户名称
     */
    private String name;

    /**
     * 图标ID
     */
    private int icon;

    /**
     * 战斗力
     */
    private int power_value;

    /**
     * 等级
     */
    private int level;

    /**
     * 段位
     */
    private int tier;

    /**
     * 字段位
     */
    private int queue;

    /**
     * 胜点
     */
    private int win_point;

    private int fellow_num;

    private int fans_num;

    private int praise_num;

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
    public void setName(String name){
        this.name = name;
    }
    public String getName(){
        return this.name;
    }
    public void setIcon(int icon){
        this.icon = icon;
    }
    public int getIcon(){
        return this.icon;
    }
    public void setPower_value(int power_value){
        this.power_value = power_value;
    }
    public int getPower_value(){
        return this.power_value;
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
    public void setFellow_num(int fellow_num){
        this.fellow_num = fellow_num;
    }
    public int getFellow_num(){
        return this.fellow_num;
    }
    public void setFans_num(int fans_num){
        this.fans_num = fans_num;
    }
    public int getFans_num(){
        return this.fans_num;
    }
    public void setPraise_num(int praise_num){
        this.praise_num = praise_num;
    }
    public int getPraise_num(){
        return this.praise_num;
    }

    @Override
    public String toString() {
        return "UserHotInfo{" +
                "area_id=" + area_id +
                ", qquin='" + qquin + '\'' +
                ", name='" + name + '\'' +
                ", icon=" + icon +
                ", power_value=" + power_value +
                ", level=" + level +
                ", tier=" + tier +
                ", queue=" + queue +
                ", win_point=" + win_point +
                ", fellow_num=" + fellow_num +
                ", fans_num=" + fans_num +
                ", praise_num=" + praise_num +
                '}';
    }
}
