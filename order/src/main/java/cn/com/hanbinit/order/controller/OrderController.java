package cn.com.hanbinit.order.controller;

import cn.com.hanbinit.order.model.Order;
import cn.com.hanbinit.order.repository.OrderRepository;
import cn.com.hanbinit.order.service.UserService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Date;
import java.util.List;

@RestController
public class OrderController {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private UserService userService;

    @Autowired
    private OrderRepository orderRepository;

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

    /**
     * 获取所有的用户
     * @return
     */
    @GetMapping("/orders")
    public List<Order> queryAllOrders(){
        return orderRepository.findAll();
    }

    /**
     * 新增用户
     * @param order
     * @return
     */
    @PostMapping("/orders")
    public Order createOrder(@RequestBody Order order){
        order.setCreateDate(new Date());
        return orderRepository.save(order);
    }
}
