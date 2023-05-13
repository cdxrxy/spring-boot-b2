package com.example.springbootb2.util;

import com.example.springbootb2.dto.OrderDto;
import com.example.springbootb2.model.Order;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(uses = OrderMapper.class)
public interface OrderListMapper {
    OrderListMapper INSTANCE = Mappers.getMapper(OrderListMapper.class);

    List<Order> orderDtoListToOrderList(List<OrderDto> orderDtoList);
    List<OrderDto> orderListToOrderDtoList(List<Order> orderList);
}
