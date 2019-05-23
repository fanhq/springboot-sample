package com.fanhq.example;

import com.fanhq.example.mapper.UsersMapper;
import com.fanhq.example.model.Users;
import com.github.pagehelper.PageHelper;
import org.apache.commons.collections4.CollectionUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @author fanhaiqiu
 * @date 2019/5/9
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class ApplicationTest {

    @Autowired
    private UsersMapper usersMapper;

    @Test
    public void mapperTest() {
        List<Users> users = usersMapper.selectAll();
        assert CollectionUtils.isNotEmpty(users);

        Example example = Example.builder(Users.class).build();
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("name", "Tom");
        List<Users> usersList = usersMapper.selectByExample(example);
        assert CollectionUtils.isNotEmpty(usersList);

        //1
        PageHelper.startPage(2, 2);
        List<Users> page = usersMapper.selectAll();
        assert CollectionUtils.isNotEmpty(page);
    }

}