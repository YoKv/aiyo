package space.aiyo.steam.entity;

import com.alibaba.fastjson.JSONArray;

/**
 * 一场比赛中的选手数据
 * Created by yo on 2017/5/27.
 */
public class Dota2MatchPlayerEntity {

    private int account_id;
    /**
     * A player's slot is returned via an 8-bit unsigned integer.
     * The first bit represent the player's team, false if Radiant and true if dire.
     * The final three bits represent the player's position in that team, from 0-4.
     * ┌─────────────── Team (false if Radiant, true if Dire).
     * │ ┌─┬─┬─┬─────── Not used.
     * │ │ │ │ │ ┌─┬─┬─ The position of a player within their team (0-4).
     * │ │ │ │ │ │ │ │
     * 0 0 0 0 0 0 0 0
     */
    private int player_slot;
    private int hero_id;
    private int item_0;
    private int item_1;
    private int item_2;
    private int item_3;
    private int item_4;
    private int item_5;
    private int backpack_0;
    private int backpack_1;
    private int backpack_2;
    private int kills;
    private int deaths;
    private int assists;
    private int leaver_status;
    private int last_hits;
    private int denies;
    private int gold_per_min;
    private int xp_per_min;
    private int level;
    private int hero_damage;
    private int tower_damage;
    private int hero_healing;
    private int gold;
    private int gold_spent;
    private int scaled_hero_damage;
    private int scaled_tower_damage;
    private int scaled_hero_healing;
    private JSONArray ability_upgrades;

    public Dota2MatchPlayerEntity(int account_id, int player_slot, int hero_id) {
        this.account_id = account_id;
        this.player_slot = player_slot;
        this.hero_id = hero_id;
    }
}

class abilityUpgrades {
    private int ability;
    private int time;
    private int level;

    public abilityUpgrades(int ability, int time, int level) {
        this.ability = ability;
        this.time = time;
        this.level = level;
    }
}
