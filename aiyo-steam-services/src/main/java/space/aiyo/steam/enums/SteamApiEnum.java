package space.aiyo.steam.enums;

/**
 * steam借口枚举
 * Created by Yo on 2017/6/1.
 */
public enum SteamApiEnum {

    GetMatchHistory("GetMatchHistory", "/IDOTA2Match_570/GetMatchHistory/v1", "获取比赛列表"),
    GetMatchHistoryBySequenceNum("GetMatchHistoryBySequenceNum", "/IDOTA2Match_570/GetMatchHistoryBySequenceNum/v1", "通过序列号获取比赛列表"),
    GetHeroes("GetHeroes", "/IEconDOTA2_570/GetHeroes/v1", "获取英雄列表"),
    GetGameItems("GetGameItems", "/IEconDOTA2_570/GetGameItems/v1", "获取装备列表");
    /**
     * 接口名
     */
    private final String name;
    /**
     * 访问地址
     */
    private final String url;
    /**
     * 描述
     */
    private final String memo;

    SteamApiEnum(String name, String url, String memo) {
        this.name = name;
        this.url = url;
        this.memo = memo;
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }

    public String getMemo() {
        return memo;
    }
}
