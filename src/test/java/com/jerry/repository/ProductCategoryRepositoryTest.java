package com.jerry.repository;



import com.jerry.dataobject.ProducetCategory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductCategoryRepositoryTest {


    @Autowired
    private ProductCategoryRepository repository;

    @Test
    public void findOneTest(){

        List<ProducetCategory> all = repository.findAll();

        System.out.println(all);
    }

    @Test
    public void saveTest(){
        ProducetCategory producetCategory=new ProducetCategory();
        producetCategory.setCategoryName("你好");
        producetCategory.setCategoryType(3);
        repository.save(producetCategory);
    }

    @Test
    public void findByCatoryTypeInTest(){


        List<Integer> list = Arrays.asList(1,2,3);

        List<ProducetCategory> result = repository.findByCategoryTypeIn(list);

        Assert.assertNotEquals(0,result.size());
    }










}