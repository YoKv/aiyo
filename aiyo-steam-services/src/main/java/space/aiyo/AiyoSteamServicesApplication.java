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
        SpringApplication.run(AiyoSteamServicesApplication.class, args);
    }

    @GetMapping("/")
    public String hello() {
        return "Hello World";
    }

}
