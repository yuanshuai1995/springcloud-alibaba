package com.itheima.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.itheima.service.impl.OrderServiceImpl3;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class OrderController3 {
    @Autowired
    private OrderServiceImpl3 orderServiceImpl3;

    int i = 0;
    //test high Thread
    @RequestMapping("/order/message1")
    public String message1(){
        //orderServiceImpl3.message();
//        i++;
//        if (i%3==0){
//            throw new RuntimeException();
//        }
        return "test message1";
    }

    //test high Thread
    @RequestMapping("/order/message2")
    public String message2(){
        //orderServiceImpl3.message();
        return "test message2";
    }

    @RequestMapping("/order/message3")
    @SentinelResource("message3")
    public String message3(String name,Integer age){
        return "test message3"+name+age;
    }
}