package space.aiyo;

import io.vertx.core.Vertx;
import space.aiyo.util.DeployVerticleUtil;

/**
 * CREATE BY Yo ON 2018/1/13 12:43
 */
public class Main {

  public static void main(String[] args) {
    //日志相关  TODO 日志配置
    System.setProperty("vertx.logger-delegate-factory-class-name",
        "io.vertx.core.logging.SLF4JLogDelegateFactory");
    //一个netty引起的bug 不影响功能，暂时解决方式,https://github.com/eclipse/vert.x/issues/2204
    System.setProperty("vertx.disableDnsResolver", "true");

    Vertx vertx = Vertx.vertx();
    //部署Verticle
    DeployVerticleUtil.deployVerticle(vertx);
  }
}
