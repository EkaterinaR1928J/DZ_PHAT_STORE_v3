'use strict';

let onLoadRequest = () => {
    $.ajax({
        url : '/onLoadIndex',
        type : 'GET',
        // datatype : 'html',
        datatype : 'application-json',
        // data : {
        //     type : 'string',
        //     user : {
        //         username: 'xxx',
        //         password: 'yyy',
        //     }
        // }
        success : function (data) {
            console.log(data);
            let response = JSON.parse(data);
            console.log(response);                      // справочно вывод в консоль результата запроса
            completeCategories(response.categories);    // добавление подписей и картинок в категории
            console.log(response.categories);           // справочно вывод в консоль результата запроса
            console.log(response.brands);               // справочно вывод в консоль результата запроса
            completeBrandImage(response.brands);    // добавление логотипов брендов в карусель

        }
    })
}

let completeCategories = (category) => {
    completeCategoryCaptions(category); //заполнение содержимым подписей под картинками категории
    completeCategoryImage(category);    //заполнение содержимым картинок
}


//Метод заполнения содержимым подписей под картинками категории
let completeCategoryCaptions = (categories) => {
    let captions = document.querySelectorAll(".group_caption");
    for (let i = 0; i < captions.length; i++) {
    captions[i].innerHTML = '<p>' + categories[i] + '</p>';
    }
}

//Метод заполнение содержимым картинок категорий
let completeCategoryImage = (categories) => {
    let imageDivs = document.querySelectorAll(".group_img");
    let img;
    for (let i = 0; i < categories.length; i++) {
        img = imageDivs[i].querySelector("img");
        // img.src = '/img/categories/' + categories[i] + '.jpg';
        img.src = `/img/categories/${categories[i]}.jpg`;
    }
}

//Метод заполнение содержимым картинок брендов
let completeBrandImage = (brands) => {
    let imageDivs = document.querySelectorAll(".brand");
    let img;
    for (let i = 0; i < brands.length; i++) {
        imageDivs[i].style.backgroundImage = `url(/img/BrandsLogo/${brands[i]}.jpg)`;

        // img = imageDivs[i].querySelector("img");
        // img.src = `/img/BrandsLogo/${brands[i]}.jpg`;
    }
}



onLoadRequest();