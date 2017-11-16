package space.aiyo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@SpringBootApplication()
@EnableScheduling
public class AiyoSteamServicesApplication {


    public static void main(String[] args) {
//       final ConfigurableApplicationContext applicationContext =
               SpringApplication.run(AiyoSteamServicesApplication.class, args);
//        DotaMatchService dotaMatchService = applicationContext.getBean(DotaMatchService.class);
//        long sequenceNumber = dotaMatchService.getRecentSequenceNumber();
//        dotaMatchService.recursion(sequenceNumber);
    }

    @GetMapping("/")
    public String hello() {
        return "Hello World";
    }

}



