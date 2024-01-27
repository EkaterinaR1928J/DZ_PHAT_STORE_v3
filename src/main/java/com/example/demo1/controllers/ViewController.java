package com.example.demo1.controllers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.example.demo1.utilities.GsonBuilderUtil.getJson;

@RestController
@RequestMapping("/")
public class ViewController {
    @GetMapping("/onLoadIndex")
    public String onLoadIndex() {
        Map<String, List<String>> result = new HashMap<>();
        result.put("categories", completeCategories());
        result.put("brands", completeBrands());
        return getJson(result);
    }

    private List<String> completeCategories() {
        List<String> categories = new ArrayList<>();
        categories.add("Головные уборы");
        categories.add("Одежда");
        categories.add("Обувь");
        categories.add("Сумки");
        categories.add("Аксессуары");
        categories.add("Творчество");
        return categories;
    }

    private List<String> completeBrands() {
        List<String> brands = new ArrayList<>();
        brands.add("Kangol");
        brands.add("Proclub");
        brands.add("Novesta");
        brands.add("Phat");
        return brands;
    }




}
