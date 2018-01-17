package space.aiyo.business;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
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
public class ItemSchedule extends AbstractVerticle {

  @Override
  public void start() {
    vertx.setPeriodic(1000000, id -> {
          vertx.eventBus().send(Route.STEAM_CRAWLER_ITEM.getAddress(), "", update());

          Map<String, Long> map = new HashMap<>();
          map.put("STEAM_CRAWLER_ITEM_PERIODIC", id);
          RedisMessage redisMessage = new RedisMessage();
          redisMessage.setHashData(map);
          redisMessage.setRedisKey(RedisKey.SCHEDULE_TIMEID);
          vertx.eventBus().send(Route.REDIS_SET.getAddress(), redisMessage);
        }
    );
  }

  private Handler<AsyncResult<Message<JsonArray>>> update() {
    return message -> {
      if (message.succeeded()) {
        CrudMessage crudMessage = new CrudMessage();
        crudMessage.setArrayData(message.result().body());
        crudMessage.setDocumentName(Documents.DOTA_ITEM.getName());
        vertx.eventBus().send(Route.DB_UPDATE.getAddress(), crudMessage);
      }
    };
  }


}
