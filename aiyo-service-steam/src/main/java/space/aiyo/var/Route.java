package space.aiyo.var;

/**
 * CREATE BY Yo ON 2018/1/13 17:57
 */
public enum Route {

  STEAM_CRAWLER_HERO("space.aiyo.data.crawler.hero"),
  STEAM_CRAWLER_ITEM("space.aiyo.data.crawler.item"),
  STEAM_CRAWLER_MATCH("space.aiyo.data.crawler.match"),
  DB_CRUD_HERO("space.aiyo.data.crud.hero"),
  DB_CRUD_ITEM("space.aiyo.data.crud.item"),
  DB_CRUD_MATCH("space.aiyo.data.crud.item")
  ;


  private String address;

  Route(String address) {
    this.address = address;
  }

  public String getAddress() {
    return address;
  }
}
