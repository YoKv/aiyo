package space.aiyo.component;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Handler;
import io.vertx.core.eventbus.EventBus;
import io.vertx.core.eventbus.Message;
import io.vertx.core.json.JsonObject;
import io.vertx.redis.RedisClient;
import io.vertx.redis.RedisOptions;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import space.aiyo.message.RedisMessage;
import enums.RedisType;
import space.aiyo.var.Route;

/**
 * CREATE BY Yo ON 2018/1/13 12:57
 */
public class RedisWrapper extends AbstractVerticle {

  private Logger logger = LoggerFactory.getLogger(this.getClass());
  private RedisClient client;

  @Override
  public JsonObject config() {
    JsonObject config = super.config();
    return config.getJsonObject(config.getString("active")).getJsonObject("redis");
  }

  @Override
  public void start() throws Exception {
    client = RedisClient.create(vertx, new RedisOptions(config()));

    //发布redis操作
    EventBus eventBus = vertx.eventBus();
    eventBus.consumer(Route.REDIS_SET.getAddress(), set());
    eventBus.consumer(Route.REDIS_GET.getAddress(), get());
    eventBus.consumer(Route.REDIS_DELETE.getAddress(), delete());
    eventBus.consumer(Route.REDIS_EXPIRE.getAddress(), expire());
    eventBus.consumer(Route.REDIS_EXISTS.getAddress(), exists());
  }

  private Handler<Message<RedisMessage>> set() {
    return message -> {
      RedisType type = message.body().getRedisKey().getDataType();
      String key = message.body().getRedisKey().getKey();
      if (Objects.equals(type, RedisType.STRING)) {
        client.set(key, message.body().getData(), result -> message.reply(result.result()));
      } else if (Objects.equals(type, RedisType.LIST)) {
        client.rpush(key, message.body().getData(), result -> message.reply(result.result()));
      } else if (Objects.equals(type, RedisType.SET)) {
        client.sadd(key, message.body().getData(), result -> message.reply(result.result()));
      } else if (Objects.equals(type, RedisType.SORTSET)) {
        client.zadd(key, Double.valueOf(message.body().getData()), message.body().getData(),
            result -> message.reply(result.result()));
      } else if (Objects.equals(type, RedisType.HASH)) {
        client.hmset(key, new JsonObject(message.body().getData()),
            result -> message.reply(result.result()));
      } else {
        logger.error("error redis set, message: {}", message);
      }
    };
  }

  private Handler<Message<RedisMessage>> get() {
    return message -> {
      RedisType type = message.body().getRedisKey().getDataType();
      String key = message.body().getRedisKey().getKey();
      if (Objects.equals(type, RedisType.STRING)) {
        client.get(key, result -> message.reply(result.result()));
      } else if (Objects.equals(type, RedisType.LIST)) {
        client.getrange(key, message.body().getStart(), message.body().getEnd(),
            result -> message.reply(result.result()));
      } else if (Objects.equals(type, RedisType.SET)) {
        client.smembers(key, result -> message.reply(result.result()));
      } else if (Objects.equals(type, RedisType.SORTSET)) {
        client.zrange(key, message.body().getStart(), message.body().getEnd(),
            result -> message.reply(result.result()));
      } else if (Objects.equals(type, RedisType.HASH)) {
        client.hget(key, message.body().getData(), result -> message.reply(result.result()));
      } else {
        logger.error("error redis get, message: {}", message);
      }
    };
  }

  //可以按数据结构区分删除,有需求再实现
  private Handler<Message<RedisMessage>> delete() {
    return message -> client
        .del(message.body().getRedisKey().getKey(), result -> message.reply(result.result()));
  }

  private Handler<Message<RedisMessage>> delMany() {
    return message -> client
        .delMany(new ArrayList<String>(Arrays.asList(message.body().getData().split(","))),
            result -> message.reply(result.result()));
  }

  private Handler<Message<Void>> flushdb() {
    return message -> client.flushdb(result -> message.reply(result.result()));
  }

  //每个set handle 调用，先不实现
  private Handler<Message<RedisMessage>> expire() {
    return message -> client
        .expire(message.body().getRedisKey().getKey(), message.body().getExpire(),
            result -> message.reply(result.result()));
  }

  private Handler<Message<RedisMessage>> exists() {
    return message -> client
        .exists(message.body().getRedisKey().getKey(), result -> message.reply(result.result()));
  }

}
