package com.fanhq.example;

import com.fanhq.example.model.User;
import com.fanhq.example.repository.UserRepository;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

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
    public void delete() {
        userRepository.deleteAll();
    }


    @Test
    public void save() {
        User user1 = new User();
        user1.setId(1L);
        user1.setAge(18);
        user1.setCreateTime(new Date());
        user1.setDescription("Java开发工程师");
        user1.setName("张三");
        User user = userRepository.save(user1);
        System.out.println(user);
        User user2 = new User();
        user2.setId(2L);
        user2.setAge(18);
        user2.setCreateTime(new Date());
        user2.setDescription("测试工程师");
        user2.setName("李四");
        userRepository.save(user2);
        User user3 = new User();
        user3.setId(3L);
        user3.setAge(18);
        user3.setCreateTime(new Date());
        user3.setDescription("运维工程师");
        user3.setName("王二麻子");
        userRepository.save(user3);
    }

    @Test
    public void queryAll() {
        Iterable<User> searchResult = userRepository.findAll();
        Iterator<User> iterator = searchResult.iterator();
        List<User> list = new ArrayList<>();
        while (iterator.hasNext()) {
            list.add(iterator.next());
        }
        System.out.println(list.size());
    }

    @Test
    public void query() {
        //完全匹配
        QueryBuilder termQuery = QueryBuilders.termQuery("description", "运维工程师");
        userRepository.search(termQuery).forEach(x -> {
            System.out.println(x.getName());
        });
        //模糊匹配
        QueryBuilder wildcardQuery = QueryBuilders.wildcardQuery("description", "*开发工程师");
        userRepository.search(wildcardQuery).forEach(x -> {
            System.out.println(x.getName());
        });
        //复合查询
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        boolQueryBuilder.must(termQuery);
        boolQueryBuilder.must(wildcardQuery);
        userRepository.search(boolQueryBuilder).forEach(x -> {
            System.out.println(x.getName());
        });
    }

    @Test
    public void pageQuery() {
        QueryBuilder queryBuilder = QueryBuilders.termQuery("description", "运维工程师");
        Pageable pageable = PageRequest.of(0, 10);
        SearchQuery searchQuery = new NativeSearchQueryBuilder().withPageable(pageable)
                .withQuery(queryBuilder).build();
        Page<User> page = userRepository.search(searchQuery);
        List<User> users = page.getContent();
        System.out.println(users.size());
    }
}
