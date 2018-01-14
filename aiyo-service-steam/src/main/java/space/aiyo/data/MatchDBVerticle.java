package space.aiyo.data;

import entity.dota.MatchEntity;
import io.vertx.core.eventbus.EventBus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import space.aiyo.crud.MongoDBCRUDVerticle;

/**
 * CREATE BY Yo ON 2018/1/13 20:45
 */
public class MatchDBVerticle extends MongoDBCRUDVerticle{

  private Logger logger = LoggerFactory.getLogger(this.getClass());

  @Override
  public void start() throws Exception {
    super.start();
    EventBus eventBus = vertx.eventBus();


  }

}