package com.softeem.config;

import com.softeem.entity.TEcommerceUser;
import com.softeem.mapper.TEcommerceUserMapper;
import com.softeem.util.AssertUtil;
import com.softeem.vo.CurrentUserInfo;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Author: JY
 * @CreateTime: 2024-08-07
 * @Description:
 * @Version: 1.0
 */
@Service
public class JwtUserDetailsService implements UserDetailsService {
    @Resource
    private TEcommerceUserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        TEcommerceUser currentUser= userMapper.findByUsername(username);
        AssertUtil.isNotNull(currentUser,"用户未找到");
        return new CurrentUserInfo(currentUser.getId(), currentUser.getUsername(), currentUser.getPassword());
    }
}
