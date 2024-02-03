package com.example.demo1.controllers;

import com.example.demo1.model.dao.brand.IDaoBrand;
import com.example.demo1.model.dao.category.IDaoCategory;
import com.example.demo1.model.dao.item.IDaoItem;
import com.example.demo1.model.entities.Brand;
import com.example.demo1.model.entities.Category;
import com.example.demo1.model.entities.Item;
import com.example.demo1.utilities.DampItem;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

//---- РЕАЛИЗАЦИЯ НЕСКОЛЬКИХ ЗАПРОСОВ НА РАБОТУ С БЗ
//---- вывод осуществляется в консоль, запросы генерируются из Postman
//---- установлено свойство <spring.jpa.hibernate.ddl-auto=create-drop>, т.е. обнуление баз при каждом перезапуске

@RestController
@RequiredArgsConstructor    //делает дефолтный конструктор
@RequestMapping("/serv")
public class GenerateController {
    private final IDaoBrand daoBrand;
    private final IDaoCategory daoCategory;
    private final IDaoItem daoItem;

    // Метод на put-запрос генерации баз
    @PutMapping("/generate")
    public String generate() {
        generateCategory();
        generateBrans();
        generateItems();
        return "Таблицы созданы";
    }

    // Метод на put-запрос обновления баз
    @PutMapping("/update")
    public void update() {
        //добавление нового бренда
        tryToAddBrand("Котофей_1");
        //изменение названия бренда по id (в нашем случае меняет 4-й бренд на Кораблик)
        tryToChangeBrandById(4, "Кораблик");
    }

    // Метод на get-запрос информации из баз
    @GetMapping("/getBrand/{id}")
    public void getBrand(@PathVariable(name = "id") int id) {
        //получаем бренд по id
        Brand br = daoBrand.update(daoBrand.findById(id));
        System.out.println(br.toString());
    }

    // Метод на get-запрос различной информации из базы
    @GetMapping("/get")
    public void getSomething() {
        //печать информации о бренде по id (в нашем случае бренд №3)
        System.out.println("\nБренд с id = 3:");
        System.out.println(daoBrand.findById(3).toString());
        //печать всех брендов
        System.out.println("\nСписок всех брендов:");
        daoBrand.findAll().stream().toList().forEach(x -> System.out.println(x));
        //печать всех категорий
        System.out.println("\nСписок всех категорий:");
        daoCategory.findAll().stream().toList().forEach(x -> System.out.println(x));
        //метод выборки товаров по id-категории (в нашем случае - 3)
        System.out.println("\nСписок товаров категории 3:");
        daoCategory.findById(3).getItems().stream().forEach(x -> System.out.println(x));
        //метод выборки товаров по id-бренда (в нашем случае - 4)
        System.out.println("\nСписок товаров бренда 4:");
        daoBrand.findById(3).getStock().stream().forEach(x -> System.out.println(x));
    }

    // Метод на delete-запрос удаления бренда из БД
    @DeleteMapping(value = "/delete/brand/{id}")
    public void deleteBrand(@PathVariable(name = "id") int id){
        if (!daoBrand.findAll().stream().filter(x -> x.getId() == id).toList().isEmpty()) {
            System.out.println("id = " + id); //для контроля выводим id элемента к удалению
            daoBrand.deleteById(id);
        } else {
            System.out.println("Нет такого бренда");
        }
    }

    // Метод на delete-запрос удаления товара из БД
    @DeleteMapping(value = "/delete/item/{id}")
    public void deleteItem(@PathVariable(name = "id") int id){
        if (!daoItem.findAll().stream().filter(x -> x.getId() == id).toList().isEmpty()) {
            System.out.println("id = " + id); //для контроля выводим id элемента к удалению
            daoItem.deleteById(id);
        } else {
            System.out.println("Нет такого товара");
        }
    }

    // Метод на delete-запрос удаления категории из БД
    @DeleteMapping(value = "/delete/category/{id}")
    public void deleteCategory(@PathVariable(name = "id") int id){
        if (!daoCategory.findAll().stream().filter(x -> x.getId() == id).toList().isEmpty()) {
            System.out.println("id = " + id); //для контроля выводим id элемента к удалению
            daoCategory.deleteById(id);
        } else {
            System.out.println("Нет такой категории");
        }
    }

    // Метод добавления нового бренда в БД
    private void tryToAddBrand(String brandToAdd) {
        try {
            daoBrand.save(new Brand(brandToAdd));
        } catch (Exception e) {
            System.out.println("Такой бренд уже существует в БД");
        }
    }

    // Метод изменения названия бренда в БД
    private void tryToChangeBrandById(int id, String brandNew) {
        Brand brandToChage = daoBrand.findById(id);
        System.out.println(brandToChage.getBrand());    //справочно показываем бренд до изменения
        brandToChage.setBrand(brandNew);
        daoBrand.update(brandToChage);
        System.out.println(brandToChage.getBrand());    //справочно показываем бренд после изменения
    }

    // Метод генерации строк с брендами в БД
    private void generateBrans() {
            daoBrand.save(new Brand("Kangol"));
            daoBrand.save(new Brand("ProClub"));
            daoBrand.save(new Brand("Novesta"));
            daoBrand.save(new Brand("PHAT"));
    }

    // Метод генерации строк с категориями в БД
    private void generateCategory() {
        daoCategory.save(new Category("Головные уборы"));
        daoCategory.save(new Category("Одежда"));
        daoCategory.save(new Category("Обувь"));
        daoCategory.save(new Category("Сумки"));
        daoCategory.save(new Category("Аксессуары"));
        daoCategory.save(new Category("Творчество"));
    }

    // Метод генерации строк с товарами в БД
    private void generateItems() {
        //получем список строк из файла
        //для каждой строки в списке запускаем метод создания Item и сохранения в базу банных daoItem.save
        List<String> list = DampItem.getDump();
        for (String itemInList: list) {
//            System.out.println(itemInList); //справочно выводим на экран конкретную строку
            String[] strItem = itemInList.split(" ");
            Item item = new Item(strItem[0], strItem[1], Integer.parseInt(strItem[2]),
                    getBrandById(Integer.parseInt(strItem[3])),
                    getCategoryById(Integer.parseInt(strItem[4])));
//            System.out.println(item.toString()); //справочно выводим на экран конкретный экземпляр Item
            daoItem.save(item);
        }
    }

    // Метод получения объекта класса Brand по id для добавления в конструктор объекта класса Item
    public Brand getBrandById (int idBrand) {
        Brand br = daoBrand.findById(idBrand);
        return br;
    }

    // Метод получения объекта класса Category по id для добавления в конструктор объекта класса Item
    public Category getCategoryById (int idCategory) {
        Category ct = daoCategory.findById(idCategory);
        return ct;
    }

}
