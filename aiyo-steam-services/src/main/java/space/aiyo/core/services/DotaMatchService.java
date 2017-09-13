package space.aiyo.core.services;


/**
 * Dota比赛相关
 * Created by yo on 2017/5/27.
 */

public interface DotaMatchService {

    void saveMatchFromSteamApiByMatchSeqNum(long matchSeqNum);
    /**
     * 获取数据库中最新的sequence number
     */
    long getRecentSequenceNumber();
    int count();
}
