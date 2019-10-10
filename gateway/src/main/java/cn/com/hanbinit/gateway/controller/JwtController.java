package cn.com.hanbinit.gateway.controller;

import cn.com.hanbinit.gateway.Account;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/jwt")
public class JwtController {
    private static final Logger logger = LoggerFactory.getLogger(JwtController.class);

    /**
     *
     * @param account 需要认证的用户信息
     * @return
     * @throws IOException
     */
    @PostMapping("/login")
    public String login(@RequestBody final Account account) {
        if(account.isEmpty()){
            return "输入账号信息不全，请重新输入";
        }
        if (verifyAccount(account)) {
           return Jwts.builder()
                   .setSubject("hanbin-subject") // 设置主体
                   .signWith(SignatureAlgorithm.HS512, "secretkey") // 测试代码，默认key为secretKey
                   .claim("custom-data", "t-data") // 添加自定义数据
                   .compact(); // 最终拼接JWT串
        } else {
            return "认证失败，不能生成JWT串";
        }
    }

    private Boolean verifyAccount(Account account) {
        String username = account.getUsername();
        String password = account.getPassword();
        if ("admin".endsWith(username) && "admin".equals(password)) {
            logger.info("认证通过");
            return true;
        } else {
            logger.warn("认证失败:{}", account);
            return false;
        }
    }
}
