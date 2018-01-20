package entity.dota.match;

import java.util.List;

/**
 * 一场比赛中的选手数据
 * Created by yo on 2017/5/27.
 */
public class MatchPlayerEntity {

  /**
   * 32-bit account ID
   */
  private long account_id;
  /**
   * A player'base slot is returned via an 8-bit unsigned integer.
   * The first bit represent the player'base team, false if Radiant and true if dire.
   * The final three bits represent the player'base position in that team, from 0-4.
   * ┌─────────────── Team (false if Radiant, true if Dire).
   * │ ┌─┬─┬─┬─────── Not used.
   * │ │ │ │ │ ┌─┬─┬─ The position of a player within their team (0-4).
   * │ │ │ │ │ │ │ │
   * 0 0 0 0 0 0 0 0
   */
  private int player_slot;
  /**
   * The hero'base unique ID. A list of hero IDs can be found via the GetHeroes method.
   */
  private int hero_id;
  /**
   * ID of the top-left inventory item.
   */
  private int item_0;
  /**
   * ID of the top-center inventory item.
   */
  private int item_1;
  /**
   * ID of the top-right inventory item.
   */
  private int item_2;
  /**
   * ID of the bottom-left inventory item.
   */
  private int item_3;
  /**
   * ID of the bottom-center inventory item.
   */
  private int item_4;
  /**
   * ID of the bottom-right inventory item.
   */
  private int item_5;
  private int backpack_0;
  private int backpack_1;
  private int backpack_2;
  private int kills;
  private int deaths;
  private int assists;
  /**
   * 0 - NONE - finished match, no abandon.
   * 1 - DISCONNECTED - player DC, no abandon.
   * 2 - DISCONNECTED_TOO_LONG - player DC > 5min, abandoned.
   * 3 - ABANDONED - player DC, clicked leave, abandoned.
   * 4 - AFK - player AFK, abandoned.
   * 5 - NEVER_CONNECTED - player never connected, no abandon.
   * 6 - NEVER_CONNECTED_TOO_LONG - player took too long to connect, no abandon.
   */
  private int leaver_status;
  /**
   * The amount of last-hits the player got during the match
   */
  private int last_hits;
  /**
   * The amount of denies the player got during the match.
   */
  private int denies;
  /**
   * The player'base overall gold/minute.
   */
  private int gold_per_min;
  /**
   * The player'base overall experience/minute.
   */
  private int xp_per_min;
  /**
   * The player'base level at match end.
   */
  private int level;
  /**
   * The amount of damage the player dealt to heroes.
   */
  private int hero_damage;
  /**
   * The amount of damage the player dealt to towers.
   */
  private int tower_damage;
  /**
   * The amount of health the player had healed on heroes.
   */
  private int hero_dealing;
  /**
   * The amount of gold the player had remaining at the end of the match.
   */
  private int gold;
  /**
   * The amount of gold the player spent during the match.
   */
  private int gold_spent;
  private int scaled_hero_damage;
  private int scaled_tower_damage;
  private int scaled_hero_healing;
  private List<AbilityUpgradesEntity> ability_upgrades;
  private List<AdditionalUnitsEntity> additional_units;


  /**
   * 无参构造方法
   * 兼容fastjson
   */
  public MatchPlayerEntity() {
  }

  public long getAccount_id() {
    return account_id;
  }

  public void setAccount_id(long account_id) {
    this.account_id = account_id;
  }

  public int getPlayer_slot() {
    return player_slot;
  }

  public void setPlayer_slot(int player_slot) {
    this.player_slot = player_slot;
  }

  public int getHero_id() {
    return hero_id;
  }

  public void setHero_id(int hero_id) {
    this.hero_id = hero_id;
  }

  public int getItem_0() {
    return item_0;
  }

  public void setItem_0(int item_0) {
    this.item_0 = item_0;
  }

  public int getItem_1() {
    return item_1;
  }

  public void setItem_1(int item_1) {
    this.item_1 = item_1;
  }

  public int getItem_2() {
    return item_2;
  }

  public void setItem_2(int item_2) {
    this.item_2 = item_2;
  }

  public int getItem_3() {
    return item_3;
  }

  public void setItem_3(int item_3) {
    this.item_3 = item_3;
  }

  public int getItem_4() {
    return item_4;
  }

  public void setItem_4(int item_4) {
    this.item_4 = item_4;
  }

  public int getItem_5() {
    return item_5;
  }

  public void setItem_5(int item_5) {
    this.item_5 = item_5;
  }

  public int getBackpack_0() {
    return backpack_0;
  }

  public void setBackpack_0(int backpack_0) {
    this.backpack_0 = backpack_0;
  }

  public int getBackpack_1() {
    return backpack_1;
  }

