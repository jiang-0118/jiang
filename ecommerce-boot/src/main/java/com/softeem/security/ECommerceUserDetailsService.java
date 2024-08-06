package com.softeem.security;

import com.softeem.mapper.TEcommerceUserMapper;
import com.softeem.model.entity.TEcommerceUser;
import com.softeem.util.AssertUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

/**
 * @Title: ECommerceUserDetailsService
 * @Author Jiang
 * @Package com.softeem.security
 * @Date 2024/7/15 20:45
 * @description:自定义用户细节
 */
@Service
public class ECommerceUserDetailsService implements UserDetailsService {
    @Autowired
    private TEcommerceUserMapper userMapper;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        TEcommerceUser currentUser = userMapper.findUserByUsername(username);
        AssertUtil.isNotNull(currentUser,"未找到该用户");
        return new MyUserDetails(currentUser.getId(),
                currentUser.getUsername(),
                currentUser.getPassword(),
                Collections.emptyList());
    }
}
