package com.fanhq.example.repository;

import com.fanhq.example.model.User;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @author fanhaiqiu
 * @date 2019/5/21
 */
public interface UserRepository extends ElasticsearchRepository<User, Long> {

}
