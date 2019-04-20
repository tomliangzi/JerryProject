package com.jerry.Service.impl;

import com.jerry.dataobject.ProductInfo;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductServiceImplTest {


    @Autowired
    private ProductServiceImpl productService;

    @Test
    public void findOne() {

        ProductInfo productInfo = productService.findOne("3456");
        Assert.assertEquals("3456",productInfo.getProductId());

    }

    @Test
    public void findUpAll() {
        List<ProductInfo> productInfoList=productService.findUpAll();
        Assert.assertNotEquals(0,productInfoList.size());

    }

    @Test
    public void findAll() {

    }

    @Test
    public void save() {
    }
}