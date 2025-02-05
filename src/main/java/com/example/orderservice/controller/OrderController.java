package com.example.orderservice.controller;

//import com.example.orderservice.domain.OrderDetailDTO;
import com.example.orderservice.domain.OrderRequestDTO;
import com.example.orderservice.domain.OrderResponseDTO;
import com.example.orderservice.service.OrderService;
//import com.example.orderservice.service.OrderStringProducer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
@Slf4j
public class OrderController {
    private final OrderService orderService;
    @PostMapping("/create")
    public void create(@RequestBody OrderRequestDTO order){
        log.info("주문내역====>{}",order);
        orderService.save(order);
        System.out.println(order);
    }
    @GetMapping("/getOrders/{customerId}")
    public List<OrderResponseDTO> getOrders(@PathVariable Long customerId){
        return null;
    }
}











