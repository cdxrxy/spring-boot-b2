package com.example.springbootb2.controller;

import com.example.springbootb2.dto.ItemDto;
import com.example.springbootb2.service.IItemService;
import com.example.springbootb2.util.ItemListMapper;
import com.example.springbootb2.util.ItemMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/items")
@RequiredArgsConstructor
public class ItemController {
    private final IItemService itemService;

    @PostMapping
    public ItemDto createItem(@RequestBody ItemDto itemDto) {
        return ItemMapper.INSTANCE.itemToItemDto(
                itemService.createItem(
                        ItemMapper.INSTANCE.itemDtoToItem(itemDto)
                )
        );
    }

    @GetMapping
    public List<ItemDto> getAllItems() {
        return ItemListMapper.INSTANCE.itemListToItemDtoList(itemService.getAllItems());
    }
}
