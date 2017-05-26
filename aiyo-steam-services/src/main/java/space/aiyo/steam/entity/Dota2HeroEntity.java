package space.aiyo.steam.entity;

import org.springframework.data.annotation.Id;

/**
 * Dota2英雄
 * Created by Yo on 2017/5/26.
 */
public class Dota2HeroEntity {

    @Id
    public int id;//id，以字母排序
    public String name;//全名
    public String localized_name;//中文名zh

    public Dota2HeroEntity(int id, String name, String localized_name) {
        this.id = id;
        this.name = name;
        this.localized_name = localized_name;
    }

    @Override
    public String toString() {
        return String.format(
                "Dota2HeroEntity[id=%s, name='%s', localized_name='%s']",
                id, name, localized_name);
    }
}
