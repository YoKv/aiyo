package space.aiyo.data;

import entity.dota.ItemEntity;
import io.vertx.core.eventbus.EventBus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import space.aiyo.crud.CRUDVerticle;

/**
 * CREATE BY Yo ON 2018/1/13 20:45
 */
public class ItemDBVerticle extends CRUDVerticle<ItemEntity> {

  private Logger logger = LoggerFactory.getLogger(this.getClass());

  @Override
  public void start() throws Exception {
    super.start();
    EventBus eventBus = vertx.eventBus();


  }

}