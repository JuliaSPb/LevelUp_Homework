package ru.levelup.julia_kalujnaya.qa.homework_2.entities.recipes;

/**
 * Класс-сущность, представляющий объект предметной области "Второе блюдо", наследующий от класса "Рецепт"
 *
 * @author Юлия Калюжная
 */
public class MainCourse extends Recipe {
    public MainCourse(String name) {
        super(name);
    }

    @Override
    public String cook() {
        return String.format("Приготовлено основное блюдо: %s (%.2f ккал)\n", this.getName(), this.getCalories());
    }
}