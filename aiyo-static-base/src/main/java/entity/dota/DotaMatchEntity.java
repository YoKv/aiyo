package entity.dota;


import entity.dota.match.MatchPlayerEntity;
import entity.dota.match.PicksBansEntity;

import java.util.List;

/**
 * 比赛详情实体
 * Created by Yo on 2017/5/26.
 */


public class DotaMatchEntity {

    private long matchId;
    /**
     * A 'sequence number', representing the order in which matches were recorded.
     */
    private long matchSeqNum;
    /**
     * Unix timestamp of when the match began.
     */
    private int startTime;
    /**
     * -1 - Invalid
     * 0 - Public matchmaking
     * 1 - Practise
     * 2 - Tournament
     * 3 - Tutorial
     * 4 - Co-op with bots.
     * 5 - Team match
     * 6 - Solo Queue
     * 7 - Ranked Matchmaking
     * 8 - 1v1 Solo Mid
     */
    private int lobbyType;
    /**
     * The season the game was played in.
     */
    private int season;
    private int radiantTeamId;
    private int direTeamId;
    /**
     * Dictates the winner of the match, true for radiant; false for dire.
     */
    private boolean radiantWin;
    /**
     * The length of the match, in seconds since the match began.
     */
    private int duration;
    private int preGameDuration;
    /**
     * A particular teams tower status is given as a 16-bit unsigned integer.
     * The rightmost 11 bits represent individual towers belonging to that team;
     * see below for a visual representation.
     * ┌─┬─┬─┬─┬─────────────────────── Not used.
     * │ │ │ │ │ ┌───────────────────── Ancient Bottom
     * │ │ │ │ │ │ ┌─────────────────── Ancient Top
     * │ │ │ │ │ │ │ ┌───────────────── Bottom Tier 3
     * │ │ │ │ │ │ │ │ ┌─────────────── Bottom Tier 2
     * │ │ │ │ │ │ │ │ │ ┌───────────── Bottom Tier 1
     * │ │ │ │ │ │ │ │ │ │ ┌─────────── Middle Tier 3
     * │ │ │ │ │ │ │ │ │ │ │ ┌───────── Middle Tier 2
     * │ │ │ │ │ │ │ │ │ │ │ │ ┌─────── Middle Tier 1
     * │ │ │ │ │ │ │ │ │ │ │ │ │ ┌───── Top Tier 3
     * │ │ │ │ │ │ │ │ │ │ │ │ │ │ ┌─── Top Tier 2
     * │ │ │ │ │ │ │ │ │ │ │ │ │ │ │ ┌─ Top Tier 1
     * │ │ │ │ │ │ │ │ │ │ │ │ │ │ │ │
     * 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0
     */
    private int towerStatusRadiant;
    private int towerStatusDire;
    /**
     * A particular teams tower status is given as an 8-bit unsigned integer.
     * The rightmost 6 bits represent the barracks belonging to that team;
     * see below for a visual representation.
     * ┌─┬───────────── Not used.
     * │ │ ┌─────────── Bottom Ranged
     * │ │ │ ┌───────── Bottom Melee
     * │ │ │ │ ┌─────── Middle Ranged
     * │ │ │ │ │ ┌───── Middle Melee
     * │ │ │ │ │ │ ┌─── Top Ranged
     * │ │ │ │ │ │ │ ┌─ Top Melee
     * │ │ │ │ │ │ │ │
     * 0 0 0 0 0 0 0 0
     */
    private int barracksStatusRadiant;
    private int barracksStatusDire;
    /**
     * The server cluster the match was played upon. Used for downloading replays of matches.
     */
    private int cluster;
    private int firstBloodTime;
    private int humanPlayers;
    /**
     * The league that this match was a part of.
     * A list of league IDs can be found via the GetLeagueListing method.
     */
    private int leagueId;
    private int positiveVotes;
    private int negativeVotes;
    /**
     * 0 - None
     * 1 - All Pick
     * 2 - Captain'base Mode
     * 3 - Random Draft
     * 4 - Single Draft
     * 5 - All Random
     * 6 - Intro
     * 7 - Diretide
     * 8 - Reverse Captain'base Mode
     * 9 - The Greeviling
     * 10 - Tutorial
     * 11 - Mid Only
     * 12 - Least Played
     * 13 - New Player Pool
     * 14 - Compendium Matchmaking
     * 15 - Co-op vs Bots
     * 16 - Captains Draft
     * 18 - Ability Draft
     * 20 - All Random Deathmatch
     * 21 - 1v1 Mid Only
     * 22 - Ranked Matchmaking
     */
    private int gameMode;
    /**
     * A list of the picks and bans in the match, if the game mode is Captains Mode.
     */
    private List<PicksBansEntity> picksBans;
    private int flags;
    private int engine;
    private int radiantScore;
    private int direScore;
    private List<MatchPlayerEntity> players;

