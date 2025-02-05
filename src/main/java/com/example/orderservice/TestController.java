package com.example.orderservice;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class TestController {
    @GetMapping("/test")
    public String test(){
        return "order service test";
    }
    //json문자열로 전송하는 것이 일반적
    @GetMapping("/getdata")
    public OrderDTO getdata(){
        return new OrderDTO("bts","방탄","서울",10000);
    }
}
