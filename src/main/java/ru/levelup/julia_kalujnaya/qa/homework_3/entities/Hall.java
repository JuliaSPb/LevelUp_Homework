package ru.levelup.julia_kalujnaya.qa.homework_3.entities;

/**
 * Класс-сущность, представляющий объект предметной области "Зал", со следующими полями:
 * name - название зала
 * amountOfSeats - количество мест в зале
 *
 * @author Юлия Калюжная
 */
public class Hall {
    private String name;
    private int amountOfSeats;

    public Hall(String name, int amountOfSeats) {
        this.name = name;
        this.amountOfSeats = amountOfSeats;
    }

    public String getName() {
        return name;
    }

    public int getAmountOfSeats() {
        return amountOfSeats;
    }
}