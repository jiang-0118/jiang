package com.softeem.service.impl;

import com.softeem.entity.TEcommerceBalance;
import com.softeem.mapper.TEcommerceBalanceMapper;
import com.softeem.service.IBalanceService;
import com.softeem.util.AssertUtil;
import com.softeem.util.JwtUtil;
import com.softeem.util.ResultInfo;
import com.softeem.util.ResultInfoFactory;
import com.softeem.vo.BalanceInfo;
import com.softeem.vo.CurrentUserInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @Author: JY
 * @CreateTime: 2024-08-12
 * @Description:
 * @Version: 1.0
 */
@Service
@Slf4j
public class BalanceService implements IBalanceService {
    @Resource
    private TEcommerceBalanceMapper balanceMapper;
    @Transactional(rollbackFor = Exception.class)
    @Override
    public ResultInfo deductBalance(BalanceInfo balanceInfo, String token) {
        //校验参数
        AssertUtil.isNotNull(balanceInfo,"账户信息不可为空");
        AssertUtil.isNotEmpty(token,"用户凭证不可为空");
        //通过凭证获取当前用户信息
        CurrentUserInfo currentUserInfo = JwtUtil.parseToken(token);
        //如果用户没有账户，则给用户创建新账户
        TEcommerceBalance balance= balanceMapper.selectById(currentUserInfo.getId());
        if (balance==null){
            TEcommerceBalance createBalance = new TEcommerceBalance();
            createBalance.setUserId(currentUserInfo.getId());
            createBalance.setBalance(0L);
            balanceMapper.insert(createBalance);
        }else {
            //校验用户的账户是否支持这次支付
            Long deduct = balanceInfo.getBalance();
            log.info("扣减金额：{}",deduct);
            Long userBalance = balance.getBalance();
            log.info("用户余额：{}",userBalance);
            AssertUtil.isTrue(userBalance<deduct,"用户余额不支持这次支付");
            //扣减余额
            balance.setBalance(userBalance-deduct);
            balanceMapper.update(balance.getBalance(),balance.getUserId());
            return ResultInfoFactory.buildSuccess("账户扣减成功");
        }
        return ResultInfoFactory.buildError("账户扣减失败");
    }
}
