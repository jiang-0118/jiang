package com.softeem.controller;

import com.softeem.service.IBalanceService;
import com.softeem.util.ResultInfo;
import com.softeem.vo.BalanceInfo;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/balance")
public class BalanceController {
    @Resource
    private IBalanceService iBalanceService;
    @Resource
    private HttpServletRequest request;

    /**
     * 完成扣减余额接口
     * @param balanceInfo
     * @return
     */
    @RequestMapping("/deductBalance")
    public ResultInfo deductBalance(@RequestBody BalanceInfo balanceInfo){
        return iBalanceService.deductBalance(balanceInfo,request.getHeader("token"));
    }

}
