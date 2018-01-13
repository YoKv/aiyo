package space.aiyo.contsant;

/**
 * steam借口枚举
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
   * Steam 开发者key，每个steam请求都要带上
   */
  public static final String STEAM_KEY = "AEC1E5CB1A7D9CC75CB4653B8C98875F";

  /**
   * Steam 接口路径
   */
  public static final String STEAM_API_DOMAIN = "api.steampowered.com";


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