    /**
     * 无参构造方法
     * 兼容fastjson
     */
    public DotaMatchEntity() {
    }

    @Override
    public String toString() {
        return String.format("DotaMatchEntity[matchId=%base, matchSeqNum='%base', startTime='%base', leagueId='%base', gameMode='%base', radiantWin='%base']", matchId, matchSeqNum, startTime, leagueId, gameMode, radiantWin);
    }

    public long getMatchId() {
        return matchId;
    }

    public void setMatchId(long matchId) {
        this.matchId = matchId;
    }

    public long getMatchSeqNum() {
        return matchSeqNum;
    }

    public void setMatchSeqNum(long matchSeqNum) {
        this.matchSeqNum = matchSeqNum;
    }

    public int getStartTime() {
        return startTime;
    }

    public void setStartTime(int startTime) {
        this.startTime = startTime;
    }

    public int getLobbyType() {
        return lobbyType;
    }

    public void setLobbyType(int lobbyType) {
        this.lobbyType = lobbyType;
    }

    public int getSeason() {
        return season;
    }

    public void setSeason(int season) {
        this.season = season;
    }

    public int getRadiantTeamId() {
        return radiantTeamId;
    }

    public void setRadiantTeamId(int radiantTeamId) {
        this.radiantTeamId = radiantTeamId;
    }

    public int getDireTeamId() {
        return direTeamId;
    }

    public void setDireTeamId(int direTeamId) {
        this.direTeamId = direTeamId;
    }

    public boolean isRadiantWin() {
        return radiantWin;
    }

    public void setRadiantWin(boolean radiantWin) {
        this.radiantWin = radiantWin;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getPreGameDuration() {
        return preGameDuration;
    }

    public void setPreGameDuration(int preGameDuration) {
        this.preGameDuration = preGameDuration;
    }

    public int getTowerStatusRadiant() {
        return towerStatusRadiant;
    }

    public void setTowerStatusRadiant(int towerStatusRadiant) {
        this.towerStatusRadiant = towerStatusRadiant;
    }

    public int getTowerStatusDire() {
        return towerStatusDire;
    }

    public void setTowerStatusDire(int towerStatusDire) {
        this.towerStatusDire = towerStatusDire;
    }

    public int getBarracksStatusRadiant() {
        return barracksStatusRadiant;
    }

    public void setBarracksStatusRadiant(int barracksStatusRadiant) {
        this.barracksStatusRadiant = barracksStatusRadiant;
    }

    public int getBarracksStatusDire() {
        return barracksStatusDire;
    }

    public void setBarracksStatusDire(int barracksStatusDire) {
        this.barracksStatusDire = barracksStatusDire;
    }

    public int getCluster() {
        return cluster;
    }

    public void setCluster(int cluster) {
        this.cluster = cluster;
    }

    public int getFirstBloodTime() {
        return firstBloodTime;
    }

    public void setFirstBloodTime(int firstBloodTime) {
        this.firstBloodTime = firstBloodTime;
    }

    public int getHumanPlayers() {
        return humanPlayers;
    }

    public void setHumanPlayers(int humanPlayers) {
        this.humanPlayers = humanPlayers;
    }

    public int getLeagueId() {
        return leagueId;
    }

    public void setLeagueId(int leagueId) {
        this.leagueId = leagueId;
    }

    public int getPositiveVotes() {
        return positiveVotes;
    }

    public void setPositiveVotes(int positiveVotes) {
        this.positiveVotes = positiveVotes;
    }

    public int getNegativeVotes() {
        return negativeVotes;
    }

    public void setNegativeVotes(int negativeVotes) {
        this.negativeVotes = negativeVotes;
    }

    public int getGameMode() {
        return gameMode;
    }

    public void setGameMode(int gameMode) {
        this.gameMode = gameMode;
    }

    public List<PicksBansEntity> getPicksBans() {
        return picksBans;
    }

    public void setPicksBans(List<PicksBansEntity> picksBans) {
        this.picksBans = picksBans;
    }

    public int getFlags() {
        return flags;
    }

    public void setFlags(int flags) {
        this.flags = flags;
    }

    public int getEngine() {
        return engine;
    }

    public void setEngine(int engine) {
        this.engine = engine;
    }

    public int getRadiantScore() {
        return radiantScore;
    }

    public void setRadiantScore(int radiantScore) {
        this.radiantScore = radiantScore;
    }

    public int getDireScore() {
        return direScore;
    }

    public void setDireScore(int direScore) {
        this.direScore = direScore;
    }

    public List<MatchPlayerEntity> getPlayers() {
        return players;
    }

    public void setPlayers(List<MatchPlayerEntity> players) {
        this.players = players;
    }
}
