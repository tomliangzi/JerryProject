package com.jerry.dto;

/**
 * @program: sell
 * @description:
 * @author: Jerry
 * @create: 2019-04-19 14:57
 **/


public class CartDTO {


    private String productId;

    private Integer productQuantity;


    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public Integer getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(Integer productQuantity) {
        this.productQuantity = productQuantity;
    }

    public CartDTO(String productId, Integer productQuantity) {
        this.productId = productId;
        this.productQuantity = productQuantity;
    }
}
