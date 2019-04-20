package com.jerry.enums;

/**
 * @program: sell
 * @description: 枚举类型 用于说明数字的含义
 * @author: Jerry
 * @create: 2019-04-18 22:17
 **/


public enum  ProductStatusEnum  {;


    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    private Integer code;

    private String message;



    ProductStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }



}
