package ru.levelup.julia_kalujnaya.qa.homework_2.entities.products;

/**
 * Класс-сущность, представляющий объект предметной области "Крупа", наследующий от класса "Продукт" (методы добавлены
 * для демонстрации принципа наследования)
 *
 * @author Юлия Калюжная
 */
public class Cereals extends Product {
    public Cereals(String name, double protein, double fat, double carbohydrate) {
        super(name, protein, fat, carbohydrate);
    }

    public String boil() {
        return "Крупа сварена: " + this.getName();
    }

    public String makePorridge() {
        return "Каша приготовлена: " + this.getName();
    }
}