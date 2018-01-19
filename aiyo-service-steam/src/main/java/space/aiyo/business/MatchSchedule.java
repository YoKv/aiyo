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
import io.vertx.ext.mongo.FindOptions;
import space.aiyo.message.CrudMessage;
import space.aiyo.message.RedisMessage;
import space.aiyo.var.Documents;
import space.aiyo.var.RedisKey;
import space.aiyo.var.Route;

/**
 * CREATE BY Yo ON 2018/1/13 16:06
 */
public class MatchSchedule extends AbstractVerticle {

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
    // todo sequenceNum逻辑再定现在有问题
    long timeId = vertx.setPeriodic(4000,
        id -> {
          RedisMessage redisMessage = new RedisMessage();
          redisMessage.setRedisKey(RedisKey.MATCHSEQNUM);

          vertx.eventBus()
              .send(Route.REDIS_GET.getAddress(), redisMessage, redisMessageOptions, message -> {
                if (message.succeeded()) {
                  Long matchSeqNum = Long.valueOf((String) message.result().body());
                  if (null == matchSeqNum) {
                    CrudMessage crudMessage = new CrudMessage();
                    FindOptions findOptions = new FindOptions().setLimit(1)
                        .setSort(new JsonObject().put("match_seq_num", -1));
                    crudMessage.setFindOptions(findOptions);
                    crudMessage.setQuery(new JsonObject());
                    crudMessage.setDocumentName(Documents.DOTA_MATCH.getName());
                    vertx.eventBus().send(Route.DB_FINDWITHOPTIONS.getAddress(), crudMessage,
                        crudMessageOptions, msg -> {
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
                                .send(Route.STEAM_CRAWLER_MATCH.getAddress(), sequenceNum + 1,
                                    insert());
                          }
                        });
                  } else {
                    vertx.eventBus()
                        .send(Route.STEAM_CRAWLER_MATCH.getAddress(), matchSeqNum + 1, insert());
                  }
                }
              });
        }
    );
    JsonObject json = new JsonObject().put("STEAM_CRAWLER_MATCH_PERIODIC_ID", timeId);
    RedisMessage redisMessage = new RedisMessage();
    redisMessage.setData(json.toString());
    redisMessage.setRedisKey(RedisKey.SCHEDULE_TIMEID);
    vertx.eventBus().send(Route.REDIS_SET.getAddress(), redisMessage, redisMessageOptions);
  }

  //TODO insert前 把sequenceNum插入redis
  private Handler<AsyncResult<Message<JsonArray>>> insert() {
    return message -> {
      if (message.succeeded()) {
        JsonArray array = message.result().body();
        resetMatchSeqNum(array.getJsonObject(array.size() - 1));
        array.getList().forEach(obj -> {
          JsonObject item = (JsonObject) obj;
          CrudMessage crudMessage = new CrudMessage();
          crudMessage.setJsonData(item);
          crudMessage.setDocumentName(Documents.DOTA_MATCH.getName());
          vertx.eventBus().send(Route.DB_INSERT.getAddress(), crudMessage, crudMessageOptions);
        });
      }

    };
  }

  private void resetMatchSeqNum(JsonObject json) {
    RedisMessage redisMessage = new RedisMessage();
    redisMessage.setData(json.getLong("match_seq_num").toString());
    redisMessage.setRedisKey(RedisKey.MATCHSEQNUM);
    vertx.eventBus().send(Route.REDIS_SET.getAddress(), redisMessage, redisMessageOptions);
  }


}
