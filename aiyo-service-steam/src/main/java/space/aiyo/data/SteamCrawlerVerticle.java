package space.aiyo.data;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.eventbus.EventBus;
import io.vertx.core.http.HttpClient;
import io.vertx.core.http.HttpClientOptions;
import io.vertx.core.http.HttpMethod;
import java.util.Objects;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import space.aiyo.var.EventBusAddress;
import space.aiyo.var.SteamApiEnum;


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

    //注册hero相关处理器
    eventBus.consumer(EventBusAddress.STEAM_CRAWLER_HERO.getAddress()).handler(message -> {
          String method = (String) message.body();
          String uri;
          if (Objects.equals(method, SteamApiEnum.GET_HEROES.getName())) {
            uri = "http://" + SteamApiEnum.STEAM_API_DOMAIN + SteamApiEnum.GET_HEROES.getUrl()
                + "?language=zh&itemizedonly=true&key=" + SteamApiEnum.STEAM_KEY;
            client.request(HttpMethod.GET, uri, response -> {
              if (response.statusCode() == 200) {
                response.bodyHandler(buffer -> message
                    .reply(buffer.toJsonObject().getJsonObject("result").getJsonArray("heroes")));
              } else {
                logger.error("getHeroes statusCode: {},message: {}", response.statusCode(),
                    response.statusMessage());
              }
            }).end();

          } else {
            logger.warn("unknown message");
          }
        }
    );

    //注册item相关处理器
    eventBus.consumer(EventBusAddress.STEAM_CRAWLER_ITEM.getAddress()).handler(message -> {
          String method = (String) message.body();
          String uri;
          if (Objects.equals(method, SteamApiEnum.GET_GAME_ITEMS.getName())) {
            uri = "http://" + SteamApiEnum.STEAM_API_DOMAIN + SteamApiEnum.GET_GAME_ITEMS.getUrl()
                + "?language=zh&key=" + SteamApiEnum.STEAM_KEY;
            client.request(HttpMethod.GET, uri, response -> {
              if (response.statusCode() == 200) {
                response.bodyHandler(buffer -> message
                    .reply(buffer.toJsonObject().getJsonObject("result").getJsonArray("items")));
              } else {
                logger.error("getItems statusCode: {},message: {}", response.statusCode(),
                    response.statusMessage());
              }
            }).end();

          } else {
            logger.warn("unknown message");
          }
        }
    );

    //注册item相关处理器
    eventBus.consumer(EventBusAddress.STEAM_CRAWLER_MATCH.getAddress()).handler(message -> {
          String method = (String) message.body();
          long sequenceNum = 2478669175L;
          String uri;
          if (Objects.equals(method, SteamApiEnum.GET_MATCH_HISTORY_BY_SEQUENCE_NUM.getName())) {
            uri = "http://" + SteamApiEnum.STEAM_API_DOMAIN
                + SteamApiEnum.GET_MATCH_HISTORY_BY_SEQUENCE_NUM.getUrl()
                + "?start_at_match_seq_num=" + sequenceNum + "&matches_requested=100&key="
                + SteamApiEnum.STEAM_KEY;
            client.request(HttpMethod.GET, uri, response -> {
              if (response.statusCode() == 200) {
                response.bodyHandler(buffer -> message
                    .reply(buffer.toJsonObject().getJsonObject("result").getJsonArray("matches")));
              } else {
                logger.error("getHeroes statusCode: {},message: {}", response.statusCode(),
                    response.statusMessage());
              }
            }).end();

          } else {
            logger.warn("unknown message");
          }
        }
    );

  }


}
