package space.aiyo.message;


import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import space.aiyo.util.AbstractCodec;
import space.aiyo.var.RedisKey;

public class RedisMessage extends AbstractCodec<RedisMessage, RedisMessage> {

  private RedisKey redisKey;
  private long expire;

  private String stringData;
  private List listData;
  private Set setData;
  private SortedSet sortedSetData;
  private Map hashData;

  @Override
  public RedisMessage transform(RedisMessage redisMessage) {
    RedisMessage message = new RedisMessage();
    message.setRedisKey(redisMessage.getRedisKey());
    message.setExpire(redisMessage.getExpire());
    message.setStringData(redisMessage.getStringData());
    message.setListData(redisMessage.getListData());
    message.setSetData(redisMessage.getSetData());
    message.setSortedSetData(redisMessage.getSortedSetData());
    message.setHashData(redisMessage.getHashData());

    return message;
  }

  @Override
  protected Class<RedisMessage> getInstanceClass() {
    return RedisMessage.class;
  }

  @Override
  public String toString() {
    return "{\"RedisMessage\":{"
        + "\"redisKey\":\"" + redisKey + "\""
        + ",\"expire\":\"" + expire + "\""
        + ",\"stringData\":\"" + stringData + "\""
        + ",\"listData\":" + listData
        + ",\"setData\":" + setData
        + ",\"sortedSetData\":" + sortedSetData
        + ",\"hashData\":" + hashData
        + "}}";
  }

  public RedisKey getRedisKey() {
    return redisKey;
  }

  public void setRedisKey(RedisKey redisKey) {
    this.redisKey = redisKey;
  }

  public long getExpire() {
    return expire;
  }

  public void setExpire(long expire) {
    this.expire = expire;
  }

  public String getStringData() {
    return stringData;
  }

  public void setStringData(String stringData) {
    this.stringData = stringData;
  }

  public List getListData() {
    return listData;
  }

  public void setListData(List listData) {
    this.listData = listData;
  }

  public Set getSetData() {
    return setData;
  }

  public void setSetData(Set setData) {
    this.setData = setData;
  }

  public SortedSet getSortedSetData() {
    return sortedSetData;
  }

  public void setSortedSetData(SortedSet sortedSetData) {
    this.sortedSetData = sortedSetData;
  }

  public Map getHashData() {
    return hashData;
  }

  public void setHashData(Map hashData) {
    this.hashData = hashData;
  }
}