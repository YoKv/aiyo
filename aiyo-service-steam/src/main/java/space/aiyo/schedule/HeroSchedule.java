package space.aiyo.schedule;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.eventbus.Message;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import space.aiyo.var.*;

/**
 * CREATE BY Yo ON 2018/1/13 16:06
 */
public class HeroSchedule extends AbstractVerticle {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public void start() {
        long timerID = vertx.setPeriodic(1000000, id -> vertx.eventBus().send(Route.STEAM_CRAWLER_HERO.getAddress(), "", crawlerHero()));
        vertx.eventBus().send(Route.REDIS_SET.getAddress(),
                new JsonObject().put("key", RedisKey.SCHEDULE_TIMEID).put("value", timerID).put("expire", -1));
    }

    private Handler<AsyncResult<Message<JsonArray>>> crawlerHero() {
        return message -> {
            if (message.succeeded()) {
                CrudMessage crudMessage = new CrudMessage();
                crudMessage.setArrayData(message.result().body());
                crudMessage.setDocumentName("");
                vertx.eventBus().send(Route.DB_UPDATE.getAddress(), crudMessage);
            }
        };
    }

}
