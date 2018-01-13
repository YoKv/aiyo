package space.aiyo.var;

/**
 * steam接口枚举
 * Created by Yo on 2017/6/1.
 */
public enum SteamApiEnum {

  /**
   * 获取比赛列表
   */
  GET_MATCH_HISTORY("GET_MATCH_HISTORY", "/IDOTA2Match_570/GetMatchHistory/v1"),
  /**
   * 通过序列号获取比赛列表
   */
  GET_MATCH_HISTORY_BY_SEQUENCE_NUM("GetMatchHistoryBySequenceNum",
      "/IDOTA2Match_570/GetMatchHistoryBySequenceNum/v1"),
  /**
   * 获取英雄列表
   */
  GET_HEROES("GetHeroes", "/IEconDOTA2_570/GetHeroes/v1"),
  /**
   * 获取装备列表
   */
  GET_GAME_ITEMS("GetGameItems", "/IEconDOTA2_570/GetGameItems/v1");

  /**
   * 接口名
   */
  private final String name;
  /**
   * 访问地址
   */
  private final String url;

  SteamApiEnum(String name, String url) {
    this.name = name;
    this.url = url;
  }

  public String getName() {
    return name;
  }

  public String getUrl() {
    return url;
  }

}
