package space.aiyo.steam.entity;

import com.alibaba.fastjson.JSONArray;
import org.springframework.data.annotation.Id;

/**
 * Created by Yo on 2017/5/26.
 */
public class Dota2MatchEntity {

    @Id
    private int id;
    /**
     * The matches unique ID.
     */
    private int match_id;
    /**
     * A 'sequence number', representing the order in which matches were recorded.
     */
    private int match_seq_num;
    /**
     * Unix timestamp of when the match began.
     */
    private int start_time;
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
    private int lobby_type;
    /**
     * The season the game was played in.
     */
    private int season;
    private int radiant_team_id;
    private int dire_team_id;
    /**
     * Dictates the winner of the match, true for radiant; false for dire.
     */
    private boolean radiant_win;
    /**
     * The length of the match, in seconds since the match began.
     */
    private int duration;
    private int pre_game_duration;
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
    private int tower_status_radiant;
    private int tower_status_dire;
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
    private int barracks_status_radiant;
    private int barracks_status_dire;
    /**
     * The server cluster the match was played upon. Used for downloading replays of matches.
     */
    private int cluster;
    private int first_blood_time;
    private int human_players;
    /**
     * The league that this match was a part of.
     * A list of league IDs can be found via the GetLeagueListing method.
     */
    private int leagueid;
    private int positive_votes;
    private int negative_votes;
    /**
     * 0 - None
     * 1 - All Pick
     * 2 - Captain's Mode
     * 3 - Random Draft
     * 4 - Single Draft
     * 5 - All Random
     * 6 - Intro
     * 7 - Diretide
     * 8 - Reverse Captain's Mode
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
    private int game_mode;
    private JSONArray picks_bans;
    private int flags;
    private int engine;
    private int radiant_score;
    private int dire_score;
    //  TODO 格式待定
    private JSONArray players;

    @Override
    public String toString() {
        return String.format(
                "Dota2HeroEntity[id=%s, match_id=%s, match_seq_num='%s', start_time='%s', leagueid='%s', game_mode='%s', radiant_win='%s']",
                id, match_id, match_seq_num, start_time, leagueid, game_mode, radiant_win);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMatch_id() {
        return match_id;
    }

    public void setMatch_id(int match_id) {
        this.match_id = match_id;
    }

    public int getMatch_seq_num() {
        return match_seq_num;
    }

    public void setMatch_seq_num(int match_seq_num) {
        this.match_seq_num = match_seq_num;
    }

    public int getStart_time() {
        return start_time;
    }

    public void setStart_time(int start_time) {
        this.start_time = start_time;
    }

    public int getLobby_type() {
        return lobby_type;
    }

    public void setLobby_type(int lobby_type) {
        this.lobby_type = lobby_type;
    }

    public int getSeason() {
        return season;
    }

    public void setSeason(int season) {
        this.season = season;
    }

    public int getRadiant_team_id() {
        return radiant_team_id;
    }

    public void setRadiant_team_id(int radiant_team_id) {
        this.radiant_team_id = radiant_team_id;
    }

    public int getDire_team_id() {
        return dire_team_id;
    }

    public void setDire_team_id(int dire_team_id) {
        this.dire_team_id = dire_team_id;
    }

    public boolean isRadiant_win() {
        return radiant_win;
    }

    public void setRadiant_win(boolean radiant_win) {
        this.radiant_win = radiant_win;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getPre_game_duration() {
        return pre_game_duration;
    }

    public void setPre_game_duration(int pre_game_duration) {
        this.pre_game_duration = pre_game_duration;
    }

    public int getTower_status_radiant() {
        return tower_status_radiant;
    }

    public void setTower_status_radiant(int tower_status_radiant) {
        this.tower_status_radiant = tower_status_radiant;
    }

    public int getTower_status_dire() {
        return tower_status_dire;
    }

    public void setTower_status_dire(int tower_status_dire) {
        this.tower_status_dire = tower_status_dire;
    }

    public int getBarracks_status_radiant() {
        return barracks_status_radiant;
    }

    public void setBarracks_status_radiant(int barracks_status_radiant) {
        this.barracks_status_radiant = barracks_status_radiant;
    }

    public int getBarracks_status_dire() {
        return barracks_status_dire;
    }

    public void setBarracks_status_dire(int barracks_status_dire) {
        this.barracks_status_dire = barracks_status_dire;
    }

    public int getCluster() {
        return cluster;
    }

    public void setCluster(int cluster) {
        this.cluster = cluster;
    }

    public int getFirst_blood_time() {
        return first_blood_time;
    }

    public void setFirst_blood_time(int first_blood_time) {
        this.first_blood_time = first_blood_time;
    }

    public int getHuman_players() {
        return human_players;
    }

    public void setHuman_players(int human_players) {
        this.human_players = human_players;
    }

    public int getLeagueid() {
        return leagueid;
    }

    public void setLeagueid(int leagueid) {
        this.leagueid = leagueid;
    }

    public int getPositive_votes() {
        return positive_votes;
    }

    public void setPositive_votes(int positive_votes) {
        this.positive_votes = positive_votes;
    }

    public int getNegative_votes() {
        return negative_votes;
    }

    public void setNegative_votes(int negative_votes) {
        this.negative_votes = negative_votes;
    }

    public int getGame_mode() {
        return game_mode;
    }

    public void setGame_mode(int game_mode) {
        this.game_mode = game_mode;
    }

    public JSONArray getPicks_bans() {
        return picks_bans;
    }

    public void setPicks_bans(JSONArray picks_bans) {
        this.picks_bans = picks_bans;
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

    public int getRadiant_score() {
        return radiant_score;
    }

    public void setRadiant_score(int radiant_score) {
        this.radiant_score = radiant_score;
    }

    public int getDire_score() {
        return dire_score;
    }

    public void setDire_score(int dire_score) {
        this.dire_score = dire_score;
    }

    public JSONArray getPlayers() {
        return players;
    }

    public void setPlayers(JSONArray players) {
        this.players = players;
    }
}
