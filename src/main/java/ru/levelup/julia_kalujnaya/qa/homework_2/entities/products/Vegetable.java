package ru.levelup.julia_kalujnaya.qa.homework_2.entities.products;

/**
 * Класс-сущность, представляющий объект предметной области "Овощ", наследующий от класса "Продукт" (методы добавлены
 * для демонстрации принципа наследования)
 *
 * @author Юлия Калюжная
 */
public class Vegetable extends Product {
    public Vegetable(String name, double protein, double fat, double carbohydrate) {
        super(name, protein, fat, carbohydrate);
    }

    public String cut() {
        return "Овощи порезаны: " + this.getName();
    }

    public String boil() {
        return "Овощи отварены: " + this.getName();
    }

    public String stew() {
        return "Овощи потушены: " + this.getName();
    }

    public String fry() {
        return "Овощи пожарены: " + this.getName();
    }
}