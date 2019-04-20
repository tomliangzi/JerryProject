package com.jerry.Service.impl;

import com.jerry.Service.ProductService;
import com.jerry.dataobject.ProductInfo;
import com.jerry.dto.CartDTO;
import com.jerry.enums.ProductStatusEnum;
import com.jerry.enums.ResultEnum;
import com.jerry.exception.SellException;
import com.jerry.repository.ProductInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @program: sell
 * @description:
 * @author: Jerry
 * @create: 2019-04-18 22:14
 **/

@Service
public class ProductServiceImpl implements ProductService {



    @Autowired
    private ProductInfoRepository repository;


    @Override
    public ProductInfo findOne(String productId) {
        return repository.findOne(productId);
    }

    @Override
    public List<ProductInfo> findUpAll() {


        return repository.findByProductStatus(0);
    }

    @Override
    public Page<ProductInfo> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }


    @Override
    public ProductInfo save(ProductInfo productInfo) {
        return repository.save(productInfo);
    }

    @Override
    public void increaseStock(List<CartDTO> cartDTOList) {


    }

    @Override
    @Transactional
    public void decreaseStock(List<CartDTO> cartDTOList) {

        for(CartDTO cartDTO:cartDTOList){

            ProductInfo productInfo = repository.findOne(cartDTO.getProductId());

            if(productInfo==null){
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
            }


           Integer result= productInfo.getProductStock() - cartDTO.getProductQuantity();

            if(result<0){
                throw new SellException(ResultEnum.PRODUCT_STOCK_ERROR);

            }

            productInfo.setProductStock(result);

            repository.save(productInfo);


        }



    }


}
