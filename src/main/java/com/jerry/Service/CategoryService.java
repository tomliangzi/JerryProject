package com.jerry.Service;

import com.jerry.dataobject.ProducetCategory;

import java.util.List;

public interface CategoryService {


    ProducetCategory findOne(Integer categoryId);

    List<ProducetCategory> findAll();

    List<ProducetCategory> findByCategoryTypeIn(List<Integer> categoryTypeList);

    ProducetCategory save(ProducetCategory productCategory);

}
