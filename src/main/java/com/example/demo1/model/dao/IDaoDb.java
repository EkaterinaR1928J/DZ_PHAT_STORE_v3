package com.example.demo1.model.dao;

import java.util.List;

public interface IDaoDb<T> {
    List<T> findAll();  //выгружать всю базу данных
    T findById(int id);     //поиск по id
    T save(T t);            //сохранение
    T update(T t);          //изменение
    T deleteById(int id);   //удаление по id


}
