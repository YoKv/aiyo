package space.aiyo.component;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Context;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import io.vertx.redis.RedisClient;
import io.vertx.redis.RedisOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import space.aiyo.var.RedisKey;
import space.aiyo.var.Route;

/**
 * CREATE BY Yo ON 2018/1/13 12:57
 */
public class RedisManager extends AbstractVerticle {

  private Logger logger = LoggerFactory.getLogger(this.getClass());
  private RedisClient client;

  @Override
  public JsonObject config() {
    JsonObject config = super.config();
    return config.getJsonObject(config.getString("active")).getJsonObject("redis");
  }

  @Override
  public void init(Vertx vertx, Context context) {
    super.init(vertx, context);
    client = RedisClient.create(vertx, new RedisOptions(config()));
  }


  @Override
  public void start() throws Exception {

    vertx.eventBus().consumer(Route.REDIS_GET.getAddress()).handler(message -> {
//      JsonObject json = (JsonObject) message.body();
//      RedisKey key = (RedisKey) json.getValue("key");
//      client.get(key.getKey(), result -> {
//        if (result.succeeded()) {
//          logger.info("set key: {}, {}", key, result.result());
//        }
//      });
    });

    vertx.eventBus().consumer(Route.REDIS_SET.getAddress()).handler(message -> {
//      String key = (String) message.body();
//      client.del(key, result -> {
//        if (result.succeeded()) {
//          logger.info("delete key: {}, {}", key, result.result());
//        }
//      });
    });

    vertx.eventBus().consumer(Route.REDIS_DELETE.getAddress()).handler(message -> {
      String key = (String) message.body();
      client.del(key, result -> {
        if (result.succeeded()) {
          logger.info("delete key: {}, {}", key, result.result());
        }
      });
    });

  }
}
