package com.example.demo1.model.dao.item;

import com.example.demo1.model.dao.category.IRepoCategory;
import com.example.demo1.model.entities.Category;
import com.example.demo1.model.entities.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DBServiceItem implements IDaoItem{
    private final IRepoItem itemRepo;

    @Override
    public List<Item> findAll() {
        return (List<Item>) itemRepo.findAll();
    }

    @Override
    public Item findById(int id) {
        return itemRepo.findById(id);
    }

    @Override
    public Item save(Item item) {
        return itemRepo.save(item);
    }

    @Override
    public Item update(Item item) {
        if (itemRepo.findById(item.getId()).isPresent()) {
            itemRepo.save(item);
        }
        return item;
    }

    @Override
    public Item deleteById(int id) {
        Item item = itemRepo.findById(id);
        itemRepo.deleteById(id);
        return item;
    }

}
