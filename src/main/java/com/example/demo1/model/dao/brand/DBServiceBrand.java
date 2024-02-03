package com.example.demo1.model.dao.brand;

import com.example.demo1.model.entities.Brand;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DBServiceBrand implements IDaoBrand{
    private final IRepoBrand brandRepo;

    @Override
    public List<Brand> findAll() {
        return (List<Brand>) brandRepo.findAll();
    }

    @Override
    public Brand findById(int id) {
        return brandRepo.findById(id);
    }

    @Override
    public Brand save(Brand brand) {
        return brandRepo.save(brand);
    }

    @Override
    public Brand update(Brand brand) {
        if (brandRepo.findById(brand.getId()).isPresent()) {
            brandRepo.save(brand);
        }
        return brand;
    }

    @Override
    public Brand deleteById(int id) {
        Brand brand = findById(id);
        System.out.println(brand.getBrand()); //справочно показываем название бренда к удалению
        brandRepo.deleteById(id);
        return brand;
    }
}
