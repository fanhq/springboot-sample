package com.fanhq.example;

import com.fanhq.example.model.User;
import com.fanhq.example.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

/**
 * @author fanhaiqiu
 * @date 2019/2=5/21
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class ApplicationTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void save() {
        User user1 = new User();
        user1.setAge(18);
        user1.setCreateTime(new Date());
        user1.setDescription("Java开发工程师");
        user1.setName("张三");
        userRepository.save(user1);
        User user2 = new User();
        user2.setAge(18);
        user2.setCreateTime(new Date());
        user2.setDescription("测试工程师");
        user2.setName("李四");
        userRepository.save(user2);
        User user3 = new User();
        user3.setAge(18);
        user3.setCreateTime(new Date());
        user3.setDescription("运维工程师");
        user3.setName("王二麻子");
        userRepository.save(user3);
    }
}
