package space.aiyo.steam.entity;

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
     ┌─────────────── Team (false if Radiant, true if Dire).
     │ ┌─┬─┬─┬─────── Not used.
     │ │ │ │ │ ┌─┬─┬─ The position of a player within their team (0-4).
     │ │ │ │ │ │ │ │
     0 0 0 0 0 0 0 0
     *
     *
     */
    private int player_slot;
    private int hero_id;

    public Dota2MatchPlayerEntity(int account_id, int player_slot, int hero_id) {
        this.account_id = account_id;
        this.player_slot = player_slot;
        this.hero_id = hero_id;
    }
}

/*
{
        "account_id":344441990,
        "player_slot":0,
        "hero_id":72,
        "item_0":63,
        "item_1":156,
        "item_2":116,
        "item_3":108,
        "item_4":154,
        "item_5":158,
        "backpack_0":46,
        "backpack_1":65,
        "backpack_2":0,
        "kills":16,
        "deaths":7,
        "assists":26,
        "leaver_status":0,
        "last_hits":321,
        "denies":13,
        "gold_per_min":679,
        "xp_per_min":653,
        "level":25,
        "hero_damage":44727,
        "tower_damage":7008,
        "hero_healing":0,
        "gold":2735,
        "gold_spent":28695,
        "scaled_hero_damage":25061,
        "scaled_tower_damage":4816,
        "scaled_hero_healing":0,
        "ability_upgrades":[
        {
        "ability":5651,
        "time":389,
        "level":1
        },
        {
        "ability":5257,
        "time":507,
        "level":2
        },
        {
        "ability":5651,
        "time":596,
        "level":3
        },
        {
        "ability":5257,
        "time":717,
        "level":4
        },
        {
        "ability":5651,
        "time":792,
        "level":5
        },
        {
        "ability":5067,
        "time":944,
        "level":6
        },
        {
        "ability":5651,
        "time":995,
        "level":7
        },
        {
        "ability":5257,
        "time":1047,
        "level":8
        },
        {
        "ability":5257,
        "time":1203,
        "level":9
        },
        {
        "ability":6405,
        "time":1271,
        "level":10
        },
        {
        "ability":5363,
        "time":1366,
        "level":11
        },
        {
        "ability":5067,
        "time":1489,
        "level":12
        },
        {
        "ability":5363,
        "time":1539,
        "level":13
        },
        {
        "ability":5363,
        "time":1550,
        "level":14
        },
        {
        "ability":5939,
        "time":1575,
        "level":15
        },
        {
        "ability":5363,
        "time":1667,
        "level":16
        },
        {
        "ability":5067,
        "time":1893,
        "level":17
        },
        {
        "ability":6077,
        "time":2165,
        "level":18
        },
        {
        "ability":6312,
        "time":2951,
        "level":19
        }
        ]

        },*/
