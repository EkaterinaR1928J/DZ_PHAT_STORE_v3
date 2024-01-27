package com.example.demo1.model.dao.category;

import com.example.demo1.model.entities.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DBServiceCategory implements IDaoCategory{
    private final IRepoCategory categoryRepo;
    @Override
    public List<Category> findAll() {
        return (List<Category>) categoryRepo.findAll();
    }

    @Override
    public Category findById(int id) {
        return categoryRepo.findById(id);
    }

    @Override
    public Category save(Category category) {
        return categoryRepo.save(category);
    }

    @Override
    public Category update(Category category) {
        if (categoryRepo.findById(category.getId()).isPresent()) {
            categoryRepo.save(category);
        }
        return category;
    }

    @Override
    public Category deleteById(int id) {
        Category category = categoryRepo.findById(id);
        categoryRepo.deleteById(id);
        return category;
    }
}
