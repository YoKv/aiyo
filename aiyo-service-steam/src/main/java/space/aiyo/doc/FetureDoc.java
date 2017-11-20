package space.aiyo.doc;

import io.vertx.core.CompositeFuture;
import io.vertx.core.Future;
import io.vertx.core.Vertx;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.http.HttpServer;
import io.vertx.core.net.NetServer;
import java.nio.file.FileSystem;
import java.util.Arrays;

/**
 * CREATE BY Yo ON 2017/11/20 22:59
 */
public class FetureDoc {

  public static void main(String[] args) {
    Vertx vertx = Vertx.vertx();

    //CompositeFuture.all 方法接受多个 Future 对象作为参数（最多6个，或者传入 List）。当所有的 Future 都成功完成，该方法将返回一个 成功的 Future；当任一个 Future 执行失败，则返回一个 失败的 Future：
    Future<HttpServer> httpServerFuture = Future.future();
    httpServer.listen(httpServerFuture.completer());

    Future<NetServer> netServerFuture = Future.future();
    netServer.listen(netServerFuture.completer());

    CompositeFuture.all(Arrays.asList(future1, future2, future3));
    CompositeFuture.all(httpServerFuture, netServerFuture).setHandler(ar -> {
      if (ar.succeeded()) {
        // 所有服务器启动完成
      } else {
        // 有一个服务器启动失败
      }
    });

    CompositeFuture.any(Arrays.asList(f1, f2, f3));
    CompositeFuture.any(future1, future2).setHandler(ar -> {
      if (ar.succeeded()) {
        // 至少一个成功
      } else {
        // 所有的都失败
      }
    });



    //join 方法的合并会等待所有的 Future 完成，无论成败。CompositeFuture.join 方法接受多个 Future 作为参数（最多6个），并将结果归并成一个 Future 。当全部 Future 成功执行完成，得到的 Future 是成功状态的；当至少一个 Future 执行失败时，得到的 Future 是失败状态的。
    CompositeFuture.join(future1, future2, future3).setHandler(ar -> {
      if (ar.succeeded()) {
        // 所有都成功
      } else {
        // 至少一个失败
      }
    });


    //和 all 以及 any 实现的并发组合不同，compose 方法作用于顺序组合 Future。
//    这里例子中，有三个操作被串起来了：
//
//    一个文件被创建（fut1）
//    一些东西被写入到文件（fut2）
//    文件被移走（startFuture）
//    如果这三个步骤全部成功，则最终的 Future（startFuture）会是成功的；其中任何一步失败，则最终 Future 就是失败的。
//
//    例子中使用了：
//
//    compose(mapper)：当前 Future 完成时，执行相关代码，并返回 Future。当返回的 Future 完成时，组合完成。
//    compose(handler, next)：当前 Future 完成时，执行相关代码，并完成下一个 Future 的处理。
//    对于第二个例子，处理器需要完成 next future，以此来汇报处理成功或者失败。
//
//    您可以使用 completer 方法来串起一个带操作结果的或失败的 Future ，它可使您避免用传统方式编写代码：如果成功则完成 Future，否则就标记为失败。（译者注：3.4.0 以后不需要再使用 completer 方法）

    io.vertx.core.file.FileSystem fs = vertx.fileSystem();
    Future<Void> startFuture = Future.future();

    Future<Void> fut1 = Future.future();
    fs.createFile("/foo", fut1.completer());

    fut1.compose(v -> {
      // fut1中文件创建完成后执行
      Future<Void> fut2 = Future.future();
      fs.writeFile("/foo", Buffer.buffer(), fut2.completer());
      return fut2;
    }).compose(v -> {
          // fut2文件写入完成后执行
          fs.move("/foo", "/bar", startFuture.completer());
        },
        // 如果任何一步失败，将startFuture标记成failed
        startFuture);

  }
}
