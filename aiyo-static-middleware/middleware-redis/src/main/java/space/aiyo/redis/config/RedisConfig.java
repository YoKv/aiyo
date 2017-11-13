package space.aiyo.redis.config;

import org.redisson.Redisson;
import org.redisson.api.RExecutorService;
import org.redisson.api.RLock;
import org.redisson.api.RMap;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

public class RedisConfig {

    public void congif() {
        Config config = new Config();
        config.useSingleServer().setAddress("redis://127.0.0.1:6379");
        RedissonClient client = Redisson.create(config);

        RMap<String, String> map = client.getMap("myMap");

        RLock lock = client.getLock("myLock");

        RExecutorService executor = client.getExecutorService("myExecutorService");


    }
}
