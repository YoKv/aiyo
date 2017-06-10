package space.aiyo.steam.entity.match;

/**
 * bp
 * Created by Yo on 2017/6/10.
 */
public class PicksBansEntity {
    /**
     * Whether this entry is a pick (true) or a ban (false).
     */
    private boolean is_pick;
    /**
     * The hero's unique ID. A list of hero IDs can be found via the GetHeroes method.
     */
    private int hero_id;
    /**
     *The team who chose the pick or ban; 0 for Radiant, 1 for Dire.
     */
    private int team;
    /**
     *The order of which the picks and bans were selected; 0-19.
     */
    private int order;

    public PicksBansEntity() {
    }

    @Override
    public String toString() {
        return "PicksBansEntity{" +
                "is_pick=" + is_pick +
                ", hero_id=" + hero_id +
                ", team=" + team +
                ", order=" + order +
                '}';
    }

    public boolean isIs_pick() {
        return is_pick;
    }

    public void setIs_pick(boolean is_pick) {
        this.is_pick = is_pick;
    }

    public int getHero_id() {
        return hero_id;
    }

    public void setHero_id(int hero_id) {
        this.hero_id = hero_id;
    }

    public int getTeam() {
        return team;
    }

    public void setTeam(int team) {
        this.team = team;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }
}
