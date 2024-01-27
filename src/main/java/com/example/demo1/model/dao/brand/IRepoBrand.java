package com.example.demo1.model.dao.brand;

import com.example.demo1.model.entities.Brand;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface IRepoBrand extends CrudRepository<Brand, Integer> {
    Brand findById(int id);



}
