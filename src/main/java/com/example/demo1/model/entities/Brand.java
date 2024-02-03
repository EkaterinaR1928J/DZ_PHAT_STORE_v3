package com.example.demo1.model.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Cascade;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

//@Data - кривая аннотация, с ней некорректно работают методы set и get
@Setter             //анотация предоставляет сеттеры
@Getter             //анотация предоставляет геттеры
@NoArgsConstructor  //анотация делает дефолтный конструктор
@Entity             //анотация говорит, что это pojo-класс
@Table(name = "brand_t")
public class Brand {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name", unique = true, nullable = false) //unique = true (свойство уникальности колонки),
    // nullable = false (свойство обязательности ненулевого значения)
    private String brand;

    @OneToMany(mappedBy = "brand", cascade = CascadeType.ALL) //cascade = CascadeType.ALL - признак CascadeType.ALL говорит,
                                        // что с зависимыми объетами надо сделать тоже, что с родительским
    private Set<Item> stock;

    public Brand(String brand) {
        this.brand = brand;
        stock = new HashSet<>();
    }

    @Override
    public String toString() {
        return "Brand{" +
                "id=" + id +
                ", brand='" + brand + '\'' +
                ", stock=" + stock.stream().map(Item::getId).collect(Collectors.toSet()) +
                '}';
    }
}
