package com.softeem.controller;

import com.softeem.service.IGoodsService;
import com.softeem.util.ResultInfo;
import com.softeem.vo.TableId;
import com.softeem.vo.UsernameAndPassword;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Author: JY
 * @CreateTime: 2024-08-07
 * @Description:
 * @Version: 1.0
 */
@RequestMapping("/good")
@RestController
public class GoodsController {
    @Resource
    private IGoodsService goodsService;
    @PostMapping("/findGoodsByIds")
    public ResultInfo findGoodsByIds(@RequestBody TableId ids){
        return goodsService.findGoodsByIds(ids);
    }
}
