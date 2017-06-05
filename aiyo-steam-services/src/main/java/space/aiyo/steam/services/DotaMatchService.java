package space.aiyo.steam.services;

import com.alibaba.fastjson.JSONObject;

/**
 * Dota比赛相关
 * Created by yo on 2017/5/27.
 */
public interface DotaMatchService {
    JSONObject getMatchFromSteam();

}
