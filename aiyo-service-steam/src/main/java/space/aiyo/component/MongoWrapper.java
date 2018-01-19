package space.aiyo.component;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Handler;
import io.vertx.core.eventbus.EventBus;
import io.vertx.core.eventbus.Message;
import io.vertx.core.json.Json;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.mongo.MongoClient;
import io.vertx.ext.mongo.UpdateOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import space.aiyo.message.CrudMessage;
import space.aiyo.var.Route;

/**
 * MongoDB的操作封装
 * CREATE BY Yo ON 2018/1/14 00:46
 */
public class MongoWrapper extends AbstractVerticle {

  private Logger logger = LoggerFactory.getLogger(this.getClass());

  private MongoClient client;
  private UpdateOptions updateOptions;

  @Override
  public JsonObject config() {
    JsonObject config = super.config();
    return config.getJsonObject(config.getString("active")).getJsonObject("mongoDB");
  }

  @Override
  public void start() throws Exception {
    client = MongoClient.createShared(vertx, config());
    updateOptions = new UpdateOptions().setMulti(true).setUpsert(true);

    //发布数据库操作
    EventBus eventBus = vertx.eventBus();
    eventBus.consumer(Route.DB_INSERT.getAddress(), insert());
    eventBus.consumer(Route.DB_INSERTBATCH.getAddress(), insertBatch());
    eventBus.consumer(Route.DB_UPDATE.getAddress(), update());
    eventBus.consumer(Route.DB_FIND.getAddress(), find());
    eventBus.consumer(Route.DB_FINDWITHOPTIONS.getAddress(), findWithOptions());
    eventBus.consumer(Route.DB_DELETE.getAddress(), delete());
    eventBus.consumer(Route.DB_COUNT.getAddress(), count());
  }

  private Handler<Message<CrudMessage>> insert() {
    return message ->
        client.insert(message.body().getDocumentName(), message.body().getJsonData(), result -> {
          if (result.succeeded()) {
            message.reply(result.result());
            logger.info("insert succeeded");
          } else {
            logger.error("insert {} failed", message.body().getDocumentName(), result.cause());
          }
        });
  }


  private Handler<Message<CrudMessage>> insertBatch() {
    return message -> System.out.println("todo");//TODO 命令行实现
  }

  private Handler<Message<CrudMessage>> update() {
    return message ->
    {
      CrudMessage body = message.body();
      client.updateCollectionWithOptions(body.getDocumentName(), body.getQuery(),
          body.getUpdate(), updateOptions, result -> {
            if (result.succeeded()) {
              message.reply(result.result());
              logger.info("update succeeded");
            } else {
              logger.error("update {} failed", body.getDocumentName(), result.cause());
            }
          });

    };
  }

  private Handler<Message<CrudMessage>> find() {
    return message ->
        client.find(message.body().getDocumentName(), message.body().getQuery(), result -> {
          if (result.succeeded()) {
            message.reply(new JsonArray(result.result()));
            logger.info("find succeeded {}",result.result());
          } else {
            logger.error("find {} failed", message.body().getDocumentName(), result.cause());
          }
        });
  }

  private Handler<Message<CrudMessage>> findWithOptions() {
    return message ->
        client.findWithOptions(message.body().getDocumentName(), message.body().getQuery(),
            message.body().getFindOptions(), result -> {
              if (result.succeeded()) {
                message.reply(new JsonArray(result.result()));
                  logger.info("findWithOptions succeeded {}",result.result());
              } else {
                logger.error("find {} failed", message.body().getDocumentName(), result.cause());
              }
            });
  }

  private Handler<Message<CrudMessage>> delete() {
    return message -> client
        .removeDocuments(message.body().getDocumentName(), message.body().getQuery(), result -> {
          if (result.succeeded()) {
            message.reply(result.result());
            logger.info("delete succeeded");
          } else {
            logger.error("delete {} failed", message.body().getDocumentName(), result.cause());
          }
        });
  }

  private Handler<Message<CrudMessage>> count() {
    return message -> client
        .count(message.body().getDocumentName(), message.body().getQuery(), result -> {
          if (result.succeeded()) {
            message.reply(result.result());
            logger.info("count succeeded");
          } else {
            logger.error("count {} failed", message.body().getDocumentName(), result.cause());
          }
        });
  }
}
