package ru.levelup.julia_kalujnaya.qa.homework_3.entities;

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

    public int getAmount() {
        return amount;
    }

    @Override
    public String toString() {
        return name + ", цена " + price + " руб., доступно " + amount + " шт.";
    }

    /**
     * Покупка товара (если в наличии есть достаточное количество)
     * @param amount - покупаемое количество товара
     * @return Возвращает true в случае успешной покупки
     */
    public boolean buyFood(int amount) {
        if (amount <= this.amount) {
            this.amount -= amount;
            return true;
        }
        return false;
    }
}