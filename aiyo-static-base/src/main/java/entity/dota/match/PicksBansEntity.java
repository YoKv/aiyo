package entity.dota.match;

/**
 * bp
 * Created by Yo on 2017/6/10.
 */
public class PicksBansEntity {

  /**
   * Whether this entry is a pick (true) or a ban (false).
   */
  private boolean isPick;
  /**
   * The hero'base unique ID. A list of hero IDs can be found via the GetHeroes method.
   */
  private int heroId;
  /**
   * The team who chose the pick or ban; 0 for Radiant, 1 for Dire.
   */
  private int team;
  /**
   * The order of which the picks and bans were selected; 0-19.
   */
  private int order;

  /**
   * 无参构造方法
   * 兼容fastjson
   */
  public PicksBansEntity() {
  }

  @Override
  public String toString() {
    return "PicksBansEntity{" + "isPick=" + isPick + ", heroId=" + heroId + ", team=" + team
        + ", order=" + order + '}';
  }

  public boolean isPick() {
    return isPick;
  }

  public void setPick(boolean pick) {
    this.isPick = pick;
  }

  public int getHeroId() {
    return heroId;
  }

  public void setHeroId(int heroId) {
    this.heroId = heroId;
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
