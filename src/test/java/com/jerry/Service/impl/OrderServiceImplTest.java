package com.jerry.Service.impl;

import com.jerry.dataobject.OrderDetail;
import com.jerry.dto.OrderDTO;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderServiceImplTest {


    @Autowired
    private OrderServiceImpl orderService;

    @Test
    public void create() throws  Exception{

        OrderDTO orderDTO=new OrderDTO();
        orderDTO.setBuyerName("廖师兄");
        orderDTO.setBuyerAddress("Muke");
        orderDTO.setBuyerPhone("123123");
        orderDTO.setBuyerOpenid("123");

        List<OrderDetail> orderDetailList=new ArrayList<>();

        OrderDetail o1=new OrderDetail();

         o1.setProductId("123");
         o1.setProductQuantity(1);
         orderDetailList.add(o1);

         orderDTO.setOrderDetailList(orderDetailList);

        OrderDTO result = orderService.create(orderDTO);

    }

    @Test
    public void findOne() {

        OrderDTO result = orderService.findOne("1555674750330828485");
        System.out.println(result);


    }

    @Test
    public void findList() {



    }

    @Test
    public void cancel() {
    }

    @Test
    public void finish() {
    }

    @Test
    public void paid() {
    }

    @Test
    public void findList1() {
    }
}