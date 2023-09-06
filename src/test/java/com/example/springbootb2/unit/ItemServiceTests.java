package com.example.springbootb2.unit;

import com.example.springbootb2.model.Item;
import com.example.springbootb2.repository.ItemRepo;
import com.example.springbootb2.service.ItemService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static com.example.springbootb2.util.TestUtil.createItem;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ItemServiceTests {
    @Mock
    private ItemRepo itemRepo;
    @InjectMocks
    private ItemService itemService;

    @Test
    public void itShouldFindAll() {
        var items = List.of(new Item(), new Item(), new Item());

        when(itemRepo.findAll()).thenReturn(items);

        assertThat(itemService.getAllItems().size()).isEqualTo(items.size());
    }

    @Test
    public void itShouldCreateItem() {
        var item = createItem();

        when(itemRepo.save(any(Item.class))).thenReturn(item);

        assertThat(itemService.createItem(item).getName()).isEqualTo(item.getName());
    }
}
