package com.fanhq.example.repository;

import com.fanhq.example.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author fanhaiqiu
 * @date 2019/5/9
 */
public interface OrderRepository extends JpaRepository<OrderEntity, Long> {

    /**
     * 模糊查询
     * @param pattern
     * @return
     */
    List<OrderEntity> findByStatusIsLike(String pattern);

}
