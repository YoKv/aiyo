package space.aiyo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import space.aiyo.repository.HeroRepository;

/**
 * CREATE BY Yo ON 2018/1/19 23:48
 */
@SpringBootApplication
public class Application implements CommandLineRunner {

  @Autowired
  private HeroRepository repository;

  public static void main(String[] args) {
    //非web环境
    new SpringApplicationBuilder(Application.class).web(false).run(args);
  }

  @Override
  public void run(String... strings) throws Exception {
    //线程阻塞
//    Thread.currentThread().join();
    System.out.println("aiyo-service-data startup");
  }
}
