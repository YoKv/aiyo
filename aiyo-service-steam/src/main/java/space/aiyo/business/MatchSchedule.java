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
public class MatchSchedule extends AbstractVerticle {

  @Override
  public void start() {

    vertx.setPeriodic(1000000,
        id -> {
          RedisMessage redisMessage = new RedisMessage();
          redisMessage.setRedisKey(RedisKey.SEQUENCENUM);
          vertx.eventBus().send(Route.REDIS_GET.getAddress(), redisMessage, message -> {
            if (message.succeeded()) {
              Long matchSeqNum = (Long) message.result().body();
              if (null == matchSeqNum) {
                CrudMessage crudMessage = new CrudMessage();
                vertx.eventBus().send(Route.DB_FINDWITHOPTIONS.getAddress(), crudMessage, msg -> {
                  if (msg.succeeded()) {
                    JsonArray array = (JsonArray) msg.result().body();
                    Long sequenceNum;
                    if (array.size() > 0) {
                      sequenceNum = array.getJsonObject(0).getLong("match_seq_num");
                    } else {
                      //2018年1月16日的一场比赛
                      sequenceNum = 3199601005L;
                    }
                    vertx.eventBus()
                        .send(Route.STEAM_CRAWLER_MATCH.getAddress(), sequenceNum, insert());
                  }
                });
              } else {
                vertx.eventBus()
                    .send(Route.STEAM_CRAWLER_MATCH.getAddress(), matchSeqNum, insert());
              }
            }
          });

          Map<String, Long> map = new HashMap<>();
          map.put("STEAM_CRAWLER_MATCH_PERIODIC", id);
          redisMessage = new RedisMessage();
          redisMessage.setHashData(map);
          redisMessage.setRedisKey(RedisKey.SCHEDULE_TIMEID);
          vertx.eventBus().send(Route.REDIS_SET.getAddress(), redisMessage);
        }
    );
  }

  private Handler<AsyncResult<Message<JsonArray>>> insert() {
    return message -> {
      CrudMessage crudMessage = new CrudMessage();
      crudMessage.setDocumentName(Documents.DOTA_MATCH.getName());
      vertx.eventBus().send(Route.DB_INSERT.getAddress(), crudMessage);

    };
  }


}
