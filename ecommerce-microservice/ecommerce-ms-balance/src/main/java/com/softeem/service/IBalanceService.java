package com.softeem.service;

import com.softeem.util.ResultInfo;
import com.softeem.vo.BalanceInfo;

public interface IBalanceService {
    /**
     * 扣减库存
     * @param balanceInfo 扣减余额
     * @param token 当前用户凭证
     * @return
     */

    ResultInfo deductBalance(BalanceInfo balanceInfo,String token);
}
