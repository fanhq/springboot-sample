package com.fanhq.example.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fanhq.example.Application;
import com.fanhq.example.entity.User;
import com.fanhq.example.mapper.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Date;
import java.util.List;

/**
 * @author fanhaiqiu
 * @date 2019/3/25
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class UserServiceImplTest {

    @Autowired
    private IUserService userService;

    @Autowired
    private UserMapper userMapper;

    @Test
    public void userServiceTest() {
        User user = userService.getById(1);
        System.out.println(user.getId());
    }

    @Test
    public void userMapperTest(){
        User user = userMapper.selectById(1);
        System.out.println(user.getId());
    }

    @Test
    public void wraperTest(){
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.gt("create_time", new Date(System.currentTimeMillis()));
        List<User> users = userMapper.selectList(queryWrapper);
        System.out.println(users.size());
    }
}