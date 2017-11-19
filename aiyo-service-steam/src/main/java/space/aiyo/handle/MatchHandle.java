package space.aiyo.handle;

/**
 * CREATE BY Yo ON 2017/11/19 14:37
 */
public interface MatchHandle {

  void saveMatchFromSteamApiByMatchSeqNum(long matchSeqNum);

  /**
   * 获取数据库中最新的sequence number
   */
  long getRecentSequenceNumber();

  int count();
}
