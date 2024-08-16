package com.softeem.controller;

import com.softeem.service.IGoodsService;
import com.softeem.util.ResultInfo;
import com.softeem.util.ResultInfoFactory;
import com.softeem.vo.DeductGoodsInventory;
import com.softeem.vo.TableId;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;


/**
 * @Author: JY
 * @CreateTime: 2024-08-08
 * @Description:
 * @Version: 1.0
 */
@RestController
@RequestMapping("/goods")
public class GoodsController {
    @Resource
    private HttpServletRequest request;
    @Resource
    private IGoodsService iGoodsService;

    @RequestMapping("/list")
    public ResultInfo list(@RequestBody TableId ids){
        return iGoodsService.findGoodsByIds(ids,request.getHeader("token"));
    }
    /**
     * 完成扣减库存接口
     * @param deductGoodsInventories
     * @return
     */
    @RequestMapping("/deductGoods")
    public ResultInfo deductGoodsInventory(@RequestBody List<DeductGoodsInventory> deductGoodsInventories){
        return iGoodsService.deductGoodsInventory(deductGoodsInventories,request.getHeader("token"));
    }
}
