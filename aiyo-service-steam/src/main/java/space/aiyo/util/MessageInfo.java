package space.aiyo.util;

import space.aiyo.var.EventEnum;

/**
 * CREATE BY Yo ON 2018/1/14 15:30
 */
public class MessageInfo {

  private EventEnum eventEnum;
  private Object var;

  public MessageInfo(EventEnum eventEnum, Object var) {
    this.eventEnum = eventEnum;
    this.var = var;
  }

  public EventEnum getEventEnum() {
    return eventEnum;
  }

  public Object getVar() {
    return var;
  }
}
