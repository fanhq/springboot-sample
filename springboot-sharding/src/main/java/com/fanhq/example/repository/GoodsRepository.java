package com.fanhq.example.repository;

import com.fanhq.example.entity.GoodsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author fanhaiqiu
 * @date 2019/5/9
 */
public interface GoodsRepository extends JpaRepository<GoodsEntity, Long> {

}
