package space.aiyo.test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import space.aiyo.core.services.DotaHeroService;

/**
 * 英雄测试
 * Created by yo on 2017/5/27.
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class HeroTest {
    long startTime = 0;
    long endTime = 0;

    @Autowired
    DotaHeroService dotaHeroService;

    @Before
    public void before() {
        startTime = System.currentTimeMillis();
    }

    @Test
    public void test() {

        System.out.println(dotaHeroService.getHero(1,"",""));
        System.out.println(dotaHeroService.getHero(0,"npc_dota_hero_axe",""));
        System.out.println(dotaHeroService.getHero(0,"","米波"));


    }

    @After
    public void after() {
        endTime = System.currentTimeMillis();
        System.out.println("test time spend: " + String.valueOf(endTime - startTime) + "ms");
    }
}
