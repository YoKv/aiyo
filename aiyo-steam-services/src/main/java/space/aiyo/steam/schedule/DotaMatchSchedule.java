package space.aiyo.steam.schedule;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import space.aiyo.steam.contsant.DotaContsant;
import space.aiyo.steam.services.DotaMatchService;

/**
 * dota比赛定时任务
 * Created by Yo on 2017/6/5.
 */

@Component
public class DotaMatchSchedule {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private DotaMatchService dotaMatchService;

    /**
     * 定时更新dota游戏比赛信息
     */
//    @Scheduled(cron = "0 0 9 * * *")  //每天的上午09:00触发
    @Scheduled(fixedRate = 10000L) //10s更新一次
    public void getDotaHero() {
        System.out.println(System.currentTimeMillis());
        logger.info("定时任务，同步游戏比赛信息");
        //获取本地最大的队列num
        long sequenceNumber = dotaMatchService.getRecentSequenceNumber();
        if (sequenceNumber == 0) {
            sequenceNumber = DotaContsant.FIRST_MATCH_SEQ_NUM;//从7.00版本开始
        }
        dotaMatchService.saveMatchFromSteamApiBySequenceNumber(sequenceNumber);

    }
}
