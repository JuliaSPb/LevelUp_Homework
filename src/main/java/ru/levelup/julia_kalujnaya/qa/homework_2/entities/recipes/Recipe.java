package ru.levelup.julia_kalujnaya.qa.homework_2.entities.recipes;

import ru.levelup.julia_kalujnaya.qa.homework_2.ChefCook;
import ru.levelup.julia_kalujnaya.qa.homework_2.entities.products.ProductParameters;

import java.util.*;

/**
 * Класс-сущность, представляющий объект предметной области "Рецепт" и реализующий интерфейс Cookable, со следующими полями:
 * name - наименование рецепта
 * ingredients - список ингредиентов
 *
 * @author Юлия Калюжная
 */
public class Recipe implements Cookable {
    private String name;
    private List<Ingredient> ingredients;

    public Recipe(String name) {
        this.name = name;
        this.ingredients = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    /**
     * Добавляет ингредиент в список ингредиентов
     * @param ingredient - добавляемый ингредиент
     */
    public void addIngredient(Ingredient ingredient) {
        if (ingredient != null) {
            ingredients.add(ingredient);
        }
    }

    /**
     * Приготовление блюда
     * @return Возвращает строку с информацией о названии блюда и его калорийности
     */
    public String cook() {
        return String.format("Приготовлено блюдо: %s (%.2f ккал)\n", name, getCalories());
    }

    /**
     * Рассчитывает общую калорийность блюда
     * @return Возвращает калорийность блюда
     */
    public double getCalories() {
        double calories = 0;
        for (Ingredient ingredient: ingredients) {
            calories += ingredient.getCalories();
        }
        return calories;
    }

    /**
     * Сортирует ингредиенты блюда по заданному параметру
     * @param parameter - параметр для сортировки
     * @return Возвращает строку со списком отсортированных ингредиентов
     */
    public String sortIngredients(ProductParameters parameter) {
        switch (parameter) {
            case PROTEIN:
                ingredients.sort(ProteinComparator);
                break;
            case FAT:
                ingredients.sort(FatComparator);
                break;
            case CARBOHYDRATE:
                ingredients.sort(CarbohydrateComparator);
        }
        return getSortedIngredients(parameter);
    }

    /**
     * Класс для сравнения ингредиентов рецепта по содержанию белков
     */
    private static Comparator<Ingredient> ProteinComparator = new Comparator<Ingredient>() {
        @Override
        public int compare(Ingredient o1, Ingredient o2) {
            return isValuePositive(o1.getProduct().getProtein() - o2.getProduct().getProtein());
        }
    };

    /**
     * Класс для сравнения ингредиентов рецепта по содержанию жиров
     */
    private static Comparator<Ingredient> FatComparator = new Comparator<Ingredient>() {
        @Override
        public int compare(Ingredient o1, Ingredient o2) {
            return isValuePositive(o1.getProduct().getFat() - o2.getProduct().getFat());
        }
    };

    /**
     * Класс для сравнения ингредиентов рецепта по содержанию углеводов
     */
    private static Comparator<Ingredient> CarbohydrateComparator = new Comparator<Ingredient>() {
        @Override
        public int compare(Ingredient o1, Ingredient o2) {
            return isValuePositive(o1.getProduct().getCarbohydrate() - o2.getProduct().getCarbohydrate());
        }
    };

    /**
     * Определяет, является ли заданное число положительным или отрицательным
     * @param value - заданное число
     * @return Возвращает 1 для положительного числа, -1 для отрицательного и 0 для 0
     */
    private static int isValuePositive(double value) {
        if (value == 0) {
            return 0;
        }
        return  (value > 0 ? 1 : -1);
    }

    /**
     * Возвращает строку со списком отсортированных ингредиентов
     * @param parameter - параметр для сортировки
     * @return Возвращает строку со списком отсортированных ингредиентов
     */
    private String getSortedIngredients(ProductParameters parameter) {
        StringBuilder result = new StringBuilder();
        result.append("Блюдо: ").append(name).append("\n");
        result.append("Параметр для сортировки: ").append(parameter).append("\n");
        result.append("Отсортированные ингредиенты:\n");
        for (Ingredient ingredient: ingredients) {
            result.append("- ").append(ingredient.getProduct().getName()).append(" (");
            switch (parameter) {
                case PROTEIN:
                    result.append(ingredient.getProduct().getProtein()).append(" г)\n");
                    break;
                case FAT:
                    result.append(ingredient.getProduct().getFat()).append(" г)\n");
                    break;
                case CARBOHYDRATE:
                    result.append(ingredient.getProduct().getCarbohydrate()).append(" г)\n");
            }
        }
        return result.toString();
    }

    /**
     * Выполняет поиск ингредиентов, параметры которых удовлетворяют заданным диапазонам значений
     * @param parameterRanges - диапазоны значений для параметров ингредиентов
     * @return Возвращает строку со списком найденных ингредиентов
     */
    public String selectIngredients(Map<ProductParameters, ChefCook.RandomRange> parameterRanges) {
        List<Ingredient> selectedIngredients = new ArrayList<>(ingredients);
        double actualValue = 0;
        for (Iterator<Ingredient> iterator = selectedIngredients.iterator(); iterator.hasNext(); ) {
            Ingredient ingredient = iterator.next();
            for (ProductParameters parameter: ProductParameters.values()) {
                int minValue = parameterRanges.get(parameter).getMinValue();
                int maxValue = parameterRanges.get(parameter).getMaxValue();
                switch (parameter) {
                    case PROTEIN:
                        actualValue = ingredient.getProduct().getProtein();
                        break;
                    case FAT:
                        actualValue = ingredient.getProduct().getFat();
                        break;
                    case CARBOHYDRATE:
                        actualValue = ingredient.getProduct().getCarbohydrate();
                        break;
                }
                if ((actualValue < minValue) || (actualValue > maxValue)) {
                    iterator.remove();
                    break;
                }
            }
        }
        return getSelectedIngredients(selectedIngredients, parameterRanges);
    }

    /**
     * Возвращает строку с информацией о списке найденных ингредиентов
     * @param list - список ингредиентов
     * @param ranges - диапазоны значений для параметров ингредиентов
     * @return Возвращает строку с информацией о списке найденных ингредиентов
     */
    private String getSelectedIngredients(List<Ingredient> list, Map<ProductParameters, ChefCook.RandomRange> ranges) {
        StringBuilder result = new StringBuilder();
        result.append("Блюдо: ").append(name).append("\n");
        result.append("Диапазоны параметров:\n");
        for (ProductParameters parameter: ProductParameters.values()) {
            result.append(String.format("- %s: [%d, %d]\n",
                    parameter, ranges.get(parameter).getMinValue(), ranges.get(parameter).getMaxValue()));
        }
        if ((list == null) || (list.size() == 0)) {
            result.append("Ингредиенты, удовлетворяющие заданным параметрам, отсутствуют.\n");
        } else {
            result.append("Ингредиенты, удовлетворяющие заданным параметрам:\n");
            for (Ingredient ingredient: list) {
                result.append(ingredient.getProduct()).append("\n");
            }
        }
        return result.toString();
    }
}