package com.jerry.Service.impl;

import com.jerry.Service.OrderService;
import com.jerry.Service.ProductService;
import com.jerry.converter.OrderMaster2OrderDTOConverter;
import com.jerry.dataobject.OrderDetail;
import com.jerry.dataobject.OrderMaster;
import com.jerry.dataobject.ProductInfo;
import com.jerry.dto.CartDTO;
import com.jerry.dto.OrderDTO;
import com.jerry.enums.OrderStatusEnum;
import com.jerry.enums.PayStatusEnum;
import com.jerry.enums.ResultEnum;
import com.jerry.exception.SellException;
import com.jerry.repository.OrderDetailRepository;
import com.jerry.repository.OrderMasterRepository;
import com.jerry.utils.KeyUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @program: sell
 * @description:
 * @author: Jerry
 * @create: 2019-04-19 14:33
 **/

@Service

public class OrderServiceImpl implements OrderService {


    @Autowired
    private ProductService productService;

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Autowired
    private OrderMasterRepository orderMasterRepository;




    @Override
    @Transactional
    public OrderDTO create(OrderDTO orderDTO) {

        String orderId=KeyUtil.genUniqueKey();

        BigDecimal orderAmount=new BigDecimal(BigInteger.ZERO);

//        List<CartDTO> cartDTOList=new ArrayList<>();

        for(OrderDetail orderDetail:orderDTO.getOrderDetailList()) {


            ProductInfo productInfo = productService.findOne(orderDetail.getProductId());
            if (productInfo == null) {
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
            }


            /**
             * 计算订单总价
             */
            orderAmount = productInfo.getProductPrice()
                    .multiply(new BigDecimal(orderDetail.getProductQuantity()))
                    .add(orderAmount);

            //订单详情入库


            orderDetail.setDetailId(KeyUtil.genUniqueKey());
            orderDetail.setOrderId(orderId);
            BeanUtils.copyProperties(productInfo, orderDetail);
            orderDetailRepository.save(orderDetail);

//            CartDTO cartDTO=new CartDTO(orderDetail.getProductId(),orderDetail.getProductQuantity());
//            cartDTOList.add(cartDTO);

        }

            //3.写入数据库

            OrderMaster orderMaster=new OrderMaster();
            BeanUtils.copyProperties(orderDTO,orderMaster);
            orderMaster.setOrderId(orderId);
            orderMaster.setOrderAmount(orderAmount);
            orderMaster.setOrderStatus(OrderStatusEnum.NEW.getCode());
            orderMaster.setPayStatus(PayStatusEnum.WAIT.getCode());


            orderMasterRepository.save(orderMaster);

            //4. 扣库存
            List<CartDTO> cartDTOList = orderDTO.getOrderDetailList().stream().map(e ->
                    new CartDTO(e.getProductId(), e.getProductQuantity())
            ).collect(Collectors.toList());
            productService.decreaseStock(cartDTOList);

            return orderDTO;

    }


    @Override
    public Page<OrderDTO> findList(String buyerOpenid, Pageable pageable) {

        Page<OrderMaster> orderMasterPage = orderMasterRepository.findByBuyerOpenid(buyerOpenid, pageable);

        List<OrderDTO> orderDTOList = OrderMaster2OrderDTOConverter.convert(orderMasterPage.getContent());

        return new PageImpl<OrderDTO>(orderDTOList, pageable, orderMasterPage.getTotalElements());


    }

    @Override
    public OrderDTO cancel(OrderDTO orderDTO) {

        OrderMaster orderMaster=new OrderMaster();
        BeanUtils.copyProperties(orderDTO,orderMaster);


        if(orderDTO.getOrderStatus().equals(OrderStatusEnum.NEW.getCode())){

            throw new SellException(ResultEnum.ORDER_STATUS_ERROR);
        }

        //修改订单状态
        orderMaster.setOrderStatus(OrderStatusEnum.CANCEL.getCode());
        OrderMaster updateResult = orderMasterRepository.save(orderMaster);

        if(updateResult==null){
            throw new SellException(ResultEnum.ORDER_UPDATE_FAIL);
        }

        if(CollectionUtils.isEmpty(orderDTO.getOrderDetailList())){


            throw  new SellException(ResultEnum.ORDER_DETAIL_EMPTY);

        }

        List<CartDTO> cartDTOList = orderDTO.getOrderDetailList().stream()
                .map(e -> new CartDTO(e.getProductId(), e.getProductQuantity()))
                .collect(Collectors.toList());
        productService.increaseStock(cartDTOList);

        /**
         * 如果已支付 需要退款
         */
        if(orderDTO.getPayStatus().equals(PayStatusEnum.SUCCESS.getCode())){


        }



        return orderDTO;
    }

    @Override
    public OrderDTO finish(OrderDTO orderDTO) {
        return null;
    }

    @Override
    public OrderDTO paid(OrderDTO orderDTO) {
        return null;
    }

    @Override
    public Page<OrderDTO> findList(Pageable pageable) {
        return null;
    }

    public OrderDTO findOne(String orderId) {


        OrderMaster orderMaster=orderMasterRepository.findOne(orderId);

        if(orderMaster==null){

            throw  new SellException(ResultEnum.ORDER_NOT_EXIST);

        }

        List<OrderDetail> orderDetailList=orderDetailRepository.findByOrderId(orderId);
        if(CollectionUtils.isEmpty(orderDetailList)){
            throw new SellException(ResultEnum.ORDER_NOT_EXIST);
        }

        OrderDTO orderDTO=new OrderDTO();
        BeanUtils.copyProperties(orderMaster,orderDTO);
        orderDTO.setOrderDetailList(orderDetailList);





        return orderDTO;
    }

}
