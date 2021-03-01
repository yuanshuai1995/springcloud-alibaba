package com.itheima.dao;

import com.itheima.domain.Order;
import com.itheima.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDao extends JpaRepository <Order,Integer>{
}
