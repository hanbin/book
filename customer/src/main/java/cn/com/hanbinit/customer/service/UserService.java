package cn.com.hanbinit.customer.service;

import cn.com.hanbinit.customer.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * 创建用户
     * @param nickname
     * @param age
     */
    public Boolean create(String nickname, Integer age){
        int number = jdbcTemplate.update("insert into tb_user(nickname, age) values (?,?)", nickname, age);
        if(number == 1){
            return true;
        }
        return false;
    }

    /**
     * 根据用户Id删除单个用户
     * @param userId
     * @return
     */
    public Boolean deleteById(Integer userId){
        int number = jdbcTemplate.update("delete from tb_user where id = ?", userId);
        if(number == 1){
            return true;
        }
        return false;
    }

    /**
     * 根据Id获取单个用户信息
     * @param userId
     * @return
     */
    public User getUserById(Integer userId){
        return jdbcTemplate.queryForObject("select id, nickname, age from tb_user where id = ?", (resultSet, i) -> {
            User user = new User();
            user.setId(resultSet.getInt("id"));
            user.setNickname(resultSet.getString("nickname"));
            user.setAge(resultSet.getInt("age"));
            return user;
        }, userId);
    }

    /**
     * 获取所有用户列表
     * @return
     */
    public List<User> getAllUsers(){
        List<User> userList = jdbcTemplate.query("select *from tb_user", (resultSet, i) -> {
            User user = new User();
            user.setId(resultSet.getInt("id"));
            user.setNickname(resultSet.getString("nickname"));
            user.setAge(resultSet.getInt("age"));
            return user;
        });
        return userList;
    }
}
