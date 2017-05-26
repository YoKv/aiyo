package space.aiyo.steam.entity;

import org.springframework.data.annotation.Id;

import java.util.List;

/**
 * Created by Yo on 2017/5/26.
 */
public class Dota2MatchEntity {

    @Id
    public  int id;
    /**
     * The matches unique ID.
     */
    public int match_id;
    /**
     * A 'sequence number', representing the order in which matches were recorded.
     */
    public int match_seq_num;
    /**
     *Unix timestamp of when the match began.
     */
    public int start_time;
    /**
     *
     -1 - Invalid
     0 - Public matchmaking
     1 - Practise
     2 - Tournament
     3 - Tutorial
     4 - Co-op with bots.
     5 - Team match
     6 - Solo Queue
     7 - Ranked Matchmaking
     8 - 1v1 Solo Mid
     */
    public int lobby_type;
    public int radiant_team_id;
    public int dire_team_id;
    public List<Dota2MatchPlayerEntity> players;


    @Override
    public String toString() {
        return String.format(
                "Dota2HeroEntity[id=%s, match_id=%s, match_seq_num='%s', start_time='%s', lobby_type='%s', radiant_team_id='%s', dire_team_id='%s', players='%s']",
                id, match_id, match_seq_num, start_time, lobby_type, radiant_team_id, dire_team_id, players);
    }

    class Dota2MatchPlayerEntity{
        public int account_id;
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
        public int player_slot;
        public int hero_id;

        public Dota2MatchPlayerEntity(int account_id, int player_slot, int hero_id) {
            this.account_id = account_id;
            this.player_slot = player_slot;
            this.hero_id = hero_id;
        }
    }
}
