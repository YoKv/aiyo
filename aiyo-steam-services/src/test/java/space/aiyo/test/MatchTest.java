package space.aiyo.test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Example;
import org.springframework.test.context.junit4.SpringRunner;
import space.aiyo.database.mongoDB.dao.DotaMatchDao;
import space.aiyo.database.mongoDB.entity.match.DotaMatchEntity;
import space.aiyo.steam.services.DotaMatchService;

import java.util.List;

/**
 * Created by Yo on 2017/6/17.
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class MatchTest {

    @Autowired
    DotaMatchService dotaMatchService;
    @Autowired
    DotaMatchDao dotaMatchDao;

    @Test
    public void test() {
//        long sequenceNumber = dotaMatchService.getRecentSequenceNumber();
//        endTime = System.currentTimeMillis();
//        System.out.println("sequenceNumber time spend: " + String.valueOf(endTime - startTime) + "ms");
//        if (sequenceNumber == 0) {
//            sequenceNumber = DotaContsant.FIRST_MATCH_SEQ_NUM;//从7.00版本开始
//        }
//        dotaMatchService.saveMatchFromSteamByMatchSeqNum(sequenceNumber);

        DotaMatchEntity matchEntity = new DotaMatchEntity();
//        matchEntity.setMatchSeqNum(2478669176L);
        matchEntity.setId(2478669176L);
        Example<DotaMatchEntity> example = Example.of(matchEntity);
        List<DotaMatchEntity> list = dotaMatchDao.findAll(example);

        System.out.println(list);
        

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
