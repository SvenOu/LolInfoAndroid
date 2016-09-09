package com.sven.ou.module.lol.entity;

import java.io.Serializable;

/**
 * 国服区信息
 */
public class Area implements Serializable{
    /**
     * 区服ID
     */
    private int id;

    /**
     * 简写
     */
    private String strid;

    /**
     * 名称
     */
    private String isp;

    /**
     * 区服名称
     */
    private String name;

    private String idc;

    private int tcls;

    /**
     * 是否支持OB文件
     */
    private int ob;

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }

    public void setStrid(String strid) {
        this.strid = strid;
    }

    public String getStrid() {
        return this.strid;
    }

    public void setIsp(String isp) {
        this.isp = isp;
    }

    public String getIsp() {
        return this.isp;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setIdc(String idc) {
        this.idc = idc;
    }

    public String getIdc() {
        return this.idc;
    }

    public void setTcls(int tcls) {
        this.tcls = tcls;
    }

    public int getTcls() {
        return this.tcls;
    }

    public void setOb(int ob) {
        this.ob = ob;
    }

    public int getOb() {
        return this.ob;
    }

    @Override
    public String toString() {
        return "Area{" +
                "id=" + id +
                ", strid='" + strid + '\'' +
                ", isp='" + isp + '\'' +
                ", name='" + name + '\'' +
                ", idc='" + idc + '\'' +
                ", tcls=" + tcls +
                ", ob=" + ob +
                '}';
    }
}
