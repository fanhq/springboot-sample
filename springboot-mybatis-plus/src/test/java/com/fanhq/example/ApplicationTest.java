package com.fanhq.example;

import com.fanhq.example.entity.User;
import com.fanhq.example.mapper.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Unit test for simple App.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void testSelect() {
        User user = userMapper.selectById(1);
        System.out.println(user.getId());
    }
}
