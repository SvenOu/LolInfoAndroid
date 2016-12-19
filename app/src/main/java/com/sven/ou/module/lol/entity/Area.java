package com.sven.ou.module.lol.entity;

import com.google.gson.annotations.SerializedName;
import com.sven.ou.common.utils.BeanUtils;
import com.sven.ou.module.lol.db.Area_;

import java.io.Serializable;

/**
 * 国服区信息
 */
public class Area implements Serializable {
    /**
     * 区服ID,
     * SerializedName("id") 是{@link  BeanUtils}所需
     */
    @SerializedName(value = "id")
    private int areaId;

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

    public Area_ toArea_() {
//        Area_ areaDB = new Area_();
//        areaDB.setAreaId(areaId);
//        areaDB.setStrid(strid);
//        areaDB.setIsp(isp);
//        areaDB.setName(name);
//        areaDB.setIdc(idc);
//        areaDB.setTcls(tcls);
//        areaDB.setOb(ob);
        return (Area_) BeanUtils.cloneObject(this, Area_.class);
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

    public int getAreaId() {
        return areaId;
    }

    public void setAreaId(int areaId) {
        this.areaId = areaId;
    }

    @Override
    public String toString() {
        return "Area{" +
                "areaId=" + areaId +
                ", strid='" + strid + '\'' +
                ", isp='" + isp + '\'' +
                ", name='" + name + '\'' +
                ", idc='" + idc + '\'' +
                ", tcls=" + tcls +
                ", ob=" + ob +
                '}';
    }
}
