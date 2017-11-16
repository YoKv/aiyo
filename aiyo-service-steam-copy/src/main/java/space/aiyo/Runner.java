package space.aiyo;

import io.vertx.core.DeploymentOptions;
import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;

import java.io.File;
import java.io.IOException;
import java.util.function.Consumer;

/*
 * @author <a href="http://tfox.org">Tim Fox</a>
 */
public class Runner {

  private static final String WEB_CLIENT_EXAMPLES_DIR = "web-client-examples";
  private static final String WEB_CLIENT_EXAMPLES_JAVA_DIR = WEB_CLIENT_EXAMPLES_DIR + "/src/main/java/";


  public static void runExample(Class clazz) {
    runExample(WEB_CLIENT_EXAMPLES_JAVA_DIR, clazz, new VertxOptions().setClustered(false), null);
  }

  public static void runExample(Class clazz, DeploymentOptions options) {
    runExample(WEB_CLIENT_EXAMPLES_JAVA_DIR, clazz, new VertxOptions().setClustered(false), options);
  }


  public static void runExample(String exampleDir, Class clazz, VertxOptions options, DeploymentOptions
    deploymentOptions) {
    runExample(exampleDir + clazz.getPackage().getName().replace(".", "/"), clazz.getName(), options, deploymentOptions);
  }


  public static void runExample(String exampleDir, String verticleID, VertxOptions options, DeploymentOptions deploymentOptions) {
    if (options == null) {
      // Default parameter
      options = new VertxOptions();
    }



    Consumer<Vertx> runner = vertx -> {
      try {
        if (deploymentOptions != null) {
          vertx.deployVerticle(verticleID, deploymentOptions);
        } else {
          vertx.deployVerticle(verticleID);
        }
      } catch (Throwable t) {
        t.printStackTrace();
      }
    };
    Vertx vertx = Vertx.vertx(options);
    runner.accept(vertx);
  }
}
