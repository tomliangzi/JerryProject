package com.jerry.repository;

import com.jerry.dataobject.ProducetCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @program: sell
 * @description:
 * @author: Jerry
 * @create: 2019-04-18 20:12
 **/


public interface ProductCategoryRepository extends JpaRepository<ProducetCategory,Integer> {



    List<ProducetCategory> findByCategoryTypeIn(List<Integer> categoryTypeList);

}
