package space.aiyo.data;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.mongo.MongoClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * CREATE BY Yo ON 2018/1/13 20:44
 */
public class HeroDBVerticle extends AbstractVerticle {

  private Logger logger = LoggerFactory.getLogger(this.getClass());

  @Override
  public void start() throws Exception {
    JsonObject config = config();
    JsonObject mongoConfig = new JsonObject();

    MongoClient client = MongoClient.createNonShared(vertx, mongoConfig);


  }
}
