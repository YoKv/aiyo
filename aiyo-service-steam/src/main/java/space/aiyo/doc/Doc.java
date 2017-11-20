package space.aiyo.doc;

import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;
import io.vertx.core.WorkerExecutor;

/**
 * CREATE BY Yo ON 2017/11/20 22:34
 */
public class Doc {

  public static void main(String[] args) {
    Vertx vertx = Vertx.vertx();

    //同步方法,异步回调处理
    vertx.executeBlocking(future -> {
      // 调用一些需要耗费显著执行时间返回结果的阻塞式API
      String result = someAPI.blockingMethod("hello");
      future.complete(result);
    }, false, res -> {
      System.out.println("The result is: " + res.result());
    });

    //同步方法,异步回调处理
    vertx.executeBlocking(future -> {
      // 调用一些需要耗费显著执行时间返回结果的阻塞式API
      String result = someAPI.blockingMethod("hello");
      future.complete(result);
    }, res -> {
      System.out.println("The result is: " + res.result());
    });

    WorkerExecutor executor = vertx.createSharedWorkerExecutor("my-worker-pool");
    //默认的阻塞式代码会在 Vert.x 的 Worker Pool 中执行，通过 setWorkerPoolSize 配置。
    new VertxOptions().setWorkerPoolSize(100);
    executor.executeBlocking(future -> {
      // 调用一些需要耗费显著执行时间返回结果的阻塞式API
      String result = someAPI.blockingMethod("hello");
      future.complete(result);
    }, res -> {
      System.out.println("The result is: " + res.result());
    });

    executor.close();


    int poolSize = 10;

// 2分钟
    long maxExecuteTime = 120000;

    WorkerExecutor executor2 = vertx.createSharedWorkerExecutor("my-worker-pool", poolSize, maxExecuteTime);

  }

}
