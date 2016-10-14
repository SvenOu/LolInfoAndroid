package com.sven.ou.module.lol.db;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Select;
import com.sven.ou.common.utils.Logger;

/**
 * 段位信息
 * 注意: migrations生成的表都要加上 id = xxx 属性
 * 而且 xxx必须 INTEGER PRIMARY KEY  AUTOINCREMENT  NOT NULL  UNIQUE
 */
@Table(name = "battle_level", id ="level_id")
public class BattleLevel extends Model{

    private static final String TAG = BattleLevel.class.getSimpleName();

    /**
     * 段
     */
    @Column(name = "tier")
    private int tier;

    /**
     * 级
     */
    @Column(name = "queue")
    private int queue;

    /**
     * 说明
     */
    @Column(name = "desc")
    private String desc;

    /**
     * 耗时操作
     * @param tier 段
     * @param queue 位
     * @return 返回段位信息
     */
    public static BattleLevel findBattleLevel(int tier,  int queue){
        BattleLevel battleLevel =
                new Select().from(BattleLevel.class).
                        where("tier = ? and queue = ? ", tier, queue)
                        .executeSingle();
        if(null == battleLevel){
            Logger.e(TAG, "cannot find battleLevel !");
        }
        return battleLevel;
    }

    public BattleLevel() {}

    public int getTier() {
        return tier;
    }

    public void setTier(int tier) {
        this.tier = tier;
    }

    public int getQueue() {
        return queue;
    }

    public void setQueue(int queue) {
        this.queue = queue;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Override
    public String toString() {
        return "BattleLevel{" +
                ", tier=" + tier +
                ", queue=" + queue +
                ", desc='" + desc + '\'' +
                "} " + super.toString();
    }
}
