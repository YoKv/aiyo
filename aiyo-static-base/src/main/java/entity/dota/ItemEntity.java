package entity.dota;

/**
 * Dota2游戏装备
 * Created by Yo on 2017/5/26.
 */
public class ItemEntity {

    private int id;
    private String name;//全名
    private int cost;//价格
    private int secretShop;//神秘商店 0否1是
    private int sideShop;//边路商店 0否1是
    private int recipe;//卷轴 0否1是
    private String localizedName;//中文名zh

    /**
     * 无参构造方法
     * 兼容fastjson
     */
    public ItemEntity() {
    }

    public ItemEntity(int id, String name, int cost, int secretShop, int sideShop, int recipe, String localizedName) {
        this.id = id;
        this.name = name;
        this.cost = cost;
        this.secretShop = secretShop;
        this.sideShop = sideShop;
        this.recipe = recipe;
        this.localizedName = localizedName;
    }

    @Override
    public String toString() {
        return String.format("ItemEntity[id=%base, name='%base', cost='%base', localizedName='%base']", id, name, cost, localizedName);
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

    public int getSecretShop() {
        return secretShop;
    }

    public void setSecretShop(int secretShop) {
        this.secretShop = secretShop;
    }

    public int getSideShop() {
        return sideShop;
    }

    public void setSideShop(int sideShop) {
        this.sideShop = sideShop;
    }

    public int getRecipe() {
        return recipe;
    }

    public void setRecipe(int recipe) {
        this.recipe = recipe;
    }

    public String getLocalizedName() {
        return localizedName;
    }

    public void setLocalizedName(String localizedName) {
        this.localizedName = localizedName;
    }
}
