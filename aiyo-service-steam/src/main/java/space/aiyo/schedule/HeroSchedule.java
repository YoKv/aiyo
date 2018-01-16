package space.aiyo.schedule;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import space.aiyo.var.RedisKey;
import space.aiyo.var.Route;
import space.aiyo.var.SteamApiEnum;

/**
 * CREATE BY Yo ON 2018/1/13 16:06
 */
public class HeroSchedule extends AbstractVerticle {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public void start(){
        long timerID = vertx.setPeriodic(1000000, id ->
                vertx.eventBus()
                        .send(Route.STEAM_CRAWLER_HERO.getAddress(), SteamApiEnum.GET_HEROES.getName(),
                                reply -> {
                                    if (reply.succeeded()) {
                                        JsonArray array = (JsonArray) reply.result().body();
                                        logger.info("get heroes from steam,size: {}", array.size());
                                        updateDB(array);
                                    }
                                })
        );
        vertx.eventBus().send(Route.REDIS_SET.getAddress(),
                new JsonObject().put("key", RedisKey.SCHEDULE_TIMEID).put("value", timerID).put("expire", -1));
    }

    /*
    更新数据库信息
     */
    private void updateDB(JsonArray array) {
        vertx.eventBus()
                .send(Route.DB_HERO_UPDATE.getAddress(), array,
                        reply -> {
                            if (reply.succeeded()) {
                                logger.info("replace heroes from steam,size: {}", reply.result());
                            } else {
                                logger.error("replace heroes from steam failed", reply.cause());
                            }
                        });
    }
}
