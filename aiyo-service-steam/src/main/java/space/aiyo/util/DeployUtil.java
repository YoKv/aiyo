package space.aiyo.util;

import io.vertx.config.ConfigRetriever;
import io.vertx.config.ConfigRetrieverOptions;
import io.vertx.config.ConfigStoreOptions;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import space.aiyo.business.HeroSchedule;
import space.aiyo.business.ItemSchedule;
import space.aiyo.business.MatchSchedule;
import space.aiyo.business.SteamCrawlerVerticle;
import space.aiyo.component.MongoWrapper;
import space.aiyo.component.RedisWrapper;
import space.aiyo.component.RocketMQWrapper;

/**
 * CREATE BY Yo ON 2018/1/13 22:00
 */
public class DeployUtil {

  private static Logger logger = LoggerFactory.getLogger(DeployUtil.class);

  public static void deployVerticle(Vertx vertx) {
    ConfigStoreOptions store = new ConfigStoreOptions()
        .setType("file")
        .setFormat("yaml")
        .setConfig(new JsonObject()
            .put("path", "config.yaml")
        );

    ConfigRetriever retriever = ConfigRetriever.create(vertx,
        new ConfigRetrieverOptions().addStore(store));

    retriever.getConfig(result -> {
      if (result.succeeded()) {
        //配置信息
        DeploymentOptions options = new DeploymentOptions().setConfig(result.result());

        //部署Verticle

        vertx.deployVerticle(RedisWrapper.class.getName(), options, res -> {
          if (res.succeeded()) {
            //业务Verticle
            vertx.deployVerticle(HeroSchedule.class.getName());
            vertx.deployVerticle(ItemSchedule.class.getName());
            vertx.deployVerticle(MatchSchedule.class.getName());
            vertx.deployVerticle(SteamCrawlerVerticle.class.getName(), options);
          } else {
            logger.error("deployVerticle RedisWrapper failed", res.cause());
            vertx.close();
          }
        });
        vertx.deployVerticle(MongoWrapper.class.getName(), options, res -> {
          if (res.failed()) {
            logger.error("deployVerticle MongoWrapper failed", res.cause());
            vertx.close();
          }
        });
        vertx.deployVerticle(RocketMQWrapper.class.getName());


      } else {
        logger.error("getConfig failed", result.cause());
        vertx.close();
      }
    });
  }
}
