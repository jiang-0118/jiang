package com.softeem.service.impl;

import cn.hutool.crypto.digest.MD5;
import com.softeem.common.ResultInfo;
import com.softeem.common.ResultInfoUtil;
import com.softeem.dao.UserDao;
import com.softeem.entity.User;
import com.softeem.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserDao userDao;

    @Override
    public ResultInfo login(String username, String password) {
        // 参数校验
        // 加密密码
        return ResultInfoUtil.buildSuccess
                ("登录成功",userDao.findByUsernameAndPassword(username,
                        MD5.create().digestHex(password)));
    }
    @Override
    public ResultInfo register(String username, String password) {
        int i = userDao.addUser(username, MD5.create().digestHex(password));
        if (i==1){
            return ResultInfoUtil.buildSuccess("注册成功","username");
        }
        return ResultInfoUtil.buildError("注册失败");
    }
}
