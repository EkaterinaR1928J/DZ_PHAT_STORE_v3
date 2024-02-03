package com.example.demo1.model.entities;

import com.example.demo1.controllers.GenerateController;
import com.example.demo1.utilities.DampItem;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Cascade;

//@Data - кривая аннотация, с ней некорректно работают методы set и get
@Setter             //анотация предоставляет сеттеры
@Getter             //анотация предоставляет геттеры
@NoArgsConstructor  //анотация делает дефолтный конструктор
@Entity             //анотация говорит, что это pojo-класс
@Table(name = "item_t")
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "model")
    private String model;

    @Column(name = "color") // можно прописать ограничение по кол-ву символов, добавив через запятую свойство length=100
                            // @Column(name = "color", length = 100). По умолчанию - 255
    private String color;

    @Column(name = "amount")
    private Integer amount;

    @ManyToOne
    @JoinColumn(name = "brand_id")
    Brand brand;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    //конструктор
    public Item(String model, String color, Integer amount, Brand brand, Category category) {
        this.model = model;
        this.color = color;
        this.amount = amount;
        this.brand = brand;
        this.category = category;
    }

    //переопределенный toString
    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", model='" + model + '\'' +
                ", color='" + color + '\'' +
                ", amount=" + amount +
                ", brand=" + brand +
                ", category=" + category +
                '}';
    }
}
