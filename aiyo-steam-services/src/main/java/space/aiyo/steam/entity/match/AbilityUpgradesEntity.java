package space.aiyo.steam.entity.match;

import java.io.Serializable;

/**
 * 技能加点
 * Created by Yo on 2017/6/10.
 */
public class AbilityUpgradesEntity implements Serializable {
    /**
     * ID of the ability upgraded.
     */
    private int ability;
    /**
     * Time since match start that the ability was upgraded.
     */
    private int time;
    /**
     * The level of the player at time of upgrading.
     */
    private int level;

    /**
     * 无参构造方法
     * 兼容fastjson
     */
    public AbilityUpgradesEntity() {
    }

    @Override
    public String toString() {
        return "AbilityUpgradesEntity{" +
                "ability=" + ability +
                ", time=" + time +
                ", level=" + level +
                '}';
    }

    public int getAbility() {
        return ability;
    }

    public void setAbility(int ability) {
        this.ability = ability;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}
