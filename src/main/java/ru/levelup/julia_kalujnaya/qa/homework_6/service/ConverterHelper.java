package ru.levelup.julia_kalujnaya.qa.homework_6.service;

/**
 * Вспомогательный класс для конвертации стоимости товара/услуги из строки в число
 *
 * @author Юлия Калюжная
 */
public class ConverterHelper {
    /**
     * Преобразует стоимость товара из строки вида "N NNN,NN руб." в число
     * @param price - цена товара в виде строки
     * @return Возвращает цену товара в виде числа
     */
    public static float convertPriceToNumber(String price) {
        return Float.parseFloat(price.substring(0, price.indexOf('р')).trim().replace(" ", "")
                .replace(",", "."));
    }
}