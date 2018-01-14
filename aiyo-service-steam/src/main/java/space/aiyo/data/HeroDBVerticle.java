package space.aiyo.data;

import io.vertx.core.eventbus.EventBus;
import io.vertx.core.json.JsonObject;
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
    documentName = "dotaHero";
    super.start();
    EventBus eventBus = vertx.eventBus();

    JsonObject query= new JsonObject().put("name","npc_dota_hero_antimage");
    find(query,result->{
      if(result.succeeded()){
        result.result().forEach(obj-> System.out.println(obj));
      }
    });

  }

}
