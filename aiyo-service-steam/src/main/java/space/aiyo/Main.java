package space.aiyo;

import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import space.aiyo.component.HttpManager;
import space.aiyo.component.JDBCClient;
import space.aiyo.component.MQClient;
import space.aiyo.component.RedisClient;

/**
 * CREATE BY Yo ON 2018/1/13 12:43
 */
public class Main {
  private static Logger logger = LoggerFactory.getLogger(Main.class);

  public static void main(String[] args) {
    Vertx vertx = Vertx.vertx();

    //日志相关
    System.setProperty("vertx.logger-delegate-factory-class-name",
        "io.vertx.core.logging.SLF4JLogDelegateFactory");

    Handler<AsyncResult<String>> handler = result -> {
      if (result.succeeded()) {
        logger.info("Deployment id is: {}", result.result());
      } else {
        logger.error("Deployment failed");
      }
    };

    vertx.deployVerticle(new HttpManager(), handler);
    vertx.deployVerticle(new JDBCClient(), handler);
    vertx.deployVerticle(new MQClient(), handler);
    vertx.deployVerticle(new RedisClient(), handler);

    logger.info("***********************     steam server start     ***********************");
  }
}
