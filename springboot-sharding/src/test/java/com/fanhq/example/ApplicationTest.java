package com.fanhq.example;

import com.fanhq.example.entity.GoodsEntity;
import com.fanhq.example.repository.GoodsRepository;
import org.apache.commons.collections4.CollectionUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * @author fanhaiqiu
 * @date 2019/5/9
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class ApplicationTest {


    @Autowired
    private GoodsRepository goodsRepository;


    @Test
    public void goodsTest() {
        for (long i = 1; i <= 40L; i++) {
            GoodsEntity goods = new GoodsEntity();
            goods.setId((i));
            goods.setName("shopping" + i);
            goods.setType(i + 1);
            goodsRepository.save(goods);
        }
        assert true;
    }

    @Test
    public void select() {
        List<GoodsEntity> goods = goodsRepository.findAll();
        assert CollectionUtils.isEmpty(goods);
    }

    @Test
    public void delete() {
        goodsRepository.deleteAll();
    }

    @Test
    public void query1() {
        List<GoodsEntity> goods = goodsRepository.findAllByIdBetween(10L, 30L);
        assert CollectionUtils.isEmpty(goods);
    }

    @Test
    public void query2() {
        List<Long> goodsIds = new ArrayList<>();
        goodsIds.add(10L);
        goodsIds.add(15L);
        goodsIds.add(20L);
        goodsIds.add(25L);
        List<GoodsEntity> goods = goodsRepository.findAllByIdIn(goodsIds);
        assert CollectionUtils.isEmpty(goods);
    }

    @Test
    public void query3() {
        List<GoodsEntity> goods = goodsRepository.findByNameIsLike("%shop%");
        assert CollectionUtils.isEmpty(goods);
    }
}