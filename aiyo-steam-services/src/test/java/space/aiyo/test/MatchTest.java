package space.aiyo.test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import space.aiyo.steam.services.DotaMatchService;

/**
 * Created by Yo on 2017/6/17.
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class MatchTest {

    @Autowired
    DotaMatchService dotaMatchService;

    @Test
    public void test() {
//        long sequenceNumber = dotaMatchService.getRecentSequenceNumber();
//        endTime = System.currentTimeMillis();
//        System.out.println("sequenceNumber time spend: " + String.valueOf(endTime - startTime) + "ms");
//        if (sequenceNumber == 0) {
//            sequenceNumber = DotaContsant.FIRST_MATCH_SEQ_NUM;//从7.00版本开始
//        }
//        dotaMatchService.saveMatchFromSteamByMatchSeqNum(sequenceNumber);

        endTime = System.currentTimeMillis();
        System.out.println("saveMatchFromSteamByMatchSeqNum time spend: " + String.valueOf(endTime - startTime) + "ms");
    }

    private long startTime = 0;
    private long endTime = 0;

    @Before
    public void before() {
        startTime = System.currentTimeMillis();
    }


    @After
    public void after() {
        endTime = System.currentTimeMillis();
        System.out.println("test time spend: " + String.valueOf(endTime - startTime) + "ms");
    }
}
