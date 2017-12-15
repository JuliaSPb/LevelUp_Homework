package ru.levelup.julia_kalujnaya.qa.homework_2.entities.products;

/**
 * Перечисление параметров объекта "Продукт" для сортировки и поиска
 *
 * @author Юлия Калюжная
 */
public enum ProductParameters {
    PROTEIN, FAT, CARBOHYDRATE;

    @Override
    public String toString() {
        switch (this) {
            case PROTEIN:
                return "белки";
            case FAT:
                return "жиры";
            case CARBOHYDRATE:
                return "углеводы";
            default:
                return "";
        }
    }
}