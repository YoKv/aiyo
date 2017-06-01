package space.aiyo.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.remoting.rmi.RmiProxyFactoryBean;
import space.aiyo.steam.services.RmiDemo1Service;

/**
 * Created by Yo on 2017/5/22.
 */
@SpringBootApplication(scanBasePackages = "space.aiyo.demo")
public class RmiDemo1Client extends SpringBootServletInitializer{
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(RmiDemo1Client.class);
    }

    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(RmiDemo1Client.class, args);
        //获取方法
        RmiDemo1Service bean = run.getBean(RmiDemo1Service.class);
        //调用接口方法，实际上是远程方法
        System.out.println(bean.serviceDemo("这是客户端调用  "));

    }
    @Bean
    public RmiProxyFactoryBean rmiProxyFactoryBean() {
        RmiProxyFactoryBean rmiProxyFactoryBean = new RmiProxyFactoryBean();
        rmiProxyFactoryBean.setServiceUrl("rmi://192.168.0.104:5656/rmiDemo1Service");
        rmiProxyFactoryBean.setServiceInterface(RmiDemo1Service.class);
        return rmiProxyFactoryBean;
    }


}