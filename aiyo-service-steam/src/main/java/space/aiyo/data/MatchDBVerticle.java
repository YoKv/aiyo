package space.aiyo.data;

import entity.dota.HeroEntity;
import entity.dota.MatchEntity;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.eventbus.EventBus;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.mongo.MongoClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import space.aiyo.crud.CRUDVerticle;

/**
 * CREATE BY Yo ON 2018/1/13 20:45
 */
public class MatchDBVerticle extends CRUDVerticle<MatchEntity> {

  private Logger logger = LoggerFactory.getLogger(this.getClass());

  @Override
  public void start() throws Exception {
    super.start();
    EventBus eventBus = vertx.eventBus();


  }

}