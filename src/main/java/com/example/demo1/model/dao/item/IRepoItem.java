package com.example.demo1.model.dao.item;

import com.example.demo1.model.entities.Item;
import org.springframework.data.repository.CrudRepository;

public interface IRepoItem extends CrudRepository<Item, Integer> {
    Item findById(int id);
}
