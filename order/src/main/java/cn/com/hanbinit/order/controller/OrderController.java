package cn.com.hanbinit.order.controller;

import cn.com.hanbinit.order.service.UserService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class OrderController {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private UserService userService;

    @HystrixCommand(fallbackMethod = "getUserInfoFailure")
    @GetMapping("/users/{userId}")
    public String getUserInfo(@PathVariable Long userId){
        System.out.printf("这里要使用Ribbon来远程调用customer服务获取用户信息");
        String userInfoStr = restTemplate.getForObject("http://customer/users/" + userId, String.class);
        return userInfoStr;
    }

    /**
     * fallback的方法入参和返回都应该和@HystrixCommand注解的接口一致
     * @param userId
     * @return
     */
    public String getUserInfoFailure(Long userId){
        System.out.println("熔断咯");
        return "我是用来充数的";
    }



    @GetMapping("/feign/users/{userId}")
    public String getUserInfoWithFeign(@PathVariable Long userId){
        System.out.printf("这里要使用Feign来远程调用customer服务获取用户信息");
        String userInfoStr = userService.getUserInfoByUserId(userId);
        return userInfoStr;
    }
}
