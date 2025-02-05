package com.example.orderservice.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UtilConfig {
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
    //Jackson라이브러에서 제공하는 클래스
    //자바객체를 JSON문자열으로 변환(직렬화),
    // JSON문자열을 자바객체로 변환(역직렬화)
    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }
}
