package space.aiyo.crud;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.AsyncResult;
import io.vertx.core.Context;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.mongo.FindOptions;
import io.vertx.ext.mongo.MongoClient;
import io.vertx.ext.mongo.MongoClientDeleteResult;
import io.vertx.ext.mongo.MongoClientUpdateResult;
import io.vertx.ext.mongo.UpdateOptions;
import java.util.List;

/**
 * MongoClient透明,便于切换数据源
 * CREATE BY Yo ON 2018/1/14 00:46
 */
public class MongoDBCRUDVerticle extends AbstractVerticle {

  private MongoClient client;
  private UpdateOptions updateOptions;
  protected String documentName;

  @Override
  public void init(Vertx vertx, Context context) {
    super.init(vertx, context);
    client = MongoClient.createShared(vertx, config());
    updateOptions = new UpdateOptions().setMulti(true).setUpsert(false);
  }

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
  public void stop() throws Exception {
    super.stop();
    client.close();
  }

  protected void insert(JsonObject document, Handler<AsyncResult<String>> handler) {
    client.insert(documentName, document, handler);

  }

  protected void insertBatch(JsonArray array) {
    // TODO: 2018/1/14 命令行
  }

  protected void update(JsonObject document, JsonObject query,
      Handler<AsyncResult<MongoClientUpdateResult>> handler) {
    client.updateCollectionWithOptions(documentName, query, new JsonObject().put("$set", document),
        updateOptions, handler);
  }

  protected void find(JsonObject query, Handler<AsyncResult<List<JsonObject>>> handler) {
    client.find(documentName, query, handler);
  }

  protected void findWithOptions(JsonObject query, FindOptions findOptions,
      Handler<AsyncResult<List<JsonObject>>> handler) {
    client.findWithOptions(documentName, query, findOptions, handler);
  }
  
  protected void delete(JsonObject query,
      Handler<AsyncResult<MongoClientDeleteResult>> handler) {
    client.removeDocuments(documentName, query, handler);
  }

  protected void count(JsonObject query, Handler<AsyncResult<Long>> handler) {
    client.count(documentName, query, handler);
  }
}
