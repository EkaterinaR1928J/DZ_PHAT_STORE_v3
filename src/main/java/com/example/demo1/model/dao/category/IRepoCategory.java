package com.example.demo1.model.dao.category;

import com.example.demo1.model.entities.Category;
import org.springframework.data.repository.CrudRepository;

public interface IRepoCategory extends CrudRepository<Category, Integer> {
    Category findById (int id);
}
