package ru.levelup.julia_kalujnaya.qa.homework_2.entities.products;

/**
 * Класс-сущность, представляющий объект предметной области "Продукт", со следующими полями:
 * name - наименование продукта
 * protein - содержание белков (в 100 г продукта)
 * fat - содержание жиров (в 100 г продукта)
 * carbohydrate - содержание углеводов (в 100 г продукта)
 *
 * @author Юлия Калюжная
 */
public class Product {
    private String name;
    private double protein;
    private double fat;
    private double carbohydrate;

    public Product(String name, double protein, double fat, double carbohydrate) {
        this.name = name;
        this.protein = protein;
        this.fat = fat;
        this.carbohydrate = carbohydrate;
    }

    public String getName() {
        return name;
    }

    public double getProtein() {
        return protein;
    }

    public double getFat() {
        return fat;
    }

    public double getCarbohydrate() {
        return carbohydrate;
    }

    /**
     * Рассчитывает калорийность продукта на основе БЖУ
     * @return Возвращает калорийность продукта (на 100 г)
     */
    public double getCaloriesPer100g() {
        return protein * 4.1 + fat * 9.3 + carbohydrate * 4.1;
    }

    @Override
    public String toString() {
        return String.format("%s (белки %.1f г, жиры %.1f г, углеводы %.1f г)",
                name, getProtein(), getFat(), getCarbohydrate());
    }
}