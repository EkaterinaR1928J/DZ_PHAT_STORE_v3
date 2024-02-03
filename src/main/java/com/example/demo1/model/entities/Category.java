package com.example.demo1.model.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;


//@Data - кривая аннотация, с ней некорректно работают методы set и get
@Setter             //анотация предоставляет сеттеры
@Getter             //анотация предоставляет геттеры
@NoArgsConstructor  //анотация делает дефолтный конструктор
@Entity             //анотация говорит, что это pojo-класс
@Table(name = "category_t")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name", unique = true, nullable = false) //unique = true (свойство уникальности колонки),
                     // nullable = false (свойство обязательности ненулевого значения)
    private String category;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL) //cascade = CascadeType.ALL - признак CascadeType.ALL говорит,
                                                // что с зависимыми объетами надо сделать тоже, что с родительским
    private Set<Item> items;

    public Category (String category) {
        this.category = category;
        items = new HashSet<>();
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", category='" + category + '\'' +
                ", items=" + items.stream().map(Item::getId).collect(Collectors.toSet()) +
                '}';
    }

}
