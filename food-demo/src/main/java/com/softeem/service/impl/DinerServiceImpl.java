package com.softeem.service.impl;

import cn.hutool.crypto.digest.MD5;
import com.softeem.entity.TDiners;
import com.softeem.mapper.TDinersMapper;
import com.softeem.service.IDinerService;
import com.softeem.util.AssertUtil;
import com.softeem.util.JwtUtil;
import com.softeem.util.ResultInfo;
import com.softeem.util.ResultInfoFactory;
import com.softeem.vo.CurrentUserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

/**
 * @Title: DinerService
 * @Author Jiang
 * @Package com.softeem.service.impl
 * @Date 2024/7/17 9:41
 * @description:
 */
@Service
public class DinerServiceImpl implements IDinerService {
    @Autowired
    private TDinersMapper tDinersMapper;
    @Override
    public ResultInfo login(String username, String password) {
        AssertUtil.isNotEmpty(username,"用户名不可为空");
        AssertUtil.isNotEmpty(password,"密码不可为空");
        TDiners currentUser = tDinersMapper.findByUsername(username);
        AssertUtil.isNotNull(currentUser,"用户不存在");
        AssertUtil.isTrue(!currentUser.getPassword().equals(MD5.create().digestHex(password)),"密码有误");
        String token = JwtUtil.generatorToken(new CurrentUserInfo(currentUser.getId(), currentUser.getUsername()));

        return ResultInfoFactory.buildSuccess(token);
    }
}
