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

}
