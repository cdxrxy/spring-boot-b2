package com.example.springbootb2.service;

import com.example.springbootb2.model.Item;

import java.util.List;

public interface IItemService {
    Item createItem(Item item);
    List<Item> getAllItems();
}
