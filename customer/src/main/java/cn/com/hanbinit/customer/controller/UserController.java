package cn.com.hanbinit.customer.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RefreshScope
@RestController
public class UserController {

    @Value("${name}")
    private String name;

    @Value("${age}")
    private Long age;

    @GetMapping("/showinfo")
    public String showNameAndAge(){
        return "name: " + name + ", age: " + age;
    }

    @GetMapping("/users/{userId}")
    public String getUserInfoByUserId(@PathVariable Long userId){
        return "userInfo with userId = " + userId;
    }
}
