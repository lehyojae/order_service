package com.example.orderservice.service.producer;

import com.example.orderservice.domain.OrderDetailDTO;
import com.example.orderservice.service.OrderService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

//OrderDetailDTO객체를 product-service로 보내기
@Service
@RequiredArgsConstructor
@Slf4j
public class OrderStringProducer {
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;
    public void sendMessage(OrderDetailDTO orderProductInfo) {
        //매개변수로 전달받은 DTO => String으로 변환
        String orderStr="";
        try {
            //자바객체 -> json문자열
            orderStr = objectMapper.writeValueAsString(orderProductInfo);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        //publish - 메시지전송
        kafkaTemplate.send("dev.shop.order.create", orderStr);
        log.info("++++++++++++++++++++++++++++++++++++++++++++++++++");
        log.info("order-service의 프로듀서가 메시지전송=>{}", orderStr);
        log.info("++++++++++++++++++++++++++++++++++++++++++++++++++");
    }
}
