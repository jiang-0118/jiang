package com.softeem.common;

public class ResultInfoUtil {

    public static ResultInfo buildSuccess(String message, Object data){
        return new ResultInfo(
                200,
                message,
                data
        );
    }

    public static ResultInfo buildError(String message){
        return new ResultInfo(
                -1,
                message,
                null
        );
    }
}
