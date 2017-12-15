package ru.levelup.julia_kalujnaya.qa.homework_2.entities.products;

/**
 * Класс-сущность, представляющий объект предметной области "Соус", наследующий от класса "Продукт"
 *
 * @author Юлия Калюжная
 */
public class Dressing extends Product {
    public Dressing(String name, double protein, double fat, double carbohydrate) {
        super(name, protein, fat, carbohydrate);
    }
}