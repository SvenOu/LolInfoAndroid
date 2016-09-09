package com.sven.ou.module.lol.entity;

import java.io.Serializable;

/**
 * 玩家排名信息
 */
public class Champion implements Serializable {
    private int champion_id;

    private int use_ratio;

    private int win_ratio;

    private int rank;

    private int kda;

    private int avg_k_num;

    private int avg_d_num;

    private int avg_a_num;

    private int minions_killed_per_min;

    private int gold_earned_per_min;

    public void setChampion_id(int champion_id){
        this.champion_id = champion_id;
    }
    public int getChampion_id(){
        return this.champion_id;
    }
    public void setUse_ratio(int use_ratio){
        this.use_ratio = use_ratio;
    }
    public int getUse_ratio(){
        return this.use_ratio;
    }
    public void setWin_ratio(int win_ratio){
        this.win_ratio = win_ratio;
    }
    public int getWin_ratio(){
        return this.win_ratio;
    }
    public void setRank(int rank){
        this.rank = rank;
    }
    public int getRank(){
        return this.rank;
    }
    public void setKda(int kda){
        this.kda = kda;
    }
    public int getKda(){
        return this.kda;
    }
    public void setAvg_k_num(int avg_k_num){
        this.avg_k_num = avg_k_num;
    }
    public int getAvg_k_num(){
        return this.avg_k_num;
    }
    public void setAvg_d_num(int avg_d_num){
        this.avg_d_num = avg_d_num;
    }
    public int getAvg_d_num(){
        return this.avg_d_num;
    }
    public void setAvg_a_num(int avg_a_num){
        this.avg_a_num = avg_a_num;
    }
    public int getAvg_a_num(){
        return this.avg_a_num;
    }
    public void setMinions_killed_per_min(int minions_killed_per_min){
        this.minions_killed_per_min = minions_killed_per_min;
    }
    public int getMinions_killed_per_min(){
        return this.minions_killed_per_min;
    }
    public void setGold_earned_per_min(int gold_earned_per_min){
        this.gold_earned_per_min = gold_earned_per_min;
    }
    public int getGold_earned_per_min(){
        return this.gold_earned_per_min;
    }

    @Override
    public String toString() {
        return "Champion{" +
                "champion_id=" + champion_id +
                ", use_ratio=" + use_ratio +
                ", win_ratio=" + win_ratio +
                ", rank=" + rank +
                ", kda=" + kda +
                ", avg_k_num=" + avg_k_num +
                ", avg_d_num=" + avg_d_num +
                ", avg_a_num=" + avg_a_num +
                ", minions_killed_per_min=" + minions_killed_per_min +
                ", gold_earned_per_min=" + gold_earned_per_min +
                '}';
    }
}
