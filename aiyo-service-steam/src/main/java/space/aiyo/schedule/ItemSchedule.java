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
public class ItemSchedule extends AbstractVerticle {

  private Logger logger = LoggerFactory.getLogger(this.getClass());

  @Override
  public void start() throws Exception {

    EventBus eventBus = vertx.eventBus();
    long timerID = vertx.setPeriodic(10000, id -> {
      eventBus
          .send(EventBusAddress.STEAM_CRAWLER_ITEM.getAddress(), SteamApiEnum.GET_GAME_ITEMS.getName(),
              reply -> {
                if (reply.succeeded()) {
                  JsonArray array = (JsonArray) reply.result().body();
                  logger.info("get items from steam,size: {}", array.size());
                }
              });
    });
  }

}
