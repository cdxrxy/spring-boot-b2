package com.example.springbootb2.unit;

import com.example.springbootb2.controller.ItemController;
import com.example.springbootb2.model.Item;
import com.example.springbootb2.security.AuthJwtFilter;
import com.example.springbootb2.service.ItemService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static com.example.springbootb2.util.TestUtil.createItem;
import static com.example.springbootb2.util.TestUtil.createItemDto;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = ItemController.class,
        excludeAutoConfiguration = SecurityAutoConfiguration.class
        ,excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = AuthJwtFilter.class))
public class ItemControllerTests {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private ItemService itemService;

    @Test
    public void itShouldCreateItem() throws Exception {
        var item = createItem();

        when(itemService.createItem(any(Item.class))).thenReturn(item);

        mockMvc.perform(post("/items")
                .contentType(APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(createItemDto())))
                .andExpectAll(
                        status().is(200),
                        content().contentType(APPLICATION_JSON),
                        jsonPath("$.name").value(item.getName()));
    }

    @Test
    public void itShouldGetAllItems() throws Exception {
        var items = List.of(new Item(), new Item(), new Item());

        when(itemService.getAllItems()).thenReturn(items);

        mockMvc.perform(get("/items"))
                .andExpectAll(
                        status().is(200),
                        content().contentType(APPLICATION_JSON),
                        content().string(objectMapper.writeValueAsString(items)));
    }
}
