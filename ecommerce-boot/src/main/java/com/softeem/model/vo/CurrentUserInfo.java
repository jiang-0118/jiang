package com.softeem.model.vo;

import com.softeem.security.MyUserDetails;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Collections;

/**
 * @Title: CurrentUserInfo
 * @Author Jiang
 * @Package com.softeem.vo
 * @Date 2024/7/15 18:47
 * @description:当前用户
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
/*Id vo类
* vo：值对象，避免将实体入参
* 1.实体中的属性过多，很多属性不需要作为参数
* 2.实体中往往会有一些敏感的属性，不应该暴露实体的结构
* 客户端-----vo-----接口
* */
public class CurrentUserInfo {
    private Long id;
    private String username;
    public MyUserDetails toDetails(){
        return new MyUserDetails(
                id,
                username,
                null,
                Collections.emptyList()
        );
    }
}
