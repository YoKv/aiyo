package space.aiyo.message;

import io.netty.util.CharsetUtil;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.eventbus.MessageCodec;
import io.vertx.core.json.Json;

/**
 * CREATE BY Yo ON 2018/1/18 19:51
 */

public abstract class AbstractCodec<S, R> implements MessageCodec<S, R> {

  @Override
  public void encodeToWire(Buffer buffer, S s) {
    String jo = Json.encode(s);
    byte[] encoded = jo.getBytes(CharsetUtil.UTF_8);
    buffer.appendInt(encoded.length);
    Buffer buff = Buffer.buffer(encoded);
    buffer.appendBuffer(buff);
  }

  @Override
  public R decodeFromWire(int pos, Buffer buffer) {
    int length = buffer.getInt(pos);
    pos += 4;
    byte[] encoded = buffer.getBytes(pos, pos + length);
    String str = new String(encoded, CharsetUtil.UTF_8);
    return Json.decodeValue(str, getInstanceClass());
  }

  @Override
  public byte systemCodecID() {
    return -1;
  }

  @Override
  public String name() {
    return getInstanceClass().getSimpleName();
  }

  protected abstract Class<R> getInstanceClass();

}