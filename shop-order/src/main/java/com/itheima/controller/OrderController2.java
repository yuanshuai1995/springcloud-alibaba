package com.itheima.controller;

import com.alibaba.fastjson.JSON;
import com.itheima.domain.Order;
import com.itheima.domain.Product;
import com.itheima.service.OrderService;
import com.itheima.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//@RestController
@Slf4j
public class OrderController2 {

    @Autowired
    private OrderService orderService;

    @Autowired
    private ProductService productService;

    //下单
    @RequestMapping("/order/prod/{pid}")
    public Order order(@PathVariable("pid") Integer pid) {
        log.info("pid:{}", pid);

        //调用商品微服务
        Product product = productService.findByPid(pid);
        try {
            Thread.sleep(2000l);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        log.info("product_pid:{},product_info:{}", pid, JSON.toJSONString(product));

        Order order = new Order();
        order.setPid(pid);
        order.setUid(1);
        order.setUsername("test_user");
        order.setNumber(1);
        order.setPprice(product.getPprice());
        order.setPname(product.getPname());
        //create order
        //orderService.createOrder(order);
        log.info("order_info:{}", JSON.toJSONString(order));
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return order;
    }


    //test high Thread
    @RequestMapping("/order/message")
    public String message(){
        return "test high Thread";
    }
}