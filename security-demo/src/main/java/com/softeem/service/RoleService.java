package com.softeem.service;

import com.softeem.mapper.TEcommerceRoleMapper;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @Author: JY
 * @CreateTime: 2024-08-06
 * @Description:
 * @Version: 1.0
 */
@Service
public class RoleService {
    @Resource
    private TEcommerceRoleMapper roleMapper;
    public List<GrantedAuthority> getRoles(int userId){
        String userRole = roleMapper.findByUserId(userId);
        SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(userRole);
        ArrayList<GrantedAuthority> role = new ArrayList<GrantedAuthority>();
        role.add(simpleGrantedAuthority);
        return role;
    }
}
