package com.jerry.converter;


import com.jerry.dataobject.OrderDetail;
import com.jerry.dto.OrderDTO;


import java.util.ArrayList;
import java.util.List;


public class OrderForm2OrderDTOConverter {

//    public static OrderDTO convert(OrderForm orderForm) {
//        Gson gson = new Gson();
//        OrderDTO orderDTO = new OrderDTO();
//
//        orderDTO.setBuyerName(orderForm.getName());
//        orderDTO.setBuyerPhone(orderForm.getPhone());
//        orderDTO.setBuyerAddress(orderForm.getAddress());
//        orderDTO.setBuyerOpenid(orderForm.getOpenid());
//
//        List<OrderDetail> orderDetailList = new ArrayList<>();
//        try {
//            orderDetailList = gson.fromJson(orderForm.getItems(),
//                    new TypeToken<List<OrderDetail>>() {
//                    }.getType());
//        } catch (Exception e) {
//            log.error("【对象转换】错误, string={}", orderForm.getItems());
//            throw new SellException(ResultEnum.PARAM_ERROR);
//        }
//        orderDTO.setOrderDetailList(orderDetailList);
//
//        return orderDTO;
//    }
}
