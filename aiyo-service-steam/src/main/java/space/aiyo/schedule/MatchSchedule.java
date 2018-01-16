package space.aiyo.schedule;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.mongo.FindOptions;
import java.util.function.Consumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import space.aiyo.var.RedisKey;
import space.aiyo.var.Route;

/**
 * CREATE BY Yo ON 2018/1/13 16:06
 */
public class MatchSchedule extends AbstractVerticle {

  private Logger logger = LoggerFactory.getLogger(this.getClass());

  @Override
  public void start() {
    long timerID = vertx.setPeriodic(10000,
        id -> getMaxSequenceNumFromDB(
            (sequenceNum) -> getMatchFromSteam(sequenceNum, this::insertDB)));
    vertx.eventBus().send(Route.REDIS_SET.getAddress(),
        new JsonObject().put("key", RedisKey.SCHEDULE_TIMEID).put("value", timerID)
            .put("expire", -1));
  }

  //TODO redis 存一份最大的sequenceNum,基准，包括未存入的
  private void getMaxSequenceNumFromRedis(Consumer<Long> consumer) {
    vertx.eventBus().send(Route.REDIS_GET.getAddress(),
        new JsonObject().put("key", RedisKey.SEQUENCENUM), reply -> {
          if (reply.succeeded()) {
            Long sequenceNum = (Long) reply.result().body();
            if (null == sequenceNum) {
              getMaxSequenceNumFromDB(consumer);
            } else {
              consumer.accept(sequenceNum);
            }
          } else {
            getMaxSequenceNumFromDB(consumer);
          }
        });
  }

  /**
   * 获取当前数据库中最大的sequenceNum
   */
  private void getMaxSequenceNumFromDB(Consumer<Long> consumer) {
    vertx.eventBus()
        .send(Route.DB_MATCH_FINDWITHOPTIONS.getAddress(),
            "",
            reply -> {
              if (reply.succeeded()) {
                JsonArray array = (JsonArray) reply.result().body();
                Long sequenceNum;
                if (array.size() > 0) {
                  sequenceNum = array.getJsonObject(0).getLong("match_seq_num");
                } else {
                  //2018年1月16日的一场比赛
                  sequenceNum = 3199601005L;
                  logger.info("sequenceNum  {}", sequenceNum);
                }
                consumer.accept(sequenceNum);
              } else {
                logger.error("getMaxSequenceNumFromDB failed", reply.cause());
              }
            });
  }

  /**
   * 根据sequenceNum获取matches
   */
  private void getMatchFromSteam(Long sequenceNum, Consumer<JsonArray> consumer) {
    vertx.eventBus()
        .send(Route.STEAM_CRAWLER_MATCH.getAddress(),
            sequenceNum,
            reply -> {
              if (reply.succeeded()) {
                JsonArray array = (JsonArray) reply.result().body();
                consumer.accept(array);
              }
            });
  }


  /*
  插入数据库
   */
  private void insertDB(JsonArray array) {
    vertx.eventBus()
        .send(Route.DB_MATCH_INSERT.getAddress(), array,
            reply -> {
              if (reply.succeeded()) {
                logger.info("insert matches,size: {}", reply.result());
              } else {
                logger.error("insert matches failed", reply.cause());
              }
            });
  }
}
