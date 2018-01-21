package space.aiyo.business;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.AsyncResult;
import io.vertx.core.Context;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.eventbus.DeliveryOptions;
import io.vertx.core.eventbus.Message;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import space.aiyo.message.CrudMessage;
import space.aiyo.message.RedisMessage;
import space.aiyo.var.Documents;
import enums.RedisKey;
import space.aiyo.var.Route;

/**
 * CREATE BY Yo ON 2018/1/13 16:06
 */
public class HeroSchedule extends AbstractVerticle {

  private DeliveryOptions redisMessageOptions;
  private DeliveryOptions crudMessageOptions;

  @Override
  public void init(Vertx vertx, Context context) {
    super.init(vertx, context);
    redisMessageOptions = new DeliveryOptions().setCodecName("RedisMessage");
    crudMessageOptions = new DeliveryOptions().setCodecName("CrudMessage");
  }

  @Override
  public void start() {
    long timeId = vertx.setPeriodic(7 * 86400 * 1000,
        id -> vertx.eventBus().send(Route.STEAM_CRAWLER_HERO.getAddress(), "", update()));
    JsonObject json = new JsonObject().put("STEAM_CRAWLER_HERO_PERIODIC_ID", timeId);
    RedisMessage redisMessage = new RedisMessage();
    redisMessage.setData(json.toString());
    redisMessage.setRedisKey(RedisKey.SCHEDULE_TIMEID);

    vertx.eventBus().send(Route.REDIS_SET.getAddress(), redisMessage, redisMessageOptions);
  }

  private Handler<AsyncResult<Message<JsonArray>>> update() {
    return message -> {
      if (message.succeeded()) {
        JsonArray array = message.result().body();
        array.getList().forEach(obj -> {
          JsonObject hero = (JsonObject) obj;
          CrudMessage crudMessage = new CrudMessage();
          crudMessage.setUpdate(new JsonObject().put("$set", hero));
          crudMessage.setQuery(new JsonObject().put("id", hero.getLong("id")));
          crudMessage.setDocumentName(Documents.DOTA_HERO.getName());
          vertx.eventBus().send(Route.DB_UPDATE.getAddress(), crudMessage, crudMessageOptions);
        });
      }
    };
  }

}
