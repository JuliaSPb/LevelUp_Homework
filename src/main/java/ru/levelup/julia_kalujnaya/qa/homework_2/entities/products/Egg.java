package ru.levelup.julia_kalujnaya.qa.homework_2.entities.products;

/**
 * Класс-сущность, представляющий объект предметной области "Яйцо", наследующий от класса "Продукт" (методы добавлены
 * для демонстрации принципа наследования)
 *
 * @author Юлия Калюжная
 */
public class Egg extends Product {
    public Egg(String name, double protein, double fat, double carbohydrate) {
        super(name, protein, fat, carbohydrate);
    }

    public String boil() {
        return "Яйца сварены: " + this.getName();
    }

    public String fry() {
        return "Яйца пожарены: " + this.getName();
    }
}