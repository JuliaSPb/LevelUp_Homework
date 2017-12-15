package ru.levelup.julia_kalujnaya.qa.homework_2.entities.products;

/**
 * Класс-сущность, представляющий объект предметной области "Мясо", наследующий от класса "Продукт" (методы добавлены
 * для демонстрации принципа наследования)
 *
 * @author Юлия Калюжная
 */
public class Meat extends Product {
    public Meat(String name, double protein, double fat, double carbohydrate) {
        super(name, protein, fat, carbohydrate);
    }

    public String cut() {
        return "Мясо порезано: " + this.getName();
    }

    public String boil() {
        return "Мясо отварено: " + this.getName();
    }

    public String stew() {
        return "Мясо потушено: " + this.getName();
    }

    public String roast() {
        return "Мясо пожарено: " + this.getName();
    }

    public String grill() {
        return "Мясо приготовлено на гриле: " + this.getName();
    }
}