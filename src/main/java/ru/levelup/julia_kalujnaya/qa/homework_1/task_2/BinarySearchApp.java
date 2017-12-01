package ru.levelup.julia_kalujnaya.qa.homework_1.task_2;

import ru.levelup.julia_kalujnaya.qa.homework_1.task_1.BubbleSortApp;
import ru.levelup.julia_kalujnaya.qa.homework_1.task_1.PrintAndRandomHelper;

/**
 * Домашнее задание. Часть 1. Задание 2
 * Реализован алгоритм бинарного поиска элемента (выбирается случайным образом) в массиве случайных целых чисел.
 * Для сортировки массива используется пузырьковая сортировка, реализованная в задании 1. После нахождения одного
 * вхождения элемента выполняется поиск всех вхождений данного элемента, если они есть
 *
 * @author Юлия Калюжная
 */
public class BinarySearchApp {
    public static void main(String[] args) {
        BinarySearchApp app = new BinarySearchApp();
        app.startApplication();
    }

    /**
     * Генерирует массив случайных целых чисел в заданном диапазоне (от 0 до 10), сортирует его методом пузырька,
     * выполняет поиск случайного значения и выводит пользователю массив, искомое значение и результаты поиска
     */
    private void startApplication() {
        PrintAndRandomHelper.printMessage("BINARY SEARCH");
        int[] arrayToSearch = PrintAndRandomHelper.getRandomIntArray(20, 10);
        BubbleSortApp.bubbleSort(arrayToSearch);
        PrintAndRandomHelper.printMessageAndArray("Sorted random integer array: ", arrayToSearch);
        int key = PrintAndRandomHelper.getRandomInt(10);
        PrintAndRandomHelper.printMessage("Key to find: " + key);
        int keyIndex = binarySearch(key, arrayToSearch);
        if (keyIndex < 0) {
            PrintAndRandomHelper.printMessage("The key was not found.");
        } else {
            PrintAndRandomHelper.printMessageAndArray("The key is found, its indexes: ",
                    findAllKeys(keyIndex, arrayToSearch));
        }
    }

    /**
     * Выполняет бинарный поиск заданного элемента в отсортированном массиве целых чисел
     * @param key - искомое целое число
     * @param array - массив целых чисел, в котором осуществляется поиск
     * @return Возвращает индекс найденного вхождения элемента, -1 в случае отсутствия элемента и -2 при пустом массиве
     */
    private int binarySearch(int key, int[] array) {
        if ((array == null) || (array.length == 0))
            return -2;
        int startIndex = 0;
        int endIndex = array.length;
        int currentIndex = 0;
        do {
            if (array[currentIndex] == key) {
                return currentIndex;
            } else if (array[currentIndex] < key) {
                startIndex = currentIndex;
            } else {
                endIndex = currentIndex;
            }
            currentIndex = (startIndex + endIndex)/2;
        } while (endIndex - startIndex > 1);
        return -1;
    }

    /**
     * Выполняет поиск всех вхождений элемента с заданным индексом, если они есть
     * @param keyIndex - индекс искомого элемента
     * @param array - массив целых чисел, в котором осуществляется поиск
     * @return Возвращает массив индексов всех вхождений заданного элемента
     */
    private int[] findAllKeys(int keyIndex, int[] array) {
        if ((array == null) || (array.length == 0) || (keyIndex < 0) || (keyIndex >= array.length))
            return null;
        int startIndex = keyIndex, endIndex = keyIndex;
        for (int i = keyIndex - 1; i >= 0; i--) {
            if (array[i] == array[keyIndex]) {
                startIndex = i;
            }
            else break;
        }
        for (int i = keyIndex + 1; i < array.length; i++) {
            if (array[i] == array[keyIndex]) {
                endIndex = i;
            }
            else break;
        }
        int[] resultIndexes = new int[endIndex - startIndex + 1];
        for (int i = 0; i < resultIndexes.length; i++) {
            resultIndexes[i] = startIndex + i;
        }
        return resultIndexes;
    }
}
