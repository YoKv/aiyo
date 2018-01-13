package space.aiyo.schedule;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.eventbus.EventBus;
import io.vertx.core.json.JsonArray;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import space.aiyo.contsant.EventBusAddress;
import space.aiyo.contsant.SteamApiEnum;

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
      eventBus
          .send(EventBusAddress.STEAM_CRAWLER_MATCH.getAddress(), SteamApiEnum.GET_MATCH_HISTORY_BY_SEQUENCE_NUM.getName(),
              reply -> {
                if (reply.succeeded()) {
                  JsonArray array = (JsonArray) reply.result().body();
                  logger.info("get macthes from steam,size: {}", array.size());
                }
              });
    });

  }

}
