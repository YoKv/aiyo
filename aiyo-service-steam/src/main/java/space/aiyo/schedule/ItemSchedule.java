package space.aiyo.schedule;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * CREATE BY Yo ON 2018/1/13 16:06
 */
public class ItemSchedule extends AbstractVerticle {

  private Logger logger = LoggerFactory.getLogger(this.getClass());

  @Override
  public void start() throws Exception {

    long timerID = vertx.setPeriodic(1000000, id -> {
      System.out.println(" timer fired!  ItemSchedule");
    });

  }

}
