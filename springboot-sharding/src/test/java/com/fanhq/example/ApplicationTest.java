package com.fanhq.example;

import com.fanhq.example.entity.OrderEntity;
import com.fanhq.example.repository.OrderRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;

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
            order.setUserId(i);
            order.setStatus("ordered");
            orderRepository.save(order);
        }
        assert true;
    }

}