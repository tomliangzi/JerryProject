package com.jerry.repository;

import com.jerry.dataobject.OrderMaster;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @program: sell
 * @description:
 * @author: Jerry
 * @create: 2019-04-19 12:00
 **/


public interface OrderMasterRepository extends JpaRepository<OrderMaster, String> {


    Page<OrderMaster> findByBuyerOpenid(String buyerOpenid, Pageable pageable);

}
