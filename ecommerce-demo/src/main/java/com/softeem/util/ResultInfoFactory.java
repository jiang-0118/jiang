package com.softeem.util;

import cn.hutool.core.util.StrUtil;
import com.softeem.constant.ApiConstant;

public class ResultInfoFactory {

    private static <T>ResultInfo<T> build(Integer code,String message,T data){
        ResultInfo resultInfo = new ResultInfo(
                code==null? ApiConstant.SUCCESS_CODE:code,
                StrUtil.isBlank(message)?ApiConstant.SUCCESS_MESSAGE:message,
                data
        );
        return resultInfo;
    }

    public static <T>ResultInfo<T> buildSuccess(String message,T data){
        return build(ApiConstant.SUCCESS_CODE,message,data);
    }

    public static <T>ResultInfo<T> buildSuccess(T data){
        return build(ApiConstant.SUCCESS_CODE,null,data);
    }

    public static <T>ResultInfo<T> buildError(Integer code,String message){
        return build(code,message,null);
    }


    public static <T>ResultInfo<T> buildError(String message){
        return build(ApiConstant.ERROR_CODE,message,null);
    }


    public static <T>ResultInfo<T> buildError(){
        return build(ApiConstant.ERROR_CODE,ApiConstant.ERROR_MESSAGE,null);
    }




}
