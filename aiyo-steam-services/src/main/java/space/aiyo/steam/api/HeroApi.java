package space.aiyo.steam.api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import space.aiyo.steam.entity.Dota2HeroEntity;

/**
 * Created by yo on 2017/6/1.
 */
@RestController
public class HeroApi {

    @RequestMapping()
    public Dota2HeroEntity getHeros(){

        return null;
    }
}
