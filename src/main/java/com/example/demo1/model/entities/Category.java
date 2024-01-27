package com.example.demo1.model.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;


@Data
@Getter             //анотация предоставляет геттеры
@NoArgsConstructor  //анотация делает дефолтный конструктор
@Entity
@Table(name = "category_t")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String category;

    @OneToMany(mappedBy = "category")
    private Set<Item> items;

    public Category (String category) {
        this.category = category;
        items = new HashSet<>();
    }

    @Override
    public String toString() {
        return "Brand{" +
                "id=" + id +
                ", brand='" + category + '\'' +
                ", stock=" + items.stream().map(Item::getId).collect(Collectors.toSet()) +
                '}';
    }

}
