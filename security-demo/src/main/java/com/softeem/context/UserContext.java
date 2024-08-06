package com.softeem.context;

import org.springframework.stereotype.Component;

/**
 * @Author: JY
 * @CreateTime: 2024-08-06
 * @Description:
 * @Version: 1.0
 */
@Component
public class UserContext {
    public static final ThreadLocal<Integer> userContext=new ThreadLocal<Integer>();
    public static void set(Integer id){
        userContext.set(id);
    }
    public static Integer get(){
       return userContext.get();
    }
    public static void remove(){
        userContext.remove();
    }
}
