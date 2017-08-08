package space.aiyo.steam.aop;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 监听发出的http请求相关的信息
 * Created by Yo on 2017/6/17.
 */
@Aspect
@Component
public class HttpRequestSentAspect {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Pointcut("execution(public * space.aiyo.util.HttpUtil.send*(..))")
    public void methods(){
        System.out.println("methods");
    }

    @Before("methods()")
    public void doBefore(){
        System.out.println("doBefore");
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();


        HttpServletRequest request = attributes.getRequest();
        HttpServletResponse response =attributes.getResponse();
        logger.info("request url" + request.getRequestURL());
        logger.info("response status" + response.getStatus());

    }
    @After("methods()")
    public void doAfter(){
        System.out.println("doAfter");
    }
}
