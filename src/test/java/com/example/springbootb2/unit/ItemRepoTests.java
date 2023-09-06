package com.example.springbootb2.unit;

import com.example.springbootb2.repository.ItemRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import static com.example.springbootb2.util.TestUtil.createItem;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
@ActiveProfiles("test")
public class ItemRepoTests {
    @Autowired
    private ItemRepo itemRepo;

    @Test
    public void itShouldSave() {
        var item = createItem();
        itemRepo.save(item);

        boolean exists = itemRepo.existsById(item.getId());
        assertTrue(exists);
    }

    @Test
    public void itShouldFindAll() {
        long count = itemRepo.findAll().size();

        assertThat(count).isEqualTo(3);
    }

    @Test
    public void itShouldFindById() {
        boolean isPresent = itemRepo.findById(1L).isPresent();

        assertTrue(isPresent);
    }
}
