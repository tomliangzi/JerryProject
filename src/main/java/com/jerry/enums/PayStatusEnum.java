package com.jerry.enums;

/**
 * @program: sell
 * @description:
 * @author: Jerry
 * @create: 2019-04-19 19:43
 **/


public enum  PayStatusEnum implements CodeEnum{

    WAIT(0, "等待支付"),
    SUCCESS(1, "支付成功") ;

     PayStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }




    private Integer code;

    private String message;


    @Override
    public Integer getCode() {
        return code;
    }

}
