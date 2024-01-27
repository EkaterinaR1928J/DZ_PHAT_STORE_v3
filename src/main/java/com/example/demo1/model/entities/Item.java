package com.example.demo1.model.entities;

import com.example.demo1.utilities.DampItem;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@Getter             //анотация предоставляет геттеры
@NoArgsConstructor  //анотация делает дефолтный конструктор
@Entity
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
    @JoinColumn(name = "brand_id", nullable = false)
    Brand brand;

    @ManyToOne
    @JoinColumn(name = "category_id")
    Category category;

    // ОТСЮДА НАЧАЛА ДОПИСЫВАТЬ
    //конструктор
    public Item(String model, String color, Integer amount, Brand brand, Category category) {
        this.model = model;
        this.color = color;
        this.amount = amount;
        this.brand = brand;
        this.category = category;
    }

    //конструктор от строки
    public Item (String itemInString) {
        String[] strItem = itemInString.split(" ");
        this.model = strItem[0];
        this.color = strItem[1];
        this.amount = Integer.parseInt(strItem[2]);
        this.brand = DampItem.getBrandById(Integer.parseInt(strItem[3]));     //как перевести в Brand ?
        this.category = DampItem.getCategoryById(Integer.parseInt(strItem[4])); //как перевести в Category ?
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
