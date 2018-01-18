package space.aiyo;

import io.vertx.core.Vertx;
import io.vertx.core.eventbus.MessageCodec;
import space.aiyo.message.CrudMessage;
import space.aiyo.message.RedisMessage;
import space.aiyo.util.DeployUtil;

/**
 * CREATE BY Yo ON 2018/1/13 12:43
 */
public class Main {

  public static void main(String[] args) {
    //日志相关
    System.setProperty("vertx.logger-delegate-factory-class-name",
        "io.vertx.core.logging.SLF4JLogDelegateFactory");
    //一个netty引起的bug 不影响功能，暂时解决方式,https://github.com/eclipse/vert.x/issues/2204
    System.setProperty("vertx.disableDnsResolver", "true");

    Vertx vertx = Vertx.vertx();
    vertx.eventBus().registerCodec(new CrudMessage()).registerCodec(new RedisMessage());
    //部署Verticle
    DeployUtil.deployVerticle(vertx);
  }

  // TODO: 2018/1/14 日志配置,逻辑理顺,redis,MQ,API

}
