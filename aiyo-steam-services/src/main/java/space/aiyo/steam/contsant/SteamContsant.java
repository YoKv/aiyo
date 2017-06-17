package space.aiyo.steam.contsant;

/**
 * Steam 相关静态变量
 * 后期通过admin配置 TODO
 * Created by yo on 2017/5/27.
 */
public class SteamContsant {

    /**
     * Steam 开发者key，每个steam请求都要带上
     */
    public static final String STEAM_KEY = "FE2198E547427C905904A154925EAEBF";

    /**
     * Steam 接口路径
     */
    public static final String STEAM_API_PATH = "http://api.steampowered.com";

    /**
     * GetMatchHistoryBySequenceNum 每次请求获取比赛数量 目前测试结果最大是100
     */
    public static final int STEAM_MATCH_PULLNUM_ONCE = 100;

}
