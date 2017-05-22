package space.aiyo.steam.services;

/**
 * Created by yo on 2017/5/22.
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.rmi.RmiServiceExporter;
import space.aiyo.steam.services.impl.RmiDemoServiceImpl;


@Configuration
public class RmiConfig {

    @Autowired
    @Qualifier("RmiDemoServiceImpl")
    private RmiDemoServiceImpl rmiDemoServiceImpl;

    @Bean
    public RmiServiceExporter initRmiServiceExporter(){
        RmiServiceExporter exporter=new RmiServiceExporter();
        exporter.setServiceInterface(RmiDemoService.class);
        exporter.setServiceName("rmiService");
        exporter.setService(rmiDemoServiceImpl);
        exporter.setServicePort(6666);
        return exporter;
    }

}
