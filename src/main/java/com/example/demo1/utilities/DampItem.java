package com.example.demo1.utilities;

import com.example.demo1.model.dao.IDaoDb;
import com.example.demo1.model.dao.brand.DBServiceBrand;
import com.example.demo1.model.dao.brand.IDaoBrand;
import com.example.demo1.model.dao.brand.IRepoBrand;
import com.example.demo1.model.dao.category.IDaoCategory;
import com.example.demo1.model.entities.Brand;
import com.example.demo1.model.entities.Category;
import com.example.demo1.model.entities.Item;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;

public class DampItem {

    //путь к файлу с данными по товарам
    public static String file = "src/main/DB_items.txt";

    //метод получения списка (List) с объектами Item
    public static List<Item> getDump() {
        try(Stream<String> strings = Files.lines(Paths.get(file))) {
            return strings
                    .map(e -> e
                            .replaceAll(",", "")
                            .replaceAll("'", "")
                            .replace("(", "")
                            .replace(")", ""))
                    .map(Item::new)
                    .toList();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    //метод поиска объекта класса Brand по id
    public static Brand getBrandById (int idBrand) {
        final IDaoBrand daoBrand = null;  //как инициализировать не в null ?
        Brand br = daoBrand.findById(idBrand); //как вытащить объект класса Brand по id ???
        return br;
    }

    //метод поиска объекта класса Category по id
    public static Category getCategoryById (int idCategory) {
        final IDaoCategory daoCategory = null;
        Category ct = daoCategory.findById(idCategory); //как вытащить объект класса Brand по id ???
        return ct;
    }

}
