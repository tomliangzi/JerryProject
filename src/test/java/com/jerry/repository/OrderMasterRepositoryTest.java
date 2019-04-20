package com.jerry.repository;

import com.jerry.dataobject.OrderMaster;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;


@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderMasterRepositoryTest {

    @Autowired
    private OrderMasterRepository repository;

    @Test
    public void saveTest(){

        OrderMaster orderMaster=new OrderMaster();



        orderMaster.setOrderId("123456");
        orderMaster.setBuyerName("a");
        orderMaster.setBuyerPhone("123123");
        orderMaster.setBuyerAddress("12");
        orderMaster.setBuyerOpenid("11");
        orderMaster.setOrderAmount(new BigDecimal(2.2));


        repository.save(orderMaster);

    }

    @Test
    public void findByBuyerOpenid() throws  Exception{

        PageRequest request=new PageRequest(0,1);


        Page<OrderMaster> result = repository.findByBuyerOpenid("11", request);

        Assert.assertNotEquals(0,result.getTotalElements());

    }







}