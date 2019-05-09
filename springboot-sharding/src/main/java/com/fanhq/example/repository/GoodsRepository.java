package com.fanhq.example.repository;

import com.fanhq.example.entity.GoodsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author fanhaiqiu
 * @date 2019/5/9
 */
public interface GoodsRepository extends JpaRepository<GoodsEntity, Long> {
    /**
     * 查询
     *
     * @param goodsId1
     * @param goodsId2
     * @return
     */
    List<GoodsEntity> findAllByIdBetween(Long goodsId1,Long goodsId2);

    /**
     * 查询
     * @param goodsIds
     * @return
     */
    List<GoodsEntity> findAllByIdIn(List<Long> goodsIds);
}
