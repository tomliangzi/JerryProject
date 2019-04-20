package com.jerry.Service.impl;

import com.jerry.Service.CategoryService;
import com.jerry.dataobject.ProducetCategory;
import com.jerry.repository.ProductCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: sell
 * @description:
 * @author: Jerry
 * @create: 2019-04-18 21:47
 **/

@Service
public class CategoryServiceImpl implements CategoryService {


    @Autowired
    private ProductCategoryRepository repository;


    public ProducetCategory findOne(Integer categoryId) {
        return repository.findOne(categoryId);
    }


    public List<ProducetCategory> findAll() {
        return repository.findAll();
    }


    public List<ProducetCategory> findByCategoryTypeIn(List<Integer> categoryTypeList) {
        return repository.findByCategoryTypeIn(categoryTypeList);
    }


    public ProducetCategory save(ProducetCategory productCategory) {
        return repository.save(productCategory);
    }



}
