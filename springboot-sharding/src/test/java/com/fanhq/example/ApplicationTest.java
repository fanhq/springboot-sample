package com.fanhq.example;

import com.fanhq.example.entity.OrderEntity;
import com.fanhq.example.repository.OrderRepository;
import org.apache.commons.collections4.CollectionUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;
import java.util.List;

/**
 * @author fanhaiqiu
 * @date 2019/5/9
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class ApplicationTest {


    @Autowired
    private DataSource dataSource;

    @Autowired
    private OrderRepository orderRepository;

    @Test
    public void baseTest() {
        assert dataSource != null;
    }

    @Test
    public void orderTest(){
        for (long i = 1; i <= 40L; i++) {
            OrderEntity order = new OrderEntity();
            order.setId((i));
            order.setUserId(i + 1);
            order.setStatus("ordered");
            orderRepository.save(order);
        }
        assert true;
    }

    @Test
    public void queryTest(){
        List<OrderEntity> orders = orderRepository.findAll();
        assert CollectionUtils.isNotEmpty(orders);
    }

    @Test
    public void likeTest(){
        List<OrderEntity> orders = orderRepository.findByStatusIsLike("%order%");
        assert CollectionUtils.isNotEmpty(orders);
    }

}