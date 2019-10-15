package cn.com.hanbinit.customer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication // 使用Spring Boot构建的项目都应该添加这个注解，它可以告诉框架，这个类是项目的引导类
public class CustomerApplication {

    public static void main(String[] args) {
        SpringApplication.run(CustomerApplication.class, args); // 启动服务
    }

}

