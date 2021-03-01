package com.itheima.controller;

import com.alibaba.fastjson.JSON;
import com.itheima.domain.Order;
import com.itheima.domain.Product;
import com.itheima.service.OrderService;
import com.itheima.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

//@RestController
@Slf4j
public class OrderController {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private OrderService orderService;

    @Autowired
    private ProductService productService;

    @Autowired
    private DiscoveryClient discoveryClient;

    //下单--feign
    @RequestMapping("/order/prod/{pid}")
    public Order order(@PathVariable("pid") Integer pid){
        log.info("pid:{}",pid);

        //调用商品微服务
        Product product = productService.findByPid(pid);
        log.info("product_pid:{},product_info:{}",pid, JSON.toJSONString(product));

        Order order = new Order();
        order.setPid(pid);
        order.setUid(1);
        order.setUsername("test_user");
        order.setNumber(1);
        order.setPprice(product.getPprice());
        order.setPname(product.getPname());
        //create order
        orderService.createOrder(order);
        log.info("order_info:{}",JSON.toJSONString(order));
        return order;
    }

}


//    //下单
//    @RequestMapping("/order/prod/{pid}")
//    public Order order(@PathVariable("pid") Integer pid){
//        log.info("pid:{}",pid);
//
//        //调用商品微服务
//        Product product = restTemplate.getForObject("http://localhost:8081/product/"+pid,Product.class);
//        log.info("product_pid:{},product_info:{}",pid, JSON.toJSONString(product));
//
//        Order order = new Order();
//        order.setPid(pid);
//        order.setUid(1);
//        order.setUsername("test_user");
//        order.setNumber(1);
//        order.setPprice(product.getPprice());
//        order.setPname(product.getPname());
//        //create order
//        orderService.createOrder(order);
//        log.info("order_info:{}",JSON.toJSONString(order));
//        return order;
//    }


//    //下单
//    @RequestMapping("/order/prod/{pid}")
//    public Order order(@PathVariable("pid") Integer pid){
//        log.info("pid:{}",pid);
//
//        //调用商品微服务
//        List<ServiceInstance> instances = discoveryClient.getInstances("service-product");
//        ServiceInstance serviceInstance = instances.get(0);
//        Product product = restTemplate.getForObject("http://"+serviceInstance.getHost()+":"+serviceInstance.getPort()+"/product/"+pid,Product.class);
//        log.info("product_pid:{},product_info:{}",pid, JSON.toJSONString(product));
//
//        Order order = new Order();
//        order.setPid(pid);
//        order.setUid(1);
//        order.setUsername("test_user");
//        order.setNumber(1);
//        order.setPprice(product.getPprice());
//        order.setPname(product.getPname());
//        //create order
//        orderService.createOrder(order);
//        log.info("order_info:{}",JSON.toJSONString(order));
//        return order;
//    }


//    //下单--自定义负载均衡
//    @RequestMapping("/order/prod/{pid}")
//    public Order order(@PathVariable("pid") Integer pid){
//        log.info("pid:{}",pid);
//
//        //调用商品微服务
//        List<ServiceInstance> instances = discoveryClient.getInstances("service-product");
//        //sui ji
//        int index = new Random().nextInt(instances.size());
//        ServiceInstance serviceInstance = instances.get(index);
//
//        Product product = restTemplate.getForObject("http://"+serviceInstance.getHost()+":"+serviceInstance.getPort()+"/product/"+pid,Product.class);
//        log.info("product_pid:{},product_info:{}",pid, JSON.toJSONString(product));
//
//        Order order = new Order();
//        order.setPid(pid);
//        order.setUid(1);
//        order.setUsername("test_user");
//        order.setNumber(1);
//        order.setPprice(product.getPprice());
//        order.setPname(product.getPname());
//        //create order
//        orderService.createOrder(order);
//        log.info("order_info:{}",JSON.toJSONString(order));
//        return order;
//    }

//
//    //下单--ribbon载均衡
//    @RequestMapping("/order/prod/{pid}")
//    public Order order(@PathVariable("pid") Integer pid){
//        log.info("pid:{}",pid);
//
//        //调用商品微服务
//        Product product = restTemplate.getForObject("http://service-product/product/"+pid,Product.class);
//        log.info("product_pid:{},product_info:{}",pid, JSON.toJSONString(product));
//
//        Order order = new Order();
//        order.setPid(pid);
//        order.setUid(1);
//        order.setUsername("test_user");
//        order.setNumber(1);
//        order.setPprice(product.getPprice());
//        order.setPname(product.getPname());
//        //create order
//        orderService.createOrder(order);
//        log.info("order_info:{}",JSON.toJSONString(order));
//        return order;
//    }