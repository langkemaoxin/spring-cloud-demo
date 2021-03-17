package com.lagou.edu;

import com.netflix.hystrix.contrib.metrics.eventstream.HystrixMetricsStreamServlet;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;


/**
 * @Target(ElementType.TYPE)
 * @Retention(RetentionPolicy.RUNTIME)
 * @Documented
 * @Inherited
 * @SpringBootApplication
 * @EnableDiscoveryClient
 * @EnableCircuitBreaker public @interface SpringCloudApplication {
 * }
 * <p>
 * 这里也可以使用 @SpringCloudApplication注解
 * 这个注解打包了一堆注解
 */
//@EnableHystrix ==> 开启断路器
@EnableCircuitBreaker  //==> 开启断路器，适用性更广
@EnableDiscoveryClient //==> 开启注册中心客户端通用型
@SpringBootApplication

//@SpringCloudApplication

public class AutodeliverApplicaton8091 {

    public static void main(String[] args) {
        SpringApplication.run(AutodeliverApplicaton8091.class, args);
    }

    //使用Resttemple进行远程调用,先对注入该对象
    @LoadBalanced
    @Bean
    public RestTemplate getRestTemplete() {
        return new RestTemplate();
    }


    //在被监测的微服务中注册监控servlet
    @Bean
    public ServletRegistrationBean getServlet() {
        HystrixMetricsStreamServlet streamServlet = new  HystrixMetricsStreamServlet();
        ServletRegistrationBean registrationBean = new   ServletRegistrationBean(streamServlet);
        registrationBean.setLoadOnStartup(1);
        registrationBean.addUrlMappings("/actuator/hystrix.stream");
        registrationBean.setName("HystrixMetricsStreamServlet");
        return registrationBean;
    }
}
