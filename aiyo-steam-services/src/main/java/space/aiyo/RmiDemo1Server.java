package space.aiyo;

/**
 * Created by yo on 2017/5/22.
 */

import org.springframework.boot.SpringApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.remoting.rmi.RmiServiceExporter;
import space.aiyo.steam.services.external.RmiDemo1Service;
import space.aiyo.steam.services.external.impl.RmiDemo1ServiceImpl;

/**
 * springboot 封装的rmi
 */
//@SpringBootApplication(scanBasePackages = "space.aiyo.steam.services")
public class RmiDemo1Server extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(RmiDemo1Server.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(RmiDemo1Server.class);
    }

    @Bean
    public static RmiServiceExporter initRmiServiceExporter(RmiDemo1ServiceImpl rmiDemo1ServiceImpl) {
        RmiServiceExporter exporter = new RmiServiceExporter();
        exporter.setServiceInterface(RmiDemo1Service.class);
        exporter.setServiceName("rmiDemo1Service");
        exporter.setService(rmiDemo1ServiceImpl);
        exporter.setRegistryPort(5656);
        return exporter;
    }

}
