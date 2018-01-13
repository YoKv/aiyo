package space.aiyo.crud;

import entity.BottomEntity;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.mongo.MongoClient;
import java.util.List;

/**
 * CREATE BY Yo ON 2018/1/14 00:46
 */
public class CRUDVerticle<T extends BottomEntity> extends AbstractVerticle {

  private MongoClient client;

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


  protected long insert(T t) {
    return 0;
  }


  protected int insertBatch(List<T> list) {
    return 0;
  }

  protected T save(T t) {
    return null;
  }


  protected int saveBatch(List<T> list) {
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


  protected List<T> findByQuery() {
    return null;
  }


  protected T findOne() {
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
