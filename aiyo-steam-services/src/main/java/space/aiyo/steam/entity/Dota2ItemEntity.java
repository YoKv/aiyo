package space.aiyo.steam.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Dota2游戏装备
 * Created by Yo on 2017/5/26.
 */

@Document(collection="dotaItem")
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public int getSecret_shop() {
        return secret_shop;
    }

    public void setSecret_shop(int secret_shop) {
        this.secret_shop = secret_shop;
    }

    public int getSide_shop() {
        return side_shop;
    }

    public void setSide_shop(int side_shop) {
        this.side_shop = side_shop;
    }

    public int getRecipe() {
        return recipe;
    }

    public void setRecipe(int recipe) {
        this.recipe = recipe;
    }

    public String getLocalized_name() {
        return localized_name;
    }

    public void setLocalized_name(String localized_name) {
        this.localized_name = localized_name;
    }
}
