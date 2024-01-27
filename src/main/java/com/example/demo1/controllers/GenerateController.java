package com.example.demo1.controllers;

import com.example.demo1.model.dao.brand.IDaoBrand;
import com.example.demo1.model.dao.category.IDaoCategory;
import com.example.demo1.model.dao.item.IDaoItem;
import com.example.demo1.model.entities.Brand;
import com.example.demo1.model.entities.Category;
import com.example.demo1.model.entities.Item;
import com.example.demo1.utilities.DampItem;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor    //делает дефолтный конструктор
@RequestMapping("/serv")
public class GenerateController {
    private final IDaoBrand daoBrand;
    private final IDaoCategory daoCategory;
    private final IDaoItem daoItem;

    @GetMapping("/generate")
    public void generate() {
//        generateCategory();
//        generateBrans();
        generateItems();
    }

    private void generateBrans() {
        daoBrand.save(new Brand("Kangol"));
        daoBrand.save(new Brand("ProClub"));
        daoBrand.save(new Brand("Novesta"));
        daoBrand.save(new Brand("PHAT"));

    }



    private void generateCategory() {
        daoCategory.save(new Category("Головные уборы"));
        daoCategory.save(new Category("Одежда"));
        daoCategory.save(new Category("Обувь"));
        daoCategory.save(new Category("Сумки"));
        daoCategory.save(new Category("Аксессуары"));
        daoCategory.save(new Category("Творчество"));
    }

    //метод добавления списка товаров
    private void generateItems() {
        //получем список объектов класса Item
        //для каждого объекта в списке запускаем метод сохранения в базу банных daoItem.save
        List<Item> list = DampItem.getDump();
        for (Item itemInList: list) {
            daoItem.save(itemInList);
        }
    }

}
