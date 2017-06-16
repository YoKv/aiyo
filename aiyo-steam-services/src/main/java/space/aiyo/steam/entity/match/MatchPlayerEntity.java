package space.aiyo.steam.entity.match;

import java.util.List;

/**
 * 一场比赛中的选手数据
 * Created by yo on 2017/5/27.
 */
public class MatchPlayerEntity {

    /**
     * 32-bit account ID
     */
    private long accountId;
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
    private int playerSlot;
    /**
     * The hero's unique ID. A list of hero IDs can be found via the GetHeroes method.
     */
    private int heroId;
    /**
     * ID of the top-left inventory item.
     */
    private int item0;
    /**
     * ID of the top-center inventory item.
     */
    private int item1;
    /**
     * ID of the top-right inventory item.
     */
    private int item2;
    /**
     * ID of the bottom-left inventory item.
     */
    private int item3;
    /**
     * ID of the bottom-center inventory item.
     */
    private int item4;
    /**
     * ID of the bottom-right inventory item.
     */
    private int item5;
    private int backpack0;
    private int backpack1;
    private int backpack2;
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
    private int leaverStatus;
    /**
     * The amount of last-hits the player got during the match
     */
    private int lastHits;
    /**
     * The amount of denies the player got during the match.
     */
    private int denies;
    /**
     * The player's overall gold/minute.
     */
    private int goldPerMin;
    /**
     * The player's overall experience/minute.
     */
    private int xpPerMin;
    /**
     * The player's level at match end.
     */
    private int level;
    /**
     * The amount of damage the player dealt to heroes.
     */
    private int heroDamage;
    /**
     * The amount of damage the player dealt to towers.
     */
    private int towerDamage;
    /**
     * The amount of health the player had healed on heroes.
     */
    private int heroHealing;
    /**
     * The amount of gold the player had remaining at the end of the match.
     */
    private int gold;
    /**
     * The amount of gold the player spent during the match.
     */
    private int goldSpent;
    private int scaledHeroDamage;
    private int scaledTowerDamage;
    private int scaledHeroHealing;
    private List<AbilityUpgradesEntity> abilityUpgrades;
    private List<AdditionalUnitsEntity> additionalUnits;

    public MatchPlayerEntity(int accountId, int playerSlot, int heroId) {
        this.accountId = accountId;
        this.playerSlot = playerSlot;
        this.heroId = heroId;
    }

    public MatchPlayerEntity() {
    }

    @Override
    public String toString() {
        return "MatchPlayerEntity{" +
                "accountId=" + accountId +
                ", playerSlot=" + playerSlot +
                ", heroId=" + heroId +
                ", kills=" + kills +
                ", deaths=" + deaths +
                ", assists=" + assists +
                '}';
    }

    public long getAccountId() {
        return accountId;
    }

    public void setAccountId(long accountId) {
        this.accountId = accountId;
    }

    public int getPlayerSlot() {
        return playerSlot;
    }

    public void setPlayerSlot(int playerSlot) {
        this.playerSlot = playerSlot;
    }

    public int getHeroId() {
        return heroId;
    }

    public void setHeroId(int heroId) {
        this.heroId = heroId;
    }

    public int getItem0() {
        return item0;
    }

    public void setItem0(int item0) {
        this.item0 = item0;
    }

    public int getItem1() {
        return item1;
    }

    public void setItem1(int item1) {
        this.item1 = item1;
    }

    public int getItem2() {
        return item2;
    }

    public void setItem2(int item2) {
        this.item2 = item2;
    }

    public int getItem3() {
        return item3;
    }

    public void setItem3(int item3) {
        this.item3 = item3;
    }

    public int getItem4() {
        return item4;
    }

    public void setItem4(int item4) {
        this.item4 = item4;
    }

    public int getItem5() {
        return item5;
    }

    public void setItem5(int item5) {
        this.item5 = item5;
    }

    public int getBackpack0() {
        return backpack0;
    }

    public void setBackpack0(int backpack0) {
        this.backpack0 = backpack0;
    }

    public int getBackpack1() {
        return backpack1;
    }

    public void setBackpack1(int backpack1) {
        this.backpack1 = backpack1;
    }

    public int getBackpack2() {
        return backpack2;
    }

    public void setBackpack2(int backpack2) {
        this.backpack2 = backpack2;
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

    public int getLeaverStatus() {
        return leaverStatus;
    }

    public void setLeaverStatus(int leaverStatus) {
        this.leaverStatus = leaverStatus;
    }

    public int getLastHits() {
        return lastHits;
    }

    public void setLastHits(int lastHits) {
        this.lastHits = lastHits;
    }

    public int getDenies() {
        return denies;
    }

    public void setDenies(int denies) {
        this.denies = denies;
    }

    public int getGoldPerMin() {
        return goldPerMin;
    }

    public void setGoldPerMin(int goldPerMin) {
        this.goldPerMin = goldPerMin;
    }

    public int getXpPerMin() {
        return xpPerMin;
    }

    public void setXpPerMin(int xpPerMin) {
        this.xpPerMin = xpPerMin;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getHeroDamage() {
        return heroDamage;
    }

    public void setHeroDamage(int heroDamage) {
        this.heroDamage = heroDamage;
    }

    public int getTowerDamage() {
        return towerDamage;
    }

    public void setTowerDamage(int towerDamage) {
        this.towerDamage = towerDamage;
    }

    public int getHeroHealing() {
        return heroHealing;
    }

    public void setHeroHealing(int heroHealing) {
        this.heroHealing = heroHealing;
    }

    public int getGold() {
        return gold;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }

    public int getGoldSpent() {
        return goldSpent;
    }

    public void setGoldSpent(int goldSpent) {
        this.goldSpent = goldSpent;
    }

    public int getScaledHeroDamage() {
        return scaledHeroDamage;
    }

    public void setScaledHeroDamage(int scaledHeroDamage) {
        this.scaledHeroDamage = scaledHeroDamage;
    }

    public int getScaledTowerDamage() {
        return scaledTowerDamage;
    }

    public void setScaledTowerDamage(int scaledTowerDamage) {
        this.scaledTowerDamage = scaledTowerDamage;
    }

    public int getScaledHeroHealing() {
        return scaledHeroHealing;
    }

    public void setScaledHeroHealing(int scaledHeroHealing) {
        this.scaledHeroHealing = scaledHeroHealing;
    }

    public List<AbilityUpgradesEntity> getAbilityUpgrades() {
        return abilityUpgrades;
    }

    public void setAbilityUpgrades(List<AbilityUpgradesEntity> abilityUpgrades) {
        this.abilityUpgrades = abilityUpgrades;
    }

    public List<AdditionalUnitsEntity> getAdditionalUnits() {
        return additionalUnits;
    }

    public void setAdditionalUnits(List<AdditionalUnitsEntity> additionalUnits) {
        this.additionalUnits = additionalUnits;
    }
}
