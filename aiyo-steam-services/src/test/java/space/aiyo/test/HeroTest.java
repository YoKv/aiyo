package space.aiyo.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import space.aiyo.steam.entity.Dota2HeroEntity;
import space.aiyo.steam.services.inside.HeroService;

import java.util.List;

/**
 * Created by yo on 2017/5/27.
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class HeroTest {

    @Autowired  HeroService heroService;

    @Test
    public void test() {
        List<Dota2HeroEntity> h = heroService.getHeroFromSteamApi();
        System.out.println(h);
        System.out.println("test start");
    }
}
