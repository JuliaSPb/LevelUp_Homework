package ru.levelup.julia_kalujnaya.qa.homework_2.entities.recipes;

import ru.levelup.julia_kalujnaya.qa.homework_2.entities.products.Product;

/**
 * Класс-сущность, представляющий объект предметной области "Ингредиент блюда", имеющий поля:
 * product - продукт
 * weight - вес продукта в граммах
 *
 * @author Юлия Калюжная
 */
public class Ingredient {
    private Product product;
    private double weight;

    public Ingredient(Product product, double weight) {
        this.product = product;
        this.weight = weight;
    }

    public Product getProduct() {
        return product;
    }

    public double getWeight() {
        return weight;
    }

    /**
     * Рассчитывает калорийность ингредиента
     * @return Возвращает калорийность ингредиента
     */
    public double getCalories() {
        return weight/100*product.getCaloriesPer100g();
    }
}