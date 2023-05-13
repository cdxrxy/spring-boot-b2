package com.example.springbootb2.util;

import com.example.springbootb2.dto.OrderItemDto;
import com.example.springbootb2.model.OrderItem;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(uses = ItemMapper.class)
public interface OrderItemListMapper {
    OrderItemListMapper INSTANCE = Mappers.getMapper(OrderItemListMapper.class);

    List<OrderItem> orderItemDtoListToOrderItemList(List<OrderItemDto> orderItemDtoList);
    List<OrderItemDto> orderItemListToOrderItemDtoList(List<OrderItem> OrderItemList);
}
