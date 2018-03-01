package space.aiyo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

/**
 * CREATE BY Yo ON 2018/1/19 23:48
 */
@SpringBootApplication
public class Application implements CommandLineRunner {

  public static void main(String[] args) {
    //非web环境
    new SpringApplicationBuilder(Application.class).web(WebApplicationType.NONE).run(args);
  }

  @Override
  public void run(String... strings) throws Exception {
    //线程阻塞,实现不好 FIXME
    Thread.currentThread().join();
    System.out.println("aiyo-service-business startup");
  }
}
