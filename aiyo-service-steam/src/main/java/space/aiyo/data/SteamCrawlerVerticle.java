package space.aiyo.data;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.eventbus.EventBus;
import io.vertx.core.http.HttpClient;
import io.vertx.core.http.HttpClientOptions;
import io.vertx.core.http.HttpClientRequest;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.json.JsonObject;
import java.util.Objects;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import space.aiyo.contsant.EventBusAddress;
import space.aiyo.contsant.SteamApiEnum;


/**
 * CREATE BY Yo ON 2018/1/13 17:26
 */
public class SteamCrawlerVerticle extends AbstractVerticle {

  private Logger logger = LoggerFactory.getLogger(this.getClass());

  @Override
  public void start() throws Exception {

    // todo HTTP2
    HttpClientOptions options = new HttpClientOptions()
        .setDefaultHost(SteamApiEnum.STEAM_API_DOMAIN);
    HttpClient client = vertx.createHttpClient(options);
    //eventBus获取请求
    EventBus eventBus = vertx.eventBus();
    //注册hero相关请求
    eventBus.consumer(EventBusAddress.STEAM_CRAWLER_HERO.getAddress()).handler(message -> {
          String method = (String) message.body();
          String uri;
          //区分方法
          if (Objects.equals(method, SteamApiEnum.GET_HEROES.getName())) {
            uri = "http://" + SteamApiEnum.STEAM_API_DOMAIN + SteamApiEnum.GET_HEROES.getUrl()
                + "?language=zh&itemizedonly=true&key="
                + SteamApiEnum.STEAM_KEY;
            logger.info("uri {} ", uri);
            client.request(HttpMethod.GET, uri, response -> {
              response.handler(buffer -> {
                JsonObject json = buffer.toJsonObject();
                message.reply(json.getJsonObject("result").getJsonArray("heroes"));
              });
            }).end();

//            HttpClientRequest request = client.get(uri, response -> {
//              System.out.println(response.statusCode());
//              response.handler(buffer -> {
//                logger.info(buffer.toString());
//                JsonObject json = buffer.toJsonObject();
//                message.reply(json.getJsonObject("result").getJsonArray("heroes"));
//              });
//            });
//            request.exceptionHandler(e -> logger.error("exception  {}  {}", uri, e));
          } else {
            logger.warn("unknown message");
          }
        }
    );
// http://api.steampowered.com/IEconDOTA2_570/GetHeroes/v1?language=zh&itemizedonly=true&key=AEC1E5CB1A7D9CC75CB4653B8C98875F
    //                        /IEconDOTA2_570/GetHeroes/v1?language=zh&itemizedonly=true&key=AEC1E5CB1A7D9CC75CB4653B8C98875F

  }
}
