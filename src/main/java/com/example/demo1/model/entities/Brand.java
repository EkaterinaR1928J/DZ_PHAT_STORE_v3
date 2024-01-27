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
@Table(name = "brand_t")
public class Brand {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String brand;

    @OneToMany(mappedBy = "brand")
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
