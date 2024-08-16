package com.softeem.service;

import com.softeem.util.ResultInfo;
import com.softeem.vo.UsernameAndPassword;

/**
 * @Author: JY
 * @CreateTime: 2024-08-07
 * @Description:
 * @Version: 1.0
 */
public interface AuthorityService {
    public ResultInfo token(UsernameAndPassword usernameAndPassword);
}
