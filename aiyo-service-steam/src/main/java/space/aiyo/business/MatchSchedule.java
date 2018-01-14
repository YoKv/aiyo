package space.aiyo.business;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.eventbus.EventBus;
import io.vertx.core.json.JsonArray;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import space.aiyo.var.Route;
import space.aiyo.var.SteamApiEnum;

/**
 * CREATE BY Yo ON 2018/1/13 16:06
 */
public class MatchSchedule extends AbstractVerticle {

  private Logger logger = LoggerFactory.getLogger(this.getClass());

  @Override
  public void start() throws Exception {
    //TODO sequenceNum
    EventBus eventBus = vertx.eventBus();
    long timerID = vertx.setPeriodic(10000, id -> {
      //TODO 查询sequenceNum实现
      eventBus
          .send(Route.STEAM_CRAWLER_MATCH.getAddress(),
              SteamApiEnum.GET_MATCH_HISTORY_BY_SEQUENCE_NUM.getName(),
              reply -> {
                if (reply.succeeded()) {
                  JsonArray array = (JsonArray) reply.result().body();
                  logger.info("get macthes from steam,size: {}", array.size());
                  insertDB(array);
                }
              });
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
                logger.info("insert matches from steam,size: {}", reply.result());
              } else {
                logger.error("insert matches from steam failed", reply.cause());
              }
            });
  }
}
