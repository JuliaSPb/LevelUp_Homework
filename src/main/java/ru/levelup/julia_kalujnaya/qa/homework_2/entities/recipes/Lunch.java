package ru.levelup.julia_kalujnaya.qa.homework_2.entities.recipes;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс-сущность, представляющий объект предметной области "Обед", включающий в себя список рецептов блюд
 *
 * @author Юлия Калюжная
 */
public class Lunch {
    private List<Recipe> recipes;

    public Lunch() {
        this.recipes = new ArrayList<>();
    }

    /**
     * Добавляет рецепт в список рецептов
     * @param recipe - добавляемый рецепт
     */
    public void addRecipe (Recipe recipe) {
        if (recipe != null)
            recipes.add(recipe);
    }

    /**
     * Приготовление обеда: по очереди готовится каждое блюдо из списка рецептов (демонстрация принципа полиморфизма),
     * выводится общая калорийность обеда
     * @return Возвращает строку с информацией о суммарной калорийности обеда
     */
    public String cook() {
        StringBuilder result = new StringBuilder();
        for (Recipe recipe: recipes) {
            result.append(recipe.cook());
        }
        return result.append(String.format("ОБЕД ГОТОВ! Суммарная калорийность: %.2f ккал\n", this.getCalories())).toString();
    }

    /**
     * Рассчитывает общую калорийность обеда
     * @return Возвращает калорийность всего обеда
     */
    private double getCalories() {
        double calories = 0;
        for (Recipe recipe: recipes) {
            calories += recipe.getCalories();
        }
        return calories;
    }
}