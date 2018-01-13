package space.aiyo.business;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import java.util.Arrays;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import space.aiyo.schedule.HeroSchedule;
import space.aiyo.schedule.ItemSchedule;
import space.aiyo.schedule.MatchSchedule;

/**
 * CREATE BY Yo ON 2018/1/13 16:01
 */
public class ScheduleVerticle extends AbstractVerticle {

  private Logger logger = LoggerFactory.getLogger(this.getClass());

  @Override
  public void start(Future<Void> startFuture) throws Exception {
    String[] classNames = new String[]{HeroSchedule.class.getName(), ItemSchedule.class.getName(),
        MatchSchedule.class.getName()};
    AtomicInteger atomicInteger = new AtomicInteger();

    Arrays.stream(classNames).forEach(clazzName ->
        vertx.deployVerticle(clazzName, res -> {
          if (res.succeeded()) {
            int count = atomicInteger.incrementAndGet();
            if (Objects.equals(count, classNames.length)) {
              logger.info("scheduleVerticle deploy succeeded");
              startFuture.complete();
            }
          } else {
            logger.error("{} deploy failed: {}", clazzName, res.cause());
            startFuture.fail(res.cause());
          }
        })
    );


  }
}
