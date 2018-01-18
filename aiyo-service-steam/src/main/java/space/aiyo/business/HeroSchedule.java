package space.aiyo.business;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.eventbus.DeliveryOptions;
import io.vertx.core.eventbus.Message;
import io.vertx.core.json.JsonArray;
import java.util.HashMap;
import java.util.Map;
import space.aiyo.message.CrudMessage;
import space.aiyo.message.RedisMessage;
import space.aiyo.var.Documents;
import space.aiyo.var.RedisKey;
import space.aiyo.var.Route;

/**
 * CREATE BY Yo ON 2018/1/13 16:06
 */
public class HeroSchedule extends AbstractVerticle {

  @Override
  public void start() {
    vertx.setPeriodic(1000, id -> {
      vertx.eventBus().send(Route.STEAM_CRAWLER_HERO.getAddress(), "", update());

      Map<String, Long> map = new HashMap<>();
      map.put("STEAM_CRAWLER_HERO_PERIODIC", id);
      RedisMessage redisMessage = new RedisMessage();
      redisMessage.setHashData(map);
      redisMessage.setRedisKey(RedisKey.SCHEDULE_TIMEID);

      DeliveryOptions options = new DeliveryOptions().setCodecName("RedisMessage");

      vertx.eventBus().send(Route.REDIS_SET.getAddress(), redisMessage, options);
    });


  }

  private Handler<AsyncResult<Message<JsonArray>>> update() {
    return message -> {
      if (message.succeeded()) {
        CrudMessage crudMessage = new CrudMessage();
        crudMessage.setArrayData(message.result().body());
        crudMessage.setDocumentName(Documents.DOTA_HERO.getName());

        DeliveryOptions options = new DeliveryOptions().setCodecName("CrudMessage");
        vertx.eventBus().send(Route.DB_UPDATE.getAddress(), crudMessage, options);
      }
    };
  }

}
