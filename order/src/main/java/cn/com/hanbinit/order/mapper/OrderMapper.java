package cn.com.hanbinit.order.mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import cn.com.hanbinit.order.model.Order;

@Mapper
public interface OrderMapper {

    List<Order> findAll();

    @Insert("insert into tb_order(title,create_date, create_by) values(#{title},#{createDate}, #{createBy})")
    Order save(@Param("title") String title, @Param("createDate") Date createDate, @Param("createBy") String createBy);
}
