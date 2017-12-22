package ru.levelup.julia_kalujnaya.qa.homework_4.entities;

/**
 * Класс-сущность, представляющий объект предметной области "Еда в баре", со следующими полями:
 * name - наименование товара
 * price - цена товара
 * amount - количество товара в наличии
 *
 * @author Юлия Калюжная
 */
public class Food {
    private String name;
    private double price;
    private int amount;

    public Food(String name, double price, int amount) {
        this.name = name;
        this.price = price;
        this.amount = amount;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getAmount() {
        return amount;
    }

    /**
     * Покупка товара
     * @param amount - покупаемое количество товара
     * @return Возвращает 0 в случае успешной покупки, -1 при ошибочных входных данных (отрицательное или нулевое
     * количество) и -2, если товара недостаточно
     */
    public int buyFood(int amount) {
        if (amount <= 0)
            return -1;
        if (amount <= this.amount) {
            this.amount -= amount;
            return 0;
        }
        return -2;
    }
}