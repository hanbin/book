package cn.com.hanbinit.customer.controller;

import cn.com.hanbinit.customer.model.User;
import cn.com.hanbinit.customer.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RefreshScope
@RestController
public class UserController {

    @Value("${name:hanbin}")
    private String name;

    @Value("${age:}")
    private Long age;

    @Autowired
    private UserService userService;

    @GetMapping("/showinfo")
    public String showNameAndAge(){
        return "name: " + name + ", age: " + age;
    }

    @GetMapping("/users/{userId}")
    public User getUserInfoByUserId(@PathVariable Integer userId){
        return userService.getUserById(userId);
    }

    @PostMapping("/users")
    public Boolean saveUser(@RequestBody User user){
        return userService.create(user.getNickname(), user.getAge());
    }

    @GetMapping("/users")
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }

    @DeleteMapping("/users/{userId}")
    public Boolean deleteUserById(@PathVariable Integer userId){
        return userService.deleteById(userId);
    }
}

