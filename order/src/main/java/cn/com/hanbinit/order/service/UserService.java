package cn.com.hanbinit.order.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("customer")
public interface UserService {

    @GetMapping("/users/{userId}")
    String getUserInfoByUserId(@PathVariable Long userId);

}
