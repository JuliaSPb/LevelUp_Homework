package ru.levelup.julia_kalujnaya.qa.homework_2;

import ru.levelup.julia_kalujnaya.qa.homework_2.entities.products.*;
import ru.levelup.julia_kalujnaya.qa.homework_2.entities.recipes.*;
import ru.levelup.julia_kalujnaya.qa.homework_3.ConsoleHelper;
import ru.levelup.julia_kalujnaya.qa.homework_3.FileHelper;

import java.io.IOException;
import java.util.*;

/**
 * Домашнее задание. Часть 2
 * Реализовано приложение "Шеф-повар", предоставляющее пользователю возможность приготовить обед из 3 блюд и рассчитать
 * его общую калорийность, отсортировать ингредиенты произвольного блюда по одному из параметров (БЖУ), а также найти
 * ингредиенты блюда по диапазону значений их параметров (БЖУ). Все блюда, параметры и диапазоны их значений выбираются
 * случайным образом. Данные о продуктах и рецептах загружаются из файла
 *
 * @author Юлия Калюжная
 */
public class ChefCook {
    private ConsoleHelper consoleHelper;
    private FileHelper fileHelper;
    private Map<String, Product> products;
    private Map<String, Recipe> recipes;
    private List<Salad> salads;
    private List<Soup> soups;
    private List<MainCourse> mainCourses;

    public static void main(String[] args) {
        ChefCook chefCook = new ChefCook();
        chefCook.loadDataFromFile();
        chefCook.initMenu();
    }

    private ChefCook() {
        consoleHelper = new ConsoleHelper();
        fileHelper = new FileHelper();
        products = new HashMap<>();
        recipes = new HashMap<>();
        salads = new ArrayList<>();
        soups = new ArrayList<>();
        mainCourses = new ArrayList<>();
    }

    /**
     * Загружает начальные данные для работы приложения из файла и создаёт все необходимые объекты
     */
    private void loadDataFromFile() {
        try {
            List<HashMap<String, String>> dataFromFile = fileHelper.parseFile("./src/main/resources/ChefCookData.txt");
            for (HashMap<String, String> data: dataFromFile) {
                switch (data.get("entity")) {
                    case "Product":
                        createProduct(data);
                        break;
                    case "Recipe":
                        createRecipe(data);
                        break;
                    case "Ingredient":
                        createIngredient(data);
                }
            }
        } catch (IOException | NullPointerException | NoSuchElementException e) {
            consoleHelper.printlnToConsole("Не удалось загрузить данные из файла.");
            System.exit(1);
        }
    }

    /**
     * Создаёт экземпляр объекта-наследника класса Product и добавляет его в перечень продуктов
     * @param data - информация для создания объекта в виде пар "свойство - значение"
     */
    private void createProduct(HashMap<String, String> data) {
        String name = data.get("name");
        double protein = Double.parseDouble(data.get("protein"));
        double fat = Double.parseDouble(data.get("fat"));
        double carbohydrate = Double.parseDouble(data.get("carbohydrate"));
        Product product = null;
        switch (data.get("type")) {
            case "Cereals":
                product = new Cereals(name, protein, fat, carbohydrate);
                break;
            case "Dressing":
                product = new Dressing(name, protein, fat, carbohydrate);
                break;
            case "Egg":
                product = new Egg(name, protein, fat, carbohydrate);
                break;
            case "Meat":
                product = new Meat(name, protein, fat, carbohydrate);
                break;
            case "Vegetable":
                product = new Vegetable(name, protein, fat, carbohydrate);
        }
        products.put(name, product);
    }

    /**
     * Создаёт экземпляр объекта-наследника класса Recipe и добавляет его в соответствующий список блюд и
     * в общий перечень рецептов
     * @param data - информация для создания объекта в виде пар "свойство - значение"
     */
    private void createRecipe(HashMap<String, String> data) {
        String name = data.get("name");
        Recipe recipe = null;
        switch (data.get("type")) {
            case "Salad":
                recipe = new Salad(name);
                salads.add((Salad) recipe);
                break;
            case "Soup":
                recipe = new Soup(name);
                soups.add((Soup) recipe);
                break;
            case "MainCourse":
                recipe = new MainCourse(name);
                mainCourses.add((MainCourse) recipe);
        }
        recipes.put(name, recipe);
    }

    /**
     * Создаёт экземпляр объекта Ingredient и добавляет его в рецепт (при условии существования рецепта и продукта)
     * @param data - информация для создания объекта в виде пар "свойство - значение"
     */
    private void createIngredient(HashMap<String, String> data) {
        Recipe recipe = recipes.get(data.get("recipe"));
        Product product = products.get(data.get("product"));
        if ((recipe != null) && (product != null)) {
            recipe.addIngredient(new Ingredient(product, Double.parseDouble(data.get("weight"))));
        } else {
            throw new NoSuchElementException();
        }
    }

