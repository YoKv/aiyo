package space.aiyo;

import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;

/**
 * CREATE BY Yo ON 2017/11/18 22:59
 */
public class MainVerticle {

  public static void main(String[] args) {
    Vertx vertx = Vertx.vertx(new VertxOptions());

    // 注意要添加对应的集群管理器依赖，详情见集群管理器章节
//    VertxOptions options = new VertxOptions();
//    Vertx.clusteredVertx(options, res -> {
//      if (res.succeeded()) {
//        Vertx vertx = res.result(); // 获取到了集群模式下的 Vertx 对象
//        // 做一些其他的事情
//      } else {
//        // 获取失败，可能是集群管理器出现了问题
//      }
//    });

    vertx.setPeriodic(1000, id -> {
      // This handler will get called every second
      // 这个处理器将会每隔一秒被调用一次
      System.out.println("timer fired!");
    });




  }
}
