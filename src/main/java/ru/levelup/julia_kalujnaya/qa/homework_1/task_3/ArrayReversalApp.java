package ru.levelup.julia_kalujnaya.qa.homework_1.task_3;

import ru.levelup.julia_kalujnaya.qa.homework_1.task_1.PrintAndRandomHelper;

/**
 * Домашнее задание. Часть 1. Задание 3
 * Реализован переворот массива случайных целых чисел в обратном порядке
 *
 * @author Юлия Калюжная
 */
public class ArrayReversalApp {
    public static void main(String[] args) {
        ArrayReversalApp app = new ArrayReversalApp();
        app.startApplication();
    }

    /**
     * Генерирует массив случайных целых чисел в заданном диапазоне (от 0 до 1000), переворачивает его в обратном порядке
     * и выводит пользователю исходный и полученный массивы
     */
    private void startApplication() {
        PrintAndRandomHelper.printMessage("ARRAY REVERSAL");
        int[] arrayToReverse = PrintAndRandomHelper.getRandomIntArray(20, 1000);
        PrintAndRandomHelper.printMessageAndArray("Random integer array: ", arrayToReverse);
        reverseArray(arrayToReverse);
        PrintAndRandomHelper.printMessageAndArray("Reversed array: ", arrayToReverse);
    }

    /**
     * Переворачивает массив целых чисел в обратном порядке. Для изменения порядка элементов массива не используется
     * дополнительное выделение памяти
     * @param array - массив целых чисел
     */
    private void reverseArray(int[] array) {
        if ((array == null) || (array.length == 0))
            return;
        for (int i = 0; i < array.length/2; i++) {
            int k = array.length - 1 - i;
            array[i] = array[i] + array[k];
            array[k] = array[k] - array[i];
            array[k] = -array[k];
            array[i] = array[i] - array[k];
        }
    }
}
