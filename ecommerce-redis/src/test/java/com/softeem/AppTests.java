package com.softeem;

import com.softeem.model.vo.TableId;
import com.softeem.service.IGoodsService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.annotation.Id;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class AppTests {


    @Resource
    private IGoodsService goodsService;
    @Test
    public void test1(){
        List<TableId.Id> ids=new ArrayList<>();
        ids.add(new TableId.Id(13l));
        ids.add(new TableId.Id(14l));
        goodsService.findSimpleGoods(new TableId(ids));
    }
}
