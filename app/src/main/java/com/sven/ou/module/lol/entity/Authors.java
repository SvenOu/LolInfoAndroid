package com.sven.ou.module.lol.entity;

/**
 * Created by sven-ou on 2016/7/29.
 */
public class Authors {
    private String id;

    /**
     * 名称
     */
    private String name;

    /**
     * 解说图片
     */
    private String img;

    /**
     * 性别
     */
    private String isex;

    private String ivideo;

    /**
     * 描述
     */
    private String desc;

    private String usernum;

    private String videonum;

    private int count;

    public void setId(String id){
        this.id = id;
    }
    public String getId(){
        return this.id;
    }
    public void setName(String name){
        this.name = name;
    }
    public String getName(){
        return this.name;
    }
    public void setImg(String img){
        this.img = img;
    }
    public String getImg(){
        return this.img;
    }
    public void setIsex(String isex){
        this.isex = isex;
    }
    public String getIsex(){
        return this.isex;
    }
    public void setIvideo(String ivideo){
        this.ivideo = ivideo;
    }
    public String getIvideo(){
        return this.ivideo;
    }
    public void setDesc(String desc){
        this.desc = desc;
    }
    public String getDesc(){
        return this.desc;
    }
    public void setUsernum(String usernum){
        this.usernum = usernum;
    }
    public String getUsernum(){
        return this.usernum;
    }
    public void setVideonum(String videonum){
        this.videonum = videonum;
    }
    public String getVideonum(){
        return this.videonum;
    }
    public void setCount(int count){
        this.count = count;
    }
    public int getCount(){
        return this.count;
    }

    @Override
    public String toString() {
        return "Authors{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", img='" + img + '\'' +
                ", isex='" + isex + '\'' +
                ", ivideo='" + ivideo + '\'' +
                ", desc='" + desc + '\'' +
                ", usernum='" + usernum + '\'' +
                ", videonum='" + videonum + '\'' +
                ", count=" + count +
                '}';
    }
}
