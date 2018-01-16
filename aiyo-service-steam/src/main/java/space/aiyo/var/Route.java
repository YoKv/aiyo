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
    DB_MATCH_FINDWITHOPTIONS("space.aiyo.data.match.findWithOptions"),

    //    REDIS_STRING_SET("space.aiyo.component.reids.string.set"),
//    REDIS_STRING_GET("space.aiyo.component.reids.string.get"),
//    REDIS_LIST_SET("space.aiyo.component.reids.list.set"),
//    REDIS_LIST_GET("space.aiyo.component.reids.list.get"),
//    REDIS_SET_SET("space.aiyo.component.reids.set.set"),
//    REDIS_SET_GET("space.aiyo.component.reids.set.get"),
//    REDIS_SORTSET_SET("space.aiyo.component.reids.sortSet.set"),
//    REDIS_SORTSET_GET("space.aiyo.component.reids.sortSet.get"),
//    REDIS_HASH_SET("space.aiyo.component.reids.hash.set"),
//    REDIS_HASH_GET("space.aiyo.component.reids.hash.get"),
    REDIS_SET("space.aiyo.component.reids.set"),
    REDIS_GET("space.aiyo.component.reids.get"),
    REDIS_DELETE("space.aiyo.component.reids.delete"),

    ;

    private String address;

    Route(String address) {
        this.address = address;
    }

    public String getAddress() {
        return address;
    }
}