    /**
     * Выводит главное меню, запрашивает у пользователя пункт меню и выполняет соответствующее действие
     */
    private void initMenu() {
        while (true) {
            consoleHelper.printlnToConsole("ГЛАВНОЕ МЕНЮ");
            consoleHelper.printlnToConsole("1 - Приготовить обед и рассчитать его калорийность");
            consoleHelper.printlnToConsole("2 - Сортировать ингредиенты блюда по 1 параметру");
            consoleHelper.printlnToConsole("3 - Выбрать ингредиенты блюда по диапазону параметров");
            consoleHelper.printlnToConsole("4 - Завершить работу");
            consoleHelper.printToConsole(">> Выберите пункт меню: ");
            int menuItem = consoleHelper.getIntValueFromConsole(4);
            consoleHelper.printlnToConsole("");
            switch (menuItem) {
                case 1: cookLunch();
                    break;
                case 2: sortIngredients();
                    break;
                case 3: selectIngredients();
                    break;
                case 4: System.exit(0);
            }
        }
    }

    /**
     * Готовит обед, состоящий из салата, супа и основного блюда, выбранных случайным образом, и рассчитывает
     * калорийность обеда
     */
    private void cookLunch() {
        consoleHelper.printlnToConsole("ОБЕД");
        Lunch lunch = new Lunch();
        Recipe salad = salads.get(getRandomInteger(salads.size()));
        Recipe soup = soups.get(getRandomInteger(soups.size()));
        Recipe mainCourse = mainCourses.get(getRandomInteger(mainCourses.size()));
        lunch.addRecipe(salad);
        lunch.addRecipe(soup);
        lunch.addRecipe(mainCourse);
        consoleHelper.printlnToConsole(lunch.cook());
    }

    /**
     * Генерирует случайное целое число в диапазоне от 0 до maxValue (не включительно)
     * @param maxValue - максимальное значение случайного числа
     * @return Возвращает случайное целое число
     */
    private int getRandomInteger(int maxValue) {
        return (new Random()).nextInt(maxValue);
    }

    /**
     * Сортирует ингредиенты блюда, выбранного случайным образом, по одному из параметров (БЖУ), также выбранному
     * случайно, и выводит список отсортированных ингредиентов
     */
    private void sortIngredients() {
        consoleHelper.printlnToConsole("СОРТИРОВКА ИНГРЕДИЕНТОВ ПО ПАРАМЕТРУ");
        Recipe recipe = getRandomRecipe();
        ProductParameters parameter = ProductParameters.values()[getRandomInteger(3)];
        consoleHelper.printlnToConsole(recipe.sortIngredients(parameter));
    }

    /**
     * Возвращает случайно выбранное блюдо (салат, суп или основное блюдо)
     * @return Возвращает случайный рецепт
     */
    private Recipe getRandomRecipe() {
        switch (getRandomInteger(3)) {
            case 0:
                return salads.get(getRandomInteger(salads.size()));
            case 1:
                return soups.get(getRandomInteger(soups.size()));
            case 2:
            default:
                return mainCourses.get(getRandomInteger(mainCourses.size()));
        }
    }

    /**
     * Выполняет поиск ингредиентов блюда, выбранного случайным образом, параметры которых (БЖУ) находятся в случайно
     * выбранных диапазонах значений, и выводит найденные ингредиенты, если они есть
     */
    private void selectIngredients() {
        consoleHelper.printlnToConsole("ПОИСК ИНГРЕДИЕНТОВ ПО ДИАПАЗОНУ ПАРАМЕТРОВ");
        Recipe recipe = getRandomRecipe();
        Map<ProductParameters, RandomRange> parameterRanges = new HashMap<>();
        for (ProductParameters parameter: ProductParameters.values()) {
            parameterRanges.put(parameter, new RandomRange(5, 70));
        }
        consoleHelper.printlnToConsole(recipe.selectIngredients(parameterRanges));
    }

    /**
     * Вспомогательный класс для хранения диапазона целых чисел, граничные значения генерируются случайным образом
     */
    public class RandomRange {
        int minValue;
        int maxValue;

        public RandomRange(int value1, int value2) {
            int random1 = getRandomInteger(value1);
            int random2 = getRandomInteger(value2);
            if (random1 <= random2) {
                minValue = random1;
                maxValue = random2;
            } else {
                minValue = random2;
                maxValue = random1;
            }
        }

        public int getMinValue() {
            return minValue;
        }

        public int getMaxValue() {
            return maxValue;
        }
    }
}