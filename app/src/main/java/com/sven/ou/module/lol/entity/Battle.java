package com.sven.ou.module.lol.entity;

import java.io.Serializable;

/**
 * 单场战斗数据
 */
public class Battle implements Serializable {
    private int game_id;

    private String battle_time;

    private int battle_map;

    private int game_type;

    private int game_mode;

    private int champion_id;

    private int win;

    private int flag;

    private int ext_flag;

    public void setGame_id(int game_id){
        this.game_id = game_id;
    }
    public int getGame_id(){
        return this.game_id;
    }
    public void setBattle_time(String battle_time){
        this.battle_time = battle_time;
    }
    public String getBattle_time(){
        return this.battle_time;
    }
    public void setBattle_map(int battle_map){
        this.battle_map = battle_map;
    }
    public int getBattle_map(){
        return this.battle_map;
    }
    public void setGame_type(int game_type){
        this.game_type = game_type;
    }
    public int getGame_type(){
        return this.game_type;
    }
    public void setGame_mode(int game_mode){
        this.game_mode = game_mode;
    }
    public int getGame_mode(){
        return this.game_mode;
    }
    public void setChampion_id(int champion_id){
        this.champion_id = champion_id;
    }
    public int getChampion_id(){
        return this.champion_id;
    }
    public void setWin(int win){
        this.win = win;
    }
    public int getWin(){
        return this.win;
    }
    public void setFlag(int flag){
        this.flag = flag;
    }
    public int getFlag(){
        return this.flag;
    }
    public void setExt_flag(int ext_flag){
        this.ext_flag = ext_flag;
    }
    public int getExt_flag(){
        return this.ext_flag;
    }

    @Override
    public String toString() {
        return "Battle{" +
                "game_id=" + game_id +
                ", battle_time='" + battle_time + '\'' +
                ", battle_map=" + battle_map +
                ", game_type=" + game_type +
                ", game_mode=" + game_mode +
                ", champion_id=" + champion_id +
                ", win=" + win +
                ", flag=" + flag +
                ", ext_flag=" + ext_flag +
                '}';
    }
}
