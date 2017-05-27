package space.aiyo.steam.entity;

import org.springframework.data.annotation.Id;

/**
 * Dota2游戏装备
 * Created by Yo on 2017/5/26.
 */
public class Dota2ItemEntity {
    @Id
    private int id;
    private String name;//全名
    private int cost;//价格
    private int secret_shop;//神秘商店 0否1是
    private int side_shop;//边路商店 0否1是
    private int recipe;//卷轴 0否1是
    private String localized_name;//中文名zh

    public Dota2ItemEntity(int id, String name, int cost, int secret_shop, int side_shop, int recipe, String localized_name) {
        this.id = id;
        this.name = name;
        this.cost = cost;
        this.secret_shop = secret_shop;
        this.side_shop = side_shop;
        this.recipe = recipe;
        this.localized_name = localized_name;
    }

    @Override
    public String toString() {
        return String.format(
                "Dota2HeroEntity[id=%s, name='%s', cost='%s', localized_name='%s']",
                id, name, cost, localized_name);
    }
}