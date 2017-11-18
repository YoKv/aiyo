package entity.dota;

/**
 * Dota2英雄
 * Created by Yo on 2017/5/26.
 */
public class DotaHeroEntity {

    private int id;//id，以字母排序
    private String name;//全名
    private String localizedName;//中文名zh

    /**
     * 无参构造方法
     * 兼容fastjson
     */
    public DotaHeroEntity() {
    }

    public DotaHeroEntity(int id, String name, String localizedName) {
        this.id = id;
        this.name = name;
        this.localizedName = localizedName;
    }

    @Override
    public String toString() {
        return String.format("DotaHeroEntity[id=%base, name='%base', localizedName='%base']", id, name, localizedName);
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

    public String getLocalizedName() {
        return localizedName;
    }

    public void setLocalizedName(String localizedName) {
        this.localizedName = localizedName;
    }
}