  public void setBackpack_1(int backpack_1) {
    this.backpack_1 = backpack_1;
  }

  public int getBackpack_2() {
    return backpack_2;
  }

  public void setBackpack_2(int backpack_2) {
    this.backpack_2 = backpack_2;
  }

  public int getKills() {
    return kills;
  }

  public void setKills(int kills) {
    this.kills = kills;
  }

  public int getDeaths() {
    return deaths;
  }

  public void setDeaths(int deaths) {
    this.deaths = deaths;
  }

  public int getAssists() {
    return assists;
  }

  public void setAssists(int assists) {
    this.assists = assists;
  }

  public int getLeaver_status() {
    return leaver_status;
  }

  public void setLeaver_status(int leaver_status) {
    this.leaver_status = leaver_status;
  }

  public int getLast_hits() {
    return last_hits;
  }

  public void setLast_hits(int last_hits) {
    this.last_hits = last_hits;
  }

  public int getDenies() {
    return denies;
  }

  public void setDenies(int denies) {
    this.denies = denies;
  }

  public int getGold_per_min() {
    return gold_per_min;
  }

  public void setGold_per_min(int gold_per_min) {
    this.gold_per_min = gold_per_min;
  }

  public int getXp_per_min() {
    return xp_per_min;
  }

  public void setXp_per_min(int xp_per_min) {
    this.xp_per_min = xp_per_min;
  }

  public int getLevel() {
    return level;
  }

  public void setLevel(int level) {
    this.level = level;
  }

  public int getHero_damage() {
    return hero_damage;
  }

  public void setHero_damage(int hero_damage) {
    this.hero_damage = hero_damage;
  }

  public int getTower_damage() {
    return tower_damage;
  }

  public void setTower_damage(int tower_damage) {
    this.tower_damage = tower_damage;
  }

  public int getHero_dealing() {
    return hero_dealing;
  }

  public void setHero_dealing(int hero_dealing) {
    this.hero_dealing = hero_dealing;
  }

  public int getGold() {
    return gold;
  }

  public void setGold(int gold) {
    this.gold = gold;
  }

  public int getGold_spent() {
    return gold_spent;
  }

  public void setGold_spent(int gold_spent) {
    this.gold_spent = gold_spent;
  }

  public int getScaled_hero_damage() {
    return scaled_hero_damage;
  }

  public void setScaled_hero_damage(int scaled_hero_damage) {
    this.scaled_hero_damage = scaled_hero_damage;
  }

  public int getScaled_tower_damage() {
    return scaled_tower_damage;
  }

  public void setScaled_tower_damage(int scaled_tower_damage) {
    this.scaled_tower_damage = scaled_tower_damage;
  }

  public int getScaled_hero_healing() {
    return scaled_hero_healing;
  }

  public void setScaled_hero_healing(int scaled_hero_healing) {
    this.scaled_hero_healing = scaled_hero_healing;
  }

  public List<AbilityUpgradesEntity> getAbility_upgrades() {
    return ability_upgrades;
  }

  public void setAbility_upgrades(List<AbilityUpgradesEntity> ability_upgrades) {
    this.ability_upgrades = ability_upgrades;
  }

  public List<AdditionalUnitsEntity> getAdditional_units() {
    return additional_units;
  }

  public void setAdditional_units(List<AdditionalUnitsEntity> additional_units) {
    this.additional_units = additional_units;
  }

  @Override
  public String toString() {
    return "MatchPlayerEntity{" +
        "account_id=" + account_id +
        ", player_slot=" + player_slot +
        ", hero_id=" + hero_id +
        ", item_0=" + item_0 +
        ", item_1=" + item_1 +
        ", item_2=" + item_2 +
        ", item_3=" + item_3 +
        ", item_4=" + item_4 +
        ", item_5=" + item_5 +
        ", backpack_0=" + backpack_0 +
        ", backpack_1=" + backpack_1 +
        ", backpack_2=" + backpack_2 +
        ", kills=" + kills +
        ", deaths=" + deaths +
        ", assists=" + assists +
        ", leaver_status=" + leaver_status +
        ", last_hits=" + last_hits +
        ", denies=" + denies +
        ", gold_per_min=" + gold_per_min +
        ", xp_per_min=" + xp_per_min +
        ", level=" + level +
        ", hero_damage=" + hero_damage +
        ", tower_damage=" + tower_damage +
        ", hero_dealing=" + hero_dealing +
        ", gold=" + gold +
        ", gold_spent=" + gold_spent +
        ", scaled_hero_damage=" + scaled_hero_damage +
        ", scaled_tower_damage=" + scaled_tower_damage +
        ", scaled_hero_healing=" + scaled_hero_healing +
        ", ability_upgrades=" + ability_upgrades +
        ", additional_units=" + additional_units +
        '}';
  }
}
