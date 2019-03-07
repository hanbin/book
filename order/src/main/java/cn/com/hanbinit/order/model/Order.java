package cn.com.hanbinit.order.model;

import javax.persistence.*;
import java.util.Date;

// @Entity 指明这个类是一个可以持久化的对象
@Entity
// @Table 指明了本类对应的数据库中表的信息
@Table(name = "tb_order")
public class Order {
    // @Id修饰的变量是主键
    @Id
    // @GeneratedValue 是一个简单的主键生成策略，在Mysql中指的是Auto-Increment
    @GeneratedValue
    private Long id;
    private String title;
    // 指明成员变量createDate对应的表字段为create_date且不能为空
    @Column(name="create_date", nullable = false)
    private Date createDate;
    // 指明成员变量createBy对应的表字段为create_by
    @Column(name="create_by", nullable = false)
    private String createBy;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = new Date();
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }
}
