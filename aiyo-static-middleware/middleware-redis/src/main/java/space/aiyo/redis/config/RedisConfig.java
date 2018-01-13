package space.aiyo.redis.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import org.redisson.Redisson;
import org.redisson.api.RExecutorService;
import org.redisson.api.RLock;
import org.redisson.api.RMap;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

public class RedisConfig {

  static {

    InputStream inputStream = Object.class.getResourceAsStream("/redis-config.properties");
    Properties p = new Properties();
    try {
      p.load(inputStream);
    } catch (IOException e1) {
      e1.printStackTrace();
    }
    System.out.println(p.getProperty("host") + ",port:" + p.getProperty("port"));
  }

  public static void main(String[] args) {

  }

  public void congif() {

    Config config = new Config();
    config.useSingleServer().setAddress("redis://127.0.0.1:6379");
    RedissonClient client = Redisson.create(config);

    RMap<String, String> map = client.getMap("myMap");

    RLock lock = client.getLock("myLock");

    RExecutorService executor = client.getExecutorService("myExecutorService");


  }
}
