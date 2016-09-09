package com.sven.ou.module.lol.entity;

import java.io.Serializable;
import java.util.List;

/**
 * 用户的每场的战斗基本信息
 */
public class Combat implements Serializable {
    private int result;

    private int total_num;

    private int list_num;

    private List<Battle> battle_list ;

    public void setResult(int result){
        this.result = result;
    }
    public int getResult(){
        return this.result;
    }
    public void setTotal_num(int total_num){
        this.total_num = total_num;
    }
    public int getTotal_num(){
        return this.total_num;
    }
    public void setList_num(int list_num){
        this.list_num = list_num;
    }
    public int getList_num(){
        return this.list_num;
    }
    public void setBattle_list(List<Battle> battle_list){
        this.battle_list = battle_list;
    }
    public List<Battle> getBattle_list(){
        return this.battle_list;
    }
}
