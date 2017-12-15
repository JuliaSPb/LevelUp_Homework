package ru.levelup.julia_kalujnaya.qa.homework_2.entities.recipes;

/**
 * Класс-сущность, представляющий объект предметной области "Суп", наследующий от класса "Рецепт"
 *
 * @author Юлия Калюжная
 */
public class Soup extends Recipe {
    public Soup(String name) {
        super(name);
    }

    @Override
    public String cook() {
        return String.format("Приготовлен суп: %s (%.2f ккал)\n", this.getName(), this.getCalories());
    }
}