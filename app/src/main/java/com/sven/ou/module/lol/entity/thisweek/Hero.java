package com.sven.ou.module.lol.entity.thisweek;

import java.util.Map;

/**
 * 英雄信息
 */
public class Hero {
    /**
     * 英文名称
     */
    private String id;

    /**
     * 英雄ID，可通过接口GetChampionImage，GetChampionCNName，GetChampionENName 获取相关信息
     */
    private String key;

    /**
     * 中文名
     */
    private String name;

    /**
     * 别名
     */
    private String title;

    /**
     * 战士,法师等
     */
    private String[] tags ;

    /**
     * 攻击,防御,魔法,难度
     */
    private Info info;

    /**
     * 暂无，可通key过接口GetChampionImage，GetChampionCNName，GetChampionENName 获取相关信息
     */
    private Image image;

    /**
     *有后台的map转换过来的key
     */
    private String keyHero;

    public void setId(String id){
        this.id = id;
    }
    public String getId(){
        return this.id;
    }
    public void setKey(String key){
        this.key = key;
    }
    public String getKey(){
        return this.key;
    }
    public void setName(String name){
        this.name = name;
    }
    public String getName(){
        return this.name;
    }
    public void setTitle(String title){
        this.title = title;
    }
    public String getTitle(){
        return this.title;
    }

    public String[] getTags() {
        return tags;
    }

    public void setTags(String[] tags) {
        this.tags = tags;
    }

    public void setInfo(Info info){
        this.info = info;
    }
    public Info getInfo(){
        return this.info;
    }
    public void setImage(Image image){
        this.image = image;
    }
    public Image getImage(){
        return this.image;
    }

    public String getKeyHero() {
        return keyHero;
    }

    public void setKeyHero(String keyHero) {
        this.keyHero = keyHero;
    }
}
