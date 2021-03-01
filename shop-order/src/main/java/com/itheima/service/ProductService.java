package com.itheima.service;
//Product product = restTemplate.getForObject("http://service-product/product/"+pid,Product.class);

import com.itheima.domain.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(value = "service-product")
public interface ProductService {

    @RequestMapping("/product/{pid}")
    Product findByPid(@PathVariable("pid") Integer pid);
}
