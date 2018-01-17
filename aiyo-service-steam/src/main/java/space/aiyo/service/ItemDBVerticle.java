package space.aiyo.service;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import space.aiyo.var.Route;

/**
 * CREATE BY Yo ON 2018/1/13 20:45
 */
public class ItemDBVerticle extends AbstractVerticle {

  private Logger logger = LoggerFactory.getLogger(this.getClass());

  @Override
  public void start() {
    documentName = "dotaItem";
    updateManager();
  }
    //更新items
    private void updateManager(){
        vertx.eventBus().consumer(Route.DB_ITEM_UPDATE.getAddress()).handler(message -> {
                    JsonArray array = (JsonArray) message.body();
                    array.forEach(jsonObject -> {
                        JsonObject json = (JsonObject) jsonObject;
                        JsonObject query = new JsonObject().put("id", json.getLong("id"));
                        update(json, query, result -> {
                            if (result.succeeded()) {
                                logger.info("update item from steam to db succeeded: {}", result.result());
                            } else {
                                logger.error("update item from steam to db failed", result.cause());
                            }
                        });
                    });
                }
        );
    }
}