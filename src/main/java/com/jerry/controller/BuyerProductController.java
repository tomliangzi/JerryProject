package com.jerry.controller;

import com.jerry.Service.CategoryService;
import com.jerry.Service.ProductService;
import com.jerry.VO.ProductInfoVO;
import com.jerry.VO.ProductVO;
import com.jerry.VO.ResultVO;
import com.jerry.dataobject.ProducetCategory;
import com.jerry.dataobject.ProductInfo;
import com.jerry.utils.ResultVOUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @program: sell
 * @description:
 * @author: Jerry
 * @create: 2019-04-18 23:22
 **/

@RestController
@RequestMapping("/buyer/product")
public class BuyerProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;


    @GetMapping("/list")
    public ResultVO list(){

        //1. 查询所有的上架商品
       List<ProductInfo> productInfoList = productService.findUpAll();


        List<Integer> categoryTypeList = productInfoList.stream()
                .map(e->e.getCategoryType())
                .collect(Collectors.toList());
        List<ProducetCategory> productCategoryList = categoryService.findByCategoryTypeIn(categoryTypeList);


        //3. 数据拼装
        List<ProductVO> productVOList = new ArrayList<>();
        for (ProducetCategory productCategory: productCategoryList) {
            ProductVO productVO = new ProductVO();
            productVO.setCategoryType(productCategory.getCategoryType());
            productVO.setCategoryName(productCategory.getCategoryName());

            List<ProductInfoVO> productInfoVOList = new ArrayList<>();
            for (ProductInfo productInfo: productInfoList) {
                if (productInfo.getCategoryType().equals(productCategory.getCategoryType())) {
                    ProductInfoVO productInfoVO = new ProductInfoVO();
                    BeanUtils.copyProperties(productInfo, productInfoVO);
                    productInfoVOList.add(productInfoVO);
                }
            }
            productVO.setProductInfoVOList(productInfoVOList);
            productVOList.add(productVO);
        }





        return ResultVOUtil.success(productVOList);
    }



}
