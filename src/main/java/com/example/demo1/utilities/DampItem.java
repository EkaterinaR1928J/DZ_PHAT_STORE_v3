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

    //метод получения списка (List) со строками из файла
    public static List<String> getDump() {
        try(Stream<String> strings = Files.lines(Paths.get(file))) {
            return strings
                    .map(e -> e
                            .replaceAll(",", "")
                            .replaceAll("'", "")
                            .replace("(", "")
                            .replace(")", ""))
                    .toList();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
