package space.aiyo.data;

import entity.dota.HeroEntity;
import io.vertx.core.eventbus.EventBus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import space.aiyo.crud.MongoDBCRUDVerticle;

/**
 * CREATE BY Yo ON 2018/1/13 20:44
 */
public class HeroDBVerticle extends MongoDBCRUDVerticle {

  private Logger logger = LoggerFactory.getLogger(this.getClass());

  @Override
  public void start() throws Exception {
    super.start();
    EventBus eventBus = vertx.eventBus();
    System.out.println(config());

  }

}
