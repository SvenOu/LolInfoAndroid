package com.sven.ou.module.lol.db;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Select;
import com.google.gson.annotations.SerializedName;
import com.sven.ou.common.utils.BeanUtils;
import com.sven.ou.common.utils.Logger;
import com.sven.ou.module.lol.entity.Area;

import java.io.Serializable;

/**
 * 国服区信息
 */
@Table(name = "area")
public class Area_ extends Model{

    private static final String TAG = Area_.class.getSimpleName();

    /**
     * 区服ID
     */
    @SerializedName(value = "id")
    @Column(name = "area_id", unique = true)
    private int areaId;

    /**
     * 简写
     */
    @Column(name = "str_id")
    private String strid;

    /**
     * 名称
     */
    @Column(name = "isp")
    private String isp;

    /**
     * 区服名称
     */
    @Column(name = "name")
    private String name;

    @Column(name = "idc")
    private String idc;

    @Column(name = "tcls")
    private int tcls;

    /**
     * 是否支持OB文件
     */
    @Column(name = "ob")
    private int ob;

    public Area_() {}

    public Area toArea(){
//        Area area = new Area();
//        area.setAreaId(areaId);
//        area.setStrid(strid);
//        area.setIsp(isp);
//        area.setName(name);
//        area.setIdc(idc);
//        area.setTcls(tcls);
//        area.setOb(ob);
        return (Area) BeanUtils.cloneObject(this, Area.class);
    }

    /**
     * 耗时操作
     * @return 返回大区信息
     */
    public static Area_ findArea_(int areaId){
        Area_ area_ =
                new Select().from(Area_.class).
                        where("area_id = ? ", areaId)
                        .executeSingle();
        if(null == area_){
            Logger.e(TAG, "cannot find Area_ !");
        }
        return area_;
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
