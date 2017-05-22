package space.aiyo.steam.services.impl;

import org.springframework.stereotype.Component;
import space.aiyo.steam.services.RmiDemoService;

import javax.annotation.PostConstruct;

/**
 * Created by yo on 2017/5/22.
 */
@Component("RmiDemoServiceImpl")
public class RmiDemoServiceImpl  implements RmiDemoService {

    @Override
    public String serviceDemo(){
        return "serviceDemo方法";
    }

    /**
     * 初始化
     * @return
     */
    @PostConstruct
    public String init(){

        return "";
    }
}
