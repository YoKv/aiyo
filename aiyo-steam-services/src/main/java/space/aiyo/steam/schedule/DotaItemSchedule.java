package space.aiyo.steam.schedule;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import space.aiyo.steam.services.DotaItemService;

/**
 * 与dota游戏装备相关的定时任务
 * Created by Yo on 2017/5/31.
 */
@Component
public class DotaItemSchedule {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private DotaItemService dotaItemService;

    /**
     * 定时更新dota游戏装备信息
     */
//    @Scheduled(cron = "0 0 8 1 * *")  //每月1号的上午08:00触发
//    @Scheduled(initialDelay = 1000, fixedRate = 2505600000L) //启动更新一次，顺延一个月更新一次
    public void getDotaHero() {
        logger.info("定时任务，同步游戏装备信息");
        dotaItemService.saveItemFromSteamApi();
    }

}
