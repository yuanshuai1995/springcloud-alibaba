package com.itheima.service.impl;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl3 {

    @SentinelResource("message")
    public String message(){
        return "message";
    }
}
