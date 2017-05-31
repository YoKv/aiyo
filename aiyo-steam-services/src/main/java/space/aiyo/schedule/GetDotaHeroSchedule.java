package space.aiyo.schedule;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Created by Yo on 2017/5/31.
 */

@Component
public class GetDotaHeroSchedule {

    @Scheduled(initialDelay=1000, fixedRate=6000)
//    @Scheduled(fixedDelay = 6000)
//    @Scheduled(fixedRate = 6000)
//    @Scheduled(cron="*/6 * * * * ?")
    public void getDotaHero(){

    }
}
