package com.example.springbootb2.util;

import com.example.springbootb2.dto.OrderDto;
import com.example.springbootb2.model.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(uses = OrderItemListMapper.class)
public interface OrderMapper {
    OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);

    @Mapping(source = "userId", target = "user.id")
    Order orderDtoToOrder(OrderDto orderDto);
    @Mapping(source = "user.id", target = "userId")
    OrderDto orderToOrderDto(Order order);
}
