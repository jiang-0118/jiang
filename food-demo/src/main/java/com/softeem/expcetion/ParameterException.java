package com.softeem.expcetion;

import com.softeem.constant.ApiConstant;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ParameterException extends RuntimeException {

    private int errorCode;
    public ParameterException(){
        super(ApiConstant.ERROR_MESSAGE);
        this.errorCode = ApiConstant.ERROR_CODE;
    }


    public ParameterException(int errorCode){
        super(ApiConstant.ERROR_MESSAGE);
        this.errorCode = errorCode;
    }

    public ParameterException(String errorMessage){
        super(errorMessage);
        this.errorCode = ApiConstant.ERROR_CODE;
    }

    public ParameterException(int errorCode, String errorMessage){
        super(errorMessage);
        this.errorCode = errorCode;
    }


}
