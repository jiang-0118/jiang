package com.softeem.config;

import com.softeem.vo.CurrentUserInfo;

/**
 * @Author: JY
 * @CreateTime: 2024-08-12
 * @Description:
 * @Version: 1.0
 */
public class UserContext {
    private static ThreadLocal<CurrentUserInfo> local=new ThreadLocal<>();
    public static CurrentUserInfo get(){
        return local.get();
    }
    public static void set(CurrentUserInfo userInfo){
         local.set(userInfo);
    }
    public static void clear(){
        local.remove();
    }
}
