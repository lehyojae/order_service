package com.example.orderservice.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//상품의 상세정보
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetailDTO {
    private Long productNo ;
    private int orderPrice;//구매가격
    private int count;//주문수량
}
