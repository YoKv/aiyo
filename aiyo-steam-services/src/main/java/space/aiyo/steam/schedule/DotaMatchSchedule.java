package space.aiyo.steam.schedule;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import space.aiyo.steam.services.DotaMatchService;

/**
 * dota比赛定时任务
 * Created by Yo on 2017/6/5.
 */

@Component
public class DotaMatchSchedule {
    //使用GetMatchHistoryBySequenceNum接口获取到详细的比赛信息 TODO
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private DotaMatchService dotaMatchService;

    /**
     * 定时更新dota游戏比赛信息
     */
//    @Scheduled(cron = "0 0 9 * * *")  //每天的上午09:00触发
    @Scheduled(fixedRate = 10000L) //10s更新一次
    public void getDotaHero() {
        logger.info("定时任务，同步游戏比赛信息");
        long sequenceNumber = dotaMatchService.getRecentSequenceNumber();
        System.out.println("------"+sequenceNumber);//FIXME
        if (sequenceNumber == 0){
            sequenceNumber = 2478669175L;//从7.00版本开始
        }
        dotaMatchService.saveMatchFromSteamApiBySequenceNumber(sequenceNumber);

    }
}
