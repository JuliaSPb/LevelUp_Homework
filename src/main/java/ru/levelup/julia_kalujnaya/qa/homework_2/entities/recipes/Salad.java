package ru.levelup.julia_kalujnaya.qa.homework_2.entities.recipes;

/**
 * Класс-сущность, представляющий объект предметной области "Салат", наследующий от класса "Рецепт"
 *
 * @author Юлия Калюжная
 */
public class Salad extends Recipe {
    public Salad(String name) {
        super(name);
    }

    @Override
    public String cook() {
        return String.format("Приготовлен салат: %s (%.2f ккал)\n", this.getName(), this.getCalories());
    }
}