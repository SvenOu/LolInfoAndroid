package com.sven.ou.module.lol.entity.thisweek;

import java.io.Serializable;

/**
 * 英雄屬性
 * 攻击,防御,魔法,难度
 */
public class Info implements Serializable{
    private int attack;

    private int defense;

    private int magic;

    private int difficulty;

    public void setAttack(int attack){
        this.attack = attack;
    }
    public int getAttack(){
        return this.attack;
    }
    public void setDefense(int defense){
        this.defense = defense;
    }
    public int getDefense(){
        return this.defense;
    }
    public void setMagic(int magic){
        this.magic = magic;
    }
    public int getMagic(){
        return this.magic;
    }
    public void setDifficulty(int difficulty){
        this.difficulty = difficulty;
    }
    public int getDifficulty(){
        return this.difficulty;
    }

    @Override
    public String toString() {
        return "Info{" +
                "attack=" + attack +
                ", defense=" + defense +
                ", magic=" + magic +
                ", difficulty=" + difficulty +
                '}';
    }
}
