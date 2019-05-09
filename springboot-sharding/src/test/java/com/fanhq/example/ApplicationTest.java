package com.fanhq.example;

import com.fanhq.example.entity.GoodsEntity;
import com.fanhq.example.repository.GoodsRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

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
    public void orderTest() {
        for(int i= 1 ; i <= 40 ; i ++){
            GoodsEntity goods = new GoodsEntity();
            goods.setGoodsId((long) i);
            goods.setGoodsName( "shangpin" + i);
            goods.setGoodsType((long) (i+1));
            goodsRepository.save(goods);
        }
        assert true;
    }
}