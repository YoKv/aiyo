package space.aiyo.component;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Handler;
import io.vertx.core.eventbus.EventBus;
import io.vertx.core.eventbus.Message;
import io.vertx.core.json.JsonObject;
import io.vertx.redis.RedisClient;
import io.vertx.redis.RedisOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import space.aiyo.var.RedisMessage;
import space.aiyo.var.Route;

/**
 * CREATE BY Yo ON 2018/1/13 12:57
 */
public class RedisWrapper extends AbstractVerticle {

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private RedisClient client;

    @Override
    public JsonObject config() {
        JsonObject config = super.config();
        return config.getJsonObject(config.getString("active")).getJsonObject("redis");
    }

    @Override
    public void start() throws Exception {
        client = RedisClient.create(vertx, new RedisOptions(config()));

        //发布数据库操作
        EventBus eventBus = vertx.eventBus();
        eventBus.consumer(Route.REDIS_SET.getAddress(), set());
        eventBus.consumer(Route.REDIS_GET.getAddress(), get());
        eventBus.consumer(Route.REDIS_DELETE.getAddress(), delete());
        eventBus.consumer(Route.REDIS_EXPIRE.getAddress(), expire());
        eventBus.consumer(Route.REDIS_EXISTS.getAddress(), exists());
        //TODO  eventBus.consumer 泛型
    }

    private Handler<Message<RedisMessage>> set() {
        return message -> {
        };
    }

    private Handler<Message<RedisMessage>> get() {
        return message -> {
        };
    }

    private Handler<Message<RedisMessage>> delete() {
        return message -> {
        };
    }

    private Handler<Message<RedisMessage>> expire() {
        return message -> {
        };
    }

    private Handler<Message<RedisMessage>> exists() {
        return message -> {
        };
    }


}
