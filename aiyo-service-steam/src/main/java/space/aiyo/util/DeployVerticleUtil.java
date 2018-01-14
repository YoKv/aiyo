package space.aiyo.util;

import io.vertx.config.ConfigRetriever;
import io.vertx.config.ConfigRetrieverOptions;
import io.vertx.config.ConfigStoreOptions;
import io.vertx.core.CompositeFuture;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.Future;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import space.aiyo.business.HeroSchedule;
import space.aiyo.business.ItemSchedule;
import space.aiyo.business.MatchSchedule;
import space.aiyo.component.RedisManager;
import space.aiyo.component.RocketMQManager;
import space.aiyo.data.HeroDBVerticle;
import space.aiyo.data.ItemDBVerticle;
import space.aiyo.data.MatchDBVerticle;
import space.aiyo.data.SteamCrawlerVerticle;

/**
 * CREATE BY Yo ON 2018/1/13 22:00
 */
public class DeployVerticleUtil {

  private static Logger logger = LoggerFactory.getLogger(DeployVerticleUtil.class);

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
        JsonObject config = result.result();
        logger.info(config.toString());
        //配置信息
        DeploymentOptions options = new DeploymentOptions().setConfig(config);
        //中间件
        vertx.deployVerticle(RocketMQManager.class.getName());
        vertx.deployVerticle(RedisManager.class.getName(), options);

        //业务

        vertx.deployVerticle(HeroSchedule.class.getName());
        vertx.deployVerticle(ItemSchedule.class.getName());
        vertx.deployVerticle(MatchSchedule.class.getName());

        vertx.deployVerticle(SteamCrawlerVerticle.class.getName(), options);

        vertx.deployVerticle(HeroDBVerticle.class.getName(), options);
        vertx.deployVerticle(ItemDBVerticle.class.getName(), options);
        vertx.deployVerticle(MatchDBVerticle.class.getName(), options);
      } else {
        logger.error("getConfig failed", result.cause());
        vertx.close();
      }
    });
  }

  /**
   * 批量部署，减少代码量 TODO
   */
  public static void deployVerticleBatch(Vertx vertx, Consumer<Integer> consumer,
      String... classNames) {

    List<Future> list = new ArrayList<>();
    AtomicInteger atomicInteger = new AtomicInteger();

    Arrays.stream(classNames).forEach(clazzName -> {
      Future<Void> future = Future.future();
      list.add(future);
      vertx.deployVerticle(clazzName, res -> {
        if (res.succeeded()) {

          int count = atomicInteger.incrementAndGet();
          logger.info("count {}", count);
          logger.info("deployVerticleBatch {}", atomicInteger.get());
          if (Objects.equals(count, classNames.length)) {
            logger.info("complete");
            future.completer();
          }
        } else {
          future.fail(res.cause());
        }
      });
    });
    logger.info("list {}", list.size());
    CompositeFuture.all(list).setHandler(result -> {
      if (result.succeeded()) {
        logger.info(" 所有服务器启动完成");
        consumer.accept(null);
      } else {
        logger.error("deployVerticleBatch failed", result.cause());
        vertx.close();
      }
    });


  }

}
