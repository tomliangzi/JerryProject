package com.jerry.exception;

import com.jerry.enums.ResultEnum;

/**
 * @program: sell
 * @description:
 * @author: Jerry
 * @create: 2019-04-19 13:00
 **/


public class SellException  extends  RuntimeException{


    private Integer code;

    public SellException(ResultEnum resultEnum) {
        super(resultEnum.getMessage());

        this.code=resultEnum.getCode();
    }
}
