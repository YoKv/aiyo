package space.aiyo.steam.services;


import space.aiyo.steam.entity.match.DotaMatchEntity;

import java.util.List;

/**
 * Dota比赛相关
 * Created by yo on 2017/5/27.
 */

public interface DotaMatchService {

    void saveMatch(DotaMatchEntity match);

    void saveMatchFromSteamApiBySequenceNumber(long sequenceNumber);
    /**
     * 获取数据库中最新的sequence number
     * @return
     */
    long getRecentSequenceNumber();
}
