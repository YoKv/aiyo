package space.aiyo.var;

/**
 * CREATE BY Yo ON 2018/1/13 17:57
 */
public enum Route {

  STEAM_CRAWLER_HERO("space.aiyo.data.crawler.hero"),
  STEAM_CRAWLER_ITEM("space.aiyo.data.crawler.item"),
  STEAM_CRAWLER_MATCH("space.aiyo.data.crawler.match"),

  DB_HERO_UPDATE("space.aiyo.data.hero.update"),
  DB_ITEM_UPDATE("space.aiyo.data.item.update"),
  DB_MATCH_INSERT("space.aiyo.data.match.insert"),
  DB_MATCH_FIND("space.aiyo.data.match.find"),
  DB_MATCH_FINDWITHOPTIONS("space.aiyo.data.match.findWithOptions");


  private String address;

  Route(String address) {
    this.address = address;
  }

  public String getAddress() {
    return address;
  }
}
