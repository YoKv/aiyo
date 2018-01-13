package space.aiyo.var;

/**
 * CREATE BY Yo ON 2018/1/13 17:57
 */
public enum EventBusAddress {
  STEAM_CRAWLER_HERO("hero.steamCrawler.aiyo.space"),
  STEAM_CRAWLER_ITEM("item.steamCrawler.aiyo.space"),
  STEAM_CRAWLER_MATCH("match.steamCrawler.aiyo.space");


  private String address;

  EventBusAddress(String address) {
    this.address = address;
  }

  public String getAddress() {
    return address;
  }
}