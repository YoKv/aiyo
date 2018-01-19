package space.aiyo.var;

public enum RedisKey {
  SCHEDULE_TIMEID("SCHEDULE_TIMEID", RedisType.HASH),
  SEQUENCENUM("SEQUENCENUM", RedisType.STRING),;

  private String key;
  private RedisType dataType;

  RedisKey(String key, RedisType dataType) {
    this.key = key;
    this.dataType = dataType;
  }

  public String getKey() {
    return key;
  }

  public RedisType getDataType() {
    return dataType;
  }
}
