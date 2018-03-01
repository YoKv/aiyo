package space.aiyo.enums;

public enum RedisKey {
  SCHEDULE_TIMEID("SCHEDULE_TIMEID", RedisType.HASH),
  MATCH_SEQ_NUM("MATCH_SEQ_NUM", RedisType.STRING),
  VERIFICATION_CODE("VERIFICATION_CODE", RedisType.STRING),;

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
