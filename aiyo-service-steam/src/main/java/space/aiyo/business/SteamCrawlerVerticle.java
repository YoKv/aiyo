package space.aiyo.business;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Handler;
import io.vertx.core.eventbus.Message;
import io.vertx.core.http.HttpClient;
import io.vertx.core.http.HttpClientOptions;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.json.JsonObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import space.aiyo.var.Route;
import space.aiyo.var.SteamApiEnum;


/**
 * CREATE BY Yo ON 2018/1/13 17:26
 */
public class SteamCrawlerVerticle extends AbstractVerticle {

  private Logger logger = LoggerFactory.getLogger(this.getClass());

  private String steamApiDomain;
  private String steamKey;
  private HttpClient client;

  @Override
  public void start() throws Exception {
    JsonObject config = config();
    steamApiDomain = config.getString("STEAM_API_DOMAIN");
    steamKey = config.getString("STEAM_KEY");
    // todo HTTP2
    client = vertx.createHttpClient(new HttpClientOptions().setDefaultHost(steamApiDomain));

    vertx.eventBus().consumer(Route.STEAM_CRAWLER_HERO.getAddress(), heroCrawler());
    vertx.eventBus().consumer(Route.STEAM_CRAWLER_ITEM.getAddress(), itemCrawler());
    vertx.eventBus().consumer(Route.STEAM_CRAWLER_MATCH.getAddress(), matchCrawler());
  }

  private Handler<Message<Void>> heroCrawler() {
    return message -> {
      String uri = "http://" + steamApiDomain + SteamApiEnum.GET_HEROES.getUrl()
          + "?language=zh&itemizedonly=true&key=" + steamKey;
      client.request(HttpMethod.GET, uri, response -> {
        if (response.statusCode() == 200) {
          response.bodyHandler(buffer -> message
              .reply(buffer.toJsonObject().getJsonObject("result").getJsonArray("heroes")));
        } else {
          logger.error("getHeroes statusCode: {},message: {}", response.statusCode(),
              response.statusMessage());
        }
      }).end();
    };
  }

  private Handler<Message<Void>> itemCrawler() {
    return message -> {
      String uri = "http://" + steamApiDomain + SteamApiEnum.GET_GAME_ITEMS.getUrl()
          + "?language=zh&key=" + steamKey;
      client.request(HttpMethod.GET, uri, response -> {
        if (response.statusCode() == 200) {
          response.bodyHandler(buffer -> message
              .reply(buffer.toJsonObject().getJsonObject("result").getJsonArray("items")));
        } else {
          logger.error("getItems statusCode: {},message: {}", response.statusCode(),
              response.statusMessage());
        }
      }).end();
    };
  }


  private Handler<Message<Long>> matchCrawler() {
    return message -> {
      Long sequenceNum = message.body();
      String uri = "http://" + steamApiDomain
          + SteamApiEnum.GET_MATCH_HISTORY_BY_SEQUENCE_NUM.getUrl()
          + "?start_at_match_seq_num=" + sequenceNum + "&matches_requested=100&key="
          + steamKey;
      client.request(HttpMethod.GET, uri, response -> {
        if (response.statusCode() == 200) {
          response.bodyHandler(buffer -> message
              .reply(buffer.toJsonObject().getJsonObject("result").getJsonArray("matches")));
        } else {
          logger.error("getHeroes statusCode: {},message: {}", response.statusCode(),
              response.statusMessage());
        }
      }).end();
    };
  }


}
