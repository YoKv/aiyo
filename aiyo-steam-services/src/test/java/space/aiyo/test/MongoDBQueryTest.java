package space.aiyo.test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import space.aiyo.steam.services.DotaItemService;
import space.aiyo.steam.services.DotaMatchService;

/**
 * mongo复杂查询
 * Created by Yo on 2017/6/11.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class MongoDBQueryTest {

    long startTime = 0;
    long endTime = 0;

    @Autowired
    DotaItemService dotaItemService;

    @Autowired
    DotaMatchService dotaMatchService;

    @Before
    public void before() {
        startTime = System.currentTimeMillis();
    }

    @Test
    public void test() {

//        System.out.println( dotaItemService.findById(12));
//        System.out.println( dotaMatchService.maxSeqNum());
        System.out.println( dotaMatchService.getRecentSequenceNumber());
    }

    @After
    public void after() {
        endTime = System.currentTimeMillis();
        System.out.println("test time spend: " + String.valueOf(endTime - startTime) + "ms");
    }
}
