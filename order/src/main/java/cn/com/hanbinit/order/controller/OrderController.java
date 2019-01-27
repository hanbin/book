package cn.com.hanbinit.order.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {

    @GetMapping("/users/{userId}")
    public String getUserInfo(@PathVariable Long userId){
        System.out.printf("这里要使用Ribbon来远程调用customer服务获取用户信息");

        return "还没有实现";
    }
}
