package space.aiyo.steam.schedule;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import space.aiyo.steam.services.DotaHeroService;

/**
 * 与dota英雄相关的定时任务
 * Created by Yo on 2017/5/31.
 */
@Component
public class DotaHeroSchedule {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private DotaHeroService dotaHeroService;

    @Autowired
    public DotaHeroSchedule(DotaHeroService dotaHeroService) {
        this.dotaHeroService = dotaHeroService;
    }

    /**
     * 定时更新dota英雄信息
     */
    @Scheduled(cron = "0 0 8 1 * *")  //每月1号的上午08:00触发
    public void getDotaHero() {
        logger.info("定时任务，同步英雄信息");
        dotaHeroService.saveHeroFromSteamApi();
    }

}
