package com.example.springbootb2.util;

import com.example.springbootb2.dto.ItemDto;
import com.example.springbootb2.model.Item;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ItemMapper {
    ItemMapper INSTANCE = Mappers.getMapper(ItemMapper.class);

    Item itemDtoToItem(ItemDto itemDto);
    ItemDto itemToItemDto(Item item);
}
