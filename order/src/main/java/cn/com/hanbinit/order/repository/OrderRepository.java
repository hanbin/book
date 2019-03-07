package cn.com.hanbinit.order.repository;

        import cn.com.hanbinit.order.model.Order;
        import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 继承JpaRepository接口，继承基本数据库操作能力
 */
public interface OrderRepository extends JpaRepository<Order, Long> {
}
