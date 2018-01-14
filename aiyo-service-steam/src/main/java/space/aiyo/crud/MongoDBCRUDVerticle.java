package space.aiyo.crud;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.mongo.MongoClient;

/**
 * CREATE BY Yo ON 2018/1/14 00:46
 */
public class MongoDBCRUDVerticle extends AbstractVerticle {

  private MongoClient client;
  protected String documentName;

  @Override
  public JsonObject config() {
    JsonObject config = super.config();
    String env = config.getString("active");
    JsonObject json = new JsonObject();
    switch (env) {
      case "dev":
        json.put("2", "1");
        break;
      case "test":
        break;
      case "prod":
        break;
      default:
        break;
    }

    return json;
  }

  @Override
  public void start() throws Exception {
    client = MongoClient.createShared(vertx, config());
  }


  protected long insert(JsonObject document) {
    return 0;
  }


  protected int insertBatch(JsonArray array) {
    return 0;
  }

  protected void save(JsonObject document, Handler<AsyncResult<String>> handler) {
    client.save(documentName, document, handler);
  }


  protected int saveBatch(JsonArray array) {
    return 0;
  }


  protected int update() {
    return 0;
  }


  protected int updateBatch() {
    return 0;
  }


  protected int updateByQuery() {
    return 0;
  }


  protected JsonArray findByQuery() {
    return null;
  }


  protected JsonObject findOne() {
    return null;
  }


  protected int delete() {
    return 0;
  }


  protected int deleteByQuery() {
    return 0;
  }


  protected int count() {
    return 0;
  }
}
