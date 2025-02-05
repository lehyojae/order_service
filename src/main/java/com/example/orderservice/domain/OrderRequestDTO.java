package com.example.orderservice.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderRequestDTO {
    private String addr; //배송주소
    private Long customerId;
    private List<OrderDetailDTO> orderDetailDTOList;
}
