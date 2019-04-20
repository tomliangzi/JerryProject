package com.jerry.enums;

/**
 * @program: sell
 * @description:
 * @author: Jerry
 * @create: 2019-04-19 19:46
 **/


public enum  OrderStatusEnum implements CodeEnum {

    NEW(0, "新订单"),
    FINISHED(1, "完结"),
    CANCEL(2, "已取消"),
    ;

    private Integer code;

    private String message;

    OrderStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public Integer getCode() {
        return code;
    }

}
