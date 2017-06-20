package space.aiyo.steam.schedule;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import space.aiyo.steam.contsant.DotaContsant;
import space.aiyo.steam.services.DotaMatchService;

import java.time.LocalTime;

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
     * 定时任务是阻塞的，前一个未结束，不会开始下一个,符合需求
     */
//    @Scheduled(cron = "0 0 9 * * *")  //每天的上午09:00触发
//    运行几小时后，不再执行，没有任何信息,初步判断是 执行间隔太快，执行速度慢，执行时间久了，spring待执行的任务多到某个值，停止定时任务了
//    执行速度慢会有问题,选用其它方式实现定时任务 FIXME
    @Scheduled(fixedRate = 6000L) //更新频率
    public void getDotaHero() {
        logger.info("定时任务，开始同步游戏比赛信息"+ LocalTime.now());
        //获取本地最大的队列num
        long sequenceNumber = dotaMatchService.getRecentSequenceNumber();
        if (sequenceNumber == 0) {
            sequenceNumber = DotaContsant.FIRST_MATCH_SEQ_NUM;//从7.00版本开始
        }
        dotaMatchService.getMatchFromSteamApiByMatchSeqNum(sequenceNumber);

    }
}
