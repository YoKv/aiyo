package space.aiyo.data;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.mongo.MongoClient;

/**
 * CREATE BY Yo ON 2018/1/13 20:45
 */
public class MatchDBVerticle extends AbstractVerticle {

  @Override
  public void start() throws Exception {
    JsonObject config = new JsonObject();

    MongoClient client = MongoClient.createNonShared(vertx, config);


  }
}
