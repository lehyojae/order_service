package com.example.orderservice.service;

import com.example.orderservice.dao.OrderDAO;
import com.example.orderservice.domain.*;
import com.example.orderservice.service.producer.OrderStringProducer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderServiceImpl implements OrderService {
    private final OrderDAO dao;
    private final ModelMapper modelMapper;
    private final OrderStringProducer producer;
    @Override
    @Transactional
    public void save(OrderRequestDTO orderRequestDTO) {
        //주문한 상품들에 대한 정보와 주문일반 내용을 테이블에 저장할 수 있도록 작업
        //DTO -> Entity
        log.info("Saving order {}", orderRequestDTO);
        //OrderRequestDTO에 저장된 상품리스트 DTO를 엔티티로 변환
        List<OrderProductEntity> datadetaillist =
                        orderRequestDTO.getOrderDetailDTOList().stream()
                                .map((detaildto) ->{
                                    return modelMapper.map(detaildto, OrderProductEntity.class);
                                }).collect(Collectors.toList());
        log.info("++++++++++++++++++++++++++++++++++++++++++++++++++");
        log.info("orderdetaillist=>{}", datadetaillist);
        log.info("++++++++++++++++++++++++++++++++++++++++++++++++++");

        //주문생성
        //양뱡향관계인 경우 부모테이블과 자식테이블의 데이터를 한 번에 저장할 수 있다.
        //부모테이블에 레코드를 저장할때 자식테이블의 레코드를 한 번에 같이 진행할 수 있다.
        OrderEntity orderEntity = OrderEntity.makeOrderEntity(orderRequestDTO.getAddr(),
                                orderRequestDTO.getCustomerId(),datadetaillist);
        dao.save(orderEntity);

        //주문이 성공하면 주문한 정보를 product-service로 보내기
        //=> 주문정보를 하나씩 꺼내서 넘기기  - 테스트
        //=> 주문정보를 한꺼번에 넘기기 - 미션
        for(OrderDetailDTO product : orderRequestDTO.getOrderDetailDTOList()){
            log.info("주문성공한 상품=>{}", product);
            producer.sendMessage(product);
        }
    }

    @Override
    public OrderResponseDTO findById(Long orderId) {
        return null;
    }

    @Override
    public List<OrderResponseDTO> findAllByCustomerId(Long customerId) {
        return List.of();
    }
}
