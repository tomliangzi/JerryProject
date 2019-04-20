package com.jerry.Service.impl;

import com.jerry.Service.CategoryService;
import com.jerry.dataobject.ProducetCategory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
@SpringBootTest
public class CategoryServiceImplTest {

    @Autowired
    private CategoryServiceImpl categoryServiceimpl;

    @Test
    public void findOne() {

        ProducetCategory one = categoryServiceimpl.findOne(1);


        System.out.println(one.toString());

    }
}