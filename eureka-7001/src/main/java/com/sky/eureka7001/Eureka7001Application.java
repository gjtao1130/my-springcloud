package com.sky.eureka7001;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer  // EurekaServer服务器端启动类,接受其它微服务注册进来
@EnableAutoConfiguration(exclude = DataSourceAutoConfiguration.class)
@SpringBootApplication
public class Eureka7001Application {

    public static void main(String[] args) {
        SpringApplication.run(Eureka7001Application.class, args);
    }

}
