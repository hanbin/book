package cn.com.hanbinit.customer.controller;

import cn.com.hanbinit.customer.model.User;
import cn.com.hanbinit.customer.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller // 这里使用@Controller注解，是因为这个文件中的接口需要进行页面跳转
public class UserViewController {

    @Autowired
    private UserService userService;

    /**
     * 根据用户Id现实用户信息
     * @param userId
     * @return
     */
    @GetMapping("/user/{userId}")
    public String userInfo(@PathVariable Integer userId, ModelMap map){
        User user = userService.getUserById(userId);
        // 将查询的user对象放入ModelMap中
        map.put("user", user);
        // 下面一行直接返回thymeleaf中的文件名（不带前缀）
        return "user_detail";

    }
}
