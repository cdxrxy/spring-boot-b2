package com.example.springbootb2.util;

import com.example.springbootb2.dto.ItemDto;
import com.example.springbootb2.model.Item;

import java.util.Date;

public class TestUtil {
    public static Item createItem() {
        Item item = new Item();
        item.setPrice(400);
        item.setName("anyItem");
        item.setManufacturer("anyManufacturer");
        item.setDescription("anyDescription");
        item.setType("anyType");
        item.setCreatedDateTime(new Date());
        item.setModifiedDateTime(new Date());
        item.setActive(true);

        return item;
    }

    public static ItemDto createItemDto() {
        ItemDto itemDto = new ItemDto();
        itemDto.setPrice(400);
        itemDto.setName("anyItem");
        itemDto.setManufacturer("anyManufacturer");
        itemDto.setDescription("anyDescription");
        itemDto.setType("anyType");
        itemDto.setCreatedDateTime(new Date());
        itemDto.setModifiedDateTime(new Date());
        itemDto.setActive(true);

        return itemDto;
    }
}
