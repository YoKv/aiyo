package space.aiyo.component;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.http.HttpClient;
import io.vertx.core.http.HttpClientOptions;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.http.HttpVersion;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * CREATE BY Yo ON 2018/1/13 12:47
 */
public class HttpManager extends AbstractVerticle {

  private Logger logger = LoggerFactory.getLogger(this.getClass());

  @Override
  public void start(Future<Void> startFuture) throws Exception {

    HttpClientOptions options = new HttpClientOptions()
        .setProtocolVersion(HttpVersion.HTTP_2).
        setSsl(true).
        setUseAlpn(true).
        setTrustAll(true);

    HttpClient client = vertx.createHttpClient(options);


//    HttpClientOptions options = new HttpClientOptions().setDefaultHost("wibble.com");
// Can also set default port if you want...
// 若您想可设置默认端口

    client.getNow("/some-uri", response -> {
      System.out.println("Received response with status code " + response.statusCode());
    });

    client.request(HttpMethod.GET, "some-uri", response -> {
      System.out.println("Received response with status code " + response.statusCode());
    }).end();

    client.request(HttpMethod.POST, "foo-uri", response -> {
      System.out.println("Received response with status code " + response.statusCode());
    }).end("some-data");
  }
}
