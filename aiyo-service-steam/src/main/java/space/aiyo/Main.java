package space.aiyo;

import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import space.aiyo.business.ScheduleVerticle;
import space.aiyo.component.MongoDBManager;
import space.aiyo.component.RedisManager;
import space.aiyo.component.RocketMQManager;
import space.aiyo.data.SteamCrawlerVerticle;

/**
 * CREATE BY Yo ON 2018/1/13 12:43
 */
public class Main {

  private static Logger logger = LoggerFactory.getLogger(Main.class);

  public static void main(String[] args) {
    //日志相关  TODO 日志配置
    System.setProperty("vertx.logger-delegate-factory-class-name",
        "io.vertx.core.logging.SLF4JLogDelegateFactory");
    //一个netty引起的bug 不影响功能，暂时解决方式,https://github.com/eclipse/vert.x/issues/2204
    System.setProperty("vertx.disableDnsResolver", "true");

    Vertx vertx = Vertx.vertx();

    Handler<AsyncResult<String>> handler = result -> {
      if (result.succeeded()) {
        logger.info("Deployment id is: {}", result.result());
      } else {
        logger.error("Deployment failed");
      }
    };

    //中间件Verticle
    vertx.deployVerticle(MongoDBManager.class.getName(), handler);
    vertx.deployVerticle(RocketMQManager.class.getName(), handler);
    vertx.deployVerticle(RedisManager.class.getName(), handler);

    //中间件Verticle全部部署成功，开始部署业务Verticle
    //业务Verticle
    vertx.deployVerticle(ScheduleVerticle.class.getName());
    vertx.deployVerticle(SteamCrawlerVerticle.class.getName());
  }
}
