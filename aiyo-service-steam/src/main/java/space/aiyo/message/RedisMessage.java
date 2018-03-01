package space.aiyo.message;

import space.aiyo.var.RedisKey;

public class RedisMessage extends AbstractCodec<RedisMessage, RedisMessage> {

  private RedisKey redisKey;
  private long expire;

  private String data;
  private long start;
  private long end;

  @Override
  public RedisMessage transform(RedisMessage redisMessage) {
    RedisMessage message = new RedisMessage();
    message.setRedisKey(redisMessage.getRedisKey());
    message.setExpire(redisMessage.getExpire());
    message.setData(redisMessage.getData());
    message.setStart(redisMessage.getStart());
    message.setEnd(redisMessage.getEnd());
    return message;
  }

  @Override
  public String toString() {
    return "{\"RedisMessage\":"
        + super.toString()
        + ",\"redisKey\":\"" + redisKey + "\""
        + ",\"expire\":\"" + expire + "\""
        + ",\"data\":\"" + data + "\""
        + ",\"start\":\"" + start + "\""
        + ",\"end\":\"" + end + "\""
        + "}";
  }

  @Override
  protected Class<RedisMessage> getInstanceClass() {
    return RedisMessage.class;
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

  public String getData() {
    return data;
  }

  public void setData(String data) {
    this.data = data;
  }

  public long getStart() {
    return start;
  }

  public void setStart(long start) {
    this.start = start;
  }

  public long getEnd() {
    return end;
  }

  public void setEnd(long end) {
    this.end = end;
  }
}