package space.aiyo.data;

import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import space.aiyo.component.MongoManager;
import space.aiyo.var.Route;

/**
 * CREATE BY Yo ON 2018/1/13 20:44
 */
public class HeroDBVerticle extends MongoManager {

  private Logger logger = LoggerFactory.getLogger(this.getClass());

  @Override
  public void start() {
    documentName = "dotaHero";
    updateManager();
  }
  //更新heroes
  private void updateManager(){
      vertx.eventBus().consumer(Route.DB_HERO_UPDATE.getAddress()).handler(message -> {
              JsonArray array = (JsonArray) message.body();
              array.forEach(jsonObject -> {
                  JsonObject json = (JsonObject) jsonObject;
                  JsonObject query = new JsonObject().put("id", json.getLong("id"));
                  update(json, query, result -> {
                      if (result.succeeded()) {
                          logger.info("update hero from steam to db succeeded: {}", result.result());
                      } else {
                          logger.error("update hero from steam to db failed", result.cause());
                      }
                  });
              });
          }
      );
  }

}
