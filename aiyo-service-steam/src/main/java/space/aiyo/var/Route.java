package space.aiyo.var;

/**
 * CREATE BY Yo ON 2018/1/13 17:57
 */
public enum Route {

    /***************************  业务层  **************************************/
    STEAM_CRAWLER_HERO("service.crawler.hero"),
    STEAM_CRAWLER_ITEM("service.crawler.item"),
    STEAM_CRAWLER_MATCH("service.crawler.match"),

    DB_HERO_UPDATE("service.hero.update"),
    DB_ITEM_UPDATE("service.item.update"),
    DB_MATCH_INSERT("service.match.insert"),
    DB_MATCH_FIND("service.match.find"),
    DB_MATCH_FINDWITHOPTIONS("service.match.findWithOptions"),



    /***************************  数据层  **************************************/
    DB_INSERT("mongo.insert"),
    DB_INSERTBATCH("mongo.insertBatch"),
    DB_UPDATE("mongo.update"),
    DB_FIND("mongo.find"),
    DB_FINDWITHOPTIONS("mongo.findWithOptions"),
    DB_DELETE("mongo.delete"),
    DB_COUNT("mongo.count"),

    REDIS_SET("reids.set"),
    REDIS_GET("reids.get"),
    REDIS_DELETE("reids.delete"),
    REDIS_EXPIRE("reids.expire"),
    REDIS_EXISTS("reids.exists"),
    ;

    private String address;

    Route(String address) {
        this.address = address;
    }

    public String getAddress() {
        return address;
    }
}
