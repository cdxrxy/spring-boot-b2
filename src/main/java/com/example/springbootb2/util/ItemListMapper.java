package com.example.springbootb2.util;

import com.example.springbootb2.dto.ItemDto;
import com.example.springbootb2.model.Item;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(uses = ItemMapper.class)
public interface ItemListMapper {
    ItemListMapper INSTANCE = Mappers.getMapper(ItemListMapper.class);

    List<Item> itemDtoListToItemList(List<ItemDto> itemDtoList);
    List<ItemDto> itemListToItemDtoList(List<Item> itemList);
}
