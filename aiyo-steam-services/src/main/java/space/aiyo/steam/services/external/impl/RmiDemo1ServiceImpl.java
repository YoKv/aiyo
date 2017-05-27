package space.aiyo.steam.services.external.impl;

import org.springframework.stereotype.Service;
import space.aiyo.steam.services.external.RmiDemo1Service;

/**
 * Created by yo on 2017/5/22.
 */
@Service()
public class RmiDemo1ServiceImpl implements RmiDemo1Service {

    @Override
    public String serviceDemo(String str) {
        return str + " 服务端serviceDemo方法";
    }
}
