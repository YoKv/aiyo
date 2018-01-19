package space.aiyo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * CREATE BY Yo ON 2018/1/19 23:48
 */
@SpringBootApplication()
public class Application {

  //以非web方式部署
  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }
}
